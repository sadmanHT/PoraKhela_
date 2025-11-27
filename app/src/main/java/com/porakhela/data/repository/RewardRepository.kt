package com.porakhela.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.porakhela.data.local.dao.RewardDao
import com.porakhela.data.model.Reward
import com.porakhela.data.model.RewardRedemption
import com.porakhela.data.model.RedemptionStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing reward data from local JSON and database
 */
@Singleton
class RewardRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val rewardDao: RewardDao
) {
    
    private val gson = Gson()
    
    companion object {
        private const val REWARDS_JSON_FILE = "rewards.json"
    }
    
    /**
     * Load rewards from JSON file and save to database
     */
    suspend fun loadRewardsFromAssets(): Result<List<Reward>> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open(REWARDS_JSON_FILE).bufferedReader().use { it.readText() }
            val listType = object : TypeToken<List<Reward>>() {}.type
            val rewards = gson.fromJson<List<Reward>>(jsonString, listType)
            
            // Save to database
            rewardDao.insertRewards(rewards)
            Timber.d("Loaded ${rewards.size} rewards from assets and saved to database")
            
            Result.success(rewards)
        } catch (e: IOException) {
            Timber.e(e, "Failed to load rewards from assets")
            Result.failure(e)
        } catch (e: Exception) {
            Timber.e(e, "Failed to parse rewards JSON")
            Result.failure(e)
        }
    }
    
    /**
     * Get all available rewards from database
     */
    fun getAllAvailableRewards(): Flow<List<Reward>> {
        return rewardDao.getAllAvailableRewards()
    }
    
    /**
     * Get reward by ID
     */
    suspend fun getRewardById(rewardId: Int): Reward? {
        return rewardDao.getRewardById(rewardId)
    }
    
    /**
     * Initialize rewards data if database is empty
     */
    suspend fun initializeRewardsIfNeeded() {
        try {
            val existingRewards = rewardDao.getAllAvailableRewards()
            // Note: This is a simplified check - in production, you might want to check if rewards are empty
            loadRewardsFromAssets()
            Timber.d("Rewards initialized successfully")
        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize rewards")
        }
    }
    
    /**
     * Create a new reward redemption
     */
    suspend fun createRedemption(reward: Reward): Result<Long> = withContext(Dispatchers.IO) {
        try {
            val redemption = RewardRedemption(
                rewardId = reward.id,
                rewardName = reward.name,
                cost = reward.cost,
                status = if (reward.approvalRequired) RedemptionStatus.PENDING_APPROVAL else RedemptionStatus.APPROVED,
                parentApprovalRequired = reward.approvalRequired
            )
            
            val redemptionId = rewardDao.insertRedemption(redemption)
            Timber.i("Created redemption with ID: $redemptionId for reward: ${reward.name}")
            
            Result.success(redemptionId)
        } catch (e: Exception) {
            Timber.e(e, "Failed to create redemption for reward: ${reward.name}")
            Result.failure(e)
        }
    }
    
    /**
     * Update redemption status
     */
    suspend fun updateRedemptionStatus(redemptionId: Long, status: RedemptionStatus, errorMessage: String? = null) {
        try {
            val redemption = rewardDao.getRedemptionById(redemptionId)
            redemption?.let {
                val updatedRedemption = it.copy(
                    status = status,
                    errorMessage = errorMessage,
                    approvedAt = if (status == RedemptionStatus.APPROVED) System.currentTimeMillis() else it.approvedAt,
                    redeemedAt = if (status == RedemptionStatus.COMPLETED) System.currentTimeMillis() else it.redeemedAt
                )
                rewardDao.updateRedemption(updatedRedemption)
                Timber.d("Updated redemption $redemptionId status to $status")
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to update redemption status")
        }
    }
    
    /**
     * Get all redemptions
     */
    fun getAllRedemptions(): Flow<List<RewardRedemption>> {
        return rewardDao.getAllRedemptions()
    }
    
    /**
     * Get redemptions by status
     */
    fun getRedemptionsByStatus(status: RedemptionStatus): Flow<List<RewardRedemption>> {
        return rewardDao.getRedemptionsByStatus(status)
    }
    
    /**
     * Get pending approval redemptions for parent dashboard
     */
    fun getPendingApprovalRedemptions(): Flow<List<RewardRedemption>> {
        return rewardDao.getPendingApprovalRedemptions()
    }
    
    /**
     * Get count of pending redemptions
     */
    fun getPendingRedemptionsCount(): Flow<Int> {
        return rewardDao.getPendingRedemptionsCount()
    }
    
    /**
     * Approve a redemption (parent action)
     */
    suspend fun approveRedemption(redemptionId: Long) {
        updateRedemptionStatus(redemptionId, RedemptionStatus.APPROVED)
        Timber.i("Redemption $redemptionId approved by parent")
    }
    
    /**
     * Reject a redemption (parent action)
     */
    suspend fun rejectRedemption(redemptionId: Long, reason: String? = null) {
        updateRedemptionStatus(redemptionId, RedemptionStatus.REJECTED, reason)
        Timber.i("Redemption $redemptionId rejected by parent: $reason")
    }
    
    // ================================
    // USSD SIMULATION SUPPORT METHODS
    // ================================
    
    /**
     * Get pending redemptions for USSD display
     */
    suspend fun getPendingRedemptions(): List<RewardRedemption> = withContext(Dispatchers.IO) {
        try {
            rewardDao.getPendingRedemptionsForUSSD()
        } catch (e: Exception) {
            Timber.e(e, "Failed to get pending redemptions for USSD")
            emptyList()
        }
    }
    
    /**
     * Get rewards awaiting parent approval for USSD
     */
    suspend fun getRewardsAwaitingApproval(): List<Reward> = withContext(Dispatchers.IO) {
        try {
            val pendingRedemptions = rewardDao.getPendingRedemptionsForUSSD()
            val rewardIds = pendingRedemptions.map { it.rewardId }
            
            val rewards = mutableListOf<Reward>()
            rewardIds.forEach { rewardId ->
                rewardDao.getRewardById(rewardId)?.let { reward ->
                    rewards.add(reward)
                }
            }
            
            Timber.d("Found ${rewards.size} rewards awaiting approval for USSD")
            rewards
        } catch (e: Exception) {
            Timber.e(e, "Failed to get rewards awaiting approval for USSD")
            emptyList()
        }
    }
    
    /**
     * Approve reward by ID for USSD
     */
    suspend fun approveReward(rewardId: String) = withContext(Dispatchers.IO) {
        try {
            val redemptions = rewardDao.getRedemptionsByRewardId(rewardId.toIntOrNull() ?: 0)
            redemptions.forEach { redemption ->
                if (redemption.status == RedemptionStatus.PENDING_APPROVAL) {
                    updateRedemptionStatus(redemption.id, RedemptionStatus.APPROVED)
                    Timber.i("USSD: Approved reward $rewardId")
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to approve reward $rewardId via USSD")
            throw e
        }
    }
    
    /**
     * Deny reward by ID for USSD
     */
    suspend fun denyReward(rewardId: String) = withContext(Dispatchers.IO) {
        try {
            val redemptions = rewardDao.getRedemptionsByRewardId(rewardId.toIntOrNull() ?: 0)
            redemptions.forEach { redemption ->
                if (redemption.status == RedemptionStatus.PENDING_APPROVAL) {
                    updateRedemptionStatus(redemption.id, RedemptionStatus.REJECTED, "Denied via USSD")
                    Timber.i("USSD: Denied reward $rewardId")
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to deny reward $rewardId via USSD")
            throw e
        }
    }
}