package com.porakhela.di;

/**
 * Hilt module providing data layer dependencies
 * Handles dependency injection for local data storage classes
 */
@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0012\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0012\u0010\u0012\u001a\u00020\u000f2\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\u0017"}, d2 = {"Lcom/porakhela/di/DataModule;", "", "()V", "provideLessonLogDao", "Lcom/porakhela/data/local/LessonLogDao;", "database", "Lcom/porakhela/data/local/ParentDashboardDatabase;", "provideParentDashboardDatabase", "context", "Landroid/content/Context;", "provideRewardDao", "Lcom/porakhela/data/local/dao/RewardDao;", "provideStreakManager", "Lcom/porakhela/data/tracking/StreakManager;", "streakPreferences", "Lcom/porakhela/data/local/StreakPreferences;", "provideStreakNotificationScheduler", "Lcom/porakhela/core/notifications/StreakNotificationScheduler;", "provideStreakPreferences", "provideTimeTracker", "Lcom/porakhela/data/tracking/TimeTracker;", "provideUserPreferences", "Lcom/porakhela/data/local/UserPreferences;", "app_release"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DataModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.di.DataModule INSTANCE = null;
    
    private DataModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.UserPreferences provideUserPreferences(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.ParentDashboardDatabase provideParentDashboardDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.LessonLogDao provideLessonLogDao(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.ParentDashboardDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.dao.RewardDao provideRewardDao(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.ParentDashboardDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.StreakPreferences provideStreakPreferences(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.TimeTracker provideTimeTracker(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.StreakPreferences streakPreferences) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.StreakManager provideStreakManager(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.StreakPreferences streakPreferences) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.core.notifications.StreakNotificationScheduler provideStreakNotificationScheduler(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}