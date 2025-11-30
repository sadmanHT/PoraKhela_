package com.porakhela.ui.rewards

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.ui.performance.ObjectPoolManager
import com.porakhela.ui.performance.OptimizedCoroutineManager
import com.porakhela.ui.performance.MemoryEfficientTransformations
import com.porakhela.ui.performance.ZeroAllocUtils
import com.porakhela.data.database.entity.Reward
import com.porakhela.data.database.entity.UserReward
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.sin
import kotlin.math.cos

/**
 * OPTIMIZED REWARD CENTER - Advanced Animation & Memory Management
 * 
 * High-performance reward system for Bangladesh low-end devices
 * Target: Smooth reward animations with minimal memory allocation
 * 
 * Key Optimizations:
 * - Object pooling for complex reward animations
 * - Zero-allocation reward calculations
 * - Memory-efficient badge rendering with caching
 * - Optimized particle effects using pooled animators
 * - GC-friendly reward collection algorithms
 * 
 * Performance Targets:
 * - < 1MB memory during reward animations
 * - 60+ FPS animation performance
 * - Zero allocation during reward collection
 * - < 150ms reward display time
 */

@HiltViewModel
class OptimizedRewardCenterViewModel @Inject constructor(
    private val objectPoolManager: ObjectPoolManager,
    private val coroutineManager: OptimizedCoroutineManager,
    private val transformations: MemoryEfficientTransformations,
    private val rewardRepository: com.porakhela.data.repository.OptimizedLearningRepository
) : ViewModel() {

    // Reusable objects for frequent operations
    private val pointsStringBuilder = objectPoolManager.acquireStringBuilder()
    private val progressStringBuilder = objectPoolManager.acquireStringBuilder()
    
    // Animation pools for smooth effects
    private val rewardAnimationPool = mutableListOf<RewardAnimation>()
    
    // Efficient state management
    private val _rewardsState = MutableStateFlow(RewardsViewState())
    val rewardsState = _rewardsState.asStateFlow()
    
    private val _userPointsState = MutableStateFlow(UserPointsState())
    val userPointsState = _userPointsState.asStateFlow()
    
    private val _animationEvents = MutableSharedFlow<RewardAnimationEvent>()
    val animationEvents = _animationEvents.asSharedFlow()
    
    override fun onCleared() {
        super.onCleared()
        objectPoolManager.releaseStringBuilder(pointsStringBuilder)
        objectPoolManager.releaseStringBuilder(progressStringBuilder)
        clearAnimationPools()
    }
    
    /**
     * Load user rewards with efficient caching
     */
    fun loadUserRewards() {
        _rewardsState.value = _rewardsState.value.copy(isLoading = true)
        
        viewModelScope.launch {
            coroutineManager.executeIOOperation(
                operation = {
                    rewardRepository.getUserRewardsWithProgress()
                },
                onSuccess = { rewards ->
                    processUserRewards(rewards)
                },
                onError = { error ->
                    _rewardsState.value = _rewardsState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                    Timber.e(error, "Failed to load user rewards")
                }
            )
        }
    }
    
    /**
     * Collect reward with smooth animation
     */
    fun collectReward(rewardId: Long, animationView: View) {
        viewModelScope.launch {
            coroutineManager.executeIOOperation(
                operation = {
                    rewardRepository.collectRewardOptimized(rewardId)
                },
                onSuccess = { collectedReward ->
                    // Update user points
                    updateUserPoints(collectedReward.points)
                    
                    // Trigger collection animation
                    animateRewardCollection(collectedReward, animationView)
                    
                    // Refresh rewards list
                    loadUserRewards()
                },
                onError = { error ->
                    Timber.e(error, "Failed to collect reward: $rewardId")
                }
            )
        }
    }
    
    /**
     * Calculate daily reward streak with zero allocation
     */
    fun calculateDailyStreak() {
        viewModelScope.launch {
            coroutineManager.executeCPUOperation(
                operation = {
                    rewardRepository.calculateUserStreak()
                },
                onSuccess = { streak ->
                    val currentPoints = _userPointsState.value
                    val bonusPoints = streak * 10 // 10 points per day streak
                    val formattedBonus = ZeroAllocUtils.formatNumber(bonusPoints.toLong(), pointsStringBuilder, " bonus")
                    
                    _userPointsState.value = currentPoints.copy(
                        dailyStreak = streak,
                        streakBonus = bonusPoints,
                        formattedStreakBonus = formattedBonus
                    )
                },
                onError = { error ->
                    Timber.e(error, "Failed to calculate daily streak")
                }
            )
        }
    }
    
    /**
     * Animate badge unlock with pooled animators
     */
    fun animateBadgeUnlock(badgeView: View, badge: Reward) {
        val animation = acquireRewardAnimation()
        animation.startBadgeUnlockAnimation(badgeView, badge) { completedAnimation ->
            releaseRewardAnimation(completedAnimation)
            
            // Emit animation event for UI feedback
            viewModelScope.launch {
                _animationEvents.emit(
                    RewardAnimationEvent.BadgeUnlocked(
                        badgeName = badge.name,
                        points = badge.points
                    )
                )
            }
        }
    }
    
    /**
     * Animate points collection with particle effects
     */
    fun animatePointsCollection(sourceView: View, targetView: View, points: Int) {
        val animation = acquireRewardAnimation()
        animation.startPointsCollectionAnimation(sourceView, targetView, points) { completedAnimation ->
            releaseRewardAnimation(completedAnimation)
        }
    }
    
    /**
     * Check for milestone achievements
     */
    fun checkMilestones() {
        viewModelScope.launch {
            coroutineManager.executeIOOperation(
                operation = {
                    rewardRepository.checkMilestoneAchievements()
                },
                onSuccess = { newMilestones ->
                    if (newMilestones.isNotEmpty()) {
                        // Animate each milestone achievement
                        newMilestones.forEach { milestone ->
                            viewModelScope.launch {
                                _animationEvents.emit(
                                    RewardAnimationEvent.MilestoneAchieved(
                                        milestoneName = milestone.name,
                                        points = milestone.points
                                    )
                                )
                            }
                        }
                    }
                },
                onError = { error ->
                    Timber.e(error, "Failed to check milestones")
                }
            )
        }
    }
    
    // PRIVATE HELPER METHODS
    
    private fun processUserRewards(rewards: List<UserReward>) {
        viewModelScope.launch {
            coroutineManager.executeCPUOperation(
                operation = {
                    val displayModels = transformations.transformList(rewards) { reward ->
                        val formattedPoints = ZeroAllocUtils.formatNumber(reward.points, pointsStringBuilder, " pts")
                        val formattedProgress = if (reward.targetProgress > 0) {
                            ZeroAllocUtils.formatProgress(
                                reward.currentProgress, 
                                reward.targetProgress, 
                                progressStringBuilder
                            )
                        } else {
                            "Completed"
                        }
                        
                        RewardDisplayModel(
                            id = reward.id,
                            name = reward.name,
                            description = reward.description,
                            points = reward.points,
                            formattedPoints = formattedPoints,
                            iconUrl = reward.iconUrl,
                            isUnlocked = reward.isUnlocked,
                            currentProgress = reward.currentProgress,
                            targetProgress = reward.targetProgress,
                            formattedProgress = formattedProgress,
                            isCollectible = reward.isUnlocked && !reward.isCollected,
                            category = reward.category
                        )
                    }
                    
                    // Group by category for organized display
                    transformations.groupByEfficient(displayModels) { it.category }
                },
                onSuccess = { groupedRewards ->
                    _rewardsState.value = _rewardsState.value.copy(
                        rewardsByCategory = groupedRewards,
                        isLoading = false,
                        errorMessage = null
                    )
                },
                onError = { error ->
                    _rewardsState.value = _rewardsState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                    Timber.e(error, "Failed to process user rewards")
                }
            )
        }
    }
    
    private fun updateUserPoints(additionalPoints: Int) {
        val currentState = _userPointsState.value
        val newTotal = currentState.totalPoints + additionalPoints
        val formattedTotal = ZeroAllocUtils.formatNumber(newTotal.toLong(), pointsStringBuilder)
        
        _userPointsState.value = currentState.copy(
            totalPoints = newTotal,
            formattedTotalPoints = formattedTotal,
            recentPointsGained = additionalPoints
        )
    }
    
    private fun animateRewardCollection(reward: Reward, animationView: View) {
        val animation = acquireRewardAnimation()
        animation.startRewardCollectionAnimation(animationView, reward) { completedAnimation ->
            releaseRewardAnimation(completedAnimation)
            
            // Emit animation event
            viewModelScope.launch {
                _animationEvents.emit(
                    RewardAnimationEvent.RewardCollected(
                        rewardName = reward.name,
                        points = reward.points
                    )
                )
            }
        }
    }
    
    private fun acquireRewardAnimation(): RewardAnimation {
        return if (rewardAnimationPool.isNotEmpty()) {
            rewardAnimationPool.removeLastOrNull() ?: RewardAnimation(objectPoolManager)
        } else {
            RewardAnimation(objectPoolManager)
        }
    }
    
    private fun releaseRewardAnimation(animation: RewardAnimation) {
        if (rewardAnimationPool.size < 10) { // Limit pool size
            animation.reset()
            rewardAnimationPool.add(animation)
        }
    }
    
    private fun clearAnimationPools() {
        rewardAnimationPool.clear()
    }
}

