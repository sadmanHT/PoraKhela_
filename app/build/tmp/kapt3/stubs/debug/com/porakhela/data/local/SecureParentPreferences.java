package com.porakhela.data.local;

/**
 * Secure storage for parent dashboard settings using EncryptedSharedPreferences
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u000eJ\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013J\u0006\u0010\u0017\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0013J\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0013J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\fJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u000eJ\u000e\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006%"}, d2 = {"Lcom/porakhela/data/local/SecureParentPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "getCooldownEndTime", "", "getDailyLimitMinutes", "", "getFailedAttempts", "getParentPin", "", "hasParentPin", "", "incrementFailedAttempts", "isApprovalRequired", "isContentLockEnabled", "isInCooldown", "resetFailedAttempts", "", "setApprovalRequired", "required", "setContentLockEnabled", "enabled", "setCooldownEndTime", "endTime", "setDailyLimitMinutes", "minutes", "setParentPin", "pin", "Companion", "app_debug"})
public final class SecureParentPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy prefs$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PARENT_PIN = "parent_pin";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FAILED_ATTEMPTS = "failed_attempts";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_COOLDOWN_END = "cooldown_end_time";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DAILY_LIMIT = "daily_limit_minutes";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CONTENT_LOCK = "content_lock";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_APPROVAL_REQUIRED = "approval_required";
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.local.SecureParentPreferences.Companion Companion = null;
    
    @javax.inject.Inject()
    public SecureParentPreferences(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    public final void setParentPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getParentPin() {
        return null;
    }
    
    public final boolean hasParentPin() {
        return false;
    }
    
    public final int getFailedAttempts() {
        return 0;
    }
    
    public final int incrementFailedAttempts() {
        return 0;
    }
    
    public final void resetFailedAttempts() {
    }
    
    public final void setCooldownEndTime(long endTime) {
    }
    
    public final long getCooldownEndTime() {
        return 0L;
    }
    
    public final boolean isInCooldown() {
        return false;
    }
    
    public final void setDailyLimitMinutes(int minutes) {
    }
    
    public final int getDailyLimitMinutes() {
        return 0;
    }
    
    public final void setContentLockEnabled(boolean enabled) {
    }
    
    public final boolean isContentLockEnabled() {
        return false;
    }
    
    public final void setApprovalRequired(boolean required) {
    }
    
    public final boolean isApprovalRequired() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/porakhela/data/local/SecureParentPreferences$Companion;", "", "()V", "KEY_APPROVAL_REQUIRED", "", "KEY_CONTENT_LOCK", "KEY_COOLDOWN_END", "KEY_DAILY_LIMIT", "KEY_FAILED_ATTEMPTS", "KEY_PARENT_PIN", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}