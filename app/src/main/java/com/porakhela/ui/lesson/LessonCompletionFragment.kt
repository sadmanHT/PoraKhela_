package com.porakhela.ui.lesson

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.porakhela.R
import com.porakhela.utils.HapticUtils
import com.porakhela.utils.PorapointsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment displaying lesson completion with trophy animation and statistics
 */
@AndroidEntryPoint
class LessonCompletionFragment : Fragment() {

    private val args: LessonCompletionFragmentArgs by navArgs()
    
    @Inject
    lateinit var porapointsManager: PorapointsManager

    // UI References
    private lateinit var trophyContainer: ViewGroup
    private lateinit var tvTrophy: TextView
    private lateinit var tvCongratulations: TextView
    private lateinit var tvMotivationalMessage: TextView
    private lateinit var tvPorapointsEarned: TextView
    private lateinit var tvCorrectCount: TextView
    private lateinit var tvTotalQuestions: TextView
    private lateinit var tvAccuracy: TextView
    private lateinit var tvTimeTaken: TextView
    private lateinit var btnContinueLearning: Button
    private lateinit var btnBackToCategories: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lesson_completion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupClickListeners()
        displayCompletionData()
        playCompletionAnimation()
        
        Timber.d("LessonCompletionFragment created with results: ${args.correctAnswers}/${args.totalQuestions}")
    }

    private fun initializeViews(view: View) {
        trophyContainer = view.findViewById(R.id.trophy_container)
        tvTrophy = view.findViewById(R.id.trophy_emoji)
        tvCongratulations = view.findViewById(R.id.tv_congratulations)
        tvMotivationalMessage = view.findViewById(R.id.tv_motivational_message)
        tvPorapointsEarned = view.findViewById(R.id.tv_porapoints_earned)
        tvCorrectCount = view.findViewById(R.id.tv_correct_count)
        tvTotalQuestions = view.findViewById(R.id.tv_total_questions)
        tvAccuracy = view.findViewById(R.id.tv_accuracy)
        tvTimeTaken = view.findViewById(R.id.tv_time_taken)
        btnContinueLearning = view.findViewById(R.id.btn_continue_learning)
        btnBackToCategories = view.findViewById(R.id.btn_back_to_categories)
    }

    private fun setupClickListeners() {
        btnContinueLearning.setOnClickListener {
            HapticUtils.lightTap(it)
            playAgain()
        }
        
        btnBackToCategories.setOnClickListener {
            HapticUtils.lightTap(it)
            navigateBack()
        }
    }

    private fun displayCompletionData() {
        val correctAnswers = args.correctAnswers
        val totalQuestions = args.totalQuestions
        val porapointsEarned = args.porapointsEarned
        val timeTakenMs = args.timeTakenMs
        
        // Calculate accuracy
        val accuracy = if (totalQuestions > 0) {
            (correctAnswers.toFloat() / totalQuestions.toFloat() * 100).toInt()
        } else 0
        
        // Format time
        val minutes = (timeTakenMs / 1000) / 60
        val seconds = (timeTakenMs / 1000) % 60
        val timeFormatted = String.format("%d:%02d", minutes, seconds)
        
        // Set trophy and title based on performance
        val (trophy, title, subtitle) = getTrophyAndTitle(accuracy)
        tvTrophy.text = trophy
        tvCongratulations.text = title
        tvMotivationalMessage.text = subtitle
        
        // Set statistics
        tvPorapointsEarned.text = "+$porapointsEarned"
        tvCorrectCount.text = "$correctAnswers"
        tvTotalQuestions.text = "$totalQuestions"
        tvAccuracy.text = "$accuracy%"
        tvTimeTaken.text = timeFormatted
        
        Timber.d("Completion data displayed - Accuracy: $accuracy%, Time: $timeFormatted")
    }

    private fun getTrophyAndTitle(accuracy: Int): Triple<String, String, String> {
        return when {
            accuracy == 100 -> Triple(
                "🏆", 
                "Perfect Score!", 
                "You're a champion!"
            )
            accuracy >= 80 -> Triple(
                "🥇", 
                "Excellent Work!", 
                "You're doing great!"
            )
            accuracy >= 60 -> Triple(
                "🥈", 
                "Well Done!", 
                "Keep practicing!"
            )
            accuracy >= 40 -> Triple(
                "🥉", 
                "Good Effort!", 
                "You're getting better!"
            )
            else -> Triple(
                "⭐", 
                "Keep Trying!", 
                "Practice makes perfect!"
            )
        }
    }

    private fun playCompletionAnimation() {
        viewLifecycleOwner.lifecycleScope.launch {
        // Initial setup - make everything invisible
        trophyContainer.alpha = 0f
        tvCongratulations.alpha = 0f
        tvMotivationalMessage.alpha = 0f            // Animate trophy entrance
            delay(300)
            animateTrophyEntrance()
            
            // Animate title and subtitle
            delay(800)
            animateTextEntrance()
            
            // Animate stats cards
            delay(1200)
            animateStatsCards()
            
            // Animate buttons
            delay(1600)
            animateButtons()
            
            // Play confetti for good performance
            if (args.correctAnswers.toFloat() / args.totalQuestions >= 0.8f) {
                delay(500)
                playConfettiAnimation()
            }
        }
    }

    private fun animateTrophyEntrance() {
        val scaleX = ObjectAnimator.ofFloat(trophyContainer, "scaleX", 0f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(trophyContainer, "scaleY", 0f, 1.2f, 1f)
        val alpha = ObjectAnimator.ofFloat(trophyContainer, "alpha", 0f, 1f)
        
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, alpha)
        animatorSet.duration = 600
        animatorSet.start()
        
        // Bounce effect
        val bounce = ObjectAnimator.ofFloat(tvTrophy, "rotation", 0f, 10f, -10f, 5f, -5f, 0f)
        bounce.duration = 800
        bounce.startDelay = 600
        bounce.start()
    }

    private fun animateTextEntrance() {
        val titleSlide = ObjectAnimator.ofFloat(tvCongratulations, "translationY", 50f, 0f)
        val titleAlpha = ObjectAnimator.ofFloat(tvCongratulations, "alpha", 0f, 1f)
        
        val subtitleSlide = ObjectAnimator.ofFloat(tvMotivationalMessage, "translationY", 50f, 0f)
        val subtitleAlpha = ObjectAnimator.ofFloat(tvMotivationalMessage, "alpha", 0f, 1f)
        
        val titleSet = AnimatorSet()
        titleSet.playTogether(titleSlide, titleAlpha)
        titleSet.duration = 400
        titleSet.start()
        
        val subtitleSet = AnimatorSet()
        subtitleSet.playTogether(subtitleSlide, subtitleAlpha)
        subtitleSet.duration = 400
        subtitleSet.startDelay = 200
        subtitleSet.start()
    }

    private fun animateStatsCards() {
        val statsViews = listOf(
            tvPorapointsEarned,
            tvCorrectCount,
            tvAccuracy,
            tvTimeTaken
        )
        
        statsViews.forEachIndexed { index, view ->
            view.alpha = 0f
            view.translationY = 30f
            
            val slide = ObjectAnimator.ofFloat(view, "translationY", 30f, 0f)
            val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(slide, alpha)
            animatorSet.duration = 300
            animatorSet.startDelay = (index * 100).toLong()
            animatorSet.start()
        }
    }

    private fun animateButtons() {
        val buttons = listOf(btnContinueLearning, btnBackToCategories)
        
        buttons.forEachIndexed { index, button ->
            button.alpha = 0f
            button.translationY = 30f
            
            val slide = ObjectAnimator.ofFloat(button, "translationY", 30f, 0f)
            val alpha = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f)
            
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(slide, alpha)
            animatorSet.duration = 300
            animatorSet.startDelay = (index * 100).toLong()
            animatorSet.start()
        }
    }

    private fun playConfettiAnimation() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val confettiEmojis = listOf("🎉", "⭐", "🌟", "✨", "🎊", "🏆", "🥇", "👏")
                val maxConfetti = 10 // Reasonable limit for performance
                val activeAnimations = mutableListOf<AnimatorSet>()
                
                repeat(maxConfetti) { _ ->
                    if (!isAdded || !viewLifecycleOwner.lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.STARTED)) {
                        return@launch // Fragment safety check
                    }
                    
                    try {
                        val emoji = TextView(requireContext()).apply {
                            text = confettiEmojis.random()
                            textSize = 16f + (Math.random() * 6).toFloat() // Varying sizes (16-22f)
                            alpha = 0.9f
                            x = (Math.random() * ((view?.width ?: 400) - 50)).toFloat().coerceAtLeast(0f)
                            y = -50f
                        }
                        
                        (view as? ViewGroup)?.addView(emoji)
                        
                        val fallAnimation = ObjectAnimator.ofFloat(emoji, "translationY", -50f, ((view?.height ?: 800) + 50f))
                        val rotateAnimation = ObjectAnimator.ofFloat(emoji, "rotation", 0f, 360f * (Math.random() * 2 - 1).toFloat())
                        
                        val animatorSet = AnimatorSet()
                        animatorSet.playTogether(fallAnimation, rotateAnimation)
                        animatorSet.duration = (1200 + Math.random() * 800).toLong() // 1.2-2.0 seconds
                        animatorSet.addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                try {
                                    activeAnimations.removeAll { it == animatorSet }
                                    if (isAdded && emoji.parent != null) {
                                        (view as? ViewGroup)?.removeView(emoji)
                                    }
                                } catch (e: Exception) {
                                    // Ignore cleanup errors
                                }
                            }
                            
                            override fun onAnimationCancel(animation: Animator) {
                                try {
                                    activeAnimations.removeAll { it == animatorSet }
                                    if (isAdded && emoji.parent != null) {
                                        (view as? ViewGroup)?.removeView(emoji)
                                    }
                                } catch (e: Exception) {
                                    // Silent cleanup
                                }
                            }
                        })
                        activeAnimations.add(animatorSet)
                        animatorSet.start()
                        
                    } catch (e: Exception) {
                        // Skip this confetti if it fails
                        Timber.w(e, "Failed to create confetti")
                    }
                    
                    delay((30 + Math.random() * 60).toLong()) // Stagger the confetti (30-90ms)
                }
                
                // Cleanup after maximum animation time
                delay(3000)
                activeAnimations.forEach { it.cancel() }
                activeAnimations.clear()
                
            } catch (e: Exception) {
                Timber.e(e, "Error in completion confetti animation")
            }
        }
    }

    private fun navigateBack() {
        try {
            // Try to navigate back to subject selection with proper error handling
            val navController = findNavController()
            
            // First try to pop to subject category
            val poppedToSubject = navController.popBackStack(R.id.subjectCategoryFragment, false)
            
            if (!poppedToSubject) {
                // If that fails, try to pop to main destination
                val poppedToMain = navController.popBackStack()
            }
            
            Timber.d("Navigated back from lesson completion")
        } catch (e: Exception) {
            Timber.e(e, "Failed to navigate back from completion")
            try {
                findNavController().popBackStack()
            } catch (e2: Exception) {
                Timber.e(e2, "Failed fallback navigation")
                // In extreme case, finish activity
                requireActivity().finish()
            }
        }
    }

    private fun playAgain() {
        try {
            // Navigate back to lesson player with same lesson
            findNavController().popBackStack(R.id.lessonPlayerFragment, false)
        } catch (e: Exception) {
            Timber.e(e, "Failed to restart lesson")
            navigateBack()
        }
    }
}