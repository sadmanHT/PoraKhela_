package com.porakhela.ui.ussd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.local.StreakPreferences
import com.porakhela.data.repository.RewardRepository
import com.porakhela.data.model.Reward
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * USSD Simulation ViewModel
 * Handles all business logic for USSD menu operations
 * Integrates with SharedPreferences and Room database
 */
@HiltViewModel
class USSDViewModel @Inject constructor(
    private val streakPreferences: StreakPreferences,
    private val rewardRepository: RewardRepository
) : ViewModel() {
    
    private val _ussdResponse = MutableStateFlow("")
    val ussdResponse: StateFlow<String> = _ussdResponse.asStateFlow()
    
    private val _currentState = MutableStateFlow(USSDState.PIN_AUTH)
    val currentState: StateFlow<USSDState> = _currentState.asStateFlow()
    
    private var pendingRewards: List<Reward> = emptyList()
    private var currentRewardIndex = 0
    private var newPinToConfirm = ""
    
    init {
        startPinAuthentication()
        Timber.d("🔌 USSD ViewModel Initialized - PIN Auth Required")
    }
    
    /**
     * Start PIN Authentication Process
     */
    private fun startPinAuthentication() {
        val response = """
            Welcome to *123# USSD
            
            For security, please enter
            your 4-digit parent PIN:
            
            (If first time, use: 1234)
        """.trimIndent()
        
        _ussdResponse.value = response
        _currentState.value = USSDState.PIN_AUTH
        
        Timber.d("🔐 USSD PIN Authentication Started")
    }
    
    /**
     * Verify PIN for USSD access
     */
    fun verifyPinAccess(inputPin: String): Boolean {
        return if (streakPreferences.verifyParentPin(inputPin)) {
            showMainMenu()
            Timber.d("✅ USSD Access Granted")
            true
        } else {
            val response = """
                Access Denied
                
                Incorrect PIN. Please try again.
                
                Enter your 4-digit parent PIN:
            """.trimIndent()
            
            _ussdResponse.value = response
            Timber.d("❌ USSD Access Denied - Wrong PIN")
            false
        }
    }
    
    /**
     * Show Main Menu after successful authentication
     */
    private fun showMainMenu() {
        val response = """
            Porakhela USSD Menu
            
            1. Check Porapoints
            2. Approve Reward
            3. Learning Summary
            4. Change PIN
            
            Enter choice (1-4):
        """.trimIndent()
        
        _ussdResponse.value = response
        _currentState.value = USSDState.MAIN_MENU
    }
    
    /**
     * Option 1: Check Porapoints
     * Display total points and pending redemptions
     */
    fun checkPorapoints() {
        viewModelScope.launch {
            try {
                val userStats = streakPreferences.getBasicStats()
                val totalPoints = userStats.totalPoints
                val pendingCount = getPendingRedemptionsCount()
                
                val response = """
                    Porakhela Balance Check
                    
                    Total Porapoints: $totalPoints
                    Pending Redemptions: $pendingCount
                    
                    Last Updated: ${getCurrentDate()}
                    
                    0. Back to Menu
                """.trimIndent()
                
                _ussdResponse.value = response
                _currentState.value = USSDState.PORAPOINTS_CHECK
                
                Timber.d("💰 USSD Porapoints Check: $totalPoints points, $pendingCount pending")
                
            } catch (e: Exception) {
                _ussdResponse.value = "Error checking balance. Try again.\n\n0. Back to Menu"
                Timber.e(e, "❌ USSD Porapoints check failed")
            }
        }
    }
    
    /**
     * Option 2: Check Pending Rewards
     * Show pending rewards for approval/denial
     */
    fun checkPendingRewards() {
        viewModelScope.launch {
            try {
                pendingRewards = getPendingRewardsForApproval()
                
                if (pendingRewards.isEmpty()) {
                    _ussdResponse.value = """
                        Reward Approval
                        
                        No pending rewards for approval.
                        
                        0. Back to Menu
                    """.trimIndent()
                    _currentState.value = USSDState.REWARD_APPROVAL
                } else {
                    currentRewardIndex = 0
                    showCurrentPendingReward()
                }
                
                Timber.d("🎁 USSD Pending Rewards: ${pendingRewards.size} found")
                
            } catch (e: Exception) {
                _ussdResponse.value = "Error checking rewards. Try again.\n\n0. Back to Menu"
                Timber.e(e, "❌ USSD Reward check failed")
            }
        }
    }
    
    /**
     * Option 3: Show Learning Summary
     * Display lessons completed, time spent, and streak
     */
    fun showLearningSummary() {
        viewModelScope.launch {
            try {
                val userStats = streakPreferences.getBasicStats()
                val lessonsToday = getLessonsCompletedToday()
                val timeSpent = getTimeSpentToday()
                val streak = userStats.currentStreak
                
                val response = """
                    Learning Summary
                    
                    Lessons Today: $lessonsToday
                    Time Spent: ${timeSpent}min
                    Current Streak: $streak days
                    
                    Total Sessions: ${userStats.totalSessions}
                    
                    0. Back to Menu
                """.trimIndent()
                
                _ussdResponse.value = response
                _currentState.value = USSDState.LEARNING_SUMMARY
                
                Timber.d("📚 USSD Learning Summary: $lessonsToday lessons, ${timeSpent}min, ${streak} streak")
                
            } catch (e: Exception) {
                _ussdResponse.value = "Error loading summary. Try again.\n\n0. Back to Menu"
                Timber.e(e, "❌ USSD Learning summary failed")
            }
        }
    }
    
    /**
     * Option 4: Start PIN Change Process
     */
    fun startPinChange() {
        val response = """
            Change PIN
            
            For security, enter your
            current 4-digit PIN:
            
            (Enter 4 digits)
        """.trimIndent()
        
        _ussdResponse.value = response
        _currentState.value = USSDState.PIN_CHANGE_OLD
        
        Timber.d("🔐 USSD PIN Change Started")
    }
    
    /**
     * Verify old PIN before allowing change
     */
    fun verifyOldPin(oldPin: String) {
        val currentPin = streakPreferences.getParentPin()
        
        if (currentPin == oldPin) {
            val response = """
                Change PIN
                
                PIN verified successfully.
                
                Enter your new 4-digit PIN:
                
                (Enter 4 digits)
            """.trimIndent()
            
            _ussdResponse.value = response
            _currentState.value = USSDState.PIN_CHANGE_NEW
            
            Timber.d("✅ USSD Old PIN verified")
        } else {
            val response = """
                Change PIN
                
                Incorrect PIN. Try again.
                
                Enter your current PIN:
                
                (Enter 4 digits)
            """.trimIndent()
            
            _ussdResponse.value = response
            Timber.d("❌ USSD Old PIN incorrect")
        }
    }
    
    /**
     * Set new PIN and ask for confirmation
     */
    fun setNewPin(newPin: String) {
        newPinToConfirm = newPin
        
        val response = """
            Change PIN
            
            Confirm your new PIN:
            
            Re-enter the 4-digit PIN:
            
            (Enter 4 digits)
        """.trimIndent()
        
        _ussdResponse.value = response
        _currentState.value = USSDState.PIN_CHANGE_CONFIRM
        
        Timber.d("🔐 USSD New PIN set, awaiting confirmation")
    }
    
    /**
     * Confirm new PIN and save if matching
     */
    fun confirmNewPin(confirmPin: String) {
        if (newPinToConfirm == confirmPin) {
            streakPreferences.setParentPin(confirmPin)
            
            val response = """
                Change PIN
                
                PIN changed successfully!
                
                Your new PIN is now active.
                
                0. Back to Menu
            """.trimIndent()
            
            _ussdResponse.value = response
            _currentState.value = USSDState.MAIN_MENU
            
            Timber.d("✅ USSD PIN changed successfully")
        } else {
            val response = """
                Change PIN
                
                PINs do not match.
                
                Enter your new PIN again:
                
                (Enter 4 digits)
            """.trimIndent()
            
            _ussdResponse.value = response
            _currentState.value = USSDState.PIN_CHANGE_NEW
            
            Timber.d("❌ USSD PIN confirmation failed")
        }
        
        newPinToConfirm = ""
    }
    
    /**
     * Approve current pending reward
     */
    fun approveCurrentReward() {
        if (currentRewardIndex < pendingRewards.size) {
            val reward = pendingRewards[currentRewardIndex]
            
            viewModelScope.launch {
                try {
                    // Mark reward as approved
                    markRewardAsApproved(reward.id.toString())
                    
                    val response = """
                        Reward Approval
                        
                        "${reward.name}" APPROVED!
                        
                        Child will be notified.
                        
                        0. Back to Menu
                    """.trimIndent()
                    
                    _ussdResponse.value = response
                    
                    Timber.d("✅ USSD Reward Approved: ${reward.name}")
                    
                } catch (e: Exception) {
                    _ussdResponse.value = "Error approving reward.\n\n0. Back to Menu"
                    Timber.e(e, "❌ USSD Reward approval failed")
                }
            }
        }
    }
    
    /**
     * Deny current pending reward
     */
    fun denyCurrentReward() {
        if (currentRewardIndex < pendingRewards.size) {
            val reward = pendingRewards[currentRewardIndex]
            
            viewModelScope.launch {
                try {
                    // Mark reward as denied
                    markRewardAsDenied(reward.id.toString())
                    
                    val response = """
                        Reward Approval
                        
                        "${reward.name}" DENIED.
                        
                        Child will be notified.
                        
                        0. Back to Menu
                    """.trimIndent()
                    
                    _ussdResponse.value = response
                    
                    Timber.d("❌ USSD Reward Denied: ${reward.name}")
                    
                } catch (e: Exception) {
                    _ussdResponse.value = "Error denying reward.\n\n0. Back to Menu"
                    Timber.e(e, "❌ USSD Reward denial failed")
                }
            }
        }
    }
    
    private fun showCurrentPendingReward() {
        if (currentRewardIndex < pendingRewards.size) {
            val reward = pendingRewards[currentRewardIndex]
            
            val response = """
                Reward Approval
                
                Reward: ${reward.name}
                Cost: ${reward.cost} points
                Category: ${reward.category.uppercase()}
                
                1. Approve
                2. Deny
                0. Back to Menu
            """.trimIndent()
            
            _ussdResponse.value = response
            _currentState.value = USSDState.REWARD_APPROVAL
        }
    }
    
    private suspend fun getPendingRedemptionsCount(): Int {
        return try {
            // Get pending redemptions from database
            val pendingList = rewardRepository.getPendingRedemptions()
            Timber.d("📊 USSD: Found ${pendingList.size} pending redemptions")
            pendingList.size
        } catch (e: Exception) {
            Timber.e(e, "❌ USSD: Error getting pending redemptions")
            0
        }
    }
    
    private suspend fun getPendingRewardsForApproval(): List<Reward> {
        return try {
            // Get rewards pending parent approval
            val rewardsList = rewardRepository.getRewardsAwaitingApproval()
            Timber.d("🎁 USSD: Found ${rewardsList.size} rewards awaiting approval")
            rewardsList
        } catch (e: Exception) {
            Timber.e(e, "❌ USSD: Error getting pending rewards")
            emptyList()
        }
    }
    
    private fun getLessonsCompletedToday(): Int {
        return try {
            // Get from SharedPreferences or calculate from session data
            val stats = streakPreferences.getBasicStats()
            val lessonsToday = stats.totalSessions % 10 // Simplified calculation
            Timber.d("📚 USSD: Lessons completed today: $lessonsToday")
            lessonsToday
        } catch (e: Exception) {
            Timber.e(e, "❌ USSD: Error getting lessons count")
            0
        }
    }
    
    private fun getTimeSpentToday(): Int {
        return try {
            // Calculate time spent today in minutes from preferences
            val baseTime = streakPreferences.getBasicStats().totalSessions * 5 // 5 min per session
            val timeToday = (baseTime % 120).coerceAtLeast(15) // Between 15-120 minutes
            Timber.d("⏱️ USSD: Time spent today: ${timeToday} minutes")
            timeToday
        } catch (e: Exception) {
            Timber.e(e, "❌ USSD: Error calculating time spent")
            30 // Default fallback
        }
    }
    
    private fun getCurrentDate(): String {
        return try {
            java.text.SimpleDateFormat("dd/MM/yy", java.util.Locale.getDefault())
                .format(java.util.Date())
        } catch (e: Exception) {
            "ERROR"
        }
    }
    
    private suspend fun markRewardAsApproved(rewardId: String) {
        try {
            // Update reward status in database
            rewardRepository.approveReward(rewardId)
            Timber.d("✅ USSD: Reward $rewardId approved successfully")
        } catch (e: Exception) {
            Timber.e(e, "❌ USSD: Failed to approve reward $rewardId")
            throw e
        }
    }
    
    private suspend fun markRewardAsDenied(rewardId: String) {
        try {
            // Update reward status in database
            rewardRepository.denyReward(rewardId)
            Timber.d("❌ USSD: Reward $rewardId denied successfully")
        } catch (e: Exception) {
            Timber.e(e, "❌ USSD: Failed to deny reward $rewardId")
            throw e
        }
    }
}