package com.porakhela.ui.ussd

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.porakhela.R
import com.porakhela.data.local.StreakPreferences
import com.porakhela.databinding.ActivityUssdSimulationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * USSD Simulation Activity - Replicates *123# telecom menu experience
 * Provides inclusivity bridge for parents without smartphones
 * Features:
 * - Monochrome text-only UI
 * - Single elastic stack navigation (no back allowed)
 * - Real data binding to SharedPreferences and Room
 */
@AndroidEntryPoint
class USSDSimulationActivity : ComponentActivity() {
    
    private lateinit var binding: ActivityUssdSimulationBinding
    private val viewModel: USSDViewModel by viewModels()
    
    // State persistence
    companion object {
        private const val KEY_USSD_STATE = "ussd_state"
        private const val KEY_INPUT_BUFFER = "input_buffer"
        private const val INPUT_COOLDOWN_MS = 300L // Prevent spam
        private const val MAX_INPUT_LENGTH = 20 // Prevent buffer overflow
    }
    
    @Inject
    lateinit var streakPreferences: StreakPreferences
    
    private var currentState = USSDState.PIN_AUTH
    private val inputBuffer = StringBuilder()
    private var lastInputTime = 0L
    private var tempOldPin = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityUssdSimulationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUSSDInterface()
        setupObservers()
        initializeMainMenu()
        
