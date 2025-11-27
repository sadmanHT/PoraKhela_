package com.porakhela.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a reward that can be redeemed with Porapoints
 */
@Entity(tableName = "rewards")
data class Reward(
    @PrimaryKey
    val id: Int,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "cost")
    val cost: Int,
    
    @ColumnInfo(name = "description")
    val description: String? = null,
    
    @ColumnInfo(name = "icon_resource")
    val iconResource: String? = null,
    
    @ColumnInfo(name = "category")
    val category: String = "general",
    
    @ColumnInfo(name = "approval_required")
    val approvalRequired: Boolean = true,
    
    @ColumnInfo(name = "is_available")
    val isAvailable: Boolean = true
)

/**
 * Data class representing a reward redemption transaction
 */
@Entity(
    tableName = "redemptions",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Reward::class,
            parentColumns = ["id"],
            childColumns = ["rewardId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class RewardRedemption(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val rewardId: Int,
    val rewardName: String,
    val cost: Int,
    val status: RedemptionStatus,
    val requestedAt: Long = System.currentTimeMillis(),
    val approvedAt: Long? = null,
    val redeemedAt: Long? = null,
    val parentApprovalRequired: Boolean = true,
    val apiTransactionId: String? = null,
    val errorMessage: String? = null
)

/**
 * Status of a reward redemption
 */
enum class RedemptionStatus {
    PENDING_APPROVAL,     // Waiting for parent approval
    APPROVED,             // Parent approved, ready for API call
    PROCESSING,           // API call in progress
    COMPLETED,            // Successfully redeemed
    FAILED,               // API call failed
    REJECTED,             // Parent rejected
    CANCELLED             // User cancelled
}