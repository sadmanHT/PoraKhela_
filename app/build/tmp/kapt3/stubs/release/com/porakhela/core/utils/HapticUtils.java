package com.porakhela.core.utils;

/**
 * Utility class for haptic feedback throughout the app
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\f"}, d2 = {"Lcom/porakhela/core/utils/HapticUtils;", "", "()V", "celebrationVibration", "", "context", "Landroid/content/Context;", "lightTap", "view", "Landroid/view/View;", "mediumTap", "strongTap", "app_release"})
public final class HapticUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.core.utils.HapticUtils INSTANCE = null;
    
    private HapticUtils() {
        super();
    }
    
    /**
     * Provide light haptic feedback for button taps
     */
    public final void lightTap(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Provide medium haptic feedback for important actions
     */
    public final void mediumTap(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Provide strong haptic feedback for success actions
     */
    public final void strongTap(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Provide custom vibration for achievements or rewards
     */
    public final void celebrationVibration(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}