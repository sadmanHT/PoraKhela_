package com.porakhela.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.Synchronized

/**
 * Manager for handling Porapoints (gamification currency)
 */
@Singleton
class PorapointsManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFS_NAME = "porakhela_porapoints"
        private const val KEY_TOTAL_PORAPOINTS = "total_porapoints"
        private const val INITIAL_PORAPOINTS = 100
    }
    
    private val prefs: SharedPreferences = 
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    /**
     * Get current total porapoints
     */
    fun getCurrentPorapoints(): Int {
        return prefs.getInt(KEY_TOTAL_PORAPOINTS, INITIAL_PORAPOINTS)
    }
    
    /**
     * Add porapoints to current total (thread-safe)
     */
    @Synchronized
    fun addPorapoints(amount: Int): Int {
        if (amount < 0) {
            throw IllegalArgumentException("Cannot add negative porapoints: $amount")
        }
        
        val current = getCurrentPorapoints()
        val newTotal = current + amount
        
        // Validate reasonable limits
        if (newTotal > 1_000_000) {
            throw IllegalStateException("Porapoints total too high: $newTotal")
        }
        
        val success = prefs.edit().putInt(KEY_TOTAL_PORAPOINTS, newTotal).commit()
        if (!success) {
            throw IllegalStateException("Failed to save porapoints")
        }
        
        return newTotal
    }
    
    /**
     * Spend porapoints (if enough available)
     */
    fun spendPorapoints(amount: Int): Boolean {
        val current = getCurrentPorapoints()
        if (current >= amount) {
            prefs.edit().putInt(KEY_TOTAL_PORAPOINTS, current - amount).apply()
            return true
        }
        return false
    }
    
    /**
     * Reset porapoints (for testing/debug)
     */
    fun resetPorapoints() {
        prefs.edit().putInt(KEY_TOTAL_PORAPOINTS, INITIAL_PORAPOINTS).apply()
    }
}