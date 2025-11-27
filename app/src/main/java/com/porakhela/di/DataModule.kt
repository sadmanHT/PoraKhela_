package com.porakhela.di

import android.content.Context
import androidx.room.Room
import com.porakhela.data.local.ParentDashboardDatabase
import com.porakhela.data.local.LessonLogDao
import com.porakhela.data.local.UserPreferences
import com.porakhela.data.local.StreakPreferences
import com.porakhela.data.local.LeaderboardCache
import com.porakhela.data.local.dao.RewardDao
import com.porakhela.data.repository.LeaderboardRepository
import com.porakhela.data.tracking.TimeTracker
import com.porakhela.data.tracking.StreakManager
import com.porakhela.core.notifications.StreakNotificationScheduler
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
    fun provideParentDashboardDatabase(@ApplicationContext context: Context): ParentDashboardDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ParentDashboardDatabase::class.java,
            "parent_dashboard_database"
        )
        .fallbackToDestructiveMigration()
        .build()
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
    fun provideStreakNotificationScheduler(@ApplicationContext context: Context): StreakNotificationScheduler {
        return StreakNotificationScheduler(context)
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
}