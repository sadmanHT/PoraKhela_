package com.porakhela.core.utils

/**
 * Sealed class representing different UI states for consistent state management
 * across the application
 */
sealed class UiState<out T> {
    /**
     * Initial state before any operation
     */
    object Idle : UiState<Nothing>()
    
    /**
     * Loading state during async operations
     */
    object Loading : UiState<Nothing>()
    
    /**
     * Success state with data
     * @param data The successful result data
     */
    data class Success<T>(val data: T) : UiState<T>()
    
    /**
     * Error state with error information
     * @param exception The exception that occurred
     * @param message User-friendly error message
     */
    data class Error(
        val exception: Throwable? = null,
        val message: String = "Something went wrong"
    ) : UiState<Nothing>()
    
    /**
     * Check if current state is loading
     */
    val isLoading: Boolean
        get() = this is Loading
    
    /**
     * Check if current state is success
     */
    val isSuccess: Boolean
        get() = this is Success
    
    /**
     * Check if current state is error
     */
    val isError: Boolean
        get() = this is Error
    
    /**
     * Get data if state is success, null otherwise
     */
    fun getDataOrNull(): T? = if (this is Success) data else null
    
    /**
     * Get error message if state is error, null otherwise
     */
    fun getErrorOrNull(): String? = if (this is Error) message else null
}