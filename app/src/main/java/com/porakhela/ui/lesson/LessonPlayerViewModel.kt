package com.porakhela.ui.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.local.UserPreferences
import com.porakhela.data.models.Lesson
import com.porakhela.data.models.LessonAttempt
import com.porakhela.data.models.Question
import com.porakhela.data.models.QuestionAnswer
import com.porakhela.data.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Lesson Player with anti-cheat protection and scoring
 */
@HiltViewModel
class LessonPlayerViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<LessonPlayerState>(LessonPlayerState.Loading)
    val uiState: StateFlow<LessonPlayerState> = _uiState

    private val _currentQuestion = MutableStateFlow<Question?>(null)
    val currentQuestion: StateFlow<Question?> = _currentQuestion

    private val _questionProgress = MutableStateFlow(0)
    val questionProgress: StateFlow<Int> = _questionProgress

    private val _timerProgress = MutableStateFlow(100)
    val timerProgress: StateFlow<Int> = _timerProgress

    // Anti-cheat and game state
    private var isUIReady = false
    private var questionStartTime = 0L
    private var suspiciousActivityCount = 0
    private var timerJob: Job? = null
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var totalQuestions = 0
    private var lessonStartTime = 0L
    private var currentLesson: Lesson? = null
    private var questionAnswers = mutableListOf<QuestionAnswer>()

    // Anti-cheat configuration
    private val MIN_QUESTION_TIME_MS = 1000L // Minimum 1 second per question
    private val QUESTION_TIME_LIMIT_MS = 30000L // 30 seconds per question

    /**
     * Load lesson and start the experience
     */
    fun loadLesson(lessonId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = LessonPlayerState.Loading
                
                delay(500) // Ensure UI has time to fully load (anti-cheat)
                
                val result = lessonRepository.getLessonById(lessonId)
                
                result.fold(
                    onSuccess = { lesson ->
                        currentLesson = lesson
                        totalQuestions = lesson.questions.size
                        lessonStartTime = System.currentTimeMillis()
                        
                        if (lesson.questions.isNotEmpty()) {
                            _uiState.value = LessonPlayerState.Ready
                            startQuestion(0)
                        } else {
                            _uiState.value = LessonPlayerState.Error("No questions found in lesson")
                        }
                        
                        Timber.d("Lesson loaded: ${lesson.title} with ${lesson.questions.size} questions")
                    },
                    onFailure = { exception ->
                        _uiState.value = LessonPlayerState.Error("Failed to load lesson: ${exception.message}")
                        Timber.e(exception, "Failed to load lesson: $lessonId")
                    }
                )
            } catch (e: Exception) {
                _uiState.value = LessonPlayerState.Error("Unexpected error: ${e.message}")
                Timber.e(e, "Unexpected error loading lesson")
            }
        }
    }

    /**
     * Mark UI as ready (anti-cheat protection)
     */
    fun markUIReady() {
        isUIReady = true
        Timber.d("UI marked as ready for question answering")
    }

    /**
     * Answer a question with anti-cheat validation
     */
    fun answerQuestion(selectedOption: Int) {
        if (!isUIReady) {
            logSuspiciousActivity("Answer submitted before UI ready")
            return
        }

        val currentTime = System.currentTimeMillis()
        val timeTaken = currentTime - questionStartTime

        if (timeTaken < MIN_QUESTION_TIME_MS) {
            logSuspiciousActivity("Answer submitted too quickly: ${timeTaken}ms")
            // Still allow the answer but flag it
            suspiciousActivityCount++
        }
        
        // Performance monitoring
        try {
            val runtime = Runtime.getRuntime()
            val usedMemory = runtime.totalMemory() - runtime.freeMemory()
            val maxMemory = runtime.maxMemory()
            val memoryUsagePercent = (usedMemory * 100 / maxMemory).toInt()
            
            if (memoryUsagePercent > 80) {
                Timber.w("High memory usage: ${memoryUsagePercent}% (${usedMemory / 1024 / 1024}MB)")
            }
        } catch (e: Exception) {
            Timber.e(e, "Error checking memory usage")
        }

        val question = _currentQuestion.value ?: return
        val isCorrect = question.isCorrect(selectedOption)

        // Record answer
        val questionAnswer = QuestionAnswer(
            question_id = question.id,
            selected_option = selectedOption,
            is_correct = isCorrect,
            time_taken_ms = timeTaken,
            timestamp = currentTime
        )
        questionAnswers.add(questionAnswer)

        if (isCorrect) {
            correctAnswers++
            _uiState.value = LessonPlayerState.CorrectAnswer(selectedOption, question.correct_option)
        } else {
            _uiState.value = LessonPlayerState.WrongAnswer(selectedOption, question.correct_option)
        }

        // Stop timer
        timerJob?.cancel()

        Timber.d("Question ${currentQuestionIndex + 1} answered: ${if (isCorrect) "correct" else "wrong"} in ${timeTaken}ms")
    }

    /**
     * Move to next question or complete lesson
     */
    fun nextQuestion() {
        currentQuestionIndex++

        if (currentQuestionIndex >= totalQuestions) {
            completeLesson()
        } else {
            startQuestion(currentQuestionIndex)
        }
    }

    /**
     * Skip current question
     */
    fun skipQuestion() {
        val currentTime = System.currentTimeMillis()
        val timeTaken = currentTime - questionStartTime
        val question = _currentQuestion.value ?: return

        // Record skipped answer
        val questionAnswer = QuestionAnswer(
            question_id = question.id,
            selected_option = -1, // -1 indicates skipped
            is_correct = false,
            time_taken_ms = timeTaken,
            timestamp = currentTime
        )
        questionAnswers.add(questionAnswer)

        nextQuestion()
        Timber.d("Question ${currentQuestionIndex} skipped")
    }

    /**
     * Handle back press (anti-cheat protection)
     */
    fun onBackPressed(): Boolean {
        // Prevent back press during active question answering
        val currentState = _uiState.value
        when (currentState) {
            is LessonPlayerState.QuestionActive -> {
                logSuspiciousActivity("Back press blocked during active question")
                return false // Block back press
            }
            is LessonPlayerState.CorrectAnswer,
            is LessonPlayerState.WrongAnswer -> {
                logSuspiciousActivity("Back press blocked during answer feedback")
                return false // Block back press during feedback
            }
            else -> return true // Allow back press in other states
        }
    }

    /**
     * Start a specific question
     */
    private fun startQuestion(questionIndex: Int) {
        val lesson = currentLesson ?: return

        if (questionIndex >= lesson.questions.size) {
            completeLesson()
            return
        }

        val question = lesson.questions[questionIndex]
        currentQuestionIndex = questionIndex
        questionStartTime = System.currentTimeMillis()

        // Update UI state
        _currentQuestion.value = question
        _questionProgress.value = ((questionIndex + 1) * 100) / totalQuestions
        _uiState.value = LessonPlayerState.QuestionActive(questionIndex + 1, totalQuestions)

        // Start question timer
        startQuestionTimer()

        Timber.d("Started question ${questionIndex + 1} of $totalQuestions")
    }

    /**
     * Start timer for current question with lifecycle awareness
     */
    private fun startQuestionTimer() {
        timerJob?.cancel()
        _timerProgress.value = 100

        timerJob = viewModelScope.launch {
            val startTime = System.currentTimeMillis()
            val duration = QUESTION_TIME_LIMIT_MS

            try {
                while (true) {
                    val elapsed = System.currentTimeMillis() - startTime
                    val progress = ((duration - elapsed) * 100 / duration).coerceIn(0, 100).toInt()
                    
                    _timerProgress.value = progress

                    if (elapsed >= duration) {
                        Timber.d("Question timer expired")
                        handleTimeUp()
                        break
                    }

                    delay(50) // Update every 50ms for smoother animation
                }
            } catch (e: Exception) {
                Timber.e(e, "Error in question timer")
            }
        }
    }

    /**
     * Handle timer expiration
     */
    private fun handleTimeUp() {
        Timber.d("Question timer expired")
        skipQuestion() // Automatically skip when time runs out
    }

    /**
     * Complete the lesson and calculate score with thread safety
     */
    private fun completeLesson() {
        val lesson = currentLesson ?: return
        val completionTime = System.currentTimeMillis()
        val totalTime = completionTime - lessonStartTime
        
        try {
            // Calculate score using lesson's scoring rules
            val porapointsEarned = lesson.calculateScore(correctAnswers)
            
            // Award points to user with proper synchronization
            viewModelScope.launch {
                try {
                    userPreferences.addPorapoints(porapointsEarned)
                    userPreferences.incrementLessonsCompleted()
                    
                    // Create lesson attempt record
                    val attempt = LessonAttempt(
                        lesson_id = lesson.id,
                        start_time = lessonStartTime,
                        completion_time = completionTime,
                        correct_answers = correctAnswers,
                        total_questions = totalQuestions,
                        porapoints_earned = porapointsEarned,
                        completed = true,
                        answers = questionAnswers.toList()
                    )

                    _uiState.value = LessonPlayerState.Completed(
                        correctAnswers = correctAnswers,
                        totalQuestions = totalQuestions,
                        porapointsEarned = porapointsEarned,
                        timeTakenMs = totalTime,
                        lessonAttempt = attempt
                    )

                    Timber.i("Lesson completed: $correctAnswers/$totalQuestions correct, $porapointsEarned points earned")
                } catch (e: Exception) {
                    Timber.e(e, "Error completing lesson")
                    _uiState.value = LessonPlayerState.Error("Failed to save progress: ${e.message}")
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error in lesson completion")
            _uiState.value = LessonPlayerState.Error("Failed to complete lesson: ${e.message}")
        }
    }

    /**
     * Log suspicious activity for anti-cheat monitoring
     */
    private fun logSuspiciousActivity(activity: String) {
        val lesson = currentLesson
        Timber.w("SUSPICIOUS ACTIVITY: $activity | Lesson: ${lesson?.id} | Question: ${currentQuestionIndex + 1}")
        
        // In production, this could send to analytics/monitoring service
    }

    /**
     * Cancel active operations for cleanup
     */
    fun cancelActiveOperations() {
        timerJob?.cancel()
        timerJob = null
        Timber.d("Active operations cancelled")
    }

    /**
     * Clean up resources
     */
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

/**
 * UI States for Lesson Player
 */
sealed class LessonPlayerState {
    object Loading : LessonPlayerState()
    object Ready : LessonPlayerState()
    data class QuestionActive(val questionNumber: Int, val totalQuestions: Int) : LessonPlayerState()
    data class CorrectAnswer(val selectedOption: Int, val correctOption: Int) : LessonPlayerState()
    data class WrongAnswer(val selectedOption: Int, val correctOption: Int) : LessonPlayerState()
    data class Completed(
        val correctAnswers: Int,
        val totalQuestions: Int,
        val porapointsEarned: Int,
        val timeTakenMs: Long,
        val lessonAttempt: LessonAttempt
    ) : LessonPlayerState()
    data class Error(val message: String) : LessonPlayerState()
}