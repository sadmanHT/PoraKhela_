package com.porakhela.data.repository

import com.porakhela.data.local.LeaderboardCache
import com.porakhela.data.local.UserPreferences
import com.porakhela.data.local.UserStats
import com.porakhela.data.models.LeaderboardEntry
import com.porakhela.data.models.LeaderboardType
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * Repository for leaderboard data management
 * Handles mock data generation, caching, and real user data integration
 */
@Singleton
class LeaderboardRepository @Inject constructor(
    private val leaderboardCache: LeaderboardCache,
    private val userPreferences: UserPreferences
) {
    
    companion object {
        // Mock data for realistic leaderboard simulation
        private val MOCK_NAMES = listOf(
            "Alex", "Emma", "Liam", "Sofia", "Noah", "Mia", "Ethan", "Ava",
            "Lucas", "Isabella", "Mason", "Olivia", "Logan", "Amelia", "Jacob",
            "Harper", "Sebastian", "Charlotte", "Jack", "Sophia", "Aiden", "Luna",
            "Owen", "Ella", "Samuel", "Grace", "Henry", "Chloe", "Wyatt", "Zoe"
        )
        
        private val AVATARS = listOf(
            "avatar_1", "avatar_2", "avatar_3", "avatar_4", "avatar_5",
            "avatar_6", "avatar_7", "avatar_8", "avatar_9", "avatar_10"
        )
        
        private const val LEADERBOARD_SIZE = 50
    }
    
    /**
     * Get leaderboard data with thread-safe caching and refresh mechanism
     */
    suspend fun getLeaderboard(type: LeaderboardType, forceRefresh: Boolean = false): List<LeaderboardEntry> {
        // Check cache first if not forcing refresh
        if (!forceRefresh) {
            val cachedData = leaderboardCache.getCachedLeaderboard(type)
            if (cachedData != null && cachedData.isNotEmpty()) {
                Timber.d("Returning cached leaderboard for $type with ${cachedData.size} entries")
                return cachedData
            }
        }
        
        // Simulate realistic network delay
        delay(800 + Random.nextLong(400))
        
        // Generate fresh data
        val leaderboardData = generateLeaderboardData(type)
        
        // Cache the data with thread safety
        if (leaderboardData.isNotEmpty()) {
            leaderboardCache.cacheLeaderboard(type, leaderboardData)
        }
        
        Timber.d("Generated and cached fresh leaderboard for $type with ${leaderboardData.size} entries")
        return leaderboardData
    }
    
    /**
     * Refresh specific leaderboard type
     */
    suspend fun refreshLeaderboard(type: LeaderboardType): List<LeaderboardEntry> {
        return getLeaderboard(type, forceRefresh = true)
    }
    
    /**
     * Get current user's rank in leaderboard
     */
    suspend fun getCurrentUserRank(type: LeaderboardType): Int? {
        val leaderboard = getLeaderboard(type)
        return leaderboard.find { it.isCurrentUser }?.rank
    }
    
    /**
     * Generate mock leaderboard data with real user integration
     */
    private fun generateLeaderboardData(type: LeaderboardType): List<LeaderboardEntry> {
        val currentUserStats = userPreferences.getUserStats()
        val currentUserName = currentUserStats.childName ?: "You"
        val currentUserAvatar = userPreferences.getSelectedAvatar() ?: "avatar_1"
        
        // Generate mock entries
        val mockEntries = mutableListOf<LeaderboardEntry>()
        
        // Add other players with realistic score distribution
        val usedNames = mutableSetOf<String>()
        usedNames.add(currentUserName)
        
        for (i in 0 until LEADERBOARD_SIZE - 1) {
            val name = generateUniqueName(usedNames)
            val avatar = AVATARS.random()
            
            val points = generateRealisticPoints(type, i)
            
            mockEntries.add(
                LeaderboardEntry(
                    childName = name,
                    points = points,
                    avatar = avatar,
                    isCurrentUser = false,
                    weeklyPoints = if (type == LeaderboardType.WEEKLY) points else Random.nextInt(0, points),
                    monthlyPoints = if (type == LeaderboardType.MONTHLY) points else Random.nextInt(points / 2, points),
                    globalPoints = if (type == LeaderboardType.GLOBAL) points else points + Random.nextInt(100, 500)
                )
            )
        }
        
        // Add current user
        val currentUserPoints = getCurrentUserPoints(type, currentUserStats)
        mockEntries.add(
            LeaderboardEntry(
                childName = currentUserName,
                points = currentUserPoints,
                avatar = currentUserAvatar,
                isCurrentUser = true,
                weeklyPoints = if (type == LeaderboardType.WEEKLY) currentUserPoints else Random.nextInt(0, currentUserPoints),
                monthlyPoints = if (type == LeaderboardType.MONTHLY) currentUserPoints else Random.nextInt(currentUserPoints / 2, currentUserPoints),
                globalPoints = currentUserStats.porapoints
            )
        )
        
        // Sort by points (descending) and assign ranks with proper tie-breaking
        val sortedEntries = mockEntries.sortedWith(
            compareByDescending<LeaderboardEntry> { it.points }
                .thenBy { it.childName } // Tie-breaker: alphabetical order
        )
        
        // Assign ranks properly, handling ties
        var currentRank = 1
        var previousPoints = Int.MAX_VALUE
        var sameRankCount = 0
        
        return sortedEntries.mapIndexed { index, entry ->
            if (entry.points != previousPoints) {
                currentRank = index + 1
                sameRankCount = 0
            } else {
                sameRankCount++
            }
            previousPoints = entry.points
            
            val previousRank = if (forceRefresh) {
                val change = Random.nextInt(-2, 3)
                (currentRank + change).coerceAtLeast(1).coerceAtMost(LEADERBOARD_SIZE)
            } else currentRank
            
            entry.copy(
                rank = currentRank,
                previousRank = previousRank,
                rankChange = previousRank - currentRank
            )
        }
    }
    
    /**
     * Generate unique name for mock entry
     */
    private fun generateUniqueName(usedNames: MutableSet<String>): String {
        var name: String
        do {
            name = MOCK_NAMES.random()
        } while (usedNames.contains(name))
        
        usedNames.add(name)
        return name
    }
    
    /**
     * Generate realistic points based on leaderboard type and position
     */
    private fun generateRealisticPoints(type: LeaderboardType, position: Int): Int {
        val basePoints = when (type) {
            LeaderboardType.WEEKLY -> {
                // Top players: 500-1000, decreasing exponentially
                when {
                    position < 5 -> Random.nextInt(800, 1200)
                    position < 15 -> Random.nextInt(400, 800)
                    position < 30 -> Random.nextInt(150, 400)
                    else -> Random.nextInt(50, 200)
                }
            }
            LeaderboardType.MONTHLY -> {
                // Higher scores for monthly
                when {
                    position < 5 -> Random.nextInt(2000, 3500)
                    position < 15 -> Random.nextInt(1200, 2000)
                    position < 30 -> Random.nextInt(600, 1200)
                    else -> Random.nextInt(200, 700)
                }
            }
            LeaderboardType.GLOBAL -> {
                // Highest scores for global
                when {
                    position < 5 -> Random.nextInt(5000, 8000)
                    position < 15 -> Random.nextInt(3000, 5000)
                    position < 30 -> Random.nextInt(1500, 3000)
                    else -> Random.nextInt(500, 1800)
                }
            }
        }
        
        // Add some randomness
        return (basePoints * (0.8 + Random.nextDouble(0.4))).toInt()
    }
    
    /**
     * Get current user's points for specific leaderboard type
     */
    private fun getCurrentUserPoints(type: LeaderboardType, userStats: UserStats): Int {
        return when (type) {
            LeaderboardType.WEEKLY -> {
                // Simulate weekly points based on recent activity
                if (userStats.wasActiveToday) {
                    (userStats.porapoints * 0.3 + Random.nextInt(50, 200)).toInt()
                } else {
                    (userStats.porapoints * 0.1 + Random.nextInt(10, 100)).toInt()
                }
            }
            LeaderboardType.MONTHLY -> {
                // Monthly points as percentage of total
                (userStats.porapoints * 0.6 + Random.nextInt(100, 300)).toInt()
            }
            LeaderboardType.GLOBAL -> {
                // Global is total points
                userStats.porapoints
            }
        }
    }
    
    /**
     * Clear all cached data (for testing/reset)
     */
    fun clearAllCaches() {
        leaderboardCache.clearAllCaches()
    }
    
    /**
     * Get cache status for debugging
     */
    fun getCacheStatus(): String {
        return leaderboardCache.getCacheStatus()
    }
    
    private var forceRefresh = false
    
    /**
     * Simulate pull-to-refresh behavior
     */
    suspend fun simulatePullToRefresh(type: LeaderboardType): List<LeaderboardEntry> {
        forceRefresh = true
        val result = refreshLeaderboard(type)
        forceRefresh = false
        return result
    }
}