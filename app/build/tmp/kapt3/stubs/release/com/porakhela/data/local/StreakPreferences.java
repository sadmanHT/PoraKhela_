package com.porakhela.data.local;

/**
 * Manages streak data persistence with anti-tampering protection
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\nH\u0002J\u0006\u0010\u0013\u001a\u00020\nJ\u0006\u0010\u0014\u001a\u00020\fJ\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016J\u0006\u0010\u0018\u001a\u00020\fJ\u0006\u0010\u0019\u001a\u00020\fJ\u0006\u0010\u001a\u001a\u00020\u0016J\u0006\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u0011H\u0002J\u0006\u0010\u001e\u001a\u00020\nJ\u0006\u0010\u001f\u001a\u00020\u0011J\u0006\u0010 \u001a\u00020\nJ\u000e\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\fJ\u000e\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u0016J\u000e\u0010%\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u0016J\u000e\u0010&\u001a\u00020\n2\u0006\u0010\'\u001a\u00020\fJ\u0010\u0010(\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\fH\u0002J\u000e\u0010)\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/porakhela/data/local/StreakPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "prefs", "Landroid/content/SharedPreferences;", "addPorapoints", "", "points", "", "addTimeToday", "additionalMs", "", "detectTimeManipulation", "", "establishBaseline", "freezeDateUntilTomorrow", "getCurrentStreak", "getLastActiveDate", "", "getLastLessonDate", "getLessonCountToday", "getMaxStreakAchieved", "getTodayDateString", "getTotalPorapointsEarned", "getTotalTimeToday", "hasBaseline", "incrementLessonCountToday", "isDateFrozen", "resetDailyData", "setCurrentStreak", "streak", "setLastActiveDate", "date", "setLastLessonDate", "setLessonCountToday", "count", "setMaxStreakAchieved", "setTotalPorapointsEarned", "setTotalTimeToday", "timeMs", "Companion", "app_release"})
public final class StreakPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_ACTIVE_DATE = "last_active_date";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_STREAK = "current_streak";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TOTAL_TIME_TODAY = "total_time_today";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LESSON_COUNT_TODAY = "lesson_count_today";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_LESSON_DATE = "last_lesson_date";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BASELINE_MONOTONIC = "baseline_monotonic";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_BASELINE_REAL_TIME = "baseline_real_time";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DATE_FROZEN_UNTIL = "date_frozen_until";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_MAX_STREAK_ACHIEVED = "max_streak_achieved";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TOTAL_PORAPOINTS_EARNED = "total_porapoints_earned";
    private static final int MAX_STREAK_DAYS = 365;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.local.StreakPreferences.Companion Companion = null;
    
    @javax.inject.Inject()
    public StreakPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final void establishBaseline() {
    }
    
    private final boolean hasBaseline() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastActiveDate() {
        return null;
    }
    
    public final void setLastActiveDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
    }
    
    public final int getCurrentStreak() {
        return 0;
    }
    
    public final void setCurrentStreak(int streak) {
    }
    
    public final long getTotalTimeToday() {
        return 0L;
    }
    
    public final void setTotalTimeToday(long timeMs) {
    }
    
    public final void addTimeToday(long additionalMs) {
    }
    
    public final int getLessonCountToday() {
        return 0;
    }
    
    public final void setLessonCountToday(int count) {
    }
    
    public final void incrementLessonCountToday() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastLessonDate() {
        return null;
    }
    
    public final void setLastLessonDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
    }
    
    public final int getMaxStreakAchieved() {
        return 0;
    }
    
    private final void setMaxStreakAchieved(int streak) {
    }
    
    public final int getTotalPorapointsEarned() {
        return 0;
    }
    
    public final void setTotalPorapointsEarned(int points) {
    }
    
    public final void addPorapoints(int points) {
    }
    
    public final boolean isDateFrozen() {
        return false;
    }
    
    public final void freezeDateUntilTomorrow() {
    }
    
    public final boolean detectTimeManipulation() {
        return false;
    }
    
    public final void resetDailyData() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTodayDateString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/porakhela/data/local/StreakPreferences$Companion;", "", "()V", "KEY_BASELINE_MONOTONIC", "", "KEY_BASELINE_REAL_TIME", "KEY_CURRENT_STREAK", "KEY_DATE_FROZEN_UNTIL", "KEY_LAST_ACTIVE_DATE", "KEY_LAST_LESSON_DATE", "KEY_LESSON_COUNT_TODAY", "KEY_MAX_STREAK_ACHIEVED", "KEY_TOTAL_PORAPOINTS_EARNED", "KEY_TOTAL_TIME_TODAY", "MAX_STREAK_DAYS", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}