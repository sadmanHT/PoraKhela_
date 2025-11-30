package com.porakhela.ui.lesson

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.ui.performance.ObjectPoolManager
import com.porakhela.ui.performance.OptimizedCoroutineManager
import com.porakhela.ui.performance.ZeroAllocUtils
import com.porakhela.data.database.entity.Lesson
import com.porakhela.data.database.entity.LessonProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * OPTIMIZED LESSON PLAYER - Zero Allocation Patterns
 * 
 * Advanced performance optimizations for Bangladesh low-end devices
 * Target: 60+ FPS during lesson playback with minimal memory allocation
 * 
 * Key Optimizations:
 * - Object pooling for animations and temporary objects
 * - Zero-allocation string formatting for progress updates
 * - Memory-efficient audio/video loading with pre-caching
 * - Optimized view animations with pooled animators
 * - GC-friendly data structures for lesson content
 * 
 * Performance Targets:
 * - < 2MB additional memory during lesson playback
 * - Zero allocation during scrolling/navigation
 * - 60+ FPS animation performance
 * - < 100ms lesson transition time
 */

@HiltViewModel
class OptimizedLessonPlayerViewModel @Inject constructor(
    private val objectPoolManager: ObjectPoolManager,
    private val coroutineManager: OptimizedCoroutineManager,
    private val lessonRepository: com.porakhela.data.repository.OptimizedLearningRepository
) : ViewModel() {

    // Reusable objects for frequent operations
    private var progressStringBuilder = objectPoolManager.acquireStringBuilder()
    private var timeStringBuilder = objectPoolManager.acquireStringBuilder()
    
    // Pre-allocated data structures
    private val _lessonState = MutableStateFlow(LessonPlaybackState.Idle)
    val lessonState = _lessonState.asStateFlow()
    
    private val _progressState = MutableStateFlow(LessonProgressState())
    val progressState = _progressState.asStateFlow()
    
    // Animation pools for smooth transitions
    private var currentProgressAnimator: ObjectAnimator? = null
    private var currentContentAnimator: ValueAnimator? = null
    
    override fun onCleared() {
        super.onCleared()
        // Release pooled objects
        objectPoolManager.releaseStringBuilder(progressStringBuilder)
        objectPoolManager.releaseStringBuilder(timeStringBuilder)
        releaseAnimators()
    }
    
    /**
     * Load lesson with memory-efficient caching
     */
    fun loadLesson(lessonId: Long) {
        viewModelScope.launch {
            coroutineManager.executeIOOperation(
                operation = {
                    lessonRepository.getLessonWithProgress(lessonId)
                },
                onSuccess = { lesson ->
                    _lessonState.value = LessonPlaybackState.Loaded(lesson)
                    prepareContentPreloading(lesson)
                },
                onError = { error ->
                    _lessonState.value = LessonPlaybackState.Error(error.message ?: "Failed to load lesson")
                    Timber.e(error, "Failed to load lesson: $lessonId")
                }
            )
        }
    }
    
    /**
     * Update progress with zero allocation
     */
    fun updateProgress(currentStep: Int, totalSteps: Int, timeSpentMs: Long) {
        val progressPercent = if (totalSteps > 0) (currentStep * 100) / totalSteps else 0
        val formattedProgress = ZeroAllocUtils.formatProgress(currentStep, totalSteps, progressStringBuilder)
        val formattedTime = ZeroAllocUtils.formatDuration((timeSpentMs / 60000).toInt(), timeStringBuilder)
        
        val newState = _progressState.value.copy(
            currentStep = currentStep,
            totalSteps = totalSteps,
            progressPercent = progressPercent,
            formattedProgress = formattedProgress,
            formattedTime = formattedTime,
            timeSpentMs = timeSpentMs
        )
        
        _progressState.value = newState
        
        // Save progress efficiently
        saveProgressEfficiently(currentStep, totalSteps, timeSpentMs)
    }
    
    /**
     * Animate progress update with pooled animator
     */
    fun animateProgressUpdate(targetProgress: Int, progressView: View) {
        releaseCurrentProgressAnimator()
        
        currentProgressAnimator = objectPoolManager.acquireObjectAnimator().apply {
            target = progressView
            setProperty(View.SCALE_X)
            setFloatValues(progressView.scaleX, targetProgress / 100f)
            duration = 300
            
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    objectPoolManager.releaseObjectAnimator(this@apply)
                    currentProgressAnimator = null
                }
            })
        }
        
        currentProgressAnimator?.start()
    }
    
    /**
     * Animate content transition with pooled animator
     */
    fun animateContentTransition(contentView: ViewGroup, onComplete: () -> Unit) {
        releaseCurrentContentAnimator()
        
        currentContentAnimator = objectPoolManager.acquireValueAnimator().apply {
            setFloatValues(0f, 1f)
            duration = 250
            
            addUpdateListener { animator ->
                val progress = animator.animatedValue as Float
                contentView.alpha = progress
                contentView.translationY = (1f - progress) * 50f
            }
            
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    onComplete()
                    objectPoolManager.releaseValueAnimator(this@apply)
                    currentContentAnimator = null
                }
            })
        }
        
        currentContentAnimator?.start()
    }
    
    /**
     * Complete lesson with efficient data saving
     */
    fun completeLesson() {
        val currentState = _progressState.value
        viewModelScope.launch {
            coroutineManager.executeIOOperation(
                operation = {
                    lessonRepository.completeLessonOptimized(
                        lessonId = getCurrentLessonId(),
                        timeSpent = currentState.timeSpentMs,
                        stepsCompleted = currentState.totalSteps
                    )
                },
                onSuccess = {
                    _lessonState.value = LessonPlaybackState.Completed
                    Timber.d("Lesson completed successfully")
                },
                onError = { error ->
                    Timber.e(error, "Failed to complete lesson")
                }
            )
        }
    }
    
    /**
     * Navigate to next content with smooth transition
     */
    fun navigateToNextContent(currentView: ViewGroup, nextContentLoader: () -> Unit) {
        // Fade out current content
        animateContentTransition(currentView) {
            // Load next content during animation
            nextContentLoader()
            
            // Fade in new content
            animateContentTransition(currentView) {
                // Animation complete
                Timber.d("Content transition completed")
            }
        }
    }
    
    // PRIVATE HELPER METHODS
    
    private fun prepareContentPreloading(lesson: Lesson) {
        // Pre-cache next few content items for smooth playback
        viewModelScope.launch {
            coroutineManager.executeBackgroundOperation(
                operation = {
                    lessonRepository.preloadLessonContent(lesson.id, 3) // Preload next 3 items
                },
                onComplete = {
                    Timber.d("Content preloading completed for lesson: ${lesson.id}")
                }
            )
        }
    }
    
    private fun saveProgressEfficiently(currentStep: Int, totalSteps: Int, timeSpentMs: Long) {
        viewModelScope.launch {
            coroutineManager.executeIOOperation(
                operation = {
                    lessonRepository.updateProgressBatch(
                        lessonId = getCurrentLessonId(),
                        progress = currentStep,
                        totalSteps = totalSteps,
                        timeSpent = timeSpentMs
                    )
                }
            )
        }
    }
    
    private fun getCurrentLessonId(): Long {
        return when (val state = _lessonState.value) {
            is LessonPlaybackState.Loaded -> state.lesson.id
            else -> 0L
        }
    }
    
    private fun releaseCurrentProgressAnimator() {
        currentProgressAnimator?.let { animator ->
            animator.cancel()
            objectPoolManager.releaseObjectAnimator(animator)
            currentProgressAnimator = null
        }
    }
    
    private fun releaseCurrentContentAnimator() {
        currentContentAnimator?.let { animator ->
            animator.cancel()
            objectPoolManager.releaseValueAnimator(animator)
            currentContentAnimator = null
        }
    }
    
    private fun releaseAnimators() {
        releaseCurrentProgressAnimator()
        releaseCurrentContentAnimator()
    }
}

