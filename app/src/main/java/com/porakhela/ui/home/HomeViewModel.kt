package com.porakhela.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.local.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Home screen with user data management
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {
    
    private val _userStats = MutableStateFlow<com.porakhela.data.local.UserStats?>(null)
    val userStats: StateFlow<com.porakhela.data.local.UserStats?> = _userStats
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    
    init {
        loadUserStats()
    }
    
    /**
     * Load user statistics from preferences
     */
    fun loadUserStats() {
        viewModelScope.launch {
            try {
                val stats = userPreferences.getUserStats()
                _userStats.value = stats
                _errorMessage.value = null
                
                Timber.d("User stats loaded: ${stats.porapoints} points, ${stats.dailyStreak} day streak")
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load user stats: ${e.message}"
                Timber.e(e, "Error loading user stats")
            }
        }
    }
    
    /**
     * Award points to user and update daily streak if needed
     */
    fun awardPoints(points: Int, reason: String = "Learning activity") {
        viewModelScope.launch {
            try {
                userPreferences.addPorapoints(points)
                
                // Update activity date and check streak
                if (!userPreferences.wasActiveToday()) {
                    userPreferences.incrementDailyStreak()
                }
                userPreferences.updateLastActivityDate()
                
                // Reload stats
                loadUserStats()
                
                Timber.i("Awarded $points points for: $reason")
            } catch (e: Exception) {
                _errorMessage.value = "Failed to award points: ${e.message}"
                Timber.e(e, "Error awarding points")
            }
        }
    }
    
    /**
     * Handle subject card selection
     */
    fun onSubjectSelected(subject: String) {
        viewModelScope.launch {
            try {
                // Track subject preference
                val currentFavorite = userPreferences.getFavoriteSubject()
                if (currentFavorite != subject) {
                    userPreferences.setFavoriteSubject(subject)
                }
                
                // Update activity if first action today
                if (!userPreferences.wasActiveToday()) {
                    userPreferences.incrementDailyStreak()
                    userPreferences.updateLastActivityDate()
                    loadUserStats()
                }
                
                Timber.d("Subject selected: $subject")
            } catch (e: Exception) {
                _errorMessage.value = "Failed to handle subject selection: ${e.message}"
                Timber.e(e, "Error handling subject selection")
            }
        }
    }
    
    /**
     * Get motivational message based on user stats
     */
    fun getMotivationalMessage(): String {
        return try {
            val stats = _userStats.value
            stats?.getMotivationalMessage() ?: "Ready to learn something new? 🚀"
        } catch (e: Exception) {
            Timber.e(e, "Error getting motivational message")
            "Ready to learn something new? 🚀"
        }
    }
    
    /**
     * Get user level based on porapoints
     */
    fun getUserLevel(): Int {
        return try {
            val stats = _userStats.value
            stats?.getUserLevel() ?: 1
        } catch (e: Exception) {
            Timber.e(e, "Error getting user level")
            1
        }
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }
}