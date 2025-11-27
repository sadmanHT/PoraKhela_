package com.porakhela.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.porakhela.R
import com.porakhela.ui.main.MainActivity
import com.porakhela.ui.onboarding.OnboardingActivity
import com.porakhela.core.utils.Constants
import com.porakhela.data.local.OnboardingPreferences
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * Splash screen activity that shows app logo and initializes the application
 */
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    
    private val viewModel: SplashViewModel by viewModels()
    private val splashHandler = Handler(Looper.getMainLooper())

    @Inject
    lateinit var onboardingPreferences: OnboardingPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        initializeApp()
    }
    
    private fun initializeApp() {
        // Start initialization
        viewModel.initialize()
        setupObservers()
        
        // Set up splash delay
        splashHandler.postDelayed({
            checkOnboardingStatusAndNavigate()
        }, Constants.SPLASH_DELAY)
    }
    
    private fun setupObservers() {
        // Observe UI state changes
        Timber.d("Setting up splash observers")
        // Add any specific observers here if needed
    }
    
    private fun checkOnboardingStatusAndNavigate() {
        val isOnboardingCompleted = onboardingPreferences.isOnboardingCompleted()
        
        // TEMPORARY: Skip onboarding for testing purposes
        if (true || isOnboardingCompleted) {
            // User has completed onboarding, go to main app
            navigateToMain()
        } else {
            // User needs to complete onboarding
            navigateToOnboarding()
        }
    }
    
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
        
        // Add slide transition
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        
        Timber.d("Navigated to MainActivity")
    }
    
    private fun navigateToOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
        
        // Add slide transition
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        
        Timber.d("Navigated to OnboardingActivity")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        splashHandler.removeCallbacksAndMessages(null)
    }
    
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Disable back button on splash screen
        // Do nothing
    }
}