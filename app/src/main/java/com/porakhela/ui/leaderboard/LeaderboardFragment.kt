package com.porakhela.ui.leaderboard

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.porakhela.R
import com.porakhela.data.models.LeaderboardType
import com.porakhela.databinding.FragmentLeaderboardBinding
import com.porakhela.utils.HapticUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Leaderboard Fragment with tabbed interface for Weekly, Monthly, and Global leaderboards
 * Features pull-to-refresh, animations, and real-time user ranking
 */
@AndroidEntryPoint
class LeaderboardFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    
    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: LeaderboardViewModel by viewModels()
    private lateinit var pagerAdapter: LeaderboardPagerAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewPager()
        setupTabLayout()
        setupSwipeRefresh()
        observeViewModel()
        setupAnimations()
        
        // Restore state or load initial data
        if (savedInstanceState == null) {
            viewModel.loadAllLeaderboards()
        } else {
            // State will be restored automatically by ViewModel
            Timber.d("Restoring leaderboard state from savedInstanceState")
        }
        
        Timber.d("LeaderboardFragment initialized")
    }
    
    /**
     * Setup ViewPager2 with leaderboard pages
     */
    private fun setupViewPager() {
        pagerAdapter = LeaderboardPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                HapticUtils.lightTap(binding.root)
                
                val type = LeaderboardType.values()[position]
                viewModel.selectLeaderboardType(type)
                
                Timber.d("Selected leaderboard: $type")
            }
        })
    }
    
    /**
     * Setup TabLayout with custom styling
     */
    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val type = LeaderboardType.values()[position]
            tab.text = when (type) {
                LeaderboardType.WEEKLY -> getString(R.string.leaderboard_weekly)
                LeaderboardType.MONTHLY -> getString(R.string.leaderboard_monthly)
                LeaderboardType.GLOBAL -> getString(R.string.leaderboard_global)
            }
        }.attach()
        
        // Add tab selection listener for animations
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                animateTabSelection(tab)
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Reset tab animation
            }
            
            override fun onTabReselected(tab: TabLayout.Tab) {
                // Refresh current leaderboard
                onRefresh()
            }
        })
    }
    
    /**
     * Setup pull-to-refresh functionality
     */
    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.swipeRefreshLayout.setColorSchemeResources(
            R.color.primary_color,
            R.color.secondary_color,
            R.color.accent_color
        )
    }
    
    /**
     * Observe ViewModel state changes
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                updateUI(state)
            }
        }
        
        lifecycleScope.launch {
            viewModel.errorMessage.collect { error ->
                error?.let {
                    showError(it)
                    viewModel.clearError()
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.currentUserRank.collect { rank ->
                updateCurrentUserRank(rank)
            }
        }
    }
    
    /**
     * Update UI based on ViewModel state
     */
    private fun updateUI(state: LeaderboardUiState) {
        binding.swipeRefreshLayout.isRefreshing = state.isLoading
        
        if (state.isLoading) {
            binding.loadingIndicator.visibility = View.VISIBLE
            binding.viewPager.alpha = 0.5f
        } else {
            binding.loadingIndicator.visibility = View.GONE
            binding.viewPager.alpha = 1.0f
        }
        
        // Update current user info
        state.currentUserName?.let {
            binding.currentUserCard.visibility = View.VISIBLE
            binding.currentUserName.text = it
        } ?: run {
            binding.currentUserCard.visibility = View.GONE
        }
    }
    
    /**
     * Update current user rank display
     */
    private fun updateCurrentUserRank(rank: Int?) {
        if (rank != null) {
            binding.currentUserRank.text = getString(R.string.current_rank_format, rank)
            binding.currentUserCard.visibility = View.VISIBLE
            animateRankUpdate()
        } else {
            binding.currentUserCard.visibility = View.GONE
        }
    }
    
    /**
     * Show error message
     */
    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.retry)) {
                onRefresh()
            }
            .show()
    }
    
    /**
     * Setup entrance animations
     */
    private fun setupAnimations() {
        // Animate tab layout entrance
        binding.tabLayout.alpha = 0f
        binding.tabLayout.translationY = -50f
        
        binding.tabLayout.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(300)
            .setStartDelay(100)
            .start()
        
        // Animate current user card entrance
        binding.currentUserCard.alpha = 0f
        binding.currentUserCard.scaleX = 0.8f
        binding.currentUserCard.scaleY = 0.8f
        
        binding.currentUserCard.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(400)
            .setStartDelay(200)
            .start()
    }
    
    /**
     * Animate tab selection
     */
    private fun animateTabSelection(tab: TabLayout.Tab) {
        tab.view.animate()
            .scaleX(1.1f)
            .scaleY(1.1f)
            .setDuration(150)
            .withEndAction {
                tab.view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start()
            }
            .start()
    }
    
    /**
     * Animate rank update with bounce effect
     */
    private fun animateRankUpdate() {
        val scaleX = ObjectAnimator.ofFloat(binding.currentUserRank, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.currentUserRank, "scaleY", 1f, 1.2f, 1f)
        
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 300
            start()
        }
    }
    
    /**
     * Handle pull-to-refresh
     */
    override fun onRefresh() {
        HapticUtils.lightTap(binding.root)
        
        val currentPosition = binding.viewPager.currentItem
        val type = LeaderboardType.values()[currentPosition]
        
        viewModel.refreshLeaderboard(type)
        
        Timber.d("Refreshing $type leaderboard")
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}