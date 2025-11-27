package com.porakhela.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager for SharedPreferences operations related to onboarding and user data
 */
@Singleton
class OnboardingPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    
    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    companion object {
        private const val PREFS_NAME = "porakhela_onboarding"
        
        // Keys for onboarding data
        private const val KEY_PARENT_NAME = "parent_name"
        private const val KEY_PARENT_PHONE = "parent_phone"
        private const val KEY_CHILD_NAME = "child_name"
        private const val KEY_CONSENT_GIVEN = "consent_given"
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
    
    /**
     * Save parent information
     */
    fun saveParentInfo(name: String, phone: String) {
        preferences.edit()
            .putString(KEY_PARENT_NAME, name.trim())
            .putString(KEY_PARENT_PHONE, phone.trim())
            .apply()
        
        Timber.d("Parent info saved: $name")
    }
    
    /**
     * Save child information
     */
    fun saveChildInfo(name: String) {
        preferences.edit()
            .putString(KEY_CHILD_NAME, name.trim())
            .apply()
        
        Timber.d("Child info saved: $name")
    }
    
    /**
     * Save consent status
     */
    fun saveConsentStatus(consentGiven: Boolean) {
        preferences.edit()
            .putBoolean(KEY_CONSENT_GIVEN, consentGiven)
            .apply()
        
        Timber.d("Consent status saved: $consentGiven")
    }
    
    /**
     * Mark onboarding as completed
     */
    fun markOnboardingCompleted() {
        preferences.edit()
            .putBoolean(KEY_ONBOARDING_COMPLETED, true)
            .apply()
        
        Timber.i("Onboarding marked as completed")
    }
    
    /**
     * Save all onboarding data at once
     */
    fun saveOnboardingData(
        parentName: String,
        parentPhone: String,
        childName: String,
        consentGiven: Boolean
    ) {
        preferences.edit()
            .putString(KEY_PARENT_NAME, parentName.trim())
            .putString(KEY_PARENT_PHONE, parentPhone.trim())
            .putString(KEY_CHILD_NAME, childName.trim())
            .putBoolean(KEY_CONSENT_GIVEN, consentGiven)
            .putBoolean(KEY_ONBOARDING_COMPLETED, true)
            .putBoolean(KEY_FIRST_LAUNCH, false)
            .apply()
        
        Timber.i("All onboarding data saved successfully")
    }
    
    /**
     * Check if onboarding is completed
     */
    fun isOnboardingCompleted(): Boolean {
        return preferences.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }
    
    /**
     * Check if this is first launch
     */
    fun isFirstLaunch(): Boolean {
        return preferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }
    
    /**
     * Get parent name
     */
    fun getParentName(): String? {
        return preferences.getString(KEY_PARENT_NAME, null)
    }
    
    /**
     * Get parent phone
     */
    fun getParentPhone(): String? {
        return preferences.getString(KEY_PARENT_PHONE, null)
    }
    
    /**
     * Get child name
     */
    fun getChildName(): String? {
        return preferences.getString(KEY_CHILD_NAME, null)
    }
    
    /**
     * Check if consent was given
     */
    fun isConsentGiven(): Boolean {
        return preferences.getBoolean(KEY_CONSENT_GIVEN, false)
    }
    
    /**
     * Get all onboarding data as a data class
     */
    fun getOnboardingData(): OnboardingData {
        return OnboardingData(
            parentName = getParentName(),
            parentPhone = getParentPhone(),
            childName = getChildName(),
            consentGiven = isConsentGiven(),
            onboardingCompleted = isOnboardingCompleted(),
            isFirstLaunch = isFirstLaunch()
        )
    }
    
    /**
     * Clear all onboarding data (for testing or reset)
     */
    fun clearOnboardingData() {
        preferences.edit()
            .remove(KEY_PARENT_NAME)
            .remove(KEY_PARENT_PHONE)
            .remove(KEY_CHILD_NAME)
            .remove(KEY_CONSENT_GIVEN)
            .remove(KEY_ONBOARDING_COMPLETED)
            .putBoolean(KEY_FIRST_LAUNCH, true)
            .apply()
        
        Timber.w("All onboarding data cleared")
    }
}

/**
 * Data class to hold all onboarding information
 */
data class OnboardingData(
    val parentName: String?,
    val parentPhone: String?,
    val childName: String?,
    val consentGiven: Boolean,
    val onboardingCompleted: Boolean,
    val isFirstLaunch: Boolean
) {
    /**
     * Check if all required data is present
     */
    fun isComplete(): Boolean {
        return !parentName.isNullOrBlank() &&
               !parentPhone.isNullOrBlank() &&
               !childName.isNullOrBlank() &&
               consentGiven &&
               onboardingCompleted
    }
    
    /**
     * Get a friendly greeting for the child
     */
    fun getChildGreeting(): String {
        return if (!childName.isNullOrBlank()) {
            "Hello, $childName!"
        } else {
            "Hello, Learner!"
        }
    }
}