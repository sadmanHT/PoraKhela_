package com.porakhela.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    
    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    protected val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    protected fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    protected fun setError(error: String?) {
        _error.value = error
    }

    protected fun clearError() {
        _error.value = null
    }

    protected fun launchSafe(
        showLoading: Boolean = true,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            try {
                if (showLoading) setLoading(true)
                clearError()
                block()
            } catch (e: Exception) {
                Timber.e(e, "Error in ViewModel operation")
                setError(e.message ?: "Unknown error occurred")
            } finally {
                if (showLoading) setLoading(false)
            }
        }
    }
}