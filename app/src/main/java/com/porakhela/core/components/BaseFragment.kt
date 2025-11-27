package com.porakhela.core.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.porakhela.core.utils.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Base Fragment providing common functionality for all fragments
 * Includes ViewBinding setup and error handling
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment() {
    
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    
    protected abstract val viewModel: VM
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return try {
            _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            Timber.d("${this::class.simpleName} view created successfully")
            binding.root
        } catch (e: Exception) {
            Timber.e(e, "Error creating view for ${this::class.simpleName}")
            null
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initializeUI()
            setupObservers()
            Timber.d("${this::class.simpleName} view setup completed")
        } catch (e: Exception) {
            Timber.e(e, "Error setting up ${this::class.simpleName}")
            handleInitializationError(e)
        }
    }
    
    /**
     * Initialize UI components - override in child fragments
     */
    protected open fun initializeUI() {
        // Override in child fragments
    }
    
    /**
     * Set up data observers - override in child fragments
     */
    protected open fun setupObservers() {
        // Override in child fragments
    }
    
    /**
     * Handle errors that occur during fragment initialization
     */
    protected open fun handleInitializationError(error: Throwable) {
        Timber.e(error, "Failed to initialize ${this::class.simpleName}")
        // Could show error state or navigate back
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
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Timber.d("${this::class.simpleName} view destroyed")
    }
}