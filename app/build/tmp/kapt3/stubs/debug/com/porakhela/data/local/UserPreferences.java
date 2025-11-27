package com.porakhela.data.local;

/**
 * Comprehensive user preferences management system
 * Handles user data including points, streaks, achievements, and profile information
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 *2\u00020\u0001:\u0001*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0006\u0010\u0011\u001a\u00020\nJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fJ\b\u0010\u0013\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010\u0014\u001a\u00020\nJ\b\u0010\u0015\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\bJ\u0006\u0010\u001a\u001a\u00020\bJ\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u000fJ\u0006\u0010\u001d\u001a\u00020\rJ\u0006\u0010\u001e\u001a\u00020\rJ\u0006\u0010\u001f\u001a\u00020\bJ\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002J\u000e\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u000fJ\u000e\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u000fJ\u0006\u0010%\u001a\u00020\bJ\u000e\u0010&\u001a\u00020\b2\u0006\u0010\'\u001a\u00020\u000fJ\u0006\u0010(\u001a\u00020\bJ\u0006\u0010)\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/porakhela/data/local/UserPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "addPorapoints", "", "points", "", "clearAllUserData", "deductPorapoints", "", "getChildName", "", "getCurrentDate", "getDailyStreak", "getFavoriteSubject", "getLastActivityDate", "getPorapoints", "getSelectedAvatar", "getTotalLessonsCompleted", "getUserStats", "Lcom/porakhela/data/local/UserStats;", "incrementDailyStreak", "incrementLessonsCompleted", "isAchievementUnlocked", "achievementKey", "isFirstLaunch", "isOnboardingComplete", "resetDailyStreak", "setAchievementUnlocked", "setChildName", "name", "setFavoriteSubject", "subject", "setOnboardingComplete", "setSelectedAvatar", "avatar", "updateLastActivityDate", "wasActiveToday", "Companion", "app_debug"})
public final class UserPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "porakhela_user_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CHILD_NAME = "child_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SELECTED_AVATAR = "selected_avatar";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PORAPOINTS = "porapoints";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DAILY_STREAK = "daily_streak";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_ACTIVITY_DATE = "last_activity_date";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TOTAL_LESSONS_COMPLETED = "total_lessons_completed";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FAVORITE_SUBJECT = "favorite_subject";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FIRST_LESSON_COMPLETE = "first_lesson_complete";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FIRST_WEEK_STREAK = "first_week_streak";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_HUNDRED_POINTS = "hundred_points";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ONBOARDING_COMPLETE = "onboarding_complete";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_APP_FIRST_LAUNCH = "app_first_launch";
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.local.UserPreferences.Companion Companion = null;
    
    public UserPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Child name management
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getChildName() {
        return null;
    }
    
    public final void setChildName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Avatar selection management
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelectedAvatar() {
        return null;
    }
    
    public final void setSelectedAvatar(@org.jetbrains.annotations.NotNull()
    java.lang.String avatar) {
    }
    
    /**
     * Porapoints system - core reward mechanism with thread safety
     */
    @kotlin.jvm.Synchronized()
    public final synchronized int getPorapoints() {
        return 0;
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized void addPorapoints(int points) {
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized boolean deductPorapoints(int points) {
        return false;
    }
    
    /**
     * Daily streak system - encourages consistent learning
     */
    public final int getDailyStreak() {
        return 0;
    }
    
    public final void incrementDailyStreak() {
    }
    
    public final void resetDailyStreak() {
    }
    
    /**
     * Activity tracking
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastActivityDate() {
        return null;
    }
    
    public final void updateLastActivityDate() {
    }
    
    public final boolean wasActiveToday() {
        return false;
    }
    
    /**
     * Lesson progress tracking with thread safety
     */
    @kotlin.jvm.Synchronized()
    public final synchronized int getTotalLessonsCompleted() {
        return 0;
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized void incrementLessonsCompleted() {
    }
    
    /**
     * Subject preferences
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFavoriteSubject() {
        return null;
    }
    
    public final void setFavoriteSubject(@org.jetbrains.annotations.NotNull()
    java.lang.String subject) {
    }
    
    /**
     * Achievement system
     */
    public final boolean isAchievementUnlocked(@org.jetbrains.annotations.NotNull()
    java.lang.String achievementKey) {
        return false;
    }
    
    private final void setAchievementUnlocked(java.lang.String achievementKey) {
    }
    
    /**
     * Onboarding state
     */
    public final boolean isOnboardingComplete() {
        return false;
    }
    
    public final void setOnboardingComplete() {
    }
    
    public final boolean isFirstLaunch() {
        return false;
    }
    
    /**
     * Get comprehensive user stats
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.UserStats getUserStats() {
        return null;
    }
    
    /**
     * Clear all user data (for reset/testing)
     */
    public final void clearAllUserData() {
    }
    
    private final java.lang.String getCurrentDate() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/porakhela/data/local/UserPreferences$Companion;", "", "()V", "KEY_APP_FIRST_LAUNCH", "", "KEY_CHILD_NAME", "KEY_DAILY_STREAK", "KEY_FAVORITE_SUBJECT", "KEY_FIRST_LESSON_COMPLETE", "KEY_FIRST_WEEK_STREAK", "KEY_HUNDRED_POINTS", "KEY_LAST_ACTIVITY_DATE", "KEY_ONBOARDING_COMPLETE", "KEY_PORAPOINTS", "KEY_SELECTED_AVATAR", "KEY_TOTAL_LESSONS_COMPLETED", "PREFS_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}