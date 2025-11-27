package com.porakhela.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.porakhela.R
import com.porakhela.core.utils.HapticUtils
import com.porakhela.ui.main.MainActivity
import com.porakhela.ui.onboarding.adapters.OnboardingPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3
import timber.log.Timber

/**
 * Main onboarding activity with ViewPager2 flow
 */
@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var pagerAdapter: OnboardingPagerAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        
        // Performance optimization
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )
        
        setupViewPager()
        setupPageIndicator()
        setupNavigationButtons()
        setupObservers()
    }
    
    private fun setupViewPager() {
        pagerAdapter = OnboardingPagerAdapter(this)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false // Disable swipe navigation
        viewPager.offscreenPageLimit = 1 // Optimize for performance
        
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)
                updateNavigationButtons(position)
                Timber.d("ViewPager page selected: $position")
            }
        })
    }
    
    private fun setupPageIndicator() {
        val indicator = findViewById<CircleIndicator3>(R.id.pageIndicator)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        indicator?.setViewPager(viewPager)
    }
    
    private fun setupNavigationButtons() {
        findViewById<View>(R.id.btnSkip)?.setOnClickListener { view ->
            HapticUtils.lightTap(view)
            onSkipClicked()
        }
        
        findViewById<View>(R.id.btnNext)?.setOnClickListener { view ->
            HapticUtils.lightTap(view)
            onNextClicked()
        }
    }
    
    private fun updateNavigationButtons(position: Int) {
        val btnSkip = findViewById<View>(R.id.btnSkip)
        val btnNext = findViewById<View>(R.id.btnNext)
        val isLastPage = position == pagerAdapter.itemCount - 1
        val isConsentPage = position == 3 // Consent is the 4th page (index 3)
        
        // Update skip button visibility
        btnSkip?.visibility = if (isConsentPage) {
            View.GONE
        } else {
            View.VISIBLE
        }
        
        // Handle different button states based on page
        when (position) {
            0, 1, 2 -> {
                // Welcome, Gamification, Porapoints pages - always enabled
                btnNext?.isEnabled = true
            }
            3 -> {
                // Consent page - will be managed by the fragment
                btnNext?.isEnabled = false
            }
        }
        
        Timber.d("Navigation buttons updated for position: $position")
    }
    
    private fun onSkipClicked() {
        Timber.d("Skip button clicked")
        // Mark onboarding as skipped and navigate to main app
        viewModel.skipOnboarding()
        // Immediate navigation for skip action
        completeOnboarding()
    }
    
    private fun onNextClicked() {
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val currentPosition = viewPager?.currentItem ?: 0
        val isLastPage = currentPosition == pagerAdapter.itemCount - 1
        
        if (isLastPage) {
            // This should be handled by the consent fragment
            Timber.d("Next clicked on last page - should be handled by consent fragment")
            return
        }
        
        // Block navigation if on consent page and form is invalid
        if (currentPosition == 3) {
            Timber.d("Navigation blocked - consent page validation required")
            return
        }
        
        // Smooth transition with slight delay to improve perceived performance
        viewPager?.setCurrentItem(currentPosition + 1, true)
        Timber.d("Navigated to page: ${currentPosition + 1}")
    }
    
    private fun setupObservers() {
        // Simplified observer setup
        Timber.d("Setting up onboarding observers")
        // Add specific observers here if needed
    }
    
    private fun navigateToMainApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        
        // Add celebration haptic feedback
        HapticUtils.celebrationVibration(this)
        
        // Add transition animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        
        Timber.i("Navigated to main app after onboarding completion")
    }
    
    /**
     * Complete the onboarding process and navigate to main app
     */
    fun completeOnboarding() {
        navigateToMainApp()
    }
    
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val currentPosition = viewPager?.currentItem ?: 0
        
        if (currentPosition > 0) {
            // Go back to previous page with smooth animation
            viewPager?.setCurrentItem(currentPosition - 1, true)
        } else {
            // Exit app if on first page
            super.onBackPressed()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Clean up ViewPager2 to prevent memory leaks
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.adapter = null
    }
    
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            TRIM_MEMORY_UI_HIDDEN,
            TRIM_MEMORY_BACKGROUND -> {
                // Clear non-essential caches when UI is hidden
                System.gc()
                Timber.d("Memory trimmed at level: $level")
            }
        }
    }
    
    /**
     * Called by consent fragment when form is valid
     */
    fun enableContinueButton() {
        val btnNext = findViewById<View>(R.id.btnNext)
        btnNext?.isEnabled = true
        Timber.d("Continue button enabled")
        
        // Update the next button to show final action
        if (btnNext is android.widget.Button) {
            btnNext.text = "Get Started"
        }
        
        btnNext?.setOnClickListener { view ->
            HapticUtils.strongTap(view)
            Timber.d("Final continue button clicked - completing onboarding")
            completeOnboarding()
        }
    }
    
    /**
     * Called by consent fragment when form becomes invalid
     */
    fun disableContinueButton() {
        val btnNext = findViewById<View>(R.id.btnNext)
        btnNext?.isEnabled = false
        
        // Reset button text
        if (btnNext is android.widget.Button) {
            btnNext.text = "Next"
        }
        
        Timber.d("Continue button disabled")
    }
}