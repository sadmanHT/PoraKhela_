package com.porakhela.ui.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.porakhela.R
import com.porakhela.ui.parent.AuthState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ParentPinAuthFragment : Fragment() {
    
    companion object {
        private const val KEY_CURRENT_PIN = "current_pin"
    }
    
    private val viewModel: ParentDashboardViewModel by viewModels()
    private var currentPin = ""
    private lateinit var pinDots: List<View>
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_pin_auth, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViews(view)
        observeAuthState()
        
        // Restore PIN state if configuration changed
        if (savedInstanceState != null) {
            currentPin = savedInstanceState.getString(KEY_CURRENT_PIN, "")
            updatePinDots()
        }
    }
    
    private fun setupViews(view: View) {
        pinDots = listOf(
            view.findViewById(R.id.pin_dot_1),
            view.findViewById(R.id.pin_dot_2),
            view.findViewById(R.id.pin_dot_3),
            view.findViewById(R.id.pin_dot_4)
        )
        
        // Setup number buttons
        val numberButtons = listOf(
            view.findViewById<Button>(R.id.btn_0) to "0",
            view.findViewById<Button>(R.id.btn_1) to "1",
            view.findViewById<Button>(R.id.btn_2) to "2",
            view.findViewById<Button>(R.id.btn_3) to "3",
            view.findViewById<Button>(R.id.btn_4) to "4",
            view.findViewById<Button>(R.id.btn_5) to "5",
            view.findViewById<Button>(R.id.btn_6) to "6",
            view.findViewById<Button>(R.id.btn_7) to "7",
            view.findViewById<Button>(R.id.btn_8) to "8",
            view.findViewById<Button>(R.id.btn_9) to "9"
        )
        
        numberButtons.forEach { (button, digit) ->
            button.setOnClickListener {
                addDigit(digit)
            }
        }
        
        view.findViewById<Button>(R.id.btn_backspace).setOnClickListener {
            removeDigit()
        }
        
        view.findViewById<Button>(R.id.btn_clear).setOnClickListener {
            clearPin()
        }
        
        view.findViewById<Button>(R.id.btn_back).setOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    private fun observeAuthState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collect { state ->
                updateUI(state)
            }
        }
    }
    
    private fun updateUI(state: AuthState) {
        val view = view ?: return
        
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvSubtitle = view.findViewById<TextView>(R.id.tv_subtitle)
        val tvError = view.findViewById<TextView>(R.id.tv_error)
        val tvCooldown = view.findViewById<TextView>(R.id.tv_cooldown)
        val pinContainer = view.findViewById<View>(R.id.pin_container)
        
        // Update title and subtitle based on state
        if (!state.hasPin) {
            tvTitle.text = "Create Parent PIN"
            tvSubtitle.text = "Enter a 4-digit PIN to secure your parent dashboard"
        } else {
            tvTitle.text = "Parent Dashboard"
            tvSubtitle.text = "Enter your 4-digit PIN"
        }
        
        // Handle authentication success
        if (state.isAuthenticated) {
            findNavController().navigate(R.id.action_parentPinAuth_to_parentDashboard)
            return
        }
        
        // Handle cooldown
        if (state.isInCooldown) {
            tvCooldown.visibility = View.VISIBLE
            tvCooldown.text = "Try again in ${state.cooldownSeconds} seconds"
            disableNumberPad()
        } else {
            tvCooldown.visibility = View.GONE
            enableNumberPad()
        }
        
        // Handle errors
        if (state.showError) {
            tvError.visibility = View.VISIBLE
            tvError.text = state.errorMessage
        } else {
            tvError.visibility = View.GONE
        }
        
        // Handle shake animation
        if (state.shouldShake) {
            val shakeAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
            pinContainer.startAnimation(shakeAnimation)
            viewModel.clearShakeAnimation()
        }
    }
    
    private fun addDigit(digit: String) {
        if (currentPin.length < 4) {
            currentPin += digit
            updatePinDots()
            
            if (currentPin.length == 4) {
                // Disable number pad immediately to prevent double-submit
                disableNumberPad()
                submitPin()
            }
        }
    }
    
    private fun removeDigit() {
        if (currentPin.isNotEmpty()) {
            currentPin = currentPin.dropLast(1)
            updatePinDots()
        }
    }
    
    private fun clearPin() {
        currentPin = ""
        updatePinDots()
    }
    
    private fun updatePinDots() {
        pinDots.forEachIndexed { index, dot ->
            if (index < currentPin.length) {
                dot.setBackgroundResource(R.drawable.pin_dot_filled)
            } else {
                dot.setBackgroundResource(R.drawable.pin_dot_empty)
            }
        }
    }
    
    private fun submitPin() {
        if (currentPin.length != 4) {
            enableNumberPad()
            return
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val currentState = viewModel.authState.value
                if (!currentState.hasPin) {
                    viewModel.createPin(currentPin)
                } else {
                    viewModel.authenticatePin(currentPin)
                }
            } catch (e: Exception) {
                Timber.e(e, "Error submitting PIN")
            } finally {
                currentPin = ""
                updatePinDots()
                enableNumberPad()
            }
        }
    }
    
    private fun disableNumberPad() {
        val view = view ?: return
        val buttons = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_backspace, R.id.btn_clear
        )
        buttons.forEach { buttonId ->
            view.findViewById<Button>(buttonId).isEnabled = false
        }
    }
    
    private fun enableNumberPad() {
        val view = view ?: return
        val buttons = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_backspace, R.id.btn_clear
        )
        buttons.forEach { buttonId ->
            view.findViewById<Button>(buttonId).isEnabled = true
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_CURRENT_PIN, currentPin)
    }
}