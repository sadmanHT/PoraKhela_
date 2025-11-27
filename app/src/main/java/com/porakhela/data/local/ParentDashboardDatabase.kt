package com.porakhela.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.porakhela.data.local.dao.RewardDao
import com.porakhela.data.model.Reward
import com.porakhela.data.model.RewardRedemption

/**
 * Room database for Parent Dashboard lesson logging and rewards
 */
@Database(
    entities = [LessonLog::class, Reward::class, RewardRedemption::class],
    version = 2,
    exportSchema = false
)
abstract class ParentDashboardDatabase : RoomDatabase() {
    
    abstract fun lessonLogDao(): LessonLogDao
    abstract fun rewardDao(): RewardDao
    
    companion object {
        @Volatile
        private var INSTANCE: ParentDashboardDatabase? = null
        
        fun getDatabase(context: Context): ParentDashboardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParentDashboardDatabase::class.java,
                    "parent_dashboard_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}