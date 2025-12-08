package com.porakhela.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.repository.ApplinkRepositoryImpl
import com.porakhela.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for OTP verification
 */
@HiltViewModel
class OtpViewModel @Inject constructor(
    private val applinkRepository: ApplinkRepositoryImpl
) : ViewModel() {
    
    private val _otpState = MutableStateFlow<OtpState>(OtpState.Idle)
    val otpState: StateFlow<OtpState> = _otpState.asStateFlow()
    
    /**
     * Send OTP to phone number
     */
    fun sendOtp(phoneNumber: String) {
        viewModelScope.launch {
            applinkRepository.sendOtp(phoneNumber).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _otpState.value = OtpState.Loading
                    }
                    is Result.Success -> {
                        _otpState.value = OtpState.OtpSent(
                            sessionId = result.data.sessionId,
                            expiresIn = 300 // Default 5 minutes
                        )
                    }
                    is Result.Error -> {
                        _otpState.value = OtpState.Error(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Verify OTP code
     */
    fun verifyOtp(sessionId: String, otpCode: String, phoneNumber: String) {
        viewModelScope.launch {
            applinkRepository.verifyOtp(sessionId, otpCode, phoneNumber).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _otpState.value = OtpState.Loading
                    }
                    is Result.Success -> {
                        if (result.data.verified) {
                            _otpState.value = OtpState.OtpVerified(
                                userId = result.data.userId ?: ""
                            )
                        } else {
                            _otpState.value = OtpState.Error("Invalid OTP code")
                        }
                    }
                    is Result.Error -> {
                        _otpState.value = OtpState.Error(result.message)
                    }
                }
            }
        }
    }
    
    /**
     * Reset state
     */
    fun resetState() {
        _otpState.value = OtpState.Idle
    }
}

/**
 * OTP UI States
 */
sealed class OtpState {
    object Idle : OtpState()
    object Loading : OtpState()
    data class OtpSent(val sessionId: String, val expiresIn: Int) : OtpState()
    data class OtpVerified(val userId: String) : OtpState()
    data class Error(val message: String) : OtpState()
}
