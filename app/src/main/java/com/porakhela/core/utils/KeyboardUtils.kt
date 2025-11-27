package com.porakhela.core.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import timber.log.Timber

/**
 * Utility class for keyboard management
 */
object KeyboardUtils {
    
    /**
     * Hide soft keyboard
     */
    fun hideKeyboard(activity: Activity) {
        try {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val currentFocus = activity.currentFocus
            if (currentFocus != null) {
                imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
            }
        } catch (e: Exception) {
            Timber.w(e, "Failed to hide keyboard")
        }
    }
    
    /**
     * Show soft keyboard for a specific view
     */
    fun showKeyboard(activity: Activity) {
        try {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } catch (e: Exception) {
            Timber.w(e, "Failed to show keyboard")
        }
    }
}