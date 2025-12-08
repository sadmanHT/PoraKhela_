package com.porakhela.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.porakhela.databinding.FragmentOtpVerificationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * OTP Verification Screen
 * Allows users to enter and verify OTP code
 */
@AndroidEntryPoint
class OtpVerificationFragment : Fragment() {
    
    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: OtpViewModel by viewModels()
    
    private var countDownTimer: CountDownTimer? = null
    private var phoneNumber: String = ""
    private var sessionId: String = ""
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Get phone number from arguments
        phoneNumber = arguments?.getString("phone_number") ?: ""
        
        setupUI()
        observeViewModel()
        
        // Send OTP automatically when screen opens
        sendOtp()
    }
    
    private fun setupUI() {
        // Format phone number display
        binding.tvPhoneNumber.text = formatPhoneNumber(phoneNumber)
        
        // OTP input handling
        binding.etOtp.addTextChangedListener { text ->
            if (text?.length == 6) {
                // Auto-submit when 6 digits entered
                verifyOtp(text.toString())
            }
        }
        
        // Verify button
        binding.btnVerify.setOnClickListener {
            val otpCode = binding.etOtp.text.toString()
            if (otpCode.length == 6) {
                verifyOtp(otpCode)
            } else {
                Toast.makeText(requireContext(), "Please enter 6-digit OTP", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Resend OTP button
        binding.btnResendOtp.setOnClickListener {
            sendOtp()
        }
        
        // Change number button
        binding.btnChangeNumber.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.otpState.collect { state ->
                when (state) {
                    is OtpState.Idle -> {
                        hideLoading()
                    }
                    is OtpState.Loading -> {
                        showLoading()
                    }
                    is OtpState.OtpSent -> {
                        hideLoading()
                        sessionId = state.sessionId
                        startCountdown(state.expiresIn)
                        Toast.makeText(requireContext(), "OTP sent successfully", Toast.LENGTH_SHORT).show()
                    }
                    is OtpState.OtpVerified -> {
                        hideLoading()
                        Toast.makeText(requireContext(), "Phone verified successfully!", Toast.LENGTH_SHORT).show()
                        // Navigate to next screen
                        onVerificationSuccess()
                    }
                    is OtpState.Error -> {
                        hideLoading()
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    
    private fun sendOtp() {
        viewModel.sendOtp(phoneNumber)
    }
    
    private fun verifyOtp(otpCode: String) {
        if (sessionId.isEmpty()) {
            Toast.makeText(requireContext(), "Please request OTP first", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.verifyOtp(sessionId, otpCode, phoneNumber)
    }
    
    private fun startCountdown(seconds: Int) {
        countDownTimer?.cancel()
        
        binding.btnResendOtp.isEnabled = false
        
        countDownTimer = object : CountDownTimer(seconds * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvTimer.text = "Resend OTP in ${secondsRemaining}s"
            }
            
            override fun onFinish() {
                binding.tvTimer.text = ""
                binding.btnResendOtp.isEnabled = true
            }
        }.start()
    }
    
    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnVerify.isEnabled = false
        binding.etOtp.isEnabled = false
    }
    
    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.btnVerify.isEnabled = true
        binding.etOtp.isEnabled = true
    }
    
    private fun formatPhoneNumber(phone: String): String {
        // Format: +880 1XXX-XXXXXX
        return if (phone.length == 11) {
            "+880 ${phone.substring(0, 4)}-${phone.substring(4)}"
        } else {
            phone
        }
    }
    
    private fun onVerificationSuccess() {
        // Navigate to main screen or next onboarding step
        // TODO: Implement navigation
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
        _binding = null
    }
}
