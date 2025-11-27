package com.porakhela.data.tracking

import android.os.SystemClock
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.porakhela.data.local.StreakPreferences
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Tracks time spent in lessons with lifecycle awareness
 * Handles app pauses, minimization, and kills
 */
@Singleton
class TimeTracker @Inject constructor(
    private val streakPreferences: StreakPreferences
) : DefaultLifecycleObserver {
    
    private var sessionStartTime: Long = 0L
    private var isTracking: Boolean = false
    private var accumulatedTime: Long = 0L
    
    companion object {
        private const val MIN_VALID_SESSION_MS = 1000L // 1 second minimum
        private const val MAX_VALID_SESSION_MS = 3600000L // 1 hour maximum
    }
    
    fun startTracking() {
        if (!isTracking) {
            sessionStartTime = SystemClock.elapsedRealtime()
            isTracking = true
            Timber.d("Started time tracking")
        }
    }
    
    fun pauseTracking() {
        if (isTracking) {
            val sessionDuration = SystemClock.elapsedRealtime() - sessionStartTime
            if (sessionDuration in MIN_VALID_SESSION_MS..MAX_VALID_SESSION_MS) {
                accumulatedTime += sessionDuration
                Timber.d("Paused tracking, session duration: ${sessionDuration}ms")
            } else {
                Timber.w("Invalid session duration: ${sessionDuration}ms, not counting")
            }
            isTracking = false
        }
    }
    
    fun stopTracking() {
        if (isTracking) {
            pauseTracking()
        }
        
        if (accumulatedTime > 0) {
            streakPreferences.addTimeToday(accumulatedTime)
            Timber.d("Stopped tracking, total accumulated: ${accumulatedTime}ms")
            accumulatedTime = 0L
        }
    }
    
    fun getCurrentSessionTime(): Long {
        return if (isTracking) {
            SystemClock.elapsedRealtime() - sessionStartTime
        } else {
            0L
        }
    }
    
    fun getTotalAccumulatedTime(): Long {
        return accumulatedTime + getCurrentSessionTime()
    }
    
    // Lifecycle observers for automatic pause/resume
    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        pauseTracking()
        Timber.d("Lifecycle onPause - tracking paused")
    }
    
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        // Don't auto-resume tracking - let the lesson fragment control this
        Timber.d("Lifecycle onResume - tracking remains paused until explicitly started")
    }
    
    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        pauseTracking()
        Timber.d("Lifecycle onStop - tracking paused")
    }
    
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        stopTracking()
        Timber.d("Lifecycle onDestroy - tracking stopped")
    }
}