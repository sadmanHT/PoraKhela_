package com.porakhela.core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.porakhela.R
import com.porakhela.data.tracking.StreakManager
import com.porakhela.ui.main.MainActivity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class StreakNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val streakManager: StreakManager
) : CoroutineWorker(context, workerParams) {
    
    companion object {
        const val NOTIFICATION_ID = 1001
        const val CHANNEL_ID = "streak_reminders"
        const val WORK_TAG = "streak_notification"
    }
    
    override suspend fun doWork(): Result {
        return try {
            // Check if user needs to complete a lesson
            if (streakManager.needsLessonToMaintainStreak()) {
                showStreakReminderNotification()
                Timber.d("Streak reminder notification sent")
            } else {
                Timber.d("User already completed lesson today, no notification needed")
            }
            Result.success()
        } catch (e: Exception) {
            Timber.e(e, "Error in streak notification worker")
            Result.failure()
        }
    }
    
    private fun showStreakReminderNotification() {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        // Create notification channel for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Streak Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily reminders to maintain your learning streak"
                enableVibration(true)
                setShowBadge(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
        
        // Create intent to open app
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val streakInfo = streakManager.getStreakInfo()
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_security) // Use existing icon
            .setContentTitle("Maintain Your Streak! 🔥")
            .setContentText("Complete one lesson to maintain your ${streakInfo.currentStreak}-day streak!")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("You're doing great! Complete just one lesson today to keep your ${streakInfo.currentStreak}-day learning streak alive. Don't break the chain!"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 250, 250, 250))
            .build()
        
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}