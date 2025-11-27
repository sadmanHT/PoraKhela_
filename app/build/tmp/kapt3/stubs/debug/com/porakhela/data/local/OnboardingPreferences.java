package com.porakhela.data.local;

/**
 * Manager for SharedPreferences operations related to onboarding and user data
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0014J\u0006\u0010\u0017\u001a\u00020\fJ\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0014J&\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0014J\u0016\u0010 \u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006#"}, d2 = {"Lcom/porakhela/data/local/OnboardingPreferences;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "preferences$delegate", "Lkotlin/Lazy;", "clearOnboardingData", "", "getChildName", "", "getOnboardingData", "Lcom/porakhela/data/local/OnboardingData;", "getParentName", "getParentPhone", "isConsentGiven", "", "isFirstLaunch", "isOnboardingCompleted", "markOnboardingCompleted", "saveChildInfo", "name", "saveConsentStatus", "consentGiven", "saveOnboardingData", "parentName", "parentPhone", "childName", "saveParentInfo", "phone", "Companion", "app_debug"})
public final class OnboardingPreferences {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy preferences$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "porakhela_onboarding";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PARENT_NAME = "parent_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PARENT_PHONE = "parent_phone";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CHILD_NAME = "child_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CONSENT_GIVEN = "consent_given";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ONBOARDING_COMPLETED = "onboarding_completed";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FIRST_LAUNCH = "first_launch";
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.local.OnboardingPreferences.Companion Companion = null;
    
    @javax.inject.Inject()
    public OnboardingPreferences(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.content.SharedPreferences getPreferences() {
        return null;
    }
    
    /**
     * Save parent information
     */
    public final void saveParentInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone) {
    }
    
    /**
     * Save child information
     */
    public final void saveChildInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Save consent status
     */
    public final void saveConsentStatus(boolean consentGiven) {
    }
    
    /**
     * Mark onboarding as completed
     */
    public final void markOnboardingCompleted() {
    }
    
    /**
     * Save all onboarding data at once
     */
    public final void saveOnboardingData(@org.jetbrains.annotations.NotNull()
    java.lang.String parentName, @org.jetbrains.annotations.NotNull()
    java.lang.String parentPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String childName, boolean consentGiven) {
    }
    
    /**
     * Check if onboarding is completed
     */
    public final boolean isOnboardingCompleted() {
        return false;
    }
    
    /**
     * Check if this is first launch
     */
    public final boolean isFirstLaunch() {
        return false;
    }
    
    /**
     * Get parent name
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getParentName() {
        return null;
    }
    
    /**
     * Get parent phone
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getParentPhone() {
        return null;
    }
    
    /**
     * Get child name
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getChildName() {
        return null;
    }
    
    /**
     * Check if consent was given
     */
    public final boolean isConsentGiven() {
        return false;
    }
    
    /**
     * Get all onboarding data as a data class
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.OnboardingData getOnboardingData() {
        return null;
    }
    
    /**
     * Clear all onboarding data (for testing or reset)
     */
    public final void clearOnboardingData() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/porakhela/data/local/OnboardingPreferences$Companion;", "", "()V", "KEY_CHILD_NAME", "", "KEY_CONSENT_GIVEN", "KEY_FIRST_LAUNCH", "KEY_ONBOARDING_COMPLETED", "KEY_PARENT_NAME", "KEY_PARENT_PHONE", "PREFS_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}