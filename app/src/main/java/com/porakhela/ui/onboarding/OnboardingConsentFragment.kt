package com.porakhela.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.porakhela.R
import com.porakhela.core.utils.HapticUtils
import com.porakhela.core.utils.KeyboardUtils
import com.porakhela.core.utils.ValidationUtils
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Parental consent form screen for onboarding
 */
class OnboardingConsentFragment : Fragment() {
    
    private val onboardingViewModel: OnboardingViewModel by activityViewModels()
    
    // View references
    private lateinit var etParentName: EditText
    private lateinit var etParentPhone: EditText
    private lateinit var etChildName: EditText
    private lateinit var cbConsent: CheckBox
    private lateinit var btnContinueConsent: Button
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_consent, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupViews()
        setupValidation()
        setupObservers()
        restoreState(savedInstanceState)
        
        Timber.d("Consent screen displayed")
    }
    
    private fun initializeViews(view: View) {
        etParentName = view.findViewById(R.id.etParentName)
        etParentPhone = view.findViewById(R.id.etParentPhone)
        etChildName = view.findViewById(R.id.etChildName)
        cbConsent = view.findViewById(R.id.cbConsent)
        btnContinueConsent = view.findViewById(R.id.btnContinueConsent)
    }
    
    private fun setupViews() {
        // Set up input actions
        etChildName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                KeyboardUtils.hideKeyboard(requireActivity())
                true
            } else false
        }
        
        // Set up consent checkbox
        cbConsent.setOnCheckedChangeListener { _, _ ->
            HapticUtils.lightTap(cbConsent)
            validateForm()
        }
        
        // Set up continue button
        btnContinueConsent.setOnClickListener {
            HapticUtils.mediumTap(it)
            onContinueClicked()
        }
    }
    
    private fun setupValidation() {
        // Add text watchers for real-time validation
        etParentName.addTextChangedListener { validateForm() }
        etParentPhone.addTextChangedListener { validateForm() }
        etChildName.addTextChangedListener { validateForm() }
    }
    
    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            onboardingViewModel.uiState.collect { state ->
                when (state) {
                    is com.porakhela.core.utils.UiState.Loading -> {
                        btnContinueConsent.isEnabled = false
                        btnContinueConsent.text = "Saving..."
                    }
                    is com.porakhela.core.utils.UiState.Success<*> -> {
                        // Navigation will be handled by the activity
                        Timber.d("Onboarding data saved successfully")
                        // Navigate to main app
                        (activity as? OnboardingActivity)?.completeOnboarding()
                    }
                    is com.porakhela.core.utils.UiState.Error -> {
                        showError(state.message)
                        btnContinueConsent.isEnabled = true
                        btnContinueConsent.text = "Try Again"
                    }
                    else -> {
                        // Idle state
                        validateForm()
                    }
                }
            }
        }
    }
    
    private fun validateForm() {
        val parentName = etParentName.text.toString()
        val parentPhone = etParentPhone.text.toString()
        val childName = etChildName.text.toString()
        val consentGiven = cbConsent.isChecked
        
        // Validate each field
        val parentNameValidation = ValidationUtils.validateParentName(parentName)
        val phoneValidation = ValidationUtils.validatePhoneNumber(parentPhone)
        val childNameValidation = ValidationUtils.validateChildName(childName)
        val consentValidation = ValidationUtils.validateConsent(consentGiven)
        
        // Enable/disable continue button
        val formValid = isFormValid()
        btnContinueConsent.isEnabled = formValid && !onboardingViewModel.isLoading.value
        
        // Notify parent activity about form state
        val onboardingActivity = activity as? OnboardingActivity
        if (formValid) {
            onboardingActivity?.enableContinueButton()
        } else {
            onboardingActivity?.disableContinueButton()
        }
        
        // Update button text based on validation
        btnContinueConsent.text = if (formValid) {
            "Continue to Porakhela"
        } else {
            "Complete Required Fields"
        }
        
        // Show specific validation errors
        showValidationErrors(parentNameValidation, phoneValidation, childNameValidation, consentValidation)
    }
    
    private fun isFormValid(): Boolean {
        val parentName = etParentName.text.toString()
        val parentPhone = etParentPhone.text.toString()
        val childName = etChildName.text.toString()
        val consentGiven = cbConsent.isChecked
        
        val validations = ValidationUtils.validateOnboardingForm(
            parentName, parentPhone, childName, consentGiven
        )
        
        return ValidationUtils.areAllValid(validations)
    }
    
    private fun onContinueClicked() {
        if (!isFormValid()) {
            showError("Please complete all required fields")
            return
        }
        
        // Validate phone number specifically with user feedback
        val phoneValidation = ValidationUtils.validatePhoneNumber(etParentPhone.text.toString())
        if (!phoneValidation.isValid) {
            showError(phoneValidation.errorMessage ?: "Invalid phone number")
            return
        }
        
        KeyboardUtils.hideKeyboard(requireActivity())
        
        val parentName = etParentName.text.toString().trim()
        val parentPhone = etParentPhone.text.toString().trim()
        val childName = etChildName.text.toString().trim()
        val consentGiven = cbConsent.isChecked
        
        // Only proceed if consent is actually checked
        if (!consentGiven) {
            showError("Parental consent is required to proceed")
            return
        }
        
        onboardingViewModel.saveOnboardingData(
            parentName = parentName,
            parentPhone = parentPhone,
            childName = childName,
            consentGiven = consentGiven
        )
        
        Timber.i("Onboarding data submitted: parent=$parentName, child=$childName, consent=$consentGiven")
    }
    
    private fun showError(message: String) {
        // Show error message (could be a Snackbar or AlertDialog)
        Timber.e("Consent form error: $message")
        // For now, just log the error - in production you'd show a user-friendly message
    }
    
    private fun showValidationErrors(
        parentNameValidation: ValidationUtils.ValidationResult,
        phoneValidation: ValidationUtils.ValidationResult,
        childNameValidation: ValidationUtils.ValidationResult,
        consentValidation: ValidationUtils.ValidationResult
    ) {
        // Clear previous errors
        etParentName.error = null
        etParentPhone.error = null
        etChildName.error = null
        
        // Show specific field errors only if user has interacted with the field
        if (etParentName.text.toString().isNotEmpty() && !parentNameValidation.isValid) {
            etParentName.error = parentNameValidation.errorMessage
        }
        
        if (etParentPhone.text.toString().isNotEmpty() && !phoneValidation.isValid) {
            etParentPhone.error = phoneValidation.errorMessage
        }
        
        if (etChildName.text.toString().isNotEmpty() && !childNameValidation.isValid) {
            etChildName.error = childNameValidation.errorMessage
        }
        
        // Don't show consent error in field, handle it in button text instead
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save form state for orientation changes
        outState.putString("parent_name", etParentName.text.toString())
        outState.putString("parent_phone", etParentPhone.text.toString())
        outState.putString("child_name", etChildName.text.toString())
        outState.putBoolean("consent_given", cbConsent.isChecked)
    }
    
    private fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.let { state ->
            etParentName.setText(state.getString("parent_name", ""))
            etParentPhone.setText(state.getString("parent_phone", ""))
            etChildName.setText(state.getString("child_name", ""))
            cbConsent.isChecked = state.getBoolean("consent_given", false)
            // Revalidate after restoration
            validateForm()
        }
    }
}