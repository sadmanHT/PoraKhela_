package com.porakhela.ui.leaderboard

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.data.models.LeaderboardEntry
import com.porakhela.data.models.RankChangeType
import com.porakhela.data.models.TrophyType
import com.porakhela.databinding.ItemLeaderboardEntryBinding
import com.porakhela.utils.HapticUtils
import timber.log.Timber

/**
 * RecyclerView adapter for leaderboard entries
 * Features animations, rank highlighting, and trophy display for top 3
 */
class LeaderboardAdapter(
    private val onItemClick: (LeaderboardEntry) -> Unit
) : ListAdapter<LeaderboardEntry, LeaderboardAdapter.LeaderboardViewHolder>(LeaderboardDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = ItemLeaderboardEntryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LeaderboardViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class LeaderboardViewHolder(
        private val binding: ItemLeaderboardEntryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(entry: LeaderboardEntry) {
            bindBasicInfo(entry)
            bindRanking(entry)
            bindTrophy(entry)
            bindHighlight(entry)
            bindRankChange(entry)
            setupClickListener(entry)
            animateEntry()
        }
        
        /**
         * Bind basic user information
         */
        private fun bindBasicInfo(entry: LeaderboardEntry) {
            binding.childName.text = entry.childName
            binding.points.text = binding.root.context.getString(R.string.points_format, entry.points)
            
            // Set avatar (placeholder for now)
            binding.avatar.setImageResource(getAvatarResource(entry.avatar))
        }
        
        /**
         * Bind ranking information
         */
        private fun bindRanking(entry: LeaderboardEntry) {
            binding.rank.text = entry.rank.toString()
            
            // Special styling for top 3
            if (entry.isTopThree()) {
                binding.rank.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.trophy_text)
                )
                binding.rank.textSize = 18f
            } else {
                binding.rank.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.primary_text)
                )
                binding.rank.textSize = 16f
            }
        }
        
        /**
         * Bind trophy display for top 3
         */
        private fun bindTrophy(entry: LeaderboardEntry) {
            val trophyType = entry.getTrophyType()
            
            if (trophyType != null) {
                binding.trophyIcon.visibility = View.VISIBLE
                binding.trophyIcon.setImageResource(getTrophyResource(trophyType))
                animateTrophy()
            } else {
                binding.trophyIcon.visibility = View.GONE
            }
        }
        
        /**
         * Bind highlighting for current user
         */
        private fun bindHighlight(entry: LeaderboardEntry) {
            if (entry.isCurrentUser) {
                // Highlight current user
                binding.root.setBackgroundResource(R.drawable.leaderboard_current_user_bg)
                binding.childName.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.current_user_text)
                )
                binding.currentUserIndicator.visibility = View.VISIBLE
                animateCurrentUserHighlight()
            } else if (entry.isTopThree()) {
                // Highlight top 3
                binding.root.setBackgroundResource(R.drawable.leaderboard_top_three_bg)
                binding.currentUserIndicator.visibility = View.GONE
                animateTopThreeShine()
            } else {
                // Normal entry
                binding.root.setBackgroundResource(R.drawable.leaderboard_normal_bg)
                binding.childName.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.primary_text)
                )
                binding.currentUserIndicator.visibility = View.GONE
            }
        }
        
        /**
         * Bind rank change indicators
         */
        private fun bindRankChange(entry: LeaderboardEntry) {
            when (entry.getRankChangeType()) {
                RankChangeType.UP -> {
                    binding.rankChangeIndicator.visibility = View.VISIBLE
                    binding.rankChangeIndicator.setImageResource(R.drawable.ic_rank_up)
                    binding.rankChangeIndicator.setColorFilter(
                        ContextCompat.getColor(binding.root.context, R.color.rank_up_color)
                    )
                    animateRankChange(true)
                }
                RankChangeType.DOWN -> {
                    binding.rankChangeIndicator.visibility = View.VISIBLE
                    binding.rankChangeIndicator.setImageResource(R.drawable.ic_rank_down)
                    binding.rankChangeIndicator.setColorFilter(
                        ContextCompat.getColor(binding.root.context, R.color.rank_down_color)
                    )
                    animateRankChange(false)
                }
                RankChangeType.NONE -> {
                    binding.rankChangeIndicator.visibility = View.GONE
                }
            }
        }
        
        /**
         * Setup click listener with haptic feedback
         */
        private fun setupClickListener(entry: LeaderboardEntry) {
            binding.root.setOnClickListener {
                HapticUtils.lightTap(binding.root)
                animateItemClick()
                onItemClick(entry)
            }
        }
        
        /**
         * Animate entry appearance
         */
        private fun animateEntry() {
            binding.root.alpha = 0f
            binding.root.translationX = 100f
            
            binding.root.animate()
                .alpha(1f)
                .translationX(0f)
                .setDuration(300)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
        
        /**
         * Animate trophy with controlled shine effect
         */
        private fun animateTrophy() {
            binding.trophyIcon.scaleX = 0f
            binding.trophyIcon.scaleY = 0f
            
            binding.trophyIcon.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    // Limited shine effect to prevent memory leaks
                    ValueAnimator.ofFloat(1f, 1.1f, 1f).apply {
                        duration = 800
                        repeatCount = 2 // Limited repeats
                        repeatMode = ValueAnimator.REVERSE
                        addUpdateListener { animator ->
                            if (binding.trophyIcon.isAttachedToWindow) {
                                val scale = animator.animatedValue as Float
                                binding.trophyIcon.scaleX = scale
                                binding.trophyIcon.scaleY = scale
                            } else {
                                animator.cancel()
                            }
                        }
                        start()
                    }
                }
                .start()
        }
        
        /**
         * Animate current user highlight with controlled lifecycle
         */
        private fun animateCurrentUserHighlight() {
            val drawable = binding.root.background as? GradientDrawable
            drawable?.let {
                val colorAnimation = ValueAnimator.ofObject(
                    ArgbEvaluator(),
                    ContextCompat.getColor(binding.root.context, R.color.current_user_bg),
                    ContextCompat.getColor(binding.root.context, R.color.current_user_bg_highlight)
                )
                colorAnimation.duration = 1200
                colorAnimation.repeatCount = 3 // Limited repeats
                colorAnimation.repeatMode = ValueAnimator.REVERSE
                colorAnimation.addUpdateListener { animator ->
                    if (binding.root.isAttachedToWindow) {
                        it.setColor(animator.animatedValue as Int)
                    } else {
                        animator.cancel()
                    }
                }
                colorAnimation.start()
            }
        }
        
        /**
         * Animate top three shine effect with controlled lifecycle
         */
        private fun animateTopThreeShine() {
            val shimmerAnimation = ObjectAnimator.ofFloat(binding.root, "alpha", 1f, 0.8f, 1f)
            shimmerAnimation.duration = 1500
            shimmerAnimation.repeatCount = 2 // Limited repeats
            shimmerAnimation.repeatMode = ValueAnimator.REVERSE
            shimmerAnimation.addUpdateListener {
                if (!binding.root.isAttachedToWindow) {
                    shimmerAnimation.cancel()
                }
            }
            shimmerAnimation.start()
        }
        
        /**
         * Animate rank change indicator
         */
        private fun animateRankChange(isUp: Boolean) {
            binding.rankChangeIndicator.alpha = 0f
            binding.rankChangeIndicator.translationY = if (isUp) 20f else -20f
            
            binding.rankChangeIndicator.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
        
        /**
         * Animate item click feedback
         */
        private fun animateItemClick() {
            binding.root.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    binding.root.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }
                .start()
        }
        
        /**
         * Get avatar resource based on avatar name
         */
        private fun getAvatarResource(avatar: String): Int {
            return when (avatar) {
                "avatar_1" -> R.drawable.avatar_1
                "avatar_2" -> R.drawable.avatar_2
                "avatar_3" -> R.drawable.avatar_3
                "avatar_4" -> R.drawable.avatar_4
                "avatar_5" -> R.drawable.avatar_5
                "avatar_6" -> R.drawable.avatar_6
                "avatar_7" -> R.drawable.avatar_7
                "avatar_8" -> R.drawable.avatar_8
                "avatar_9" -> R.drawable.avatar_9
                "avatar_10" -> R.drawable.avatar_10
                else -> R.drawable.ic_child_profile_24
            }
        }
        
        /**
         * Get trophy resource based on trophy type
         */
        private fun getTrophyResource(trophyType: TrophyType): Int {
            return when (trophyType) {
                TrophyType.GOLD -> R.drawable.ic_trophy_gold
                TrophyType.SILVER -> R.drawable.ic_trophy_silver
                TrophyType.BRONZE -> R.drawable.ic_trophy_bronze
            }
        }
    }
}

/**
 * DiffCallback for efficient list updates
 */
class LeaderboardDiffCallback : DiffUtil.ItemCallback<LeaderboardEntry>() {
    
    override fun areItemsTheSame(oldItem: LeaderboardEntry, newItem: LeaderboardEntry): Boolean {
        return oldItem.childName == newItem.childName
    }
    
    override fun areContentsTheSame(oldItem: LeaderboardEntry, newItem: LeaderboardEntry): Boolean {
        return oldItem == newItem
    }
}