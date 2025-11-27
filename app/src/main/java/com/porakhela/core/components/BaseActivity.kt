package com.porakhela.core.components

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.porakhela.core.utils.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Base Activity providing common functionality for all activities
 * Includes ViewBinding setup and error handling
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {
    
    protected lateinit var binding: VB
    protected abstract val viewModel: VM
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Set up ViewBinding
            binding = DataBindingUtil.setContentView(this, layoutId)
            binding.lifecycleOwner = this
            
            // Initialize UI
            initializeUI()
            
            // Set up observers
            setupObservers()
            
            Timber.d("${this::class.simpleName} created successfully")
        } catch (e: Exception) {
            Timber.e(e, "Error initializing ${this::class.simpleName}")
            handleInitializationError(e)
        }
    }
    
    /**
     * Initialize UI components - override in child activities
     */
    protected open fun initializeUI() {
        // Override in child activities
    }
    
    /**
     * Set up data observers - override in child activities
     */
    protected open fun setupObservers() {
        // Override in child activities
    }
    
    /**
     * Handle errors that occur during activity initialization
     */
    protected open fun handleInitializationError(error: Throwable) {
        Timber.e(error, "Failed to initialize ${this::class.simpleName}")
        // Could show error dialog or finish activity
        finish()
    }
    
    /**
     * Helper method to observe UI state changes
     */
    protected fun <T> observeUiState(
        stateFlow: StateFlow<UiState<T>>,
        onLoading: (() -> Unit)? = null,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((String) -> Unit)? = null,
        onIdle: (() -> Unit)? = null
    ) {
        lifecycleScope.launch {
            stateFlow.collect { state ->
                when (state) {
                    is UiState.Idle -> onIdle?.invoke()
                    is UiState.Loading -> onLoading?.invoke()
                    is UiState.Success -> onSuccess?.invoke(state.data)
                    is UiState.Error -> onError?.invoke(state.message)
                }
            }
        }
    }
    
    /**
     * Helper method to observe simple state flows
     */
    protected fun <T> observeStateFlow(
        stateFlow: StateFlow<T>,
        onStateChanged: (T) -> Unit
    ) {
        lifecycleScope.launch {
            stateFlow.collect { state ->
                onStateChanged(state)
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::binding.isInitialized) {
            binding.unbind()
        }
        Timber.d("${this::class.simpleName} destroyed")
    }
}