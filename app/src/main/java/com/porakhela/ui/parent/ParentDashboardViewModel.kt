package com.porakhela.ui.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.repository.ParentDashboardRepository
import com.porakhela.data.local.DailyLearningReport
import com.porakhela.data.repository.ChildProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ParentDashboardViewModel @Inject constructor(
    private val repository: ParentDashboardRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ParentDashboardUiState())
    val uiState: StateFlow<ParentDashboardUiState> = _uiState.asStateFlow()
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    init {
        checkInitialAuthState()
    }
    
    private fun checkInitialAuthState() {
        val hasPin = repository.hasParentPin()
        _authState.value = _authState.value.copy(
            hasPin = hasPin,
            isInCooldown = repository.isInCooldown(),
            failedAttempts = repository.getFailedAttempts()
        )
        
        if (repository.isInCooldown()) {
            startCooldownTimer()
        }
    }
    
    fun createPin(pin: String) {
        if (pin.length == 4 && pin.all { it.isDigit() }) {
            repository.setParentPin(pin)
            _authState.value = _authState.value.copy(
                hasPin = true,
                isAuthenticated = true,
                showError = false,
                errorMessage = ""
            )
            loadDashboardData()
        } else {
            _authState.value = _authState.value.copy(
                showError = true,
                errorMessage = "PIN must be 4 digits"
            )
        }
    }
    
    fun authenticatePin(pin: String) {
        if (repository.isInCooldown()) {
            _authState.value = _authState.value.copy(
                showError = true,
                errorMessage = "Too many failed attempts. Try again in ${repository.getCooldownRemainingSeconds()} seconds"
            )
            return
        }
        
        viewModelScope.launch {
            val isValid = repository.authenticatePin(pin)
            if (isValid) {
                _authState.value = _authState.value.copy(
                    isAuthenticated = true,
                    showError = false,
                    errorMessage = "",
                    failedAttempts = 0
                )
                loadDashboardData()
            } else {
                val attempts = repository.getFailedAttempts()
                _authState.value = _authState.value.copy(
                    showError = true,
                    errorMessage = if (attempts >= 3) "Too many attempts. Locked for 30 seconds" else "Incorrect PIN. ${3 - attempts} attempts remaining",
                    failedAttempts = attempts,
                    shouldShake = true
                )
                
                if (attempts >= 3) {
                    _authState.value = _authState.value.copy(isInCooldown = true)
                    startCooldownTimer()
                }
            }
        }
    }
    
    private fun startCooldownTimer() {
        viewModelScope.launch {
            while (repository.isInCooldown()) {
                val remaining = repository.getCooldownRemainingSeconds()
                _authState.value = _authState.value.copy(
                    cooldownSeconds = remaining,
                    isInCooldown = remaining > 0
                )
                delay(1000)
            }
            _authState.value = _authState.value.copy(
                isInCooldown = false,
                cooldownSeconds = 0
            )
        }
    }
    
    fun clearShakeAnimation() {
        _authState.value = _authState.value.copy(shouldShake = false)
    }
    
    fun logout() {
        _authState.value = _authState.value.copy(
            isAuthenticated = false,
            showError = false,
            errorMessage = ""
        )
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                
                val childProgress = repository.getChildProgress()
                val dailyReports = repository.getDailyReports()
                val todayScreenTime = repository.getTodayScreenTime()
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    childProgress = childProgress,
                    dailyReports = dailyReports,
                    dailyLimit = repository.getDailyLimit(),
                    contentLockEnabled = repository.isContentLockEnabled(),
                    approvalRequired = repository.isApprovalRequired(),
                    todayScreenTime = todayScreenTime
                )
            } catch (e: Exception) {
                Timber.e(e, "Error loading dashboard data")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    showError = true,
                    errorMessage = "Failed to load data: ${e.message}"
                )
            }
        }
    }
    
    fun updateDailyLimit(minutes: Int) {
        repository.setDailyLimit(minutes)
        _uiState.value = _uiState.value.copy(dailyLimit = minutes)
    }
    
    fun updateContentLock(enabled: Boolean) {
        repository.setContentLock(enabled)
        _uiState.value = _uiState.value.copy(contentLockEnabled = enabled)
    }
    
    fun updateApprovalRequired(required: Boolean) {
        repository.setApprovalRequired(required)
        _uiState.value = _uiState.value.copy(approvalRequired = required)
    }
    
    fun refreshData() {
        loadDashboardData()
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(showError = false, errorMessage = "")
    }
}

data class ParentDashboardUiState(
    val isLoading: Boolean = false,
    val childProgress: ChildProgress? = null,
    val dailyReports: List<DailyLearningReport> = emptyList(),
    val dailyLimit: Int = 60,
    val contentLockEnabled: Boolean = false,
    val approvalRequired: Boolean = true,
    val todayScreenTime: Int = 0,
    val showError: Boolean = false,
    val errorMessage: String = ""
)

data class AuthState(
    val hasPin: Boolean = false,
    val isAuthenticated: Boolean = false,
    val isInCooldown: Boolean = false,
    val failedAttempts: Int = 0,
    val cooldownSeconds: Int = 0,
    val showError: Boolean = false,
    val errorMessage: String = "",
    val shouldShake: Boolean = false
)