package com.porakhela.core.notifications

import android.content.Context
import androidx.work.*
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StreakNotificationScheduler @Inject constructor(
    private val context: Context
) {
    
    private val workManager = WorkManager.getInstance(context)
    
    fun scheduleDaily8PMNotification() {
        val currentTime = Calendar.getInstance()
        val targetTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 20) // 8 PM
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            
            // If 8 PM has already passed today, schedule for tomorrow
            if (timeInMillis <= currentTime.timeInMillis) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        
        val initialDelay = targetTime.timeInMillis - currentTime.timeInMillis
        
        val dailyWorkRequest = PeriodicWorkRequestBuilder<StreakNotificationWorker>(
            repeatInterval = 24,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .setRequiresBatteryNotLow(false)
                    .build()
            )
            .addTag(StreakNotificationWorker.WORK_TAG)
            .build()
        
        workManager.enqueueUniquePeriodicWork(
            "daily_streak_reminder",
            ExistingPeriodicWorkPolicy.REPLACE,
            dailyWorkRequest
        )
        
        Timber.d("Scheduled daily 8PM streak notifications, initial delay: ${initialDelay}ms")
    }
    
    fun cancelNotifications() {
        workManager.cancelAllWorkByTag(StreakNotificationWorker.WORK_TAG)
        Timber.d("Cancelled streak notifications")
    }
}