        // Disable back navigation - USSD behaves like single elastic stack
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing - USSD has no back navigation
                Timber.d("🚫 USSD: Back navigation blocked")
            }
        })
        
        Timber.d("🔌 USSD Simulation Started - *123# Menu Active")
    }
    
    private fun setupUSSDInterface() {
        // Hide system UI for authentic USSD experience
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
        
        // Setup input handling
        binding.ussdInputField.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_ENTER -> {
                        processInput()
                        true
                    }
                    KeyEvent.KEYCODE_DEL -> {
                        if (inputBuffer.isNotEmpty()) {
                            inputBuffer.deleteCharAt(inputBuffer.length - 1)
                            updateInputDisplay()
                        }
                        true
                    }
                    KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_2,
                    KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4, KeyEvent.KEYCODE_5,
                    KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_7, KeyEvent.KEYCODE_8,
                    KeyEvent.KEYCODE_9 -> {
                        val digit = (keyCode - KeyEvent.KEYCODE_0).toString()
                        inputBuffer.append(digit)
                        updateInputDisplay()
                        true
                    }
                    else -> false
                }
            } else false
        }
        
        // Setup number pad buttons
        setupNumberPad()
    }
    
    private fun setupNumberPad() {
        val numberButtons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )
        
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (canProcessInput() && inputBuffer.length < MAX_INPUT_LENGTH) {
                    inputBuffer.append(index.toString())
                    updateInputDisplay()
                    lastInputTime = System.currentTimeMillis()
                }
            }
        }
        
        binding.btnSend.setOnClickListener { 
            if (canProcessInput()) {
                processInput()
                lastInputTime = System.currentTimeMillis()
            }
        }
        binding.btnClear.setOnClickListener { 
            if (canProcessInput()) {
                clearInput()
                lastInputTime = System.currentTimeMillis()
            }
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.ussdResponse.collect { response ->
                binding.ussdDisplay.text = response
            }
        }
        
        lifecycleScope.launch {
            viewModel.currentState.collect { state ->
                currentState = state
                updateStateUI(state)
            }
        }
    }
    
    private fun initializeMainMenu() {
        val mainMenu = """
            *123# - Porakhela Menu
            
            Welcome to Porakhela USSD
            
            1. Check Porapoints
            2. Approve Reward  
            3. Learning Summary
            4. Change PIN
            
            Enter option (1-4):
        """.trimIndent()
        
        binding.ussdDisplay.text = mainMenu
        currentState = USSDState.PIN_AUTH
        
        Timber.d("🏠 USSD Main Menu Displayed")
    }
    
    private fun processInput() {
        val input = inputBuffer.toString().trim()
        if (input.isEmpty()) return
        
        Timber.d("📱 USSD Input: '$input' in state $currentState")
        
        when (currentState) {
            USSDState.PIN_AUTH -> handlePinAuthInput(input)
            USSDState.MAIN_MENU -> handleMainMenuInput(input)
            USSDState.PORAPOINTS_CHECK -> handlePorapointsInput(input)
            USSDState.REWARD_APPROVAL -> handleRewardApprovalInput(input)
            USSDState.LEARNING_SUMMARY -> handleLearningSummaryInput(input)
            USSDState.PIN_CHANGE_OLD -> handleOldPinInput(input)
            USSDState.PIN_CHANGE_NEW -> handleNewPinInput(input)
            USSDState.PIN_CHANGE_CONFIRM -> handleConfirmPinInput(input)
        }
        
        clearInput()
    }
    
    private fun handlePinAuthInput(input: String) {
        if (input.length == 4 && input.all { it.isDigit() }) {
            val isAuthenticated = viewModel.verifyPinAccess(input)
            if (isAuthenticated) {
                currentState = USSDState.MAIN_MENU
            }
        } else {
            showMessage("Invalid PIN format. Enter 4 digits:")
        }
        clearInput()
    }

    private fun handleMainMenuInput(input: String) {
        when (input) {
            "1" -> viewModel.checkPorapoints()
            "2" -> viewModel.checkPendingRewards()
            "3" -> viewModel.showLearningSummary()
            "4" -> viewModel.startPinChange()
            else -> showInvalidOptionMessage()
        }
    }
    
    private fun handlePorapointsInput(input: String) {
        when (input) {
            "0" -> initializeMainMenu()
            else -> showInvalidOptionMessage()
        }
    }
    
    private fun handleRewardApprovalInput(input: String) {
        when (input) {
            "1" -> viewModel.approveCurrentReward()
            "2" -> viewModel.denyCurrentReward()
            "0" -> initializeMainMenu()
            else -> showInvalidOptionMessage()
        }
    }
    
    private fun handleLearningSummaryInput(input: String) {
        when (input) {
            "0" -> initializeMainMenu()
            else -> showInvalidOptionMessage()
        }
    }
    
    private fun handleOldPinInput(input: String) {
        if (input.length == 4 && input.all { it.isDigit() }) {
            tempOldPin = input
            viewModel.verifyOldPin(input)
        } else {
            showMessage("Invalid PIN format. Enter 4 digits:")
        }
    }
    
    private fun handleNewPinInput(input: String) {
        if (input.length == 4 && input.all { it.isDigit() }) {
            viewModel.setNewPin(input)
        } else {
            showMessage("Invalid PIN format. Enter 4 digits:")
        }
    }
    
    private fun handleConfirmPinInput(input: String) {
        viewModel.confirmNewPin(input)
    }
    
    private fun showInvalidOptionMessage() {
        showMessage("Invalid option. Try again:")
    }
    
    private fun showMessage(message: String) {
        val currentDisplay = binding.ussdDisplay.text.toString()
        binding.ussdDisplay.text = "$currentDisplay\n\n$message"
    }
    
    /**
     * Prevent input spam that could crash the app
     */
    private fun canProcessInput(): Boolean {
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastInputTime) >= INPUT_COOLDOWN_MS
    }
    
    private fun updateInputDisplay() {
        binding.ussdInputField.setText(inputBuffer.toString())
    }
    
    private fun clearInput() {
        inputBuffer.clear()
        binding.ussdInputField.setText("")
    }
    
    private fun updateStateUI(state: USSDState) {
        // Update UI elements based on current USSD state
        when (state) {
            USSDState.PIN_AUTH -> {
                // PIN entry UI - hide menu options, show secure input
            }
            USSDState.MAIN_MENU -> {
                binding.ussdStatusText.text = "USSD Menu Active"
            }
            USSDState.PORAPOINTS_CHECK -> {
                binding.ussdStatusText.text = "Checking Porapoints..."
            }
            USSDState.REWARD_APPROVAL -> {
                binding.ussdStatusText.text = "Reward Approval"
            }
            USSDState.LEARNING_SUMMARY -> {
                binding.ussdStatusText.text = "Learning Summary"
            }
            USSDState.PIN_CHANGE_OLD,
            USSDState.PIN_CHANGE_NEW,
            USSDState.PIN_CHANGE_CONFIRM -> {
                binding.ussdStatusText.text = "PIN Change"
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Timber.d("📱 USSD Simulation Ended")
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_USSD_STATE, currentState.name)
        outState.putString(KEY_INPUT_BUFFER, inputBuffer.toString())
        Timber.d("🔄 USSD State Saved: ${currentState.name}")
    }
    
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        
        val stateName = savedInstanceState.getString(KEY_USSD_STATE)
        stateName?.let {
            try {
                currentState = USSDState.valueOf(it)
                Timber.d("🔄 USSD State Restored: ${currentState.name}")
            } catch (e: IllegalArgumentException) {
                Timber.w("❌ Invalid state name: $it, defaulting to PIN_AUTH")
                currentState = USSDState.PIN_AUTH
            }
        }
        
        val inputText = savedInstanceState.getString(KEY_INPUT_BUFFER) ?: ""
        inputBuffer.clear()
        inputBuffer.append(inputText)
        updateInputDisplay()
    }
}

/**
 * USSD State Machine
 * Represents different states in the USSD flow
 */
enum class USSDState {
    PIN_AUTH,
    MAIN_MENU,
    PORAPOINTS_CHECK,
    REWARD_APPROVAL,
    LEARNING_SUMMARY,
    PIN_CHANGE_OLD,
    PIN_CHANGE_NEW,
    PIN_CHANGE_CONFIRM
}