package com.porakhela.data.local.dao

import androidx.room.*
import com.porakhela.data.model.Reward
import com.porakhela.data.model.RewardRedemption
import com.porakhela.data.model.RedemptionStatus
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for reward-related operations
 */
@Dao
interface RewardDao {
    
    // Reward operations
    @Query("SELECT * FROM rewards WHERE is_available = 1 ORDER BY cost ASC")
    fun getAllAvailableRewards(): Flow<List<Reward>>
    
    @Query("SELECT * FROM rewards WHERE id = :rewardId")
    suspend fun getRewardById(rewardId: Int): Reward?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRewards(rewards: List<Reward>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReward(reward: Reward)
    
    @Update
    suspend fun updateReward(reward: Reward)
    
    @Delete
    suspend fun deleteReward(reward: Reward)
    
    @Query("DELETE FROM rewards")
    suspend fun clearAllRewards()
    
    // Redemption operations
    @Query("SELECT * FROM redemptions ORDER BY requestedAt DESC")
    fun getAllRedemptions(): Flow<List<RewardRedemption>>
    
    @Query("SELECT * FROM redemptions WHERE status = :status ORDER BY requestedAt DESC")
    fun getRedemptionsByStatus(status: RedemptionStatus): Flow<List<RewardRedemption>>
    
    @Query("SELECT * FROM redemptions WHERE status = 'PENDING_APPROVAL' ORDER BY requestedAt ASC")
    fun getPendingApprovalRedemptions(): Flow<List<RewardRedemption>>
    
    @Query("SELECT * FROM redemptions WHERE id = :redemptionId")
    suspend fun getRedemptionById(redemptionId: Long): RewardRedemption?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRedemption(redemption: RewardRedemption): Long
    
    @Update
    suspend fun updateRedemption(redemption: RewardRedemption)
    
    @Delete
    suspend fun deleteRedemption(redemption: RewardRedemption)
    
    @Query("SELECT COUNT(*) FROM redemptions WHERE status IN ('PENDING_APPROVAL', 'APPROVED', 'PROCESSING')")
    fun getPendingRedemptionsCount(): Flow<Int>
    
    @Query("""
        SELECT * FROM redemptions 
        WHERE requestedAt >= :startTime AND requestedAt <= :endTime 
        ORDER BY requestedAt DESC
    """)
    suspend fun getRedemptionsInTimeRange(startTime: Long, endTime: Long): List<RewardRedemption>
    
    // Combined queries with reward details
    @Query("""
        SELECT r.* 
        FROM rewards r 
        INNER JOIN redemptions rd ON r.id = rd.rewardId 
        WHERE rd.status = :status 
        ORDER BY rd.requestedAt DESC
    """)
    suspend fun getRewardsWithRedemptionStatus(status: RedemptionStatus): List<Reward>
    
    // ================================
    // USSD SIMULATION SUPPORT QUERIES
    // ================================
    
    @Query("SELECT * FROM redemptions WHERE status = 'PENDING_APPROVAL' ORDER BY requestedAt ASC")
    suspend fun getPendingRedemptionsForUSSD(): List<RewardRedemption>
    
    @Query("SELECT * FROM redemptions WHERE rewardId = :rewardId")
    suspend fun getRedemptionsByRewardId(rewardId: Int): List<RewardRedemption>
}