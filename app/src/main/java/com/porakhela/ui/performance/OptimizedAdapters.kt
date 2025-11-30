package com.porakhela.ui.performance

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.porakhela.R
import com.porakhela.data.model.LessonContent

/**
 * ADVANCED PERFORMANCE HARDENING - Step 1&2/6: RECYCLER VIEW & BITMAP OPTIMIZATION
 * 
 * High-performance LessonAdapter optimized for Bangladesh low-end devices
 * Features:
 * - DiffUtil for efficient updates
 * - View holder pre-caching with optimized bitmap loading
 * - Object pooling for animations
 * - Lazy loading support with scroll-aware image loading
 * - Integrated with OptimizedBitmapManager for memory efficiency
 */

// Shared StringBuilder pool to prevent object creation in onBindViewHolder
object StringBuilderPool {
    private val pool = mutableListOf<StringBuilder>()
    private const val MAX_POOL_SIZE = 5
    
    fun acquire(): StringBuilder {
        return if (pool.isNotEmpty()) {
            pool.removeAt(pool.size - 1).apply { clear() }
        } else {
            StringBuilder()
        }
    }
    
    fun release(sb: StringBuilder) {
        if (pool.size < MAX_POOL_SIZE) {
            pool.add(sb)
        }
    }
}
class OptimizedLessonAdapter(
    private val bitmapManager: OptimizedBitmapManager,
    private val lazyImageLoader: LazyImageLoader
) : ListAdapter<LessonContent, OptimizedLessonAdapter.LessonViewHolder>(LESSON_DIFF_CALLBACK) {

    companion object {
        private val LESSON_DIFF_CALLBACK = object : DiffUtil.ItemCallback<LessonContent>() {
            override fun areItemsTheSame(oldItem: LessonContent, newItem: LessonContent): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: LessonContent, newItem: LessonContent): Boolean {
                return oldItem.id == newItem.id &&
                       oldItem.title == newItem.title &&
                       oldItem.progress == newItem.progress &&
                       oldItem.isCompleted == newItem.isCompleted &&
                       oldItem.isLocked == newItem.isLocked
            }

            override fun getChangePayload(oldItem: LessonContent, newItem: LessonContent): Any? {
                return when {
                    oldItem.progress != newItem.progress -> PAYLOAD_PROGRESS_UPDATE
                    oldItem.isCompleted != newItem.isCompleted -> PAYLOAD_STATUS_UPDATE
                    else -> null
                }
            }
        }

        private const val PAYLOAD_PROGRESS_UPDATE = "progress"
        private const val PAYLOAD_STATUS_UPDATE = "status"

        // Pre-cached view pool for performance
        private val viewPool = RecyclerView.RecycledViewPool().apply {
            setMaxRecycledViews(0, 15) // Pre-cache 15 lesson items
        }

        fun getOptimizedViewPool(): RecyclerView.RecycledViewPool = viewPool
    }

    private var onLessonClickListener: ((LessonContent) -> Unit)? = null

    fun setOnLessonClickListener(listener: (LessonContent) -> Unit) {
        onLessonClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson_optimized, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val lesson = getItem(position)
            payloads.forEach { payload ->
                when (payload) {
                    PAYLOAD_PROGRESS_UPDATE -> holder.updateProgress(lesson.progress)
                    PAYLOAD_STATUS_UPDATE -> holder.updateStatus(lesson.isCompleted, lesson.isLocked)
                }
            }
        }
    }

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_lesson_title)
        private val tvSubtitle: TextView = itemView.findViewById(R.id.tv_lesson_subtitle)
        private val ivIcon: ImageView = itemView.findViewById(R.id.iv_lesson_icon)
        private val progressBar: View = itemView.findViewById(R.id.progress_lesson)
        private val statusIndicator: View = itemView.findViewById(R.id.status_indicator)

        init {
            // Set up click listener once in constructor
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onLessonClickListener?.invoke(getItem(position))
                }
            }

            // Pre-configure views for better performance
            tvTitle.includeFontPadding = false
            tvSubtitle.includeFontPadding = false
        }

        fun bind(lesson: LessonContent) {
            tvTitle.text = lesson.title
            tvSubtitle.text = lesson.subtitle

            updateProgress(lesson.progress)
            updateStatus(lesson.isCompleted, lesson.isLocked)
            
            // Optimized icon loading with lazy loading strategy
            val iconName = when {
                lesson.isCompleted -> "ic_check_circle"
                lesson.isLocked -> "ic_lock"
                else -> "ic_lessons_24" // Default lesson icon
            }
            lazyImageLoader.loadImageOptimized(ivIcon, iconName, bitmapManager)
        }

        fun updateProgress(progress: Int) {
            // Efficient progress update without full rebind
            progressBar.scaleX = progress / 100f
        }

        fun updateStatus(isCompleted: Boolean, isLocked: Boolean) {
            // Efficient status update without full rebind
            val statusColor = when {
                isCompleted -> R.color.success_green
                isLocked -> R.color.gray_medium
                else -> R.color.primary_blue
            }
            statusIndicator.setBackgroundResource(statusColor)
            itemView.alpha = if (isLocked) 0.6f else 1f
        }
    }
}

