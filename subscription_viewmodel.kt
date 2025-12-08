package com.porakhela.ui.subscription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.api.ApplinkConfig
import com.porakhela.data.models.SubscriptionResponse
import com.porakhela.data.repository.ApplinkRepositoryImpl
import com.porakhela.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Subscription management
 */
@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val applinkRepository: ApplinkRepositoryImpl
) : ViewModel() {
    
    private val _subscriptionState = MutableStateFlow<SubscriptionState>(SubscriptionState.Idle)
    val subscriptionState: StateFlow<SubscriptionState> = _subscriptionState.asStateFlow()
    
    private val _currentSubscription = MutableStateFlow<SubscriptionResponse?>(null)
    val currentSubscription: StateFlow<SubscriptionResponse?> = _currentSubscription.asStateFlow()
    
    /**
     * Start new subscription
     */
    fun startSubscription(
        phoneNumber: String,
        planType: String,
        userId: String,
        autoRenew: Boolean = true
    ) {
        viewModelScope.launch {
            applinkRepository.startSubscription(
                phoneNumber = phoneNumber,
                planType = planType,
                userId = userId,
                autoRenew = autoRenew
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _subscriptionState.value = SubscriptionState.Loading
                    }
                    is Result.Success -> {
                        _currentSubscription.value = result.data
                        _subscriptionState.value = SubscriptionState.SubscriptionStarted(result.data)
                    }
                    is Result.Error -> {
                        _subscriptionState.value = SubscriptionState.Error(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Check subscription status
     */
    fun checkSubscriptionStatus(subscriptionId: String) {
        viewModelScope.launch {
            applinkRepository.getSubscriptionStatus(subscriptionId).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _subscriptionState.value = SubscriptionState.Loading
                    }
                    is Result.Success -> {
                        _currentSubscription.value = result.data
                        _subscriptionState.value = SubscriptionState.SubscriptionStatusChecked(result.data)
                    }
                    is Result.Error -> {
                        _subscriptionState.value = SubscriptionState.Error(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Cancel subscription
     */
    fun cancelSubscription(
        subscriptionId: String,
        userId: String,
        reason: String? = null,
        immediate: Boolean = false
    ) {
        viewModelScope.launch {
            applinkRepository.cancelSubscription(
                subscriptionId = subscriptionId,
                userId = userId,
                reason = reason,
                immediate = immediate
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _subscriptionState.value = SubscriptionState.Loading
                    }
                    is Result.Success -> {
                        _currentSubscription.value = null
                        _subscriptionState.value = SubscriptionState.SubscriptionCancelled
                    }
                    is Result.Error -> {
                        _subscriptionState.value = SubscriptionState.Error(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Renew subscription
     */
    fun renewSubscription(
        subscriptionId: String,
        userId: String,
        newPlanType: String? = null
    ) {
        viewModelScope.launch {
            applinkRepository.renewSubscription(
                subscriptionId = subscriptionId,
                userId = userId,
                newPlanType = newPlanType
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _subscriptionState.value = SubscriptionState.Loading
                    }
                    is Result.Success -> {
                        _currentSubscription.value = result.data
                        _subscriptionState.value = SubscriptionState.SubscriptionRenewed(result.data)
                    }
                    is Result.Error -> {
                        _subscriptionState.value = SubscriptionState.Error(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Get plan price
     */
    fun getPlanPrice(planType: String): Double {
        return when (planType) {
            ApplinkConfig.SubscriptionPlans.DAILY -> ApplinkConfig.SubscriptionPlans.DAILY_PRICE
            ApplinkConfig.SubscriptionPlans.WEEKLY -> ApplinkConfig.SubscriptionPlans.WEEKLY_PRICE
            ApplinkConfig.SubscriptionPlans.MONTHLY -> ApplinkConfig.SubscriptionPlans.MONTHLY_PRICE
            else -> 0.0
        }
    }
    
    /**
     * Check if user has active subscription
     */
    fun hasActiveSubscription(): Boolean {
        val subscription = _currentSubscription.value
        return subscription?.status == "active" && 
               System.currentTimeMillis() < subscription.endDate
    }
    
    /**
     * Reset state
     */
    fun resetState() {
        _subscriptionState.value = SubscriptionState.Idle
    }
}

/**
 * Subscription UI States
 */
sealed class SubscriptionState {
    object Idle : SubscriptionState()
    object Loading : SubscriptionState()
    data class SubscriptionStarted(val subscription: SubscriptionResponse) : SubscriptionState()
    data class SubscriptionStatusChecked(val subscription: SubscriptionResponse) : SubscriptionState()
    data class SubscriptionRenewed(val subscription: SubscriptionResponse) : SubscriptionState()
    object SubscriptionCancelled : SubscriptionState()
    data class Error(val message: String) : SubscriptionState()
}
