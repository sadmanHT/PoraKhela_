package com.porakhela.data.repository

import android.content.Context
import com.porakhela.data.models.Lesson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for loading lesson data from JSON assets
 */
@Singleton
class LessonRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    
    /**
     * Load lesson by ID from assets
     */
    suspend fun getLessonById(lessonId: String): Result<Lesson> = withContext(Dispatchers.IO) {
        try {
            // For demo, we'll use a simple mapping
            val fileName = when {
                lessonId.contains("math") -> "math_lessons.json"
                lessonId.contains("english") -> "english_lessons.json"
                lessonId.contains("science") -> "science_lessons.json"
                lessonId.contains("social") -> "social_studies_lessons.json"
                else -> "sample_lessons.json"
            }
            
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val lessons = json.decodeFromString<List<Lesson>>(jsonString)
            
            val lesson = lessons.find { it.id == lessonId } 
                ?: lessons.firstOrNull()
                ?: createSampleLesson(lessonId)
            
            Timber.d("Successfully loaded lesson: ${lesson.title} with ${lesson.questions.size} questions")
            Result.success(lesson)
        } catch (e: Exception) {
            Timber.e(e, "Failed to load lesson: $lessonId")
            // Create a sample lesson as fallback
            Result.success(createSampleLesson(lessonId))
        }
    }
    
    /**
     * Create a sample lesson for testing/fallback
     */
    private fun createSampleLesson(lessonId: String): Lesson {
        return Lesson(
            id = lessonId,
            title = "Sample Math Lesson",
            subject = "math",
            category = "basic_numbers",
            difficulty = "EASY",
            estimated_minutes = 5,
            porapoints_base = 10,
            porapoints_per_question = 5,
            questions = listOf(
                com.porakhela.data.models.Question(
                    id = "q1",
                    question_text = "What is 2 + 2?",
                    options = listOf("3", "4", "5", "6"),
                    correct_option = 1
                ),
                com.porakhela.data.models.Question(
                    id = "q2", 
                    question_text = "Which number comes after 5?",
                    options = listOf("4", "6", "7", "8"),
                    correct_option = 1
                ),
                com.porakhela.data.models.Question(
                    id = "q3",
                    question_text = "What shape has 3 sides?",
                    options = listOf("Circle", "Square", "Triangle", "Rectangle"),
                    correct_option = 2
                ),
                com.porakhela.data.models.Question(
                    id = "q4",
                    question_text = "How many fingers are on one hand?",
                    options = listOf("4", "5", "6", "10"),
                    correct_option = 1
                ),
                com.porakhela.data.models.Question(
                    id = "q5",
                    question_text = "What is 1 + 1?",
                    options = listOf("1", "2", "3", "4"),
                    correct_option = 1
                ),
                com.porakhela.data.models.Question(
                    id = "q6",
                    question_text = "Which number is bigger?",
                    options = listOf("7", "3", "Both same", "Cannot tell"),
                    correct_option = 0
                )
            )
        )
    }
}