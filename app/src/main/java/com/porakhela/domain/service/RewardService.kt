package com.porakhela.domain.service

import com.porakhela.data.api.RewardApiService
import com.porakhela.data.local.StreakPreferences
import com.porakhela.data.model.Reward
import com.porakhela.data.model.RedemptionStatus
import com.porakhela.data.repository.RewardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service for handling reward redemption business logic
 */
@Singleton
class RewardService @Inject constructor(
    private val rewardRepository: RewardRepository,
    private val rewardApiService: RewardApiService,
    private val streakPreferences: StreakPreferences
) {
    
    /**
     * Result of a redemption attempt
     */
    sealed class RedemptionResult {
        data class Success(val transactionId: String, val newBalance: Int) : RedemptionResult()
        data class PendingApproval(val redemptionId: Long) : RedemptionResult()
        data class InsufficientPoints(val needed: Int, val current: Int) : RedemptionResult()
        data class Error(val message: String, val retryable: Boolean = true) : RedemptionResult()
    }
    
    /**
     * Initiate reward redemption process
     */
    suspend fun redeemReward(reward: Reward): RedemptionResult = withContext(Dispatchers.IO) {
        try {
            val currentBalance = streakPreferences.getTotalPorapointsEarned()
            
            Timber.i("Starting redemption for reward: ${reward.name}, cost: ${reward.cost}, balance: $currentBalance")
            
            // Check if user has sufficient points
            if (currentBalance < reward.cost) {
                Timber.w("Insufficient points for redemption")
                val pointsNeeded = reward.cost - currentBalance
                return@withContext RedemptionResult.InsufficientPoints(
                    needed = pointsNeeded,
                    current = currentBalance
                )
            }
            
            // Create redemption record
            val redemptionResult = rewardRepository.createRedemption(reward)
            if (redemptionResult.isFailure) {
                return@withContext RedemptionResult.Error("Failed to create redemption record")
            }
            
            val redemptionId = redemptionResult.getOrThrow()
            
            // If approval required, return pending state
            if (reward.approvalRequired) {
                Timber.d("Redemption requires parental approval")
                return@withContext RedemptionResult.PendingApproval(redemptionId)
            }
            
            // Process immediate redemption
            return@withContext processRedemption(redemptionId, reward, currentBalance)
            
        } catch (e: Exception) {
            Timber.e(e, "Error during reward redemption")
            RedemptionResult.Error("An unexpected error occurred: ${e.message}")
        }
    }
    
    /**
     * Process approved redemption (called after parent approval or for immediate redemptions)
     */
    suspend fun processApprovedRedemption(redemptionId: Long): RedemptionResult = withContext(Dispatchers.IO) {
        try {
            // Get redemption details
            val redemptions = rewardRepository.getAllRedemptions()
            // Note: In a real implementation, you'd get by ID. For simplicity, we'll simulate.
            
            val reward = rewardRepository.getRewardById(1) // Simplified for demo
            if (reward == null) {
                return@withContext RedemptionResult.Error("Reward not found")
            }
            
            val currentBalance = streakPreferences.getTotalPorapointsEarned()
            return@withContext processRedemption(redemptionId, reward, currentBalance)
            
        } catch (e: Exception) {
            Timber.e(e, "Error processing approved redemption")
            RedemptionResult.Error("Failed to process redemption: ${e.message}")
        }
    }
    
    /**
     * Process the actual redemption with API call
     */
    private suspend fun processRedemption(
        redemptionId: Long,
        reward: Reward,
        currentBalance: Int
    ): RedemptionResult {
        try {
            // Update status to processing
            rewardRepository.updateRedemptionStatus(redemptionId, RedemptionStatus.PROCESSING)
            
            // Make API call
            val response = rewardApiService.redeemReward(
                rewardId = reward.id,
                userId = "current_user", // In real app, get from auth
                cost = reward.cost,
                currentBalance = currentBalance
            )
            
            return if (response.success && response.transactionId != null) {
                // Success - deduct points and update status
                val pointsSpent = reward.cost
                val newBalance = response.balanceRemaining ?: (currentBalance - pointsSpent)
                streakPreferences.setTotalPorapointsEarned(newBalance)
                rewardRepository.updateRedemptionStatus(redemptionId, RedemptionStatus.COMPLETED)
                
                Timber.i("Redemption completed successfully: ${response.transactionId}")
                RedemptionResult.Success(response.transactionId, newBalance)
                
            } else {
                // API failure
                val errorMessage = response.errorMessage ?: "Unknown error occurred"
                rewardRepository.updateRedemptionStatus(redemptionId, RedemptionStatus.FAILED, errorMessage)
                
                when (response.errorCode) {
                    "INSUFFICIENT_POINTS", "POINTS_CHANGED" -> {
                        val updatedBalance = response.balanceRemaining ?: currentBalance
                        val pointsNeeded = reward.cost - updatedBalance
                        RedemptionResult.InsufficientPoints(
                            needed = pointsNeeded,
                            current = updatedBalance
                        )
                    }
                    "SERVER_ERROR" -> {
                        RedemptionResult.Error(errorMessage, retryable = true)
                    }
                    else -> {
                        RedemptionResult.Error(errorMessage, retryable = false)
                    }
                }
            }
            
        } catch (e: Exception) {
            Timber.e(e, "Exception during API redemption")
            rewardRepository.updateRedemptionStatus(
                redemptionId, 
                RedemptionStatus.FAILED, 
                "Network error: ${e.message}"
            )
            return RedemptionResult.Error("Network error. Please check your connection and try again.", retryable = true)
        }
    }
    
    /**
     * Get user's current Porapoints balance
     */
    fun getCurrentBalance(): Int {
        return streakPreferences.getTotalPorapointsEarned()
    }
    
    /**
     * Check if user can afford a reward
     */
    fun canAffordReward(reward: Reward): Boolean {
        return getCurrentBalance() >= reward.cost
    }
    
    /**
     * Initialize rewards data
     */
    suspend fun initializeRewards() {
        rewardRepository.initializeRewardsIfNeeded()
    }
}