/**
 * Optimized animation manager for smooth reward effects
 */
class RewardAnimation(
    private val objectPoolManager: ObjectPoolManager
) {
    
    private var currentAnimator: ValueAnimator? = null
    private var particleAnimators = mutableListOf<ObjectAnimator>()
    
    /**
     * Badge unlock animation with scale and rotation effects
     */
    fun startBadgeUnlockAnimation(
        badgeView: View, 
        badge: Reward,
        onComplete: (RewardAnimation) -> Unit
    ) {
        reset()
        
        currentAnimator = objectPoolManager.acquireValueAnimator().apply {
            setFloatValues(0f, 1f)
            duration = 800
            
            addUpdateListener { animator ->
                val progress = animator.animatedValue as Float
                
                // Scale animation
                val scale = 0.5f + (progress * 0.5f)
                badgeView.scaleX = scale
                badgeView.scaleY = scale
                
                // Rotation animation
                badgeView.rotation = progress * 360f
                
                // Alpha animation
                badgeView.alpha = progress
            }
            
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    onComplete(this@RewardAnimation)
                }
            })
        }
        
        currentAnimator?.start()
    }
    
    /**
     * Points collection animation with particle effects
     */
    fun startPointsCollectionAnimation(
        sourceView: View,
        targetView: View,
        points: Int,
        onComplete: (RewardAnimation) -> Unit
    ) {
        reset()
        
        // Create particle effect
        createParticleEffect(sourceView, targetView, points)
        
        currentAnimator = objectPoolManager.acquireValueAnimator().apply {
            setFloatValues(0f, 1f)
            duration = 600
            
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    onComplete(this@RewardAnimation)
                }
            })
        }
        
        currentAnimator?.start()
    }
    
    /**
     * Reward collection animation with feedback effects
     */
    fun startRewardCollectionAnimation(
        animationView: View,
        reward: Reward,
        onComplete: (RewardAnimation) -> Unit
    ) {
        reset()
        
        currentAnimator = objectPoolManager.acquireValueAnimator().apply {
            setFloatValues(0f, 1f)
            duration = 500
            
            addUpdateListener { animator ->
                val progress = animator.animatedValue as Float
                
                // Bounce effect
                val bounceProgress = sin(progress * Math.PI).toFloat()
                animationView.scaleX = 1f + (bounceProgress * 0.2f)
                animationView.scaleY = 1f + (bounceProgress * 0.2f)
                
                // Glow effect (alpha modulation)
                animationView.alpha = 0.8f + (bounceProgress * 0.2f)
            }
            
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    animationView.scaleX = 1f
                    animationView.scaleY = 1f
                    animationView.alpha = 1f
                    onComplete(this@RewardAnimation)
                }
            })
        }
        
        currentAnimator?.start()
    }
    
    /**
     * Create particle effect for points collection
     */
    private fun createParticleEffect(sourceView: View, targetView: View, points: Int) {
        val particleCount = minOf(points / 10, 8) // Limit particles for performance
        
        repeat(particleCount) { index ->
            val animator = objectPoolManager.acquireObjectAnimator().apply {
                target = sourceView // In real implementation, create particle views
                setProperty(View.TRANSLATION_Y)
                
                val startY = sourceView.y
                val endY = targetView.y
                setFloatValues(startY, endY)
                
                duration = 400 + (index * 50) // Stagger particles
                startDelay = index * 30L
                
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        objectPoolManager.releaseObjectAnimator(this@apply)
                    }
                })
            }
            
            particleAnimators.add(animator)
            animator.start()
        }
    }
    
    /**
     * Reset animation state for reuse
     */
    fun reset() {
        currentAnimator?.cancel()
        currentAnimator?.let { objectPoolManager.releaseValueAnimator(it) }
        currentAnimator = null
        
        particleAnimators.forEach { animator ->
            animator.cancel()
            objectPoolManager.releaseObjectAnimator(animator)
        }
        particleAnimators.clear()
    }
}

// STATE CLASSES AND EVENTS

data class RewardsViewState(
    val rewardsByCategory: Map<String, List<RewardDisplayModel>> = emptyMap(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class UserPointsState(
    val totalPoints: Int = 0,
    val formattedTotalPoints: String = "0",
    val recentPointsGained: Int = 0,
    val dailyStreak: Int = 0,
    val streakBonus: Int = 0,
    val formattedStreakBonus: String = "0 bonus"
)

data class RewardDisplayModel(
    val id: Long,
    val name: String,
    val description: String,
    val points: Int,
    val formattedPoints: String,
    val iconUrl: String?,
    val isUnlocked: Boolean,
    val currentProgress: Int,
    val targetProgress: Int,
    val formattedProgress: String,
    val isCollectible: Boolean,
    val category: String
)

sealed class RewardAnimationEvent {
    data class RewardCollected(val rewardName: String, val points: Int) : RewardAnimationEvent()
    data class BadgeUnlocked(val badgeName: String, val points: Int) : RewardAnimationEvent()
    data class MilestoneAchieved(val milestoneName: String, val points: Int) : RewardAnimationEvent()
}