package com.porakhela.ui.leaderboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.porakhela.data.models.LeaderboardType

/**
 * ViewPager2 adapter for leaderboard tabs
 * Manages fragments for Weekly, Monthly, and Global leaderboards
 */
class LeaderboardPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    
    override fun getItemCount(): Int = LeaderboardType.values().size
    
    override fun createFragment(position: Int): Fragment {
        val type = LeaderboardType.values()[position]
        return LeaderboardTabFragment.newInstance(type)
    }
}