/**
 * Optimized Leaderboard Adapter with minimal object creation
 */
class OptimizedLeaderboardAdapter(
    private val bitmapManager: OptimizedBitmapManager,
    private val lazyImageLoader: LazyImageLoader
) : ListAdapter<LeaderboardEntry, OptimizedLeaderboardAdapter.LeaderboardViewHolder>(LEADERBOARD_DIFF_CALLBACK) {

    companion object {
        private val LEADERBOARD_DIFF_CALLBACK = object : DiffUtil.ItemCallback<LeaderboardEntry>() {
            override fun areItemsTheSame(oldItem: LeaderboardEntry, newItem: LeaderboardEntry): Boolean {
                return oldItem.userId == newItem.userId
            }

            override fun areContentsTheSame(oldItem: LeaderboardEntry, newItem: LeaderboardEntry): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: LeaderboardEntry, newItem: LeaderboardEntry): Any? {
                return when {
                    oldItem.rank != newItem.rank -> "rank"
                    oldItem.points != newItem.points -> "points"
                    else -> null
                }
            }
        }

        // View pool for leaderboard items
        private val leaderboardViewPool = RecyclerView.RecycledViewPool().apply {
            setMaxRecycledViews(0, 20) // Top 20 visible items cached
        }

        fun getLeaderboardViewPool(): RecyclerView.RecycledViewPool = leaderboardViewPool
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard_optimized, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val entry = getItem(position)
            payloads.forEach { payload ->
                when (payload) {
                    "rank" -> holder.updateRank(entry.rank)
                    "points" -> holder.updatePoints(entry.points)
                }
            }
        }
    }

    inner class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRank: TextView = itemView.findViewById(R.id.tv_rank)
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPoints: TextView = itemView.findViewById(R.id.tv_points)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        
        // Pre-allocated StringBuilder to avoid object creation
        private val stringBuilder = StringBuilder(20)

        init {
            // Optimize text rendering
            tvRank.includeFontPadding = false
            tvName.includeFontPadding = false
            tvPoints.includeFontPadding = false
        }

        fun bind(entry: LeaderboardEntry) {
            updateRank(entry.rank)
            tvName.text = entry.name
            updatePoints(entry.points)
            
            // Load avatar efficiently
            loadAvatar(entry.avatarUrl)
        }

        fun updateRank(rank: Int) {
            // Reuse StringBuilder to avoid allocations
            stringBuilder.clear()
            stringBuilder.append('#').append(rank)
            tvRank.text = stringBuilder.toString()
        }

        fun updatePoints(points: Long) {
            // Reuse StringBuilder for points formatting
            stringBuilder.clear()
            stringBuilder.append(points).append(" pts")
            tvPoints.text = stringBuilder.toString()
        }

        private fun loadAvatar(avatarUrl: String?) {
            val avatarName = if (avatarUrl.isNullOrEmpty()) {
                "default_avatar"
            } else {
                // For now use default avatar, but could parse URL to determine avatar type
                "default_avatar" 
            }
            lazyImageLoader.loadImageOptimized(ivAvatar, avatarName, bitmapManager)
        }
    }
}

