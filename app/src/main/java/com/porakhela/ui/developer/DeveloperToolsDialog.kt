package com.porakhela.ui.developer

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.lifecycle.LifecycleCoroutineScope
import com.porakhela.R
import com.porakhela.data.api.MockApplinkInterceptor
import com.porakhela.data.repository.ApplinkRepository
import com.porakhela.utils.PorapointsManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import timber.log.Timber
import java.net.SocketTimeoutException

/**
 * Developer Tools popup for testing Applink API integrations
 */
class DeveloperToolsDialog(
    private val context: Context,
    private val lifecycleScope: LifecycleCoroutineScope,
    private val applinkRepository: ApplinkRepository,
    private val porapointsManager: PorapointsManager,
    private val onPointsUpdated: () -> Unit
) {
    
    private var dialog: Dialog? = null
    
    fun show() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_developer_tools, null)
        
        dialog = Dialog(context).apply {
            setContentView(dialogView)
            setTitle("Developer Tools - Applink API Testing")
            setCancelable(true)
        }
        
        setupViews(dialogView)
        dialog?.show()
    }
    
    private fun setupViews(view: View) {
        // API Testing Buttons
        val btnMockSubscribe = view.findViewById<Button>(R.id.btn_mock_subscribe)
        val btnMockSms = view.findViewById<Button>(R.id.btn_mock_sms)
        val btnMockRedeem = view.findViewById<Button>(R.id.btn_mock_redeem)
        val btnMockOtp = view.findViewById<Button>(R.id.btn_mock_otp)
        
        // Error Simulation Controls
        val switchErrorSimulation = view.findViewById<Switch>(R.id.switch_error_simulation)
        val spinnerErrorCode = view.findViewById<Spinner>(R.id.spinner_error_code)
        val switchTimeoutSimulation = view.findViewById<Switch>(R.id.switch_timeout_simulation)
        val seekBarDelay = view.findViewById<SeekBar>(R.id.seekbar_delay)
        val tvDelayValue = view.findViewById<TextView>(R.id.tv_delay_value)
        
        // Status Display
        val tvCurrentPoints = view.findViewById<TextView>(R.id.tv_current_points)
        val tvApiStatus = view.findViewById<TextView>(R.id.tv_api_status)
        val tvLastOperation = view.findViewById<TextView>(R.id.tv_last_operation)
        
        // Initialize status display
        updatePointsDisplay(tvCurrentPoints)
        updateApiStatus(tvApiStatus)
        
        // Setup error code spinner
        val errorCodes = listOf(400, 401, 403, 404, 429, 500, 503)
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, errorCodes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerErrorCode.adapter = adapter
        
        // Setup delay seekbar
        seekBarDelay.max = 5000 // 5 seconds max
        seekBarDelay.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvDelayValue.text = "${progress}ms"
                MockApplinkInterceptor.responseDelay = progress.toLong()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // Setup error simulation controls
        switchErrorSimulation.setOnCheckedChangeListener { _, isChecked ->
            MockApplinkInterceptor.simulateError = isChecked
            spinnerErrorCode.isEnabled = isChecked
            updateApiStatus(tvApiStatus)
        }
        
        spinnerErrorCode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                MockApplinkInterceptor.errorCode = errorCodes[position]
                updateApiStatus(tvApiStatus)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        
        switchTimeoutSimulation.setOnCheckedChangeListener { _, isChecked ->
            MockApplinkInterceptor.simulateTimeout = isChecked
            updateApiStatus(tvApiStatus)
        }
        
        // API Testing Button Handlers - Crash Resistant
        btnMockSubscribe.setOnClickListener {
            try {
                testSubscription(tvLastOperation, tvApiStatus)
            } catch (e: Exception) {
                Timber.e(e, "Error in Mock Subscribe")
                showToast("Button error prevented crash")
            }
        }
        
        btnMockSms.setOnClickListener {
            try {
                testSmsNotification(tvLastOperation, tvApiStatus)
            } catch (e: Exception) {
                Timber.e(e, "Error in Mock SMS")
                showToast("Button error prevented crash")
            }
        }
        
        btnMockRedeem.setOnClickListener {
            try {
                testRewardsRedemption(tvLastOperation, tvApiStatus, tvCurrentPoints)
            } catch (e: Exception) {
                Timber.e(e, "Error in Mock Redeem")
                showToast("Button error prevented crash")
            }
        }
        
        btnMockOtp.setOnClickListener {
            try {
                testOtpVerification(tvLastOperation, tvApiStatus)
            } catch (e: Exception) {
                Timber.e(e, "Error in Mock OTP")
                showToast("Button error prevented crash")
            }
        }
        
        // Additional controls
        view.findViewById<Button>(R.id.btn_reset_points).setOnClickListener {
            porapointsManager.resetPorapoints()
            updatePointsDisplay(tvCurrentPoints)
            onPointsUpdated()
            updateLastOperation(tvLastOperation, "Points reset to initial value")
        }
        
        view.findViewById<Button>(R.id.btn_add_test_points).setOnClickListener {
            porapointsManager.addPorapoints(500)
            updatePointsDisplay(tvCurrentPoints)
            onPointsUpdated()
            updateLastOperation(tvLastOperation, "Added 500 test points")
        }
        
        view.findViewById<Button>(R.id.btn_close).setOnClickListener {
            dialog?.dismiss()
        }
    }
    
    private fun testSubscription(tvLastOperation: TextView, tvApiStatus: TextView) {
        lifecycleScope.launch {
            try {
                updateLastOperation(tvLastOperation, "Testing subscription...")
                val result = applinkRepository.startSubscription("test_user_123")
                
                result.fold(
                    onSuccess = { subscription: com.porakhela.data.models.SubscriptionResponse ->
                        updateLastOperation(tvLastOperation, "✅ Subscription started: ${subscription.subscription_id}")
                        showToast("Success toast")
                    },
                    onFailure = { error ->
                        handleApiError(error, tvLastOperation, "Subscription") {
                            testSubscription(tvLastOperation, tvApiStatus)
                        }
                    }
                )
            } catch (e: SocketTimeoutException) {
                updateLastOperation(tvLastOperation, "⏱️ Network slow - subscription timeout")
                showToast("Network slow")
            } catch (e: Exception) {
                updateLastOperation(tvLastOperation, "💥 Subscription error: ${e.message}")
                showToast("Error: ${e.message}")
            }
        }
    }
    
    private fun testSmsNotification(tvLastOperation: TextView, tvApiStatus: TextView) {
        lifecycleScope.launch {
            try {
                updateLastOperation(tvLastOperation, "Sending learning report SMS...")
                
                val reportData = mapOf(
                    "student_name" to "Test Student",
                    "lessons_completed" to "5",
                    "total_score" to "450",
                    "week" to "Week 12"
                )
                
                val result = applinkRepository.sendLearningReport("+256700123456", reportData)
                
                result.fold(
                    onSuccess = { sms: com.porakhela.data.models.SmsResponse ->
                        updateLastOperation(tvLastOperation, "✅ SMS sent: ${sms.sms_id} (${sms.delivery_status})")
                        showToast("Success toast")
                    },
                    onFailure = { error ->
                        handleApiError(error, tvLastOperation, "SMS") {
                            testSmsNotification(tvLastOperation, tvApiStatus)
                        }
                    }
                )
            } catch (e: SocketTimeoutException) {
                updateLastOperation(tvLastOperation, "⏱️ Network slow - SMS timeout")
                showToast("Network slow")
            } catch (e: Exception) {
                updateLastOperation(tvLastOperation, "💥 SMS error: ${e.message}")
                showToast("Error: ${e.message}")
            }
        }
    }
    
    private fun testRewardsRedemption(tvLastOperation: TextView, tvApiStatus: TextView, tvCurrentPoints: TextView) {
        lifecycleScope.launch {
            try {
                val currentPoints = porapointsManager.getCurrentPorapoints()
                if (currentPoints < 100) {
                    updateLastOperation(tvLastOperation, "❌ Insufficient points: $currentPoints (need 100)")
                    showToast("Insufficient Porapoints")
                    return@launch
                }
                
                updateLastOperation(tvLastOperation, "Redeeming 100 points for airtime...")
                
                val result = applinkRepository.redeemReward("test_user_123", "airtime", 100)
                
                result.fold(
                    onSuccess = { reward: com.porakhela.data.models.RewardsResponse ->
                        updatePointsDisplay(tvCurrentPoints)
                        onPointsUpdated()
                        updateLastOperation(tvLastOperation, "✅ Redeemed: ${reward.redemption_id} (-100 points)")
                        showToast("Points deducted exactly")
                    },
                    onFailure = { error ->
                        handleApiError(error, tvLastOperation, "Redemption") {
                            testRewardsRedemption(tvLastOperation, tvApiStatus, tvCurrentPoints)
                        }
                    }
                )
            } catch (e: SocketTimeoutException) {
                updateLastOperation(tvLastOperation, "⏱️ Network slow - redemption timeout")
                showToast("Network slow")
            } catch (e: Exception) {
                updateLastOperation(tvLastOperation, "💥 Redemption error: ${e.message}")
                showToast("Error: ${e.message}")
            }
        }
    }
    
    private fun testOtpVerification(tvLastOperation: TextView, tvApiStatus: TextView) {
        lifecycleScope.launch {
            try {
                updateLastOperation(tvLastOperation, "Testing OTP verification...")
                
                // First send OTP
                val sendResult = applinkRepository.sendOtp("+256700123456", "login")
                sendResult.fold(
                    onSuccess = { otpData ->
                        val sessionId = otpData["session_id"] as? String
                        
                        // Then verify with mock code
                        val verifyResult = applinkRepository.verifyOtp("+256700123456", "123456", sessionId)
                        verifyResult.fold(
                            onSuccess = { verification: com.porakhela.data.models.OtpResponse ->
                                updateLastOperation(tvLastOperation, "✅ OTP verified: ${verification.verification_id}")
                                showToast("Must return success")
                            },
                            onFailure = { error ->
                                handleApiError(error, tvLastOperation, "OTP Verification") {
                                    testOtpVerification(tvLastOperation, tvApiStatus)
                                }
                            }
                        )
                    },
                    onFailure = { error ->
                        handleApiError(error, tvLastOperation, "OTP Send") {
                            testOtpVerification(tvLastOperation, tvApiStatus)
                        }
                    }
                )
            } catch (e: SocketTimeoutException) {
                updateLastOperation(tvLastOperation, "⏱️ Network slow - OTP timeout")
                showToast("Network slow")
            } catch (e: Exception) {
                updateLastOperation(tvLastOperation, "💥 OTP error: ${e.message}")
                showToast("Error: ${e.message}")
            }
        }
    }
    
    private fun updatePointsDisplay(tvCurrentPoints: TextView) {
        val points = porapointsManager.getCurrentPorapoints()
        tvCurrentPoints.text = "Current Points: $points"
    }
    
    private fun updateApiStatus(tvApiStatus: TextView) {
        val status = when {
            MockApplinkInterceptor.simulateTimeout -> "⏱️ TIMEOUT SIMULATION"
            MockApplinkInterceptor.simulateError -> "❌ ERROR ${MockApplinkInterceptor.errorCode}"
            MockApplinkInterceptor.responseDelay > 0 -> "🐌 DELAY ${MockApplinkInterceptor.responseDelay}ms"
            else -> "✅ NORMAL MODE"
        }
        tvApiStatus.text = "API Status: $status"
    }
    
    private fun updateLastOperation(tvLastOperation: TextView, message: String) {
        tvLastOperation.text = "Last: $message"
        Timber.d("DevTools: $message")
    }
    
    private fun showToast(message: String) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Timber.e(e, "Error showing toast: $message")
        }
    }

    private fun handleApiError(error: Throwable, tvLastOperation: TextView, operation: String, retryAction: () -> Unit) {
        val errorMessage = error.message ?: "Unknown error"
        
        when {
            errorMessage.contains("400") || errorMessage.contains("Bad request") -> {
                updateLastOperation(tvLastOperation, "❌ $operation failed: Validation error")
                showToast("Validation error")
            }
            errorMessage.contains("500") || errorMessage.contains("Internal server error") -> {
                updateLastOperation(tvLastOperation, "🔄 $operation failed: Server error - Retrying...")
                showToast("Server error - Retrying...")
                lifecycleScope.launch {
                    delay(2000) // Wait 2 seconds before retry
                    retryAction()
                }
            }
            else -> {
                updateLastOperation(tvLastOperation, "❌ $operation failed: $errorMessage")
                showToast("Error: $errorMessage")
            }
        }
    }
}