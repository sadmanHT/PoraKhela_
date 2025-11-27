package com.porakhela.ui.main

import com.porakhela.core.components.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel for MainActivity
 * Manages main activity state and navigation
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    // Add repositories here when needed
) : BaseViewModel() {
    
    private val _currentTab = MutableStateFlow(CurrentTab.HOME)
    val currentTab: StateFlow<CurrentTab> = _currentTab.asStateFlow()
    
    fun setCurrentTab(tab: CurrentTab) {
        _currentTab.value = tab
    }
    
    enum class CurrentTab {
        HOME,
        LESSONS,
        REWARDS,
        PROFILE
    }
}