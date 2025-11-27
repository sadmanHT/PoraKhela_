package com.porakhela.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.porakhela.data.models.LeaderboardEntry
import com.porakhela.data.models.LeaderboardType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Thread-safe cache implementation for leaderboard data with automatic expiration
 * Supports Weekly, Monthly, and Global leaderboard types with proper lifecycle management
 */
@Singleton
class LeaderboardCache @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        private const val CACHE_PREFS = "leaderboard_cache"
        private const val WEEKLY_DATA_KEY = "weekly_data"
        private const val MONTHLY_DATA_KEY = "monthly_data"
        private const val GLOBAL_DATA_KEY = "global_data"
        private const val WEEKLY_TIMESTAMP_KEY = "weekly_timestamp"
        private const val MONTHLY_TIMESTAMP_KEY = "monthly_timestamp"
        private const val GLOBAL_TIMESTAMP_KEY = "global_timestamp"
        
        // Cache expiration times
        private const val GLOBAL_CACHE_DURATION = 4 * 60 * 60 * 1000L // 4 hours
        private const val WEEKLY_CACHE_DURATION = 24 * 60 * 60 * 1000L // 24 hours
        private const val MONTHLY_CACHE_DURATION = 7 * 24 * 60 * 60 * 1000L // 7 days
    }
    
    private val prefs: SharedPreferences = context.getSharedPreferences(CACHE_PREFS, Context.MODE_PRIVATE)
    private val gson = Gson()
    private val cacheMutex = Mutex() // Thread safety

    /**
     * Cache leaderboard data with thread safety
     */
    suspend fun cacheLeaderboard(type: LeaderboardType, data: List<LeaderboardEntry>) {
        cacheMutex.withLock {
            try {
                val json = gson.toJson(data)
                val timestamp = System.currentTimeMillis()
                
                prefs.edit()
                    .putString(getDataKey(type), json)
                    .putLong(getTimestampKey(type), timestamp)
                    .apply()
                
                Timber.d("Cached ${data.size} entries for $type leaderboard")
            } catch (e: Exception) {
                Timber.e(e, "Failed to cache leaderboard data for $type")
                clearCacheForType(type)
            }
        }
    }
    
    /**
     * Get cached leaderboard data if valid and not expired
     */
    suspend fun getCachedLeaderboard(type: LeaderboardType): List<LeaderboardEntry>? {
        return cacheMutex.withLock {
            try {
                if (!isCacheValid(type)) {
                    clearCacheForType(type)
                    return@withLock null
                }
                
                val json = prefs.getString(getDataKey(type), null) ?: return@withLock null
                val typeToken = object : TypeToken<List<LeaderboardEntry>>() {}.type
                val result = gson.fromJson<List<LeaderboardEntry>>(json, typeToken)
                
                Timber.d("Retrieved ${result?.size ?: 0} cached entries for $type leaderboard")
                result
            } catch (e: Exception) {
                Timber.e(e, "Failed to retrieve cached data for $type")
                clearCacheForType(type)
                null
            }
        }
    }
    
    /**
     * Check if cache is valid and not expired with improved logic
     */
    private fun isCacheValid(type: LeaderboardType): Boolean {
        val timestamp = prefs.getLong(getTimestampKey(type), 0)
        if (timestamp == 0L) return false
        
        val now = System.currentTimeMillis()
        
        return when (type) {
            LeaderboardType.WEEKLY -> {
                // Weekly cache expires on Sunday at midnight OR after 24 hours
                val weekStart = getWeekStart(now)
                val cacheWeekStart = getWeekStart(timestamp)
                val withinTimeLimit = (now - timestamp) < WEEKLY_CACHE_DURATION
                
                weekStart == cacheWeekStart && withinTimeLimit
            }
            LeaderboardType.MONTHLY -> {
                // Monthly cache expires on first day of month OR after 7 days
                val monthStart = getMonthStart(now)
                val cacheMonthStart = getMonthStart(timestamp)
                val withinTimeLimit = (now - timestamp) < MONTHLY_CACHE_DURATION
                
                monthStart == cacheMonthStart && withinTimeLimit
            }
            LeaderboardType.GLOBAL -> {
                // Global cache expires after 4 hours
                (now - timestamp) < GLOBAL_CACHE_DURATION
            }
        }
    }
    
    /**
     * Clear cache for specific type
     */
    private fun clearCacheForType(type: LeaderboardType) {
        prefs.edit()
            .remove(getDataKey(type))
            .remove(getTimestampKey(type))
            .apply()
    }
    
    /**
     * Clear all cached data
     */
    fun clearAllCaches() {
        prefs.edit().clear().apply()
        Timber.d("Cleared all leaderboard caches")
    }
    
    /**
     * Get cache status for debugging
     */
    fun getCacheStatus(): String {
        val weekly = if (isCacheValid(LeaderboardType.WEEKLY)) "Valid" else "Expired"
        val monthly = if (isCacheValid(LeaderboardType.MONTHLY)) "Valid" else "Expired"
        val global = if (isCacheValid(LeaderboardType.GLOBAL)) "Valid" else "Expired"
        
        return "Weekly: $weekly, Monthly: $monthly, Global: $global"
    }
    
    /**
     * Helper methods
     */
    private fun getDataKey(type: LeaderboardType): String {
        return when (type) {
            LeaderboardType.WEEKLY -> WEEKLY_DATA_KEY
            LeaderboardType.MONTHLY -> MONTHLY_DATA_KEY
            LeaderboardType.GLOBAL -> GLOBAL_DATA_KEY
        }
    }
    
    private fun getTimestampKey(type: LeaderboardType): String {
        return when (type) {
            LeaderboardType.WEEKLY -> WEEKLY_TIMESTAMP_KEY
            LeaderboardType.MONTHLY -> MONTHLY_TIMESTAMP_KEY
            LeaderboardType.GLOBAL -> GLOBAL_TIMESTAMP_KEY
        }
    }
    
    private fun getWeekStart(timestamp: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
    
    private fun getMonthStart(timestamp: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}