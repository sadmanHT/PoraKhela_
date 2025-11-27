package com.porakhela.data.models

import kotlinx.serialization.Serializable

/**
 * Data models for the lesson player system
 */
@Serializable
data class Question(
    val id: String,
    val question_text: String,
    val options: List<String>, // A, B, C, D options
    val correct_option: Int, // 0-based index (0=A, 1=B, 2=C, 3=D)
    val difficulty: String = "EASY",
    val category: String = "GENERAL",
    val explanation: String? = null
) {
    fun isCorrect(selectedOption: Int): Boolean = selectedOption == correct_option
    
    fun getCorrectOptionText(): String = options.getOrNull(correct_option) ?: ""
}

@Serializable
data class Lesson(
    val id: String,
    val title: String,
    val subject: String,
    val category: String,
    val difficulty: String,
    val questions: List<Question>,
    val estimated_minutes: Int,
    val porapoints_base: Int = 10, // Base points for completion
    val porapoints_per_question: Int = 5 // Points per correct answer
) {
    fun getTotalPossiblePoints(): Int = (questions.size * porapoints_per_question) + porapoints_base
    
    fun calculateScore(correctAnswers: Int): Int {
        val questionPoints = correctAnswers * porapoints_per_question
        val completionBonus = if (correctAnswers > 0) porapoints_base else 0
        return questionPoints + completionBonus
    }
}

@Serializable
data class LessonAttempt(
    val lesson_id: String,
    val start_time: Long,
    val completion_time: Long? = null,
    val correct_answers: Int = 0,
    val total_questions: Int,
    val porapoints_earned: Int = 0,
    val completed: Boolean = false,
    val answers: List<QuestionAnswer> = emptyList()
)

@Serializable
data class QuestionAnswer(
    val question_id: String,
    val selected_option: Int,
    val is_correct: Boolean,
    val time_taken_ms: Long,
    val timestamp: Long = System.currentTimeMillis()
)