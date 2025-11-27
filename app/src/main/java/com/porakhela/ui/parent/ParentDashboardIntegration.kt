package com.porakhela.ui.parent

import androidx.lifecycle.lifecycleScope
import com.porakhela.data.repository.ParentDashboardRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Helper class for integrating lesson completion logging with Parent Dashboard
 * Use this in your lesson completion flows to automatically log progress
 */
@Singleton
class ParentDashboardIntegration @Inject constructor(
    private val repository: ParentDashboardRepository
) {
    
    /**
     * Call this method whenever a lesson is completed
     * This will automatically log the lesson for parent dashboard tracking
     */
    suspend fun logLessonCompletion(
        lessonId: String,
        pointsEarned: Int,
        durationMinutes: Int,
        subjectName: String = "",
        categoryName: String = ""
    ) {
        try {
            repository.logLessonCompletion(
                lessonId = lessonId,
                pointsEarned = pointsEarned,
                durationMinutes = durationMinutes,
                subjectName = subjectName,
                categoryName = categoryName
            )
            Timber.d("Lesson completion logged for parent dashboard: $lessonId")
        } catch (e: Exception) {
            Timber.e(e, "Failed to log lesson completion for parent dashboard")
        }
    }
    
    /**
     * Quick test method to verify lesson logging is working
     */
    suspend fun testLessonLogging() {
        logLessonCompletion(
            lessonId = "test_lesson_001",
            pointsEarned = 25,
            durationMinutes = 5,
            subjectName = "English",
            categoryName = "Vocabulary Test"
        )
    }
}