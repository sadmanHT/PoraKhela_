package com.porakhela.ui.onboarding.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.porakhela.ui.onboarding.OnboardingWelcomeFragment
import com.porakhela.ui.onboarding.OnboardingGamificationFragment
import com.porakhela.ui.onboarding.OnboardingPorapointsFragment
import com.porakhela.ui.onboarding.OnboardingConsentFragment

/**
 * ViewPager2 adapter for onboarding flow
 */
class OnboardingPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    
    override fun getItemCount(): Int = 4
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingWelcomeFragment()
            1 -> OnboardingGamificationFragment()
            2 -> OnboardingPorapointsFragment()
            3 -> OnboardingConsentFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}