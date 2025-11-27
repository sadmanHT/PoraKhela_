package com.porakhela.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.porakhela.R
import com.porakhela.ui.developer.DeveloperToolsDialog
import com.porakhela.data.repository.ApplinkRepository
import com.porakhela.utils.HapticUtils
import com.porakhela.utils.PorapointsManager
import com.porakhela.data.local.UserPreferences
import com.porakhela.data.tracking.StreakManager
import com.porakhela.ui.streak.StreakViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Home fragment displaying dashboard with subject cards and user stats
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    
    private val viewModel: HomeViewModel by viewModels()
    private val streakViewModel: StreakViewModel by viewModels()
    
    @Inject
    lateinit var userPreferences: UserPreferences
    
    @Inject
    lateinit var applinkRepository: ApplinkRepository
    
    @Inject
    lateinit var porapointsManager: PorapointsManager
    
    @Inject
    lateinit var streakManager: StreakManager
    
    // View references
    private lateinit var tvWelcome: TextView
    private lateinit var tvPorapointsValue: TextView
    private lateinit var tvDailyStreak: TextView
    private lateinit var cardMath: View
    private lateinit var cardEnglish: View
    private lateinit var cardScience: View
    private lateinit var cardSocialStudies: View
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize streak tracking
        streakManager.onAppOpened()
        
        initializeViews(view)
        setupSubjectCards()
        setupObservers()
        setupStreakDisplay()
        setupDeveloperTools()
        loadUserData()
        
        Timber.d("Home screen displayed with enhanced UI and streak tracking")
    }
    
    private fun initializeViews(view: View) {
        tvWelcome = view.findViewById(R.id.tv_welcome)
        tvPorapointsValue = view.findViewById(R.id.tv_porapoints_value)
        tvDailyStreak = view.findViewById(R.id.tv_daily_streak)
        cardMath = view.findViewById(R.id.card_math)
        cardEnglish = view.findViewById(R.id.card_english)
        cardScience = view.findViewById(R.id.card_science)
        cardSocialStudies = view.findViewById(R.id.card_social_studies)
    }
    
    private fun setupSubjectCards() {
        // Math card
        cardMath.setOnClickListener {
            HapticUtils.mediumTap(it)
            animateCardPress(cardMath) {
                navigateToSubjectCategory("math")
            }
        }
        
        // English card
        cardEnglish.setOnClickListener {
            HapticUtils.mediumTap(it)
            animateCardPress(cardEnglish) {
                navigateToSubjectCategory("english")
            }
        }
        
        // Science card
        cardScience.setOnClickListener {
            HapticUtils.mediumTap(it)
            animateCardPress(cardScience) {
                navigateToSubjectCategory("science")
            }
        }
        
        // Social Studies card
        cardSocialStudies.setOnClickListener {
            HapticUtils.mediumTap(it)
            animateCardPress(cardSocialStudies) {
                navigateToSubjectCategory("social_studies")
            }
        }
    }
    
    private fun animateCardPress(card: View, onComplete: () -> Unit) {
        val scaleDown = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(card, "scaleX", 1f, 0.95f),
                ObjectAnimator.ofFloat(card, "scaleY", 1f, 0.95f)
            )
            duration = 100
        }
        
        val scaleUp = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(card, "scaleX", 0.95f, 1f),
                ObjectAnimator.ofFloat(card, "scaleY", 0.95f, 1f)
            )
            duration = 100
        }
        
        scaleDown.start()
        scaleDown.doOnEnd {
            scaleUp.start()
            scaleUp.doOnEnd {
                onComplete()
            }
        }
    }
    
    private fun AnimatorSet.doOnEnd(action: () -> Unit) {
        addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                action()
            }
        })
    }
    
    private fun setupObservers() {
        // Observer setup will be added when ViewModel is properly implemented
        Timber.d("Observers setup completed")
    }
    
    private fun setupStreakDisplay() {
        viewLifecycleOwner.lifecycleScope.launch {
            streakViewModel.streakInfo.collect { streakInfo ->
                updateStreakUI(streakInfo)
            }
        }
    }
    
    private fun updateStreakUI(streakInfo: com.porakhela.data.tracking.StreakInfo) {
        val view = view ?: return
        
        try {
            // Update current streak display
            val streakText = view.findViewById<TextView>(R.id.tv_current_streak)
            streakText?.text = "Current Streak: ${streakInfo.currentStreak} days"
            
            // Update today's learning time
            val timeText = view.findViewById<TextView>(R.id.tv_learning_time_today)
            val minutes = (streakInfo.timeToday / 60000).toInt()
            val seconds = ((streakInfo.timeToday % 60000) / 1000).toInt()
            val timeString = when {
                minutes > 0 -> "Today's Learning Time: ${minutes}m ${seconds}s"
                else -> "Today's Learning Time: ${seconds}s"
            }
            timeText?.text = timeString
            
            // Show streak message
            val messageText = view.findViewById<TextView>(R.id.tv_streak_message)
            messageText?.text = streakViewModel.getStreakMessage()
            
            // Show freeze warning if date is manipulated
            if (streakInfo.isDateFrozen) {
                val warningText = view.findViewById<TextView>(R.id.tv_warning)
                warningText?.visibility = View.VISIBLE
                warningText?.text = "⚠️ Date manipulation detected. Streak updates frozen."
            }
            
        } catch (e: Exception) {
            Timber.e(e, "Error updating streak UI")
        }
    }
    
    private fun loadUserData() {
        // Load child name
        val childName = userPreferences.getChildName() ?: "Learner"
        tvWelcome.text = "Hi, $childName!"
        
        // Load and display porapoints from manager
        val porapoints = porapointsManager.getCurrentPorapoints()
        tvPorapointsValue.text = porapoints.toString()
        
        // Load and display daily streak
        val dailyStreak = userPreferences.getDailyStreak()
        tvDailyStreak.text = "$dailyStreak days"
        
        Timber.d("User data loaded: child=$childName, points=$porapoints, streak=$dailyStreak")
    }
    
    private fun setupDeveloperTools() {
        // Long press on welcome text to open developer tools
        tvWelcome.setOnLongClickListener {
            HapticUtils.longPress(it)
            openDeveloperTools()
            true
        }
        
        // Alternative: Long press on porapoints to open developer tools
        tvPorapointsValue.setOnLongClickListener {
            HapticUtils.longPress(it)
            openDeveloperTools()
            true
        }
        
        Timber.d("Developer tools access configured (long press welcome/points)")
    }
    
    private fun openDeveloperTools() {
        try {
            val dialog = DeveloperToolsDialog(
                context = requireContext(),
                lifecycleScope = viewLifecycleOwner.lifecycleScope,
                applinkRepository = applinkRepository,
                porapointsManager = porapointsManager,
                onPointsUpdated = {
                    // Refresh points display when updated
                    loadUserData()
                }
            )
            dialog.show()
            Timber.d("Developer tools dialog opened")
        } catch (e: Exception) {
            Timber.e(e, "Failed to open developer tools")
        }
    }
    
    private fun navigateToSubjectCategory(subject: String) {
        try {
            val bundle = Bundle().apply {
                putString("subject", subject)
            }
            findNavController().navigate(R.id.action_home_to_subject_category, bundle)
            Timber.d("Navigating to subject: $subject")
        } catch (e: Exception) {
            Timber.e(e, "Failed to navigate to subject: $subject")
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh user data when returning to home
        loadUserData()
        // Refresh streak data when returning to home
        streakViewModel.refreshStreakInfo()
    }
}