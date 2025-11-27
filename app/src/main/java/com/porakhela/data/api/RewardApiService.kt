package com.porakhela.data.api

import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * Mock API service for reward redemptions
 * Simulates real API calls with various response scenarios
 */
@Singleton
class RewardApiService @Inject constructor() {
    
    companion object {
        private const val API_DELAY_MS = 2000L // Simulate network delay
        private const val SUCCESS_RATE = 0.7f // 70% success rate
        private const val INSUFFICIENT_POINTS_RATE = 0.2f // 20% insufficient points
        private const val SERVER_ERROR_RATE = 0.1f // 10% server error
    }
    
    /**
     * Mock API response for reward redemption
     */
    data class RedemptionResponse(
        val success: Boolean,
        val transactionId: String?,
        val errorCode: String?,
        val errorMessage: String?,
        val balanceRemaining: Int?
    )
    
    /**
     * Simulate API call to redeem a reward
     */
    suspend fun redeemReward(
        rewardId: Int,
        userId: String,
        cost: Int,
        currentBalance: Int
    ): RedemptionResponse {
        Timber.d("API: Attempting to redeem reward $rewardId for user $userId, cost: $cost")
        
        // Simulate network delay
        delay(API_DELAY_MS)
        
        // Check if user has sufficient points
        if (currentBalance < cost) {
            Timber.w("API: Insufficient points - need $cost, have $currentBalance")
            return RedemptionResponse(
                success = false,
                transactionId = null,
                errorCode = "INSUFFICIENT_POINTS",
                errorMessage = "You need ${cost - currentBalance} more Porapoints to redeem this reward",
                balanceRemaining = currentBalance
            )
        }
        
        // Simulate random API responses
        val randomValue = Random.nextFloat()
        
        return when {
            randomValue < SUCCESS_RATE -> {
                val transactionId = generateTransactionId()
                val newBalance = currentBalance - cost
                Timber.i("API: Redemption successful - Transaction ID: $transactionId, New balance: $newBalance")
                RedemptionResponse(
                    success = true,
                    transactionId = transactionId,
                    errorCode = null,
                    errorMessage = null,
                    balanceRemaining = newBalance
                )
            }
            
            randomValue < SUCCESS_RATE + INSUFFICIENT_POINTS_RATE -> {
                // Simulate edge case where points were spent elsewhere
                Timber.w("API: Points were spent elsewhere during transaction")
                RedemptionResponse(
                    success = false,
                    transactionId = null,
                    errorCode = "POINTS_CHANGED",
                    errorMessage = "Your Porapoints balance changed during the transaction. Please try again.",
                    balanceRemaining = currentBalance - Random.nextInt(1, cost)
                )
            }
            
            else -> {
                // Server error
                val errorMessages = listOf(
                    "Service temporarily unavailable. Please try again later.",
                    "Unable to process redemption. Please contact support.",
                    "Network timeout. Please check your connection and try again.",
                    "Redemption service is under maintenance."
                )
                val errorMessage = errorMessages.random()
                Timber.e("API: Server error - $errorMessage")
                RedemptionResponse(
                    success = false,
                    transactionId = null,
                    errorCode = "SERVER_ERROR",
                    errorMessage = errorMessage,
                    balanceRemaining = currentBalance
                )
            }
        }
    }
    
    /**
     * Check redemption status (for polling)
     */
    suspend fun checkRedemptionStatus(transactionId: String): RedemptionResponse {
        delay(1000L) // Shorter delay for status checks
        
        // Most transactions complete successfully
        val success = Random.nextFloat() < 0.95f
        
        return if (success) {
            RedemptionResponse(
                success = true,
                transactionId = transactionId,
                errorCode = null,
                errorMessage = "Redemption completed successfully",
                balanceRemaining = null // Not relevant for status check
            )
        } else {
            RedemptionResponse(
                success = false,
                transactionId = transactionId,
                errorCode = "PROCESSING_FAILED",
                errorMessage = "Redemption processing failed. Your points have been refunded.",
                balanceRemaining = null
            )
        }
    }
    
    /**
     * Generate a mock transaction ID
     */
    private fun generateTransactionId(): String {
        val prefix = "TXN"
        val timestamp = System.currentTimeMillis().toString().takeLast(8)
        val randomSuffix = Random.nextInt(1000, 9999)
        return "$prefix$timestamp$randomSuffix"
    }
    
    /**
     * Simulate getting user's current point balance from server
     */
    suspend fun getCurrentBalance(userId: String): Int {
        delay(500L)
        // This would normally fetch from server, but we'll return a mock value
        return Random.nextInt(100, 5000)
    }
}