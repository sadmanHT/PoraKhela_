package com.porakhela.core.utils

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * Utility class for input validation
 */
object ValidationUtils {
    
    // Validation patterns
    private val PHONE_PATTERN = Pattern.compile("^[0-9]{11}$")
    private val NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s.'-]{2,}$")
    
    /**
     * Validation result data class
     */
    data class ValidationResult(
        val isValid: Boolean,
        val errorMessage: String? = null
    )
    
    /**
     * Validate parent/guardian name
     */
    fun validateParentName(name: String?): ValidationResult {
        return when {
            name.isNullOrBlank() -> ValidationResult(false, "Parent name is required")
            name.trim().length < 2 -> ValidationResult(false, "Name must be at least 2 characters")
            name.trim().length > 50 -> ValidationResult(false, "Name must be less than 50 characters")
            !NAME_PATTERN.matcher(name.trim()).matches() -> ValidationResult(false, "Please enter a valid name")
            else -> ValidationResult(true)
        }
    }
    
    /**
     * Validate child name
     */
    fun validateChildName(name: String?): ValidationResult {
        return when {
            name.isNullOrBlank() -> ValidationResult(false, "Child's name is required")
            name.trim().length < 2 -> ValidationResult(false, "Name must be at least 2 characters")
            name.trim().length > 50 -> ValidationResult(false, "Name must be less than 50 characters")
            !NAME_PATTERN.matcher(name.trim()).matches() -> ValidationResult(false, "Please enter a valid name")
            else -> ValidationResult(true)
        }
    }
    
    /**
     * Validate Bangladesh phone number (11 digits)
     */
    fun validatePhoneNumber(phone: String?): ValidationResult {
        return when {
            phone.isNullOrBlank() -> ValidationResult(false, "Phone number is required")
            !phone.all { it.isDigit() } -> ValidationResult(false, "Phone number must contain only digits")
            phone.length != 11 -> ValidationResult(false, "Phone number must be exactly 11 digits")
            !phone.startsWith("01") -> ValidationResult(false, "Phone number must start with 01")
            !PHONE_PATTERN.matcher(phone).matches() -> ValidationResult(false, "Please enter a valid phone number")
            else -> ValidationResult(true)
        }
    }
    
    /**
     * Validate consent checkbox
     */
    fun validateConsent(consentGiven: Boolean): ValidationResult {
        return if (consentGiven) {
            ValidationResult(true)
        } else {
            ValidationResult(false, "Parental consent is required to proceed")
        }
    }
    
    /**
     * Validate all onboarding form fields
     */
    fun validateOnboardingForm(
        parentName: String?,
        parentPhone: String?,
        childName: String?,
        consentGiven: Boolean
    ): List<ValidationResult> {
        return listOf(
            validateParentName(parentName),
            validatePhoneNumber(parentPhone),
            validateChildName(childName),
            validateConsent(consentGiven)
        )
    }
    
    /**
     * Check if all validation results are valid
     */
    fun areAllValid(validationResults: List<ValidationResult>): Boolean {
        return validationResults.all { it.isValid }
    }
    
    /**
     * Get first error message from validation results
     */
    fun getFirstErrorMessage(validationResults: List<ValidationResult>): String? {
        return validationResults.firstOrNull { !it.isValid }?.errorMessage
    }
    
    /**
     * Format phone number for display (add +880 prefix)
     */
    fun formatPhoneForDisplay(phone: String): String {
        return if (phone.length == 11 && phone.startsWith("01")) {
            "+880 ${phone.substring(1)}"
        } else {
            phone
        }
    }
    
    /**
     * Clean phone number (remove +880 prefix if present)
     */
    fun cleanPhoneNumber(phone: String): String {
        return phone.replace("+880", "").replace(" ", "").trim()
    }
}