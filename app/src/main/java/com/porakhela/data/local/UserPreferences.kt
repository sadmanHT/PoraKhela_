package com.porakhela.data.local

import android.content.Context
import android.content.SharedPreferences
import timber.log.Timber

/**
 * Comprehensive user preferences management system
 * Handles user data including points, streaks, achievements, and profile information
 */
class UserPreferences(context: Context) {
    
    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "porakhela_user_prefs"
        
        // Core user data keys
        private const val KEY_CHILD_NAME = "child_name"
        private const val KEY_SELECTED_AVATAR = "selected_avatar"
        private const val KEY_PORAPOINTS = "porapoints"
        private const val KEY_DAILY_STREAK = "daily_streak"
        private const val KEY_LAST_ACTIVITY_DATE = "last_activity_date"
        private const val KEY_TOTAL_LESSONS_COMPLETED = "total_lessons_completed"
        private const val KEY_FAVORITE_SUBJECT = "favorite_subject"
        
        // Achievement tracking keys
        private const val KEY_FIRST_LESSON_COMPLETE = "first_lesson_complete"
        private const val KEY_FIRST_WEEK_STREAK = "first_week_streak"
        private const val KEY_HUNDRED_POINTS = "hundred_points"
        
        // Onboarding and app state keys
        private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
        private const val KEY_APP_FIRST_LAUNCH = "app_first_launch"
    }
    
    /**
     * Child name management
     */
    fun getChildName(): String? = preferences.getString(KEY_CHILD_NAME, null)
    
    fun setChildName(name: String) {
        preferences.edit().putString(KEY_CHILD_NAME, name).apply()
        Timber.d("Child name set to: $name")
    }
    
    /**
     * Avatar selection management
     */
    fun getSelectedAvatar(): String? = preferences.getString(KEY_SELECTED_AVATAR, null)
    
    fun setSelectedAvatar(avatar: String) {
        preferences.edit().putString(KEY_SELECTED_AVATAR, avatar).apply()
        Timber.d("Avatar selected: $avatar")
    }
    
    /**
     * Porapoints system - core reward mechanism with thread safety
     */
    @Synchronized
    fun getPorapoints(): Int = preferences.getInt(KEY_PORAPOINTS, 0)
    
    @Synchronized
    fun addPorapoints(points: Int) {
        if (points < 0) {
            Timber.w("Attempted to add negative points: $points")
            return
        }
        
        val currentPoints = getPorapoints()
        val newTotal = currentPoints + points
        
        // Validate reasonable limits
        if (newTotal > 1_000_000) {
            Timber.w("Porapoints total would be too high: $newTotal")
            return
        }
        
        val success = preferences.edit().putInt(KEY_PORAPOINTS, newTotal).commit()
        if (success) {
            // Update last activity only on successful save
            updateLastActivityDate()
            Timber.d("Added $points porapoints. Total: $newTotal")
        } else {
            Timber.e("Failed to save porapoints update")
        }
    }
    
    @Synchronized
    fun deductPorapoints(points: Int): Boolean {
        val currentPoints = getPorapoints()
        return if (currentPoints >= points) {
            val newTotal = currentPoints - points
            val success = preferences.edit().putInt(KEY_PORAPOINTS, newTotal).commit()
            if (success) {
                Timber.d("Deducted $points porapoints. Total: $newTotal")
                true
            } else {
                Timber.e("Failed to save porapoints deduction")
                false
            }
        } else {
            Timber.w("Insufficient porapoints. Requested: $points, Available: $currentPoints")
            false
        }
    }
    
    /**
     * Daily streak system - encourages consistent learning
     */
    fun getDailyStreak(): Int = preferences.getInt(KEY_DAILY_STREAK, 0)
    
    fun incrementDailyStreak() {
        val currentStreak = getDailyStreak()
        val newStreak = currentStreak + 1
        preferences.edit().putInt(KEY_DAILY_STREAK, newStreak).apply()
        
        // Update last activity
        updateLastActivityDate()
        
        Timber.d("Daily streak incremented to: $newStreak")
        
        // Check for streak achievement
        if (newStreak == 7) {
            setAchievementUnlocked(KEY_FIRST_WEEK_STREAK)
        }
    }
    
    fun resetDailyStreak() {
        preferences.edit().putInt(KEY_DAILY_STREAK, 0).apply()
        Timber.d("Daily streak reset")
    }
    
    /**
     * Activity tracking
     */
    fun getLastActivityDate(): String? = preferences.getString(KEY_LAST_ACTIVITY_DATE, null)
    
    fun updateLastActivityDate() {
        val today = getCurrentDate()
        preferences.edit().putString(KEY_LAST_ACTIVITY_DATE, today).apply()
        Timber.d("Last activity updated to: $today")
    }
    
    fun wasActiveToday(): Boolean {
        val lastActivityDate = getLastActivityDate()
        val today = getCurrentDate()
        return lastActivityDate == today
    }
    
    /**
     * Lesson progress tracking with thread safety
     */
    @Synchronized
    fun getTotalLessonsCompleted(): Int = preferences.getInt(KEY_TOTAL_LESSONS_COMPLETED, 0)
    
    @Synchronized
    fun incrementLessonsCompleted() {
        val current = getTotalLessonsCompleted()
        val newTotal = current + 1
        
        val success = preferences.edit().putInt(KEY_TOTAL_LESSONS_COMPLETED, newTotal).commit()
        if (success) {
            // Update last activity only on successful save
            updateLastActivityDate()
            
            Timber.d("Total lessons completed: $newTotal")
            
            // Check for first lesson achievement
            if (newTotal == 1) {
                setAchievementUnlocked(KEY_FIRST_LESSON_COMPLETE)
            }
        } else {
            Timber.e("Failed to save lesson completion update")
        }
    }
    
    /**
     * Subject preferences
     */
    fun getFavoriteSubject(): String? = preferences.getString(KEY_FAVORITE_SUBJECT, null)
    
    fun setFavoriteSubject(subject: String) {
        preferences.edit().putString(KEY_FAVORITE_SUBJECT, subject).apply()
        Timber.d("Favorite subject set to: $subject")
    }
    
    /**
     * Achievement system
     */
    fun isAchievementUnlocked(achievementKey: String): Boolean {
        return preferences.getBoolean(achievementKey, false)
    }
    
    private fun setAchievementUnlocked(achievementKey: String) {
        if (!isAchievementUnlocked(achievementKey)) {
            preferences.edit().putBoolean(achievementKey, true).apply()
            Timber.d("Achievement unlocked: $achievementKey")
        }
    }
    
    /**
     * Onboarding state
     */
    fun isOnboardingComplete(): Boolean = preferences.getBoolean(KEY_ONBOARDING_COMPLETE, false)
    
    fun setOnboardingComplete() {
        preferences.edit().putBoolean(KEY_ONBOARDING_COMPLETE, true).apply()
        Timber.d("Onboarding marked as complete")
    }
    
    fun isFirstLaunch(): Boolean {
        val isFirst = preferences.getBoolean(KEY_APP_FIRST_LAUNCH, true)
        if (isFirst) {
            preferences.edit().putBoolean(KEY_APP_FIRST_LAUNCH, false).apply()
            Timber.d("App first launch detected")
        }
        return isFirst
    }
    
    /**
     * Get comprehensive user stats
     */
    fun getUserStats(): UserStats {
        return UserStats(
            porapoints = getPorapoints(),
            dailyStreak = getDailyStreak(),
            totalLessonsCompleted = getTotalLessonsCompleted(),
            favoriteSubject = getFavoriteSubject(),
            childName = getChildName(),
            lastActivityDate = getLastActivityDate(),
            wasActiveToday = wasActiveToday()
        )
    }
    
    /**
     * Clear all user data (for reset/testing)
     */
    fun clearAllUserData() {
        preferences.edit()
            .remove(KEY_PORAPOINTS)
            .remove(KEY_DAILY_STREAK)
            .remove(KEY_LAST_ACTIVITY_DATE)
            .remove(KEY_TOTAL_LESSONS_COMPLETED)
            .remove(KEY_FAVORITE_SUBJECT)
            .apply()
        
        Timber.w("All user data cleared")
    }
    
    private fun getCurrentDate(): String {
        return java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            .format(java.util.Date())
    }
}

/**
 * Data class holding comprehensive user statistics
 */
data class UserStats(
    val porapoints: Int,
    val dailyStreak: Int,
    val totalLessonsCompleted: Int,
    val favoriteSubject: String?,
    val childName: String?,
    val lastActivityDate: String?,
    val wasActiveToday: Boolean
) {
    /**
     * Get a motivational message based on stats
     */
    fun getMotivationalMessage(): String {
        return when {
            dailyStreak >= 7 -> "Awesome streak! You're on fire! 🔥"
            dailyStreak >= 3 -> "Great job! Keep it up! 🌟"
            totalLessonsCompleted >= 10 -> "You're learning so much! 📚"
            porapoints >= 1000 -> "Wow! Look at those points! ⭐"
            else -> "Ready to learn something new? 🚀"
        }
    }
    
    /**
     * Get user level based on total points
     */
    fun getUserLevel(): Int {
        return when {
            porapoints >= 5000 -> 5
            porapoints >= 3000 -> 4
            porapoints >= 1500 -> 3
            porapoints >= 500 -> 2
            else -> 1
        }
    }
}