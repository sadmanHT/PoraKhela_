package com.porakhela.ui.leaderboard

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.porakhela.R
import com.porakhela.data.models.LeaderboardType
import com.porakhela.databinding.FragmentLeaderboardTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Fragment for individual leaderboard tab (Weekly, Monthly, or Global)
 * Displays the leaderboard list with animations and ranking highlights
 */
@AndroidEntryPoint
class LeaderboardTabFragment : Fragment() {
    
    private var _binding: FragmentLeaderboardTabBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: LeaderboardViewModel by activityViewModels()
    private lateinit var adapter: LeaderboardAdapter
    private lateinit var leaderboardType: LeaderboardType
    
    companion object {
        private const val ARG_LEADERBOARD_TYPE = "leaderboard_type"
        
        fun newInstance(type: LeaderboardType): LeaderboardTabFragment {
            return LeaderboardTabFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_LEADERBOARD_TYPE, type)
                }
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leaderboardType = arguments?.getSerializable(ARG_LEADERBOARD_TYPE) as? LeaderboardType
            ?: LeaderboardType.WEEKLY
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaderboardTabBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
        setupEmptyState()
        
        Timber.d("LeaderboardTabFragment created for $leaderboardType")
    }
    
    /**
     * Setup RecyclerView with adapter and animations
     */
    private fun setupRecyclerView() {
        adapter = LeaderboardAdapter { entry ->
            // Handle item click (could navigate to user profile)
            Timber.d("Clicked on leaderboard entry: ${entry.childName}")
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@LeaderboardTabFragment.adapter
            
            // Add item decorations for spacing
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                ).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.leaderboard_divider)!!)
                }
            )
        }
    }
    
    /**
     * Observe ViewModel for leaderboard data changes
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.getLeaderboardForType(leaderboardType).collect { entries ->
                updateLeaderboard(entries)
            }
        }
        
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                updateLoadingState(state.isLoading)
            }
        }
    }
    
    /**
     * Update leaderboard data with animations
     */
    private fun updateLeaderboard(entries: List<com.porakhela.data.models.LeaderboardEntry>) {
        if (entries.isEmpty()) {
            showEmptyState()
        } else {
            hideEmptyState()
            adapter.submitList(entries) {
                // Animate list entrance
                animateListEntrance()
            }
        }
    }
    
    /**
     * Update loading state
     */
    private fun updateLoadingState(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.alpha = 0.5f
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.alpha = 1.0f
        }
    }
    
    /**
     * Setup empty state view
     */
    private fun setupEmptyState() {
        binding.emptyStateTitle.text = when (leaderboardType) {
            LeaderboardType.WEEKLY -> getString(R.string.empty_weekly_leaderboard)
            LeaderboardType.MONTHLY -> getString(R.string.empty_monthly_leaderboard)
            LeaderboardType.GLOBAL -> getString(R.string.empty_global_leaderboard)
        }
        
        binding.emptyStateMessage.text = getString(R.string.empty_leaderboard_message)
    }
    
    /**
     * Show empty state
     */
    private fun showEmptyState() {
        binding.emptyStateContainer.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        
        // Animate empty state entrance
        binding.emptyStateContainer.alpha = 0f
        binding.emptyStateContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .start()
    }
    
    /**
     * Hide empty state
     */
    private fun hideEmptyState() {
        binding.emptyStateContainer.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }
    
    /**
     * Animate list entrance with stagger effect
     */
    private fun animateListEntrance() {
        binding.recyclerView.alpha = 0f
        binding.recyclerView.translationY = 50f
        
        binding.recyclerView.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(400)
            .start()
            
        // Animate individual items with stagger
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        for (i in 0 until minOf(adapter.itemCount, 10)) {
            val viewHolder = binding.recyclerView.findViewHolderForAdapterPosition(i)
            viewHolder?.itemView?.let { itemView ->
                itemView.alpha = 0f
                itemView.translationX = 100f
                
                itemView.animate()
                    .alpha(1f)
                    .translationX(0f)
                    .setDuration(300)
                    .setStartDelay(i * 50L)
                    .start()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}