package com.porakhela.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.porakhela.data.models.LeaderboardEntry
import com.porakhela.data.models.LeaderboardType
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages leaderboard caching with automatic expiration
 * Handles weekly (reset Sunday 12:00 AM) and monthly (reset first day of month) cache lifecycle
 */
@Singleton
class LeaderboardCache @Inject constructor(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    
    companion object {
        private const val PREFS_NAME = "leaderboard_cache"
        private const val KEY_WEEKLY_CACHE = "leaderboard_weekly_cache"
        private const val KEY_MONTHLY_CACHE = "leaderboard_monthly_cache"
        private const val KEY_GLOBAL_CACHE = "leaderboard_global_cache"
        private const val KEY_WEEKLY_LAST_UPDATE = "weekly_last_update"
        private const val KEY_MONTHLY_LAST_UPDATE = "monthly_last_update"
        private const val KEY_GLOBAL_LAST_UPDATE = "global_last_update"
    }
    
    /**
     * Cache leaderboard data with automatic expiration handling
     */
    fun cacheLeaderboard(type: LeaderboardType, entries: List<LeaderboardEntry>) {
        val (cacheKey, updateKey) = getKeys(type)
        val currentTime = System.currentTimeMillis()
        
        try {
            val json = gson.toJson(entries)
            prefs.edit()
                .putString(cacheKey, json)
                .putLong(updateKey, currentTime)
                .apply()
            
            Timber.d("Cached ${entries.size} entries for $type leaderboard")
        } catch (e: Exception) {
            Timber.e(e, "Failed to cache leaderboard data for $type")
        }
    }
    
    /**
     * Get cached leaderboard data if not expired
     */
    fun getCachedLeaderboard(type: LeaderboardType): List<LeaderboardEntry>? {
        if (isCacheExpired(type)) {
            clearCache(type)
            return null
        }
        
        val (cacheKey, _) = getKeys(type)
        val json = prefs.getString(cacheKey, null) ?: return null
        
        return try {
            val listType = object : TypeToken<List<LeaderboardEntry>>() {}.type
            val entries: List<LeaderboardEntry> = gson.fromJson(json, listType)
            Timber.d("Retrieved ${entries.size} cached entries for $type leaderboard")
            entries
        } catch (e: Exception) {
            Timber.e(e, "Failed to parse cached leaderboard data for $type")
            clearCache(type)
            null
        }
    }
    
    /**
     * Check if cache is expired based on leaderboard type
     */
    fun isCacheExpired(type: LeaderboardType): Boolean {
        val (_, updateKey) = getKeys(type)
        val lastUpdate = prefs.getLong(updateKey, 0)
        
        if (lastUpdate == 0L) return true
        
        val lastUpdateCalendar = Calendar.getInstance().apply {
            timeInMillis = lastUpdate
        }
        
        return when (type) {
            LeaderboardType.WEEKLY -> isWeeklyExpired(lastUpdateCalendar)
            LeaderboardType.MONTHLY -> isMonthlyExpired(lastUpdateCalendar)
            LeaderboardType.GLOBAL -> isGlobalExpired(lastUpdateCalendar)
        }
    }
    
    /**
     * Clear cache for specific leaderboard type
     */
    fun clearCache(type: LeaderboardType) {
        val (cacheKey, updateKey) = getKeys(type)
        prefs.edit()
            .remove(cacheKey)
            .remove(updateKey)
            .apply()
        
        Timber.d("Cleared cache for $type leaderboard")
    }
    
    /**
     * Clear all leaderboard caches
     */
    fun clearAllCaches() {
        prefs.edit().clear().apply()
        Timber.d("Cleared all leaderboard caches")
    }
    
    /**
     * Get cache keys for leaderboard type
     */
    private fun getKeys(type: LeaderboardType): Pair<String, String> {
        return when (type) {
            LeaderboardType.WEEKLY -> KEY_WEEKLY_CACHE to KEY_WEEKLY_LAST_UPDATE
            LeaderboardType.MONTHLY -> KEY_MONTHLY_CACHE to KEY_MONTHLY_LAST_UPDATE
            LeaderboardType.GLOBAL -> KEY_GLOBAL_CACHE to KEY_GLOBAL_LAST_UPDATE
        }
    }
    
    /**
     * Check if weekly cache is expired (reset Sunday 12:00 AM)
     */
    private fun isWeeklyExpired(lastUpdate: Calendar): Boolean {
        val now = Calendar.getInstance()
        val nextSunday = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            
            // If today is Sunday and it's past midnight, move to next Sunday
            if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && now.timeInMillis > this.timeInMillis) {
                add(Calendar.WEEK_OF_YEAR, 1)
            }
            
            // If we're past Sunday, move to next Sunday
            if (now.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                add(Calendar.WEEK_OF_YEAR, 1)
            }
        }
        
        return now.timeInMillis >= nextSunday.timeInMillis || lastUpdate.timeInMillis < getLastSundayMidnight()
    }
    
    /**
     * Check if monthly cache is expired (reset first day of month)
     */
    private fun isMonthlyExpired(lastUpdate: Calendar): Boolean {
        val now = Calendar.getInstance()
        val firstOfThisMonth = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        
        return lastUpdate.timeInMillis < firstOfThisMonth.timeInMillis
    }
    
    /**
     * Check if global cache is expired (refresh every 4 hours)
     */
    private fun isGlobalExpired(lastUpdate: Calendar): Boolean {
        val now = Calendar.getInstance()
        val fourHoursAgo = Calendar.getInstance().apply {
            add(Calendar.HOUR_OF_DAY, -4)
        }
        
        return lastUpdate.timeInMillis < fourHoursAgo.timeInMillis
    }
    
    /**
     * Get last Sunday midnight timestamp
     */
    private fun getLastSundayMidnight(): Long {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            
            // If today is not Sunday, go back to last Sunday
            val today = Calendar.getInstance()
            if (today.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                add(Calendar.WEEK_OF_YEAR, -1)
            }
        }
        
        return calendar.timeInMillis
    }
    
    /**
     * Get cache status information for debugging
     */
    fun getCacheStatus(): String {
        val weekly = if (isCacheExpired(LeaderboardType.WEEKLY)) "EXPIRED" else "VALID"
        val monthly = if (isCacheExpired(LeaderboardType.MONTHLY)) "EXPIRED" else "VALID"
        val global = if (isCacheExpired(LeaderboardType.GLOBAL)) "EXPIRED" else "VALID"
        
        return "Weekly: $weekly, Monthly: $monthly, Global: $global"
    }
}