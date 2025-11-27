package com.porakhela.ui.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.local.UserPreferences
import com.porakhela.data.models.LeaderboardEntry
import com.porakhela.data.models.LeaderboardType
import com.porakhela.data.repository.LeaderboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for leaderboard system
 * Manages leaderboard data, UI state, and user interactions
 */
@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    
    // UI State
    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()
    
    // Error handling
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    // Leaderboard data
    private val _weeklyLeaderboard = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    private val _monthlyLeaderboard = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    private val _globalLeaderboard = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    
    // Current user rank tracking
    private val _currentUserRank = MutableStateFlow<Int?>(null)
    val currentUserRank: StateFlow<Int?> = _currentUserRank.asStateFlow()
    
    // Current leaderboard type
    private val _currentType = MutableStateFlow(LeaderboardType.WEEKLY)
    val currentType: StateFlow<LeaderboardType> = _currentType.asStateFlow()
    
    init {
        initializeUserInfo()
    }
    
    /**
     * Initialize user information
     */
    private fun initializeUserInfo() {
        val userStats = userPreferences.getUserStats()
        _uiState.value = _uiState.value.copy(
            currentUserName = userStats.childName,
            currentUserAvatar = userPreferences.getSelectedAvatar(),
            currentUserPoints = userStats.porapoints
        )
    }
    
    /**
     * Load all leaderboard types
     */
    fun loadAllLeaderboards() {
        loadLeaderboard(LeaderboardType.WEEKLY)
        loadLeaderboard(LeaderboardType.MONTHLY)
        loadLeaderboard(LeaderboardType.GLOBAL)
    }
    
    /**
     * Load specific leaderboard type
     */
    fun loadLeaderboard(type: LeaderboardType) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                val leaderboard = leaderboardRepository.getLeaderboard(type)
                updateLeaderboardData(type, leaderboard)
                
                // Update current user rank
                val userRank = leaderboard.find { it.isCurrentUser }?.rank
                _currentUserRank.value = userRank
                
                _uiState.value = _uiState.value.copy(isLoading = false)
                
                Timber.d("Loaded $type leaderboard with ${leaderboard.size} entries")
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                _errorMessage.value = "Failed to load leaderboard: ${e.message}"
                Timber.e(e, "Failed to load $type leaderboard")
            }
        }
    }
    
    /**
     * Refresh specific leaderboard with pull-to-refresh
     */
    fun refreshLeaderboard(type: LeaderboardType) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isRefreshing = true)
                
                val leaderboard = leaderboardRepository.simulatePullToRefresh(type)
                updateLeaderboardData(type, leaderboard)
                
                // Update current user rank
                val userRank = leaderboard.find { it.isCurrentUser }?.rank
                _currentUserRank.value = userRank
                
                _uiState.value = _uiState.value.copy(isRefreshing = false)
                
                Timber.d("Refreshed $type leaderboard with ${leaderboard.size} entries")
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isRefreshing = false)
                _errorMessage.value = "Failed to refresh leaderboard: ${e.message}"
                Timber.e(e, "Failed to refresh $type leaderboard")
            }
        }
    }
    
    /**
     * Select leaderboard type (for tab changes)
     */
    fun selectLeaderboardType(type: LeaderboardType) {
        _currentType.value = type
        
        // Update current user rank for selected type
        viewModelScope.launch {
            try {
                val rank = leaderboardRepository.getCurrentUserRank(type)
                _currentUserRank.value = rank
            } catch (e: Exception) {
                Timber.e(e, "Failed to get current user rank for $type")
            }
        }
    }
    
    /**
     * Get leaderboard data for specific type as Flow
     */
    fun getLeaderboardForType(type: LeaderboardType): StateFlow<List<LeaderboardEntry>> {
        return when (type) {
            LeaderboardType.WEEKLY -> _weeklyLeaderboard.asStateFlow()
            LeaderboardType.MONTHLY -> _monthlyLeaderboard.asStateFlow()
            LeaderboardType.GLOBAL -> _globalLeaderboard.asStateFlow()
        }
    }
    
    /**
     * Update leaderboard data based on type
     */
    private fun updateLeaderboardData(type: LeaderboardType, leaderboard: List<LeaderboardEntry>) {
        when (type) {
            LeaderboardType.WEEKLY -> _weeklyLeaderboard.value = leaderboard
            LeaderboardType.MONTHLY -> _monthlyLeaderboard.value = leaderboard
            LeaderboardType.GLOBAL -> _globalLeaderboard.value = leaderboard
        }
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }
    
    /**
     * Get cache status for debugging
     */
    fun getCacheStatus(): String {
        return leaderboardRepository.getCacheStatus()
    }
    
    /**
     * Clear all caches (for testing/reset)
     */
    fun clearAllCaches() {
        viewModelScope.launch {
            try {
                leaderboardRepository.clearAllCaches()
                loadAllLeaderboards()
                Timber.d("Cleared all leaderboard caches and reloaded data")
            } catch (e: Exception) {
                _errorMessage.value = "Failed to clear caches: ${e.message}"
                Timber.e(e, "Failed to clear caches")
            }
        }
    }
}

/**
 * UI State for leaderboard
 */
data class LeaderboardUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val currentUserName: String? = null,
    val currentUserAvatar: String? = null,
    val currentUserPoints: Int = 0,
    val selectedType: LeaderboardType = LeaderboardType.WEEKLY
)