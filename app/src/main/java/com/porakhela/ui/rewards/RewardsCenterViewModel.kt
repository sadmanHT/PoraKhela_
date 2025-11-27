package com.porakhela.ui.rewards

import androidx.lifecycle.viewModelScope
import com.porakhela.core.components.BaseViewModel
import com.porakhela.data.model.Reward
import com.porakhela.data.repository.RewardRepository
import com.porakhela.domain.service.RewardService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for the Rewards Center
 */
@HiltViewModel
class RewardsCenterViewModel @Inject constructor(
    private val rewardRepository: RewardRepository,
    private val rewardService: RewardService
) : BaseViewModel() {
    
    private val _rewards = MutableStateFlow<List<Reward>>(emptyList())
    val rewards: StateFlow<List<Reward>> = _rewards.asStateFlow()
    
    private val _userBalance = MutableStateFlow(0)
    val userBalance: StateFlow<Int> = _userBalance.asStateFlow()
    
    private val _redemptionResult = MutableStateFlow<RewardService.RedemptionResult?>(null)
    val redemptionResult: StateFlow<RewardService.RedemptionResult?> = _redemptionResult.asStateFlow()
    
    init {
        loadUserBalance()
        initializeRewards()
    }
    
    /**
     * Load available rewards from repository
     */
    fun loadRewards() {
        launchWithErrorHandling(showLoading = true) {
            rewardRepository.getAllAvailableRewards().collect { rewardList ->
                _rewards.value = rewardList
                Timber.d("Loaded ${rewardList.size} rewards")
                setSuccess(rewardList)
            }
        }
    }
    
    /**
     * Initialize rewards data if needed
     */
    private fun initializeRewards() {
        viewModelScope.launch {
            try {
                rewardService.initializeRewards()
                loadRewards()
            } catch (e: Exception) {
                Timber.e(e, "Failed to initialize rewards")
                handleError(e)
            }
        }
    }
    
    /**
     * Load user's current Porapoints balance
     */
    private fun loadUserBalance() {
        viewModelScope.launch {
            try {
                val balance = rewardService.getCurrentBalance()
                _userBalance.value = balance
                Timber.d("User balance: $balance Porapoints")
            } catch (e: Exception) {
                Timber.e(e, "Failed to load user balance")
            }
        }
    }
    
    /**
     * Attempt to redeem a reward
     */
    fun redeemReward(reward: Reward) {
        launchWithErrorHandling(showLoading = true) {
            Timber.i("Attempting to redeem reward: ${reward.name}")
            
            val result = rewardService.redeemReward(reward)
            _redemptionResult.value = result
            
            // Update balance after redemption
            when (result) {
                is RewardService.RedemptionResult.Success -> {
                    _userBalance.value = result.newBalance
                }
                is RewardService.RedemptionResult.InsufficientPoints -> {
                    _userBalance.value = result.current
                }
                else -> {
                    // For other cases, refresh balance
                    loadUserBalance()
                }
            }
            
            setSuccess(result)
        }
    }
    
    /**
     * Clear redemption result after handling
     */
    fun clearRedemptionResult() {
        _redemptionResult.value = null
    }
    
    /**
     * Check if user can afford a specific reward
     */
    fun canAffordReward(reward: Reward): Boolean {
        return rewardService.canAffordReward(reward)
    }
    
    /**
     * Refresh all data
     */
    fun refresh() {
        loadUserBalance()
        loadRewards()
    }
}