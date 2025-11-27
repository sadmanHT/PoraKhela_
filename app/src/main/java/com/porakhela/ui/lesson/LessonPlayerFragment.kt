package com.porakhela.ui.lesson

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.porakhela.R
import com.porakhela.utils.HapticUtils
import com.porakhela.data.tracking.TimeTracker
import com.porakhela.data.tracking.StreakManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for the gamified lesson player experience
 */
@AndroidEntryPoint
class LessonPlayerFragment : Fragment() {

    private val viewModel: LessonPlayerViewModel by viewModels()
    private val args: LessonPlayerFragmentArgs by navArgs()
    
    @Inject
    lateinit var timeTracker: TimeTracker
    
    @Inject
    lateinit var streakManager: StreakManager

    // UI References
    private lateinit var progressBar: ProgressBar
    private lateinit var timerProgress: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvTimer: TextView
    private lateinit var tvQuestionBadge: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btnOptionA: Button
    private lateinit var btnOptionB: Button
    private lateinit var btnOptionC: Button
    private lateinit var btnOptionD: Button
    private lateinit var btnSkip: Button
    private lateinit var btnNext: Button
    private lateinit var loadingOverlay: View
    private lateinit var confettiContainer: View
    
    private val optionButtons by lazy { listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD) }
    private var isAnswerSelected = false
    private var selectedOptionIndex = -1
    
    // Animation and feedback
    private lateinit var vibrator: Vibrator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lesson_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initializeViews(view)
            setupClickListeners()
            setupObservers()
            handleBackPress()
            
            // Initialize time tracking
            lifecycle.addObserver(timeTracker)
            timeTracker.startTracking()
            
            // Mark UI as ready after setup (anti-cheat)
            viewLifecycleOwner.lifecycleScope.launch {
                delay(100) // Ensure UI is fully loaded
                viewModel.markUIReady()
            }
            
            // Load the lesson
            viewModel.loadLesson(args.lessonId)
            
            Timber.d("LessonPlayerFragment created for lesson: ${args.lessonId}")
        } catch (e: Exception) {
            Timber.e(e, "Error in onViewCreated")
            showError("Failed to initialize lesson: ${e.message}")
        }
    }
    
    override fun onDestroyView() {
        try {
            // Cancel any ongoing operations
            viewModel.cancelActiveOperations()
            
            // Stop time tracking
            timeTracker.stopTracking()
            lifecycle.removeObserver(timeTracker)
            
            // Clean up animations safely
            confettiContainer?.let { container ->
                try {
                    (container as? ViewGroup)?.let { viewGroup ->
                        // Cancel any running animations on children
                        for (i in 0 until viewGroup.childCount) {
                            val child = viewGroup.getChildAt(i)
                            child?.clearAnimation()
                        }
                        viewGroup.removeAllViews()
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error cleaning confetti container")
                }
            }
            
            // Reset UI state
            isAnswerSelected = false
            selectedOptionIndex = -1
            lastButtonPressTime = 0L
            
        } catch (e: Exception) {
            Timber.e(e, "Error cleaning up view")
        }
        super.onDestroyView()
    }

    private fun initializeViews(view: View) {
        progressBar = view.findViewById(R.id.progress_bar)
        timerProgress = view.findViewById(R.id.timer_progress)
        tvProgress = view.findViewById(R.id.tv_progress)
        tvTimer = view.findViewById(R.id.tv_timer)
        tvQuestionBadge = view.findViewById(R.id.tv_question_badge)
        tvQuestion = view.findViewById(R.id.tv_question)
        btnOptionA = view.findViewById(R.id.btn_option_a)
        btnOptionB = view.findViewById(R.id.btn_option_b)
        btnOptionC = view.findViewById(R.id.btn_option_c)
        btnOptionD = view.findViewById(R.id.btn_option_d)
        btnSkip = view.findViewById(R.id.btn_skip)
        btnNext = view.findViewById(R.id.btn_next)
        loadingOverlay = view.findViewById(R.id.loading_overlay)
        confettiContainer = view.findViewById(R.id.confetti_container)
        
        // Initialize vibrator
        vibrator = ContextCompat.getSystemService(requireContext(), Vibrator::class.java)!!
    }

    private fun setupClickListeners() {
        // Back button
        view?.findViewById<View>(R.id.btn_back)?.setOnClickListener {
            handleBackNavigation()
        }
        
        // Option buttons
        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (!isAnswerSelected) {
                    selectOption(index)
                }
            }
        }
        
        // Control buttons
        btnSkip.setOnClickListener {
            viewModel.skipQuestion()
        }
        
        btnNext.setOnClickListener {
            viewModel.nextQuestion()
            resetQuestionState()
        }
    }

    private fun setupObservers() {
        // UI State observer
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                handleUIState(state)
            }
        }
        
        // Current question observer
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentQuestion.collect { question ->
                question?.let { displayQuestion(it) }
            }
        }
        
        // Progress observer
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.questionProgress.collect { progress ->
                progressBar.progress = progress
            }
        }
        
        // Timer observer
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.timerProgress.collect { progress ->
                timerProgress.progress = progress
                updateTimerDisplay(progress)
            }
        }
    }

    private fun handleUIState(state: LessonPlayerState) {
        when (state) {
            is LessonPlayerState.Loading -> {
                showLoading(true)
            }
            
            is LessonPlayerState.Ready -> {
                showLoading(false)
            }
            
            is LessonPlayerState.QuestionActive -> {
                showLoading(false)
                updateProgressDisplay(state.questionNumber, state.totalQuestions)
                enableOptionButtons(true)
                btnNext.isEnabled = false
                btnSkip.isEnabled = true
            }
            
            is LessonPlayerState.CorrectAnswer -> {
                handleCorrectAnswer(state.selectedOption, state.correctOption)
            }
            
            is LessonPlayerState.WrongAnswer -> {
                handleWrongAnswer(state.selectedOption, state.correctOption)
            }
            
            is LessonPlayerState.Completed -> {
                navigateToCompletion(state)
            }
            
            is LessonPlayerState.Error -> {
                showError(state.message)
            }
        }
    }

    private fun displayQuestion(question: com.porakhela.data.models.Question) {
        tvQuestion.text = question.question_text
        
        // Update option buttons
        question.options.forEachIndexed { index, option ->
            if (index < optionButtons.size) {
                optionButtons[index].text = "${('A' + index)}. $option"
                optionButtons[index].visibility = View.VISIBLE
            }
        }
        
        // Hide unused option buttons
        for (i in question.options.size until optionButtons.size) {
            optionButtons[i].visibility = View.GONE
        }
        
        resetQuestionState()
    }

    private fun selectOption(optionIndex: Int) {
        if (isAnswerSelected) return
        
        // Enhanced anti-spam protection
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastButtonPressTime < 200) { // Increased to 200ms
            Timber.w("Button spam detected - ignoring (${currentTime - lastButtonPressTime}ms)")
            return
        }
        lastButtonPressTime = currentTime
        
        // Additional validation
        if (optionIndex < 0 || optionIndex >= optionButtons.size) {
            Timber.e("Invalid option index: $optionIndex")
            return
        }
        
        if (!optionButtons[optionIndex].isEnabled) {
            Timber.w("Button not enabled - ignoring")
            return
        }
        
        isAnswerSelected = true
        selectedOptionIndex = optionIndex
        
        // Disable all buttons immediately
        enableOptionButtons(false)
        
        try {
            // Provide haptic feedback
            HapticUtils.lightTap(optionButtons[optionIndex])
            
            // Visual feedback - highlight selected option
            optionButtons[optionIndex].isPressed = true
            
            // Submit answer after brief delay with lifecycle check
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    delay(150) // Brief delay for visual feedback
                    if (isAdded && viewLifecycleOwner.lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.STARTED)) {
                        viewModel.answerQuestion(optionIndex)
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error submitting answer")
                    // Reset state on error
                    if (isAdded) {
                        isAnswerSelected = false
                        enableOptionButtons(true)
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error in selectOption")
            isAnswerSelected = false
            enableOptionButtons(true)
        }
        
        Timber.d("Option ${optionIndex + 1} selected")
    }
    
    private var lastButtonPressTime = 0L

    private fun handleCorrectAnswer(selectedOption: Int, correctOption: Int) {
        // Show correct answer feedback
        optionButtons[correctOption].isSelected = true
        
        // Play confetti animation
        playConfettiAnimation()
        
        // Haptic feedback for success
        HapticUtils.successPattern(requireContext())
        
        // Enable next button
        btnNext.isEnabled = true
        btnSkip.isEnabled = false
        
        Timber.d("Correct answer feedback displayed")
    }

    private fun handleWrongAnswer(selectedOption: Int, correctOption: Int) {
        // Show wrong answer (red) and correct answer (green)
        optionButtons[selectedOption].isActivated = true // Red background
        optionButtons[correctOption].isSelected = true // Green background
        
        // Shake animation for wrong answer
        playShakeAnimation(optionButtons[selectedOption])
        
        // Vibrate for wrong answer
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 100, 50, 100), -1))
        }
        
        // Enable next button
        btnNext.isEnabled = true
        btnSkip.isEnabled = false
        
        Timber.d("Wrong answer feedback displayed")
    }

    private fun playConfettiAnimation() {
        // Simple confetti effect using emoji with memory management
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val confettiEmojis = listOf("🎉", "⭐", "🌟", "✨", "🎊")
                val maxConfetti = 6 // Reduced for performance and stability
                val activeAnimations = mutableListOf<ObjectAnimator>()
                
                repeat(maxConfetti) { index ->
                    if (!isAdded || !viewLifecycleOwner.lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.STARTED)) {
                        return@launch // Fragment safety check
                    }
                    
                    val emoji = TextView(requireContext()).apply {
                        text = confettiEmojis.random()
                        textSize = 16f
                        alpha = 0.9f
                        x = (Math.random() * (confettiContainer.width - 50)).toFloat().coerceAtLeast(0f)
                        y = -50f
                    }
                    
                    (confettiContainer as ViewGroup).addView(emoji)
                    
                    val animator = ObjectAnimator.ofFloat(emoji, "translationY", -50f, confettiContainer.height + 50f)
                    animator.duration = 1200L
                    animator.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            try {
                                activeAnimations.remove(animation as? ObjectAnimator)
                                if (isAdded && emoji.parent != null) {
                                    (confettiContainer as ViewGroup).removeView(emoji)
                                }
                            } catch (e: Exception) {
                                Timber.e(e, "Error removing confetti")
                            }
                        }
                        
                        override fun onAnimationCancel(animation: Animator) {
                            try {
                                activeAnimations.remove(animation as? ObjectAnimator)
                                if (isAdded && emoji.parent != null) {
                                    (confettiContainer as ViewGroup).removeView(emoji)
                                }
                            } catch (e: Exception) {
                                // Silent cleanup
                            }
                        }
                    })
                    activeAnimations.add(animator)
                    animator.start()
                    
                    delay(60) // Stagger the confetti
                }
                
                // Cleanup after animation completes
                delay(1500)
                activeAnimations.forEach { it.cancel() }
                activeAnimations.clear()
                
            } catch (e: Exception) {
                Timber.e(e, "Error in confetti animation")
            }
        }
    }

    private fun playShakeAnimation(view: View) {
        val shake = ObjectAnimator.ofFloat(view, "translationX", 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
        shake.duration = 500
        shake.start()
    }

    private fun updateProgressDisplay(questionNumber: Int, totalQuestions: Int) {
        tvProgress.text = "Question $questionNumber of $totalQuestions"
        tvQuestionBadge.text = "Q$questionNumber"
    }

    private fun updateTimerDisplay(progress: Int) {
        val seconds = (progress * 30) / 100 // 30 second timer
        tvTimer.text = "0:${seconds.toString().padStart(2, '0')}"
        
        // Change color based on remaining time
        val color = when {
            progress > 50 -> ContextCompat.getColor(requireContext(), R.color.success_color)
            progress > 20 -> ContextCompat.getColor(requireContext(), android.R.color.holo_orange_dark)
            else -> ContextCompat.getColor(requireContext(), R.color.error_color)
        }
        tvTimer.setTextColor(color)
    }

    private fun enableOptionButtons(enabled: Boolean) {
        optionButtons.forEach { button ->
            button.isEnabled = enabled
        }
    }

    private fun resetQuestionState() {
        isAnswerSelected = false
        selectedOptionIndex = -1
        
        // Reset button states
        optionButtons.forEach { button ->
            button.isSelected = false
            button.isActivated = false
            button.isPressed = false
        }
        
        enableOptionButtons(true)
        btnNext.isEnabled = false
        btnSkip.isEnabled = true
    }

    private fun showLoading(show: Boolean) {
        loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        // Simple error display in question text
        tvQuestion.text = "Error: $message"
        enableOptionButtons(false)
        btnNext.text = "Retry"
        btnNext.isEnabled = true
    }

    private fun navigateToCompletion(state: LessonPlayerState.Completed) {
        try {
            // Stop time tracking and record lesson completion for streak
            timeTracker.stopTracking()
            streakManager.onLessonCompleted()
            
            val action = LessonPlayerFragmentDirections.actionLessonPlayerToCompletion(
                correctAnswers = state.correctAnswers,
                totalQuestions = state.totalQuestions,
                porapointsEarned = state.porapointsEarned,
                timeTakenMs = state.timeTakenMs
            )
            findNavController().navigate(action)
        } catch (e: Exception) {
            Timber.e(e, "Failed to navigate to completion screen")
            // Fallback navigation
            findNavController().popBackStack()
        }
    }

    private fun handleBackNavigation() {
        if (viewModel.onBackPressed()) {
            findNavController().popBackStack()
        }
    }

    private fun handleBackPress() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackNavigation()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}