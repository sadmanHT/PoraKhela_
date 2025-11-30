package com.porakhela.di

import android.content.Context
import androidx.room.Room
import com.porakhela.data.local.ParentDashboardDatabase
import com.porakhela.data.local.LessonLogDao
import com.porakhela.data.local.UserPreferences
import com.porakhela.data.local.StreakPreferences
import com.porakhela.data.local.LeaderboardCache
import com.porakhela.data.local.dao.RewardDao
import com.porakhela.data.local.dao.SecurityEventDao
import com.porakhela.data.dao.NotificationEventDao
import com.porakhela.data.repository.LeaderboardRepository
import com.porakhela.data.tracking.TimeTracker
import com.porakhela.data.tracking.StreakManager
import com.porakhela.domain.analytics.AnalyticsService
import com.porakhela.domain.gamification.ExperimentsManager
import com.porakhela.domain.gamification.RulesValidator
import com.porakhela.security.RateLimitManager
import com.porakhela.security.SecurityService
import com.porakhela.ui.performance.DatabaseOptimizer
import com.porakhela.ui.performance.BatchDatabaseOperations
import com.porakhela.ui.performance.DatabaseQueryCache
import com.porakhela.ui.performance.OptimizedCoroutineManager
import com.porakhela.ui.performance.DebouncedOperationManager
import com.porakhela.ui.performance.BackgroundTaskScheduler
import com.porakhela.ui.performance.ObjectPoolManager
import com.porakhela.ui.performance.MemoryEfficientTransformations
import com.porakhela.ui.performance.RecyclerViewGCOptimizer
import com.porakhela.ui.performance.monitoring.PerformanceMonitoringSystem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing data layer dependencies
 * Handles dependency injection for local data storage classes
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }
    
    @Provides
    @Singleton
    fun provideDatabaseOptimizer(): DatabaseOptimizer {
        return DatabaseOptimizer()
    }
    
    @Provides
    @Singleton
    fun provideParentDashboardDatabase(
        @ApplicationContext context: Context,
        databaseOptimizer: DatabaseOptimizer
    ): ParentDashboardDatabase {
        return databaseOptimizer.createOptimizedDatabase(context)
    }
    
    @Provides
    fun provideLessonLogDao(database: ParentDashboardDatabase): LessonLogDao {
        return database.lessonLogDao()
    }
    
    @Provides
    fun provideRewardDao(database: ParentDashboardDatabase): RewardDao {
        return database.rewardDao()
    }
    
    @Provides
    fun provideRewardLedgerDao(database: ParentDashboardDatabase): com.porakhela.data.local.dao.RewardLedgerDao {
        return database.rewardLedgerDao()
    }
    
    @Provides
    @Singleton
    fun provideBatchDatabaseOperations(database: ParentDashboardDatabase): BatchDatabaseOperations {
        return BatchDatabaseOperations(database)
    }
    
    @Provides
    @Singleton
    fun provideDatabaseQueryCache(): DatabaseQueryCache {
        return DatabaseQueryCache()
    }
    
    // ================================
    // THREADING & COROUTINE OPTIMIZATION
    // ================================
    
    @Provides
    @Singleton
    fun provideOptimizedCoroutineManager(): OptimizedCoroutineManager {
        return OptimizedCoroutineManager()
    }
    
    @Provides
    @Singleton
    fun provideDebouncedOperationManager(
        coroutineManager: OptimizedCoroutineManager
    ): DebouncedOperationManager {
        return DebouncedOperationManager(coroutineManager)
    }
    
    @Provides
    @Singleton
    fun provideBackgroundTaskScheduler(
        optimizedCoroutineManager: OptimizedCoroutineManager
    ): BackgroundTaskScheduler {
        return BackgroundTaskScheduler(optimizedCoroutineManager)
    }

    // GARBAGE COLLECTION OPTIMIZATION COMPONENTS
    @Provides
    @Singleton
    fun provideObjectPoolManager(): ObjectPoolManager {
        return ObjectPoolManager()
    }

    @Provides
    @Singleton
    fun provideMemoryEfficientTransformations(
        objectPoolManager: ObjectPoolManager
    ): MemoryEfficientTransformations {
        return MemoryEfficientTransformations(objectPoolManager)
    }

    @Provides
    @Singleton
    fun provideRecyclerViewGCOptimizer(
        objectPoolManager: ObjectPoolManager
    ): RecyclerViewGCOptimizer {
        return RecyclerViewGCOptimizer(objectPoolManager)
    }

    // PERFORMANCE MONITORING SYSTEM
    @Provides
    @Singleton
    fun providePerformanceMonitoringSystem(
        @ApplicationContext context: Context,
        objectPoolManager: ObjectPoolManager
    ): PerformanceMonitoringSystem {
        return PerformanceMonitoringSystem(context, objectPoolManager)
    }
    
    @Provides
    fun provideGamificationEventDao(database: ParentDashboardDatabase): com.porakhela.data.dao.GamificationEventDao {
        return database.gamificationEventDao()
    }
    
    @Provides
    fun provideNotificationEventDao(database: ParentDashboardDatabase): NotificationEventDao {
        return database.notificationEventDao()
    }
    
    @Provides
    @Singleton
    fun provideStreakPreferences(@ApplicationContext context: Context): StreakPreferences {
        return StreakPreferences(context)
    }
    
    @Provides
    @Singleton
    fun provideTimeTracker(streakPreferences: StreakPreferences): TimeTracker {
        return TimeTracker(streakPreferences)
    }
    
    @Provides
    @Singleton
    fun provideStreakManager(streakPreferences: StreakPreferences): StreakManager {
        return StreakManager(streakPreferences)
    }
    
    @Provides
    @Singleton
    fun provideLeaderboardCache(@ApplicationContext context: Context): LeaderboardCache {
        return LeaderboardCache(context)
    }
    
    @Provides
    @Singleton
    fun provideLeaderboardRepository(
        leaderboardCache: LeaderboardCache,
        userPreferences: UserPreferences
    ): LeaderboardRepository {
        return LeaderboardRepository(leaderboardCache, userPreferences)
    }
    
    @Provides
    @Singleton
    fun provideExperimentsManager(@ApplicationContext context: Context): ExperimentsManager {
        return ExperimentsManager(context)
    }
    
    @Provides
    @Singleton
    fun provideRulesValidator(@ApplicationContext context: Context): RulesValidator {
        return RulesValidator(context)
    }
    
    @Provides
    @Singleton
    fun provideAnalyticsService(
        database: ParentDashboardDatabase,
        userPreferences: UserPreferences
    ): AnalyticsService {
        return AnalyticsService(database.analyticsEventDao(), userPreferences)
    }
    
    @Provides
    fun provideSecurityEventDao(database: ParentDashboardDatabase): SecurityEventDao {
        return database.securityEventDao()
    }
    
    @Provides
    @Singleton
    fun provideRateLimitManager(@ApplicationContext context: Context): RateLimitManager {
        return RateLimitManager(context)
    }
    
    @Provides
    @Singleton
    fun provideSecurityService(
        @ApplicationContext context: Context,
        rateLimitManager: RateLimitManager,
        database: ParentDashboardDatabase
    ): SecurityService {
        return SecurityService(context, rateLimitManager, database)
    }
}