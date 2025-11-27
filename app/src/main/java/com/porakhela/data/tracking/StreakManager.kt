package com.porakhela.data.tracking

import com.porakhela.data.local.StreakPreferences
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages daily streak logic with strict rules enforcement
 */
@Singleton
class StreakManager @Inject constructor(
    private val streakPreferences: StreakPreferences
) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    /**
     * Call this when app opens to update streak based on usage
     */
    fun onAppOpened() {
        val today = streakPreferences.getTodayDateString()
        val lastActiveDate = streakPreferences.getLastActiveDate()
        
        // Check for date manipulation
        if (streakPreferences.detectTimeManipulation()) {
            streakPreferences.freezeDateUntilTomorrow()
            return
        }
        
        // Skip if date is frozen due to manipulation
        if (streakPreferences.isDateFrozen()) {
            Timber.w("Streak updates frozen due to date manipulation")
            return
        }
        
        // Update last active date
        streakPreferences.setLastActiveDate(today)
        
        // Reset daily data if it's a new day
        if (lastActiveDate != today) {
            handleNewDay(lastActiveDate, today)
        }
        
        Timber.d("App opened, streak check complete")
    }
    
    private fun handleNewDay(lastActiveDate: String?, today: String) {
        streakPreferences.resetDailyData()
        
        if (lastActiveDate != null) {
            val daysBetween = getDaysBetween(lastActiveDate, today)
            val lastLessonDate = streakPreferences.getLastLessonDate()
            
            when {
                daysBetween == 1 -> {
                    // Yesterday - check if lesson was completed
                    if (lastLessonDate != lastActiveDate) {
                        // No lesson completed yesterday - reset streak to 0
                        resetStreak("No lesson completed on $lastActiveDate")
                    }
                    // If lesson was completed yesterday, streak continues (no change)
                }
                daysBetween > 1 -> {
                    // Missed more than one day - reset streak to 0
                    resetStreak("Missed $daysBetween days")
                }
                daysBetween == 0 -> {
                    // Same day - shouldn't happen, log warning
                    Timber.w("Same day transition detected: $lastActiveDate")
                }
            }
        }
        
        Timber.d("Handled new day transition: $lastActiveDate -> $today")
    }
    
    /**
     * Call this when a lesson is completed
     */
    fun onLessonCompleted() {
        val today = streakPreferences.getTodayDateString()
        
        // Skip if date is frozen
        if (streakPreferences.isDateFrozen()) {
            Timber.w("Lesson completion ignored - date frozen")
            return
        }
        
        // Increment lesson count for today
        streakPreferences.incrementLessonCountToday()
        
        // Check if this is the first lesson today
        val lastLessonDate = streakPreferences.getLastLessonDate()
        if (lastLessonDate != today) {
            // First lesson of the day - increment streak
            val currentStreak = streakPreferences.getCurrentStreak()
            streakPreferences.setCurrentStreak(currentStreak + 1)
            streakPreferences.setLastLessonDate(today)
            
            Timber.d("First lesson of day completed - streak increased to ${currentStreak + 1}")
        } else {
            Timber.d("Additional lesson completed today")
        }
    }
    
    private fun resetStreak(reason: String) {
        streakPreferences.setCurrentStreak(0)
        Timber.d("Streak reset: $reason")
    }
    
    private fun getDaysBetween(startDate: String, endDate: String): Int {
        return try {
            val start = dateFormat.parse(startDate)?.time ?: return 0
            val end = dateFormat.parse(endDate)?.time ?: return 0
            ((end - start) / (24 * 60 * 60 * 1000)).toInt()
        } catch (e: Exception) {
            Timber.e(e, "Error calculating days between dates")
            0
        }
    }
    
    /**
     * Get current streak information
     */
    fun getStreakInfo(): StreakInfo {
        return StreakInfo(
            currentStreak = streakPreferences.getCurrentStreak(),
            maxStreak = streakPreferences.getMaxStreakAchieved(),
            lessonsToday = streakPreferences.getLessonCountToday(),
            timeToday = streakPreferences.getTotalTimeToday(),
            isDateFrozen = streakPreferences.isDateFrozen()
        )
    }
    
    /**
     * Check if user needs to complete a lesson to maintain streak
     */
    fun needsLessonToMaintainStreak(): Boolean {
        val today = streakPreferences.getTodayDateString()
        val lastLessonDate = streakPreferences.getLastLessonDate()
        val currentStreak = streakPreferences.getCurrentStreak()
        
        return currentStreak > 0 && lastLessonDate != today
    }
}

data class StreakInfo(
    val currentStreak: Int,
    val maxStreak: Int,
    val lessonsToday: Int,
    val timeToday: Long,
    val isDateFrozen: Boolean
)