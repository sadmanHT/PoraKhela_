package com.porakhela.ui.onboarding

import androidx.lifecycle.viewModelScope
import com.porakhela.core.components.BaseViewModel
import com.porakhela.data.local.OnboardingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for onboarding flow
 */
@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingPreferences: OnboardingPreferences
) : BaseViewModel() {
    
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()
    
    private val _onboardingCompleted = MutableStateFlow(false)
    val onboardingCompleted: StateFlow<Boolean> = _onboardingCompleted.asStateFlow()
    
    init {
        checkOnboardingStatus()
    }
    
    private fun checkOnboardingStatus() {
        val isCompleted = onboardingPreferences.isOnboardingCompleted()
        _onboardingCompleted.value = isCompleted
        Timber.d("Onboarding completion status: $isCompleted")
    }
    
    fun setCurrentPage(page: Int) {
        _currentPage.value = page
        Timber.d("Onboarding page changed to: $page")
    }
    
    fun saveOnboardingData(
        parentName: String,
        parentPhone: String,
        childName: String,
        consentGiven: Boolean
    ) {
        launchWithErrorHandling(showLoading = true) {
            Timber.i("Saving onboarding data...")
            
            // Simulate saving delay for better UX
            delay(1500)
            
            // Save all data to SharedPreferences
            onboardingPreferences.saveOnboardingData(
                parentName = parentName,
                parentPhone = parentPhone,
                childName = childName,
                consentGiven = consentGiven
            )
            
            // Mark as completed
            _onboardingCompleted.value = true
            
            Timber.i("Onboarding data saved successfully")
            setSuccess(true)
        }
    }
    
    fun skipOnboarding() {
        launchWithErrorHandling {
            // Mark onboarding as completed but without full data
            onboardingPreferences.markOnboardingCompleted()
            _onboardingCompleted.value = true
            
            Timber.w("Onboarding skipped by user")
            setSuccess(true)
        }
    }
    
    fun getOnboardingData() = onboardingPreferences.getOnboardingData()
    
    fun isOnboardingCompleted() = onboardingPreferences.isOnboardingCompleted()
}