package com.porakhela.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.porakhela.R
import com.porakhela.core.notifications.StreakNotificationScheduler
import com.porakhela.data.local.StreakPreferences
import com.porakhela.data.tracking.StreakManager
import com.porakhela.data.tracking.TimeTracker
import com.porakhela.ui.rewards.RewardsCenterActivity
import com.porakhela.ui.ussd.USSDLauncherActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Main activity that hosts the app content and streak testing interface
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private val viewModel: MainViewModel by viewModels()
    
    @Inject
    lateinit var notificationScheduler: StreakNotificationScheduler
    
    @Inject
    lateinit var streakManager: StreakManager
    
    @Inject
    lateinit var timeTracker: TimeTracker
    
    @Inject
    lateinit var streakPreferences: StreakPreferences
    
    private lateinit var tvStreakInfo: TextView
    private lateinit var tvTimeInfo: TextView
    private lateinit var btnCompleteLesson: Button
    private lateinit var btnStartTimer: Button
    private lateinit var btnPauseTimer: Button
    private lateinit var btnResumeTimer: Button
    private lateinit var btnRewardsCenter: Button
    private lateinit var btnSet300Points: Button
    private lateinit var btnSet600Points: Button
    private lateinit var btnSet2000Points: Button
    private lateinit var btnUSSDInterface: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initializeViews()
        setupClickListeners()
        updateUI()
        
        // Initialize streak notification system
        notificationScheduler.scheduleDaily8PMNotification()
        
        // Simplified main activity
        Timber.i("MainActivity initialized successfully with streak testing interface")
        setupObservers()
    }
    
    private fun initializeViews() {
        tvStreakInfo = findViewById(R.id.tvStreakInfo)
        tvTimeInfo = findViewById(R.id.tvTimeInfo)
        btnCompleteLesson = findViewById(R.id.btnCompleteLesson)
        btnStartTimer = findViewById(R.id.btnStartTimer)
        btnPauseTimer = findViewById(R.id.btnPauseTimer)
        btnResumeTimer = findViewById(R.id.btnResumeTimer)
        btnRewardsCenter = findViewById(R.id.btnRewardsCenter)
        btnSet300Points = findViewById(R.id.btnSet300Points)
        btnSet600Points = findViewById(R.id.btnSet600Points)
        btnSet2000Points = findViewById(R.id.btnSet2000Points)
        btnUSSDInterface = findViewById(R.id.btnUSSDInterface)
    }
    
    private fun setupClickListeners() {
        btnCompleteLesson.setOnClickListener {
            lifecycleScope.launch {
                Timber.i("STREAK TEST: Completing lesson manually")
                streakManager.onLessonCompleted()
                updateUI()
                Timber.i("STREAK TEST: Lesson completion triggered")
            }
        }
        
        btnStartTimer.setOnClickListener {
            Timber.i("STREAK TEST: Starting timer")
            timeTracker.startTracking()
            updateUI()
        }
        
        btnPauseTimer.setOnClickListener {
            Timber.i("STREAK TEST: Pausing timer")
            timeTracker.pauseTracking()
            updateUI()
        }
        
        btnResumeTimer.setOnClickListener {
            Timber.i("STREAK TEST: Starting timer (resume)")
            timeTracker.startTracking()
            updateUI()
        }
        
        btnRewardsCenter.setOnClickListener {
            Timber.i("NAVIGATION: Opening Rewards Center")
            val intent = Intent(this, RewardsCenterActivity::class.java)
            startActivity(intent)
        }
        
        btnUSSDInterface.setOnClickListener {
            Timber.i("NAVIGATION: Opening USSD Interface")
            val intent = Intent(this, USSDLauncherActivity::class.java)
            startActivity(intent)
        }
        
        // Point testing buttons
        btnSet300Points.setOnClickListener {
            Timber.i("POINTS TEST: Setting user balance to 300")
            streakPreferences.setTotalPorapointsEarned(300)
            updateUI()
        }
        
        btnSet600Points.setOnClickListener {
            Timber.i("POINTS TEST: Setting user balance to 600")
            streakPreferences.setTotalPorapointsEarned(600)
            updateUI()
        }
        
        btnSet2000Points.setOnClickListener {
            Timber.i("POINTS TEST: Setting user balance to 2000")
            streakPreferences.setTotalPorapointsEarned(2000)
            updateUI()
        }
    }
    
    private fun updateUI() {
        lifecycleScope.launch {
            val streakInfo = streakManager.getStreakInfo()
            val currentSessionTime = timeTracker.getCurrentSessionTime()
            val currentBalance = streakPreferences.getTotalPorapointsEarned()
            
            tvStreakInfo.text = "Current streak: ${streakInfo.currentStreak} days"
            tvTimeInfo.text = "Balance: $currentBalance Porapoints | Session: ${currentSessionTime}ms"
            
            Timber.d("UI updated - Streak: ${streakInfo.currentStreak}, Balance: $currentBalance, Session Time: $currentSessionTime")
        }
    }
    
    override fun onResume() {
        super.onResume()
        updateUI()
        Timber.d("MainActivity resumed, UI refreshed")
    }
    
    private fun setupObservers() {
        // Setup any observers for the main activity
        Timber.d("MainActivity observers setup")
    }
}