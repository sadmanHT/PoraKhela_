package com.porakhela.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Data model for leaderboard entries
 * Represents a child's ranking in the leaderboard system
 */
@Entity(tableName = "leaderboard_entries")
data class LeaderboardEntry(
    @PrimaryKey
    val childName: String,
    val points: Int,
    val rank: Int = 0,
    val avatar: String = "default",
    val isCurrentUser: Boolean = false,
    val weeklyPoints: Int = 0,
    val monthlyPoints: Int = 0,
    val globalPoints: Int = points,
    val lastUpdated: Date = Date(),
    val previousRank: Int = rank,
    val rankChange: Int = 0 // Positive = moved up, Negative = moved down
) {
    /**
     * Get rank change type for animations
     */
    fun getRankChangeType(): RankChangeType {
        return when {
            rankChange > 0 -> RankChangeType.UP
            rankChange < 0 -> RankChangeType.DOWN
            else -> RankChangeType.NONE
        }
    }
    
    /**
     * Check if this entry is in top 3
     */
    fun isTopThree(): Boolean = rank <= 3
    
    /**
     * Get trophy type for top 3 entries
     */
    fun getTrophyType(): TrophyType? {
        return when (rank) {
            1 -> TrophyType.GOLD
            2 -> TrophyType.SILVER
            3 -> TrophyType.BRONZE
            else -> null
        }
    }
}

enum class RankChangeType {
    UP, DOWN, NONE
}

enum class TrophyType {
    GOLD, SILVER, BRONZE
}

enum class LeaderboardType {
    WEEKLY, MONTHLY, GLOBAL
}