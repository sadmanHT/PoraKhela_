package com.porakhela.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Secure storage for parent dashboard settings using EncryptedSharedPreferences
 */
@Singleton
class SecureParentPreferences @Inject constructor(
    private val context: Context
) {
    private val prefs: SharedPreferences by lazy {
        try {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            
            EncryptedSharedPreferences.create(
                context,
                "parent_secure_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            Timber.e(e, "Failed to create encrypted prefs, falling back to regular")
            context.getSharedPreferences("parent_fallback_prefs", Context.MODE_PRIVATE)
        }
    }
    
    // PIN Management
    fun setParentPin(pin: String) {
        if (pin.length != 4 || !pin.all { it.isDigit() }) {
            Timber.e("Invalid PIN format: must be exactly 4 digits")
            return
        }
        prefs.edit().putString(KEY_PARENT_PIN, pin).apply()
    }
    
    fun getParentPin(): String? {
        return prefs.getString(KEY_PARENT_PIN, null)
    }
    
    fun hasParentPin(): Boolean {
        return getParentPin() != null
    }
    
    // Failed Attempts Management
    fun getFailedAttempts(): Int {
        return prefs.getInt(KEY_FAILED_ATTEMPTS, 0)
    }
    
    fun incrementFailedAttempts(): Int {
        val attempts = getFailedAttempts() + 1
        prefs.edit().putInt(KEY_FAILED_ATTEMPTS, attempts).apply()
        return attempts
    }
    
    fun resetFailedAttempts() {
        prefs.edit().putInt(KEY_FAILED_ATTEMPTS, 0).apply()
    }
    
    // Cooldown Management
    fun setCooldownEndTime(endTime: Long) {
        prefs.edit().putLong(KEY_COOLDOWN_END, endTime).apply()
    }
    
    fun getCooldownEndTime(): Long {
        return prefs.getLong(KEY_COOLDOWN_END, 0)
    }
    
    fun isInCooldown(): Boolean {
        return System.currentTimeMillis() < getCooldownEndTime()
    }
    
    // Screen Time Control
    fun setDailyLimitMinutes(minutes: Int) {
        prefs.edit().putInt(KEY_DAILY_LIMIT, minutes).apply()
    }
    
    fun getDailyLimitMinutes(): Int {
        return prefs.getInt(KEY_DAILY_LIMIT, 60) // Default 60 minutes
    }
    
    // Content Lock
    fun setContentLockEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_CONTENT_LOCK, enabled).apply()
    }
    
    fun isContentLockEnabled(): Boolean {
        return prefs.getBoolean(KEY_CONTENT_LOCK, false)
    }
    
    // Redemption Approval
    fun setApprovalRequired(required: Boolean) {
        prefs.edit().putBoolean(KEY_APPROVAL_REQUIRED, required).apply()
    }
    
    fun isApprovalRequired(): Boolean {
        return prefs.getBoolean(KEY_APPROVAL_REQUIRED, true) // Default true for safety
    }
    
    companion object {
        private const val KEY_PARENT_PIN = "parent_pin"
        private const val KEY_FAILED_ATTEMPTS = "failed_attempts"
        private const val KEY_COOLDOWN_END = "cooldown_end_time"
        private const val KEY_DAILY_LIMIT = "daily_limit_minutes"
        private const val KEY_CONTENT_LOCK = "content_lock"
        private const val KEY_APPROVAL_REQUIRED = "approval_required"
    }
}