package com.porakhela.data.models

import kotlinx.serialization.Serializable

/**
 * Data model for subject categories loaded from JSON assets
 */
@Serializable
data class SubjectCategory(
    val id: String,
    val name: String,
    val description: String,
    val iconEmoji: String,
    val colorHex: String,
    val lessons: List<LessonInfo> = emptyList(),
    val isLocked: Boolean = false,
    val requiredLevel: Int = 1
)

/**
 * Data model for individual lesson info within a category
 */
@Serializable
data class LessonInfo(
    val id: String,
    val title: String,
    val description: String,
    val type: LessonType,
    val difficulty: Difficulty,
    val estimatedMinutes: Int,
    val porapointsReward: Int,
    val isCompleted: Boolean = false,
    val thumbnailUrl: String? = null
)

/**
 * Enum for different lesson types
 */
@Serializable
enum class LessonType {
    INTERACTIVE_GAME,
    VIDEO_LESSON,
    QUIZ,
    STORY,
    PRACTICE_EXERCISE
}

/**
 * Enum for lesson difficulty levels
 */
@Serializable
enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}

/**
 * Container for all subjects and their categories
 */
@Serializable
data class SubjectData(
    val subject: String,
    val displayName: String,
    val description: String,
    val iconEmoji: String,
    val colorHex: String,
    val categories: List<SubjectCategory>
)