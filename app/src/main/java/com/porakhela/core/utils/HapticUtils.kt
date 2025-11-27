package com.porakhela.core.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.annotation.RequiresApi
import timber.log.Timber

/**
 * Utility class for haptic feedback throughout the app
 */
object HapticUtils {
    
    /**
     * Provide light haptic feedback for button taps
     */
    fun lightTap(view: View) {
        try {
            view.performHapticFeedback(
                HapticFeedbackConstants.VIRTUAL_KEY,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            )
        } catch (e: Exception) {
            Timber.w(e, "Failed to perform haptic feedback")
        }
    }
    
    /**
     * Provide medium haptic feedback for important actions
     */
    fun mediumTap(view: View) {
        try {
            view.performHapticFeedback(
                HapticFeedbackConstants.KEYBOARD_TAP,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            )
        } catch (e: Exception) {
            Timber.w(e, "Failed to perform haptic feedback")
        }
    }
    
    /**
     * Provide strong haptic feedback for success actions
     */
    fun strongTap(view: View) {
        try {
            view.performHapticFeedback(
                HapticFeedbackConstants.LONG_PRESS,
                HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            )
        } catch (e: Exception) {
            Timber.w(e, "Failed to perform haptic feedback")
        }
    }
    
    /**
     * Provide custom vibration for achievements or rewards
     */
    fun celebrationVibration(context: Context) {
        try {
            val vibrator = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }
            
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                // Create a celebration pattern: short-long-short
                val pattern = longArrayOf(0, 100, 50, 200, 50, 100)
                vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(longArrayOf(0, 100, 50, 200, 50, 100), -1)
            }
        } catch (e: Exception) {
            Timber.w(e, "Failed to perform celebration vibration")
        }
    }
}