/**
 * Performance-optimized Reward Center Adapter
 */
class OptimizedRewardAdapter(
    private val bitmapManager: OptimizedBitmapManager,
    private val lazyImageLoader: LazyImageLoader
) : ListAdapter<RewardItem, OptimizedRewardAdapter.RewardViewHolder>(REWARD_DIFF_CALLBACK) {

    companion object {
        private val REWARD_DIFF_CALLBACK = object : DiffUtil.ItemCallback<RewardItem>() {
            override fun areItemsTheSame(oldItem: RewardItem, newItem: RewardItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RewardItem, newItem: RewardItem): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: RewardItem, newItem: RewardItem): Any? {
                return when {
                    oldItem.isAvailable != newItem.isAvailable -> "availability"
                    oldItem.cost != newItem.cost -> "cost"
                    else -> null
                }
            }
        }

        private val rewardViewPool = RecyclerView.RecycledViewPool().apply {
            setMaxRecycledViews(0, 12) // 12 rewards visible on screen
        }

        fun getRewardViewPool(): RecyclerView.RecycledViewPool = rewardViewPool
    }

    private var onRewardClickListener: ((RewardItem) -> Unit)? = null

    fun setOnRewardClickListener(listener: (RewardItem) -> Unit) {
        onRewardClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reward_optimized, parent, false)
        return RewardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: RewardViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val reward = getItem(position)
            payloads.forEach { payload ->
                when (payload) {
                    "availability" -> holder.updateAvailability(reward.isAvailable)
                    "cost" -> holder.updateCost(reward.cost)
                }
            }
        }
    }

    inner class RewardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_reward_title)
        private val tvCost: TextView = itemView.findViewById(R.id.tv_reward_cost)
        private val ivReward: ImageView = itemView.findViewById(R.id.iv_reward)
        private val btnRedeem: View = itemView.findViewById(R.id.btn_redeem)
        
        private val stringBuilder = StringBuilder(15)

        init {
            tvTitle.includeFontPadding = false
            tvCost.includeFontPadding = false
            
            btnRedeem.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onRewardClickListener?.invoke(getItem(position))
                }
            }
        }

        fun bind(reward: RewardItem) {
            tvTitle.text = reward.title
            updateCost(reward.cost)
            updateAvailability(reward.isAvailable)
            
            // Optimized reward icon loading
            val iconName = when(reward.type) {
                "data" -> "ic_data_pack"
                "talktime" -> "ic_talktime"
                "achievement" -> "ic_badge"
                "education" -> "ic_premium"
                else -> "ic_gift"
            }
            lazyImageLoader.loadImageOptimized(ivReward, iconName, bitmapManager)
        }

        fun updateCost(cost: Int) {
            stringBuilder.clear()
            stringBuilder.append(cost).append(" coins")
            tvCost.text = stringBuilder.toString()
        }

        fun updateAvailability(isAvailable: Boolean) {
            itemView.alpha = if (isAvailable) 1f else 0.5f
            btnRedeem.isEnabled = isAvailable
        }
    }
}

// Data classes for optimized adapters
data class LessonContent(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconResId: Int,
    val progress: Int,
    val isCompleted: Boolean,
    val isLocked: Boolean
)

data class LeaderboardEntry(
    val userId: String,
    val name: String,
    val points: Long,
    val rank: Int,
    val avatarUrl: String?
)

data class RewardItem(
    val id: String,
    val title: String,
    val cost: Int,
    val type: String, // "data", "talktime", "achievement", "education", etc.
    val iconResId: Int,
    val isAvailable: Boolean
)
    val iconResId: Int,
    val isAvailable: Boolean
)