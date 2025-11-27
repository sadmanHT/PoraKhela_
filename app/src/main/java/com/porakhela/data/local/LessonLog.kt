package com.porakhela.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Room entity for logging lesson completion data
 */
@Entity(tableName = "lesson_logs")
@Serializable
data class LessonLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val lessonId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val pointsEarned: Int,
    val durationMinutes: Int,
    val childName: String = "",
    val subjectName: String = "",
    val categoryName: String = ""
)

/**
 * Data class for daily learning summary
 */
@Serializable
data class DailyLearningReport(
    val date: String, // YYYY-MM-DD format
    val lessonsCompleted: Int,
    val totalPointsEarned: Int,
    val totalTimeMinutes: Int,
    val subjects: List<String> = emptyList()
)