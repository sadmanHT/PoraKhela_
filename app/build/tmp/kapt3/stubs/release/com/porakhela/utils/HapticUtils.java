package com.porakhela.utils;

/**
 * Utility class for haptic feedback throughout the app
 * Provides consistent haptic feedback patterns for user interactions
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\r"}, d2 = {"Lcom/porakhela/utils/HapticUtils;", "", "()V", "errorPattern", "", "context", "Landroid/content/Context;", "lightTap", "view", "Landroid/view/View;", "longPress", "mediumTap", "successPattern", "app_release"})
public final class HapticUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.utils.HapticUtils INSTANCE = null;
    
    private HapticUtils() {
        super();
    }
    
    /**
     * Light tap feedback - for button presses, card taps
     */
    public final void lightTap(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Medium tap feedback - for important actions, navigation
     */
    public final void mediumTap(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Long press feedback - for special actions, developer tools
     */
    public final void longPress(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    /**
     * Success pattern - for completing lessons, earning points
     */
    public final void successPattern(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Error pattern - for mistakes, invalid actions
     */
    public final void errorPattern(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}