package com.porakhela.core.components

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.porakhela.core.utils.HapticUtils

/**
 * Custom button that provides automatic haptic feedback and enhanced touch experience
 */
class PorakhelaButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    
    private var hapticEnabled = true
    
    init {
        // Enable haptic feedback by default
        isHapticFeedbackEnabled = true
    }
    
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (hapticEnabled && isEnabled) {
                    HapticUtils.lightTap(this)
                }
                // Add slight scale down animation for press feedback
                animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .start()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // Return to normal scale
                animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(100)
                    .start()
            }
        }
        return super.onTouchEvent(event)
    }
    
    /**
     * Enable or disable haptic feedback for this button
     */
    fun setHapticEnabled(enabled: Boolean) {
        hapticEnabled = enabled
    }
    
    /**
     * Trigger success feedback (stronger haptic + celebration)
     */
    fun triggerSuccessFeedback() {
        if (hapticEnabled && isEnabled) {
            HapticUtils.strongTap(this)
            // Add success animation
            animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setDuration(150)
                .withEndAction {
                    animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(150)
                        .start()
                }
                .start()
        }
    }
}