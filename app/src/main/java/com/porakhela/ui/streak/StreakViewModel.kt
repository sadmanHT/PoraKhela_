package com.porakhela.ui.streak

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.tracking.StreakInfo
import com.porakhela.data.tracking.StreakManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreakViewModel @Inject constructor(
    private val streakManager: StreakManager
) : ViewModel() {
    
    private val _streakInfo = MutableStateFlow(StreakInfo(
        currentStreak = 0,
        maxStreak = 0,
        lessonsToday = 0,
        timeToday = 0L,
        isDateFrozen = false
    ))
    val streakInfo: StateFlow<StreakInfo> = _streakInfo.asStateFlow()
    
    init {
        refreshStreakInfo()
    }
    
    fun refreshStreakInfo() {
        viewModelScope.launch {
            val info = streakManager.getStreakInfo()
            _streakInfo.value = info
        }
    }
    
    fun getFormattedTimeToday(): String {
        val timeMs = _streakInfo.value.timeToday
        val minutes = (timeMs / 60000).toInt()
        val seconds = ((timeMs % 60000) / 1000).toInt()
        
        return when {
            minutes > 0 -> "${minutes}m ${seconds}s"
            else -> "${seconds}s"
        }
    }
    
    fun getStreakMessage(): String {
        val streak = _streakInfo.value.currentStreak
        return when {
            streak == 0 -> "Start your learning streak today!"
            streak == 1 -> "Great start! Keep it going tomorrow."
            streak < 7 -> "Building momentum! $streak days strong."
            streak < 30 -> "Amazing! $streak days of consistent learning."
            streak < 100 -> "Incredible dedication! $streak days streak."
            else -> "Legendary learner! $streak days of mastery."
        }
    }
}