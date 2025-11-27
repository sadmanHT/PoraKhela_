package com.porakhela.data.repository

import com.porakhela.data.local.*
import com.porakhela.utils.PorapointsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Parent Dashboard operations
 */
@Singleton
class ParentDashboardRepository @Inject constructor(
    private val securePrefs: SecureParentPreferences,
    private val database: ParentDashboardDatabase,
    private val porapointsManager: PorapointsManager,
    private val userPreferences: UserPreferences
) {
    
    private val lessonLogDao = database.lessonLogDao()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    // Authentication Methods
    suspend fun authenticatePin(pin: String): Boolean {
        return withContext(Dispatchers.IO) {
            val storedPin = securePrefs.getParentPin()
            if (storedPin == null || storedPin != pin) {
                val attempts = securePrefs.incrementFailedAttempts()
                if (attempts >= 3) {
                    // Lock for 30 seconds
                    val lockUntil = System.currentTimeMillis() + 30_000
                    securePrefs.setCooldownEndTime(lockUntil)
                }
                false
            } else {
                securePrefs.resetFailedAttempts()
                true
            }
        }
    }
    
    fun setParentPin(pin: String) {
        securePrefs.setParentPin(pin)
    }
    
    fun hasParentPin(): Boolean = securePrefs.hasParentPin()
    
    fun getFailedAttempts(): Int = securePrefs.getFailedAttempts()
    
    fun isInCooldown(): Boolean = securePrefs.isInCooldown()
    
    fun getCooldownRemainingSeconds(): Int {
        val remaining = securePrefs.getCooldownEndTime() - System.currentTimeMillis()
        return maxOf(0, (remaining / 1000).toInt())
    }
    
    // Child Progress Methods
    suspend fun getChildProgress(): ChildProgress {
        return withContext(Dispatchers.IO) {
            try {
                val totalLessons = lessonLogDao.getTotalLessonsCount()
                val lastLessonTime = lessonLogDao.getLastLessonTimestamp()
                
                ChildProgress(
                    childName = userPreferences.getChildName() ?: "Child",
                    totalPorapoints = try { porapointsManager.getCurrentPorapoints() } catch (e: Exception) { 0 },
                    dailyStreak = try { userPreferences.getDailyStreak() } catch (e: Exception) { 0 },
                    lessonsCompleted = totalLessons,
                    lastLessonTime = lastLessonTime
                )
            } catch (e: Exception) {
                Timber.e(e, "Error getting child progress")
                ChildProgress(
                    childName = "Child",
                    totalPorapoints = 0,
                    dailyStreak = 0,
                    lessonsCompleted = 0,
                    lastLessonTime = null
                )
            }
        }
    }
    
    // Screen Time Control Methods
    fun setDailyLimit(minutes: Int) {
        securePrefs.setDailyLimitMinutes(minutes)
    }
    
    fun getDailyLimit(): Int = securePrefs.getDailyLimitMinutes()
    
    fun setContentLock(enabled: Boolean) {
        securePrefs.setContentLockEnabled(enabled)
    }
    
    fun isContentLockEnabled(): Boolean = securePrefs.isContentLockEnabled()
    
    suspend fun getTodayScreenTime(): Int {
        return withContext(Dispatchers.IO) {
            lessonLogDao.getTodayScreenTime() ?: 0
        }
    }
    
    // Learning Reports Methods
    suspend fun getDailyReports(): List<DailyLearningReport> {
        return withContext(Dispatchers.IO) {
            try {
                val rawReports = lessonLogDao.getDailyReports()
                rawReports.map { raw ->
                    DailyLearningReport(
                        date = raw.date,
                        lessonsCompleted = raw.lessonsCompleted,
                        totalPointsEarned = raw.totalPointsEarned,
                        totalTimeMinutes = raw.totalTimeMinutes
                    )
                }
            } catch (e: Exception) {
                Timber.e(e, "Error getting daily reports")
                emptyList()
            }
        }
    }
    
    // Redemption Approval Methods
    fun setApprovalRequired(required: Boolean) {
        securePrefs.setApprovalRequired(required)
    }
    
    fun isApprovalRequired(): Boolean = securePrefs.isApprovalRequired()
    
    // Lesson Logging
    suspend fun logLessonCompletion(
        lessonId: String,
        pointsEarned: Int,
        durationMinutes: Int,
        subjectName: String = "",
        categoryName: String = ""
    ) {
        withContext(Dispatchers.IO) {
            try {
                val log = LessonLog(
                    lessonId = lessonId,
                    pointsEarned = pointsEarned,
                    durationMinutes = durationMinutes,
                    childName = userPreferences.getChildName() ?: "Child",
                    subjectName = subjectName,
                    categoryName = categoryName
                )
                lessonLogDao.insertLessonLog(log)
                
                // Clean up old logs (keep 90 days)
                val cutoffTime = System.currentTimeMillis() - (90L * 24 * 60 * 60 * 1000)
                lessonLogDao.deleteOldLogs(cutoffTime)
                
                Timber.d("Lesson logged: $lessonId, Points: $pointsEarned, Duration: $durationMinutes min")
            } catch (e: Exception) {
                Timber.e(e, "Error logging lesson completion")
            }
        }
    }
}

/**
 * Data class for child progress information
 */
data class ChildProgress(
    val childName: String,
    val totalPorapoints: Int,
    val dailyStreak: Int,
    val lessonsCompleted: Int,
    val lastLessonTime: Long?
)