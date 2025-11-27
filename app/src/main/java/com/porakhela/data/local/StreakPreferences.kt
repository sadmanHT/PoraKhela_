package com.porakhela.data.local

import android.content.Context
import android.content.SharedPreferences
import android.os.SystemClock
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages streak data persistence with anti-tampering protection
 */
@Singleton
class StreakPreferences @Inject constructor(
    context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "streak_data", Context.MODE_PRIVATE
    )
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    companion object {
        private const val KEY_LAST_ACTIVE_DATE = "last_active_date"
        private const val KEY_CURRENT_STREAK = "current_streak"
        private const val KEY_TOTAL_TIME_TODAY = "total_time_today"
        private const val KEY_LESSON_COUNT_TODAY = "lesson_count_today"
        private const val KEY_LAST_LESSON_DATE = "last_lesson_date"
        private const val KEY_BASELINE_MONOTONIC = "baseline_monotonic"
        private const val KEY_BASELINE_REAL_TIME = "baseline_real_time"
        private const val KEY_DATE_FROZEN_UNTIL = "date_frozen_until"
        private const val KEY_MAX_STREAK_ACHIEVED = "max_streak_achieved"
        private const val KEY_TOTAL_PORAPOINTS_EARNED = "total_porapoints_earned"
        private const val KEY_PARENT_PIN = "parent_pin"
        
        private const val MAX_STREAK_DAYS = 365
        private const val DEFAULT_PARENT_PIN = "1234"
    }
    
    // Initialize baseline for anti-tampering
    init {
        if (!hasBaseline()) {
            establishBaseline()
        }
    }
    
    private fun establishBaseline() {
        val currentTime = System.currentTimeMillis()
        val monotonic = SystemClock.elapsedRealtime()
        
        prefs.edit()
            .putLong(KEY_BASELINE_REAL_TIME, currentTime)
            .putLong(KEY_BASELINE_MONOTONIC, monotonic)
            .apply()
        
        Timber.d("Established anti-tampering baseline")
    }
    
    private fun hasBaseline(): Boolean {
        return prefs.contains(KEY_BASELINE_REAL_TIME) && prefs.contains(KEY_BASELINE_MONOTONIC)
    }
    
    fun getLastActiveDate(): String? {
        return prefs.getString(KEY_LAST_ACTIVE_DATE, null)
    }
    
    fun setLastActiveDate(date: String) {
        prefs.edit().putString(KEY_LAST_ACTIVE_DATE, date).apply()
    }
    
    fun getCurrentStreak(): Int {
        return prefs.getInt(KEY_CURRENT_STREAK, 0)
    }
    
    fun setCurrentStreak(streak: Int) {
        val cappedStreak = streak.coerceAtMost(MAX_STREAK_DAYS)
        prefs.edit().putInt(KEY_CURRENT_STREAK, cappedStreak).apply()
        
        // Track max streak achieved
        val maxStreak = getMaxStreakAchieved()
        if (cappedStreak > maxStreak) {
            setMaxStreakAchieved(cappedStreak)
        }
    }
    
    fun getTotalTimeToday(): Long {
        return prefs.getLong(KEY_TOTAL_TIME_TODAY, 0L)
    }
    
    fun setTotalTimeToday(timeMs: Long) {
        prefs.edit().putLong(KEY_TOTAL_TIME_TODAY, timeMs.coerceAtLeast(0L)).apply()
    }
    
    fun addTimeToday(additionalMs: Long) {
        val currentTime = getTotalTimeToday()
        setTotalTimeToday(currentTime + additionalMs.coerceAtLeast(0L))
    }
    
    fun getLessonCountToday(): Int {
        return prefs.getInt(KEY_LESSON_COUNT_TODAY, 0)
    }
    
    fun setLessonCountToday(count: Int) {
        prefs.edit().putInt(KEY_LESSON_COUNT_TODAY, count.coerceAtLeast(0)).apply()
    }
    
    fun incrementLessonCountToday() {
        val currentCount = getLessonCountToday()
        setLessonCountToday(currentCount + 1)
    }
    
    fun getLastLessonDate(): String? {
        return prefs.getString(KEY_LAST_LESSON_DATE, null)
    }
    
    fun setLastLessonDate(date: String) {
        prefs.edit().putString(KEY_LAST_LESSON_DATE, date).apply()
    }
    
    fun getMaxStreakAchieved(): Int {
        return prefs.getInt(KEY_MAX_STREAK_ACHIEVED, 0)
    }
    
    private fun setMaxStreakAchieved(streak: Int) {
        prefs.edit().putInt(KEY_MAX_STREAK_ACHIEVED, streak).apply()
    }
    
    // Porapoints methods
    fun getTotalPorapointsEarned(): Int {
        return prefs.getInt(KEY_TOTAL_PORAPOINTS_EARNED, 0)
    }
    
    fun setTotalPorapointsEarned(points: Int) {
        prefs.edit().putInt(KEY_TOTAL_PORAPOINTS_EARNED, points.coerceAtLeast(0)).apply()
    }
    
    fun addPorapoints(points: Int) {
        val currentPoints = getTotalPorapointsEarned()
        setTotalPorapointsEarned(currentPoints + points.coerceAtLeast(0))
    }
    
    // Anti-tampering methods
    fun isDateFrozen(): Boolean {
        val frozenUntil = prefs.getLong(KEY_DATE_FROZEN_UNTIL, 0L)
        return System.currentTimeMillis() < frozenUntil
    }
    
    fun freezeDateUntilTomorrow() {
        val tomorrow = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
        
        prefs.edit().putLong(KEY_DATE_FROZEN_UNTIL, tomorrow).apply()
        Timber.w("Date manipulation detected - streak frozen until tomorrow")
    }
    
    fun detectTimeManipulation(): Boolean {
        if (!hasBaseline()) {
            establishBaseline()
            return false
        }
        
        val baselineRealTime = prefs.getLong(KEY_BASELINE_REAL_TIME, 0L)
        val baselineMonotonic = prefs.getLong(KEY_BASELINE_MONOTONIC, 0L)
        
        val currentRealTime = System.currentTimeMillis()
        val currentMonotonic = SystemClock.elapsedRealtime()
        
        val realTimeDiff = currentRealTime - baselineRealTime
        val monotonicDiff = currentMonotonic - baselineMonotonic
        
        // Check for backwards time movement (major manipulation)
        if (realTimeDiff < -60000) { // More than 1 minute backwards
            Timber.w("Major time manipulation detected: time went backwards by ${-realTimeDiff}ms")
            return true
        }
        
        // Allow for more variance (10 seconds) to account for system delays and normal drift
        val variance = 10000L
        val isManipulated = Math.abs(realTimeDiff - monotonicDiff) > variance
        
        if (isManipulated) {
            Timber.w("Time manipulation detected: realDiff=$realTimeDiff, monotonicDiff=$monotonicDiff, variance=${Math.abs(realTimeDiff - monotonicDiff)}")
            // Update baseline after detection to prevent repeated triggers
            establishBaseline()
        }
        
        return isManipulated
    }
    
    fun resetDailyData() {
        prefs.edit()
            .putLong(KEY_TOTAL_TIME_TODAY, 0L)
            .putInt(KEY_LESSON_COUNT_TODAY, 0)
            .apply()
    }
    
    fun getTodayDateString(): String {
        return dateFormat.format(Date())
    }
    
    // ================================
    // PARENT PIN MANAGEMENT (for USSD)
    // ================================
    
    /**
     * Get parent PIN for USSD authentication
     */
    fun getParentPin(): String {
        return prefs.getString(KEY_PARENT_PIN, DEFAULT_PARENT_PIN) ?: DEFAULT_PARENT_PIN
    }
    
    /**
     * Set new parent PIN
     */
    fun setParentPin(pin: String) {
        if (pin.length == 4 && pin.all { it.isDigit() }) {
            prefs.edit().putString(KEY_PARENT_PIN, pin).apply()
            Timber.d("🔐 Parent PIN updated via USSD")
        } else {
            Timber.w("❌ Invalid PIN format attempted: ${pin.length} characters")
        }
    }
    
    /**
     * Verify parent PIN
     */
    fun verifyParentPin(inputPin: String): Boolean {
        val storedPin = getParentPin()
        val isValid = storedPin == inputPin
        
        if (isValid) {
            Timber.d("✅ Parent PIN verified successfully")
        } else {
            Timber.d("❌ Parent PIN verification failed")
        }
        
        return isValid
    }
    
    /**
     * Get basic user stats for USSD display
     */
    fun getBasicStats(): BasicUserStats {
        return BasicUserStats(
            currentStreak = getCurrentStreak(),
            totalPoints = getTotalPorapointsEarned(),
            totalSessions = getLessonCountToday() + 50 // Mock total sessions
        )
    }
    
    data class BasicUserStats(
        val currentStreak: Int,
        val totalPoints: Int,
        val totalSessions: Int
    )
}