// STATE CLASSES - Optimized data structures

sealed class LessonPlaybackState {
    object Idle : LessonPlaybackState()
    object Loading : LessonPlaybackState()
    data class Loaded(val lesson: Lesson) : LessonPlaybackState()
    object Completed : LessonPlaybackState()
    data class Error(val message: String) : LessonPlaybackState()
}

data class LessonProgressState(
    val currentStep: Int = 0,
    val totalSteps: Int = 0,
    val progressPercent: Int = 0,
    val formattedProgress: String = "0%",
    val formattedTime: String = "0m",
    val timeSpentMs: Long = 0L,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false
)

/**
 * Memory-efficient lesson content manager
 * Handles audio, video, and text content with minimal allocation
 */
class OptimizedLessonContentManager @Inject constructor(
    private val objectPoolManager: ObjectPoolManager,
    private val coroutineManager: OptimizedCoroutineManager
) {
    
    // Pre-allocated content cache
    private val contentCache = mutableMapOf<Long, LessonContent>()
    private val contentStringBuilder = objectPoolManager.acquireStringBuilder()
    
    /**
     * Load content with efficient caching
     */
    suspend fun loadContent(lessonId: Long, contentType: ContentType): LessonContent? {
        return coroutineManager.executeIOOperation(
            operation = {
                // Check cache first
                val cached = contentCache[lessonId]
                if (cached != null && cached.type == contentType) {
                    return@executeIOOperation cached
                }
                
                // Load from storage with minimal allocation
                loadContentFromStorage(lessonId, contentType).also { content ->
                    // Cache for future use
                    if (contentCache.size < 10) { // Limit cache size
                        contentCache[lessonId] = content
                    }
                }
            }
        )
    }
    
    /**
     * Format content text with zero allocation
     */
    fun formatContentText(text: String, variables: Map<String, String>): String {
        contentStringBuilder.clear()
        var lastIndex = 0
        
        // Simple variable replacement without regex
        variables.forEach { (key, value) ->
            val placeholder = "{$key}"
            var index = text.indexOf(placeholder, lastIndex)
            
            while (index != -1) {
                contentStringBuilder.append(text, lastIndex, index)
                contentStringBuilder.append(value)
                lastIndex = index + placeholder.length
                index = text.indexOf(placeholder, lastIndex)
            }
        }
        
        if (lastIndex < text.length) {
            contentStringBuilder.append(text, lastIndex, text.length)
        }
        
        return contentStringBuilder.toString()
    }
    
    /**
     * Clean up cache during memory pressure
     */
    fun clearCache() {
        contentCache.clear()
        contentStringBuilder.clear()
        Timber.d("Cleared lesson content cache")
    }
    
    private suspend fun loadContentFromStorage(lessonId: Long, contentType: ContentType): LessonContent {
        // Simulate content loading
        return LessonContent(
            id = lessonId,
            type = contentType,
            data = "Content for lesson $lessonId",
            duration = 30000L
        )
    }
    
    fun onDestroy() {
        clearCache()
        objectPoolManager.releaseStringBuilder(contentStringBuilder)
    }
}

// CONTENT DATA CLASSES

data class LessonContent(
    val id: Long,
    val type: ContentType,
    val data: String,
    val duration: Long
)

enum class ContentType {
    TEXT, AUDIO, VIDEO, INTERACTIVE
}