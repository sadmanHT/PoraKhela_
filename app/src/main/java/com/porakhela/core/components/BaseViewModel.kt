package com.porakhela.core.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.core.utils.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Base ViewModel providing common functionality for all ViewModels in the app
 * Includes error handling and loading state management
 */
abstract class BaseViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow<UiState<Any>>(UiState.Idle)
    val uiState: StateFlow<UiState<Any>> = _uiState.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    /**
     * Global exception handler for coroutines launched in this ViewModel
     */
    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, "Unhandled exception in ${this::class.simpleName}")
        handleError(exception)
    }
    
    /**
     * Launch a coroutine with built-in error handling
     */
    protected fun launchWithErrorHandling(
        showLoading: Boolean = true,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(exceptionHandler) {
            try {
                if (showLoading) setLoading(true)
                clearError()
                block()
            } catch (e: Exception) {
                handleError(e)
            } finally {
                if (showLoading) setLoading(false)
            }
        }
    }
    
    /**
     * Set loading state
     */
    protected fun setLoading(loading: Boolean) {
        _isLoading.value = loading
        if (loading) {
            _uiState.value = UiState.Loading
        }
    }
    
    /**
     * Handle errors consistently across ViewModels
     */
    protected fun handleError(throwable: Throwable) {
        val message = when (throwable) {
            is java.net.UnknownHostException -> "No internet connection"
            is java.net.SocketTimeoutException -> "Request timeout. Please try again"
            is java.io.IOException -> "Network error. Please check your connection"
            else -> throwable.message ?: "An unexpected error occurred"
        }
        
        Timber.e(throwable, "Error in ${this::class.simpleName}: $message")
        _errorMessage.value = message
        _uiState.value = UiState.Error(throwable, message)
    }
    
    /**
     * Clear any current error state
     */
    fun clearError() {
        _errorMessage.value = null
        if (_uiState.value is UiState.Error) {
            _uiState.value = UiState.Idle
        }
    }
    
    /**
     * Set success state with data
     */
    protected fun <T> setSuccess(data: T) {
        _uiState.value = UiState.Success(data as Any)
    }
    
    /**
     * Reset to idle state
     */
    protected fun resetState() {
        _uiState.value = UiState.Idle
        _isLoading.value = false
        _errorMessage.value = null
    }
}