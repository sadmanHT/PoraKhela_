package com.porakhela.core.utils

/**
 * Constants used throughout the application
 */
object Constants {
    
    // App Configuration
    const val APP_NAME = "Porakhela"
    const val APP_VERSION = "1.0.0"
    
    // Porapoints System
    const val INITIAL_PORAPOINTS = 100
    const val DAILY_LOGIN_BONUS = 10
    const val LESSON_COMPLETION_POINTS = 50
    const val PERFECT_SCORE_BONUS = 25
    
    // Lesson Configuration
    const val MIN_LESSON_SCORE = 0
    const val MAX_LESSON_SCORE = 100
    const val PASSING_SCORE = 70
    
    // Animation Durations (in milliseconds)
    const val ANIMATION_DURATION_SHORT = 200L
    const val ANIMATION_DURATION_MEDIUM = 300L
    const val ANIMATION_DURATION_LONG = 500L
    const val SPLASH_DELAY = 2000L
    
    // Database
    const val DATABASE_NAME = "porakhela_database"
    const val DATABASE_VERSION = 1
    
    // SharedPreferences Keys
    const val PREF_NAME = "porakhela_prefs"
    const val PREF_FIRST_LAUNCH = "first_launch"
    const val PREF_USER_PORAPOINTS = "user_porapoints"
    const val PREF_LAST_LOGIN_DATE = "last_login_date"
    const val PREF_SOUND_ENABLED = "sound_enabled"
    const val PREF_HAPTIC_ENABLED = "haptic_enabled"
    
    // API Configuration (for future Applink integration)
    const val BASE_URL = "https://api.applink.com/"
    const val API_TIMEOUT = 30L // seconds
    const val RETRY_COUNT = 3
    
    // Age Groups (for content filtering)
    const val MIN_AGE = 6
    const val MAX_AGE = 11
    
    // Content Categories
    object ContentCategory {
        const val ALPHABET = "alphabet"
        const val VOCABULARY = "vocabulary"
        const val GRAMMAR = "grammar"
        const val PRONUNCIATION = "pronunciation"
        const val READING = "reading"
    }
    
    // Notification IDs
    const val NOTIFICATION_ID_DAILY_REMINDER = 1001
    const val NOTIFICATION_ID_ACHIEVEMENT = 1002
    
    // File Paths
    const val AUDIO_CACHE_DIR = "audio_cache"
    const val IMAGE_CACHE_DIR = "image_cache"
}