package com.porakhela.core.utils;

/**
 * Utility class for input validation
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ\u0016\u0010\u0010\u001a\u0004\u0018\u00010\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0010\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\bJ2\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\bJ\u0010\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u001a\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/porakhela/core/utils/ValidationUtils;", "", "()V", "NAME_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "PHONE_PATTERN", "areAllValid", "", "validationResults", "", "Lcom/porakhela/core/utils/ValidationUtils$ValidationResult;", "cleanPhoneNumber", "", "phone", "formatPhoneForDisplay", "getFirstErrorMessage", "validateChildName", "name", "validateConsent", "consentGiven", "validateOnboardingForm", "parentName", "parentPhone", "childName", "validateParentName", "validatePhoneNumber", "ValidationResult", "app_debug"})
public final class ValidationUtils {
    private static final java.util.regex.Pattern PHONE_PATTERN = null;
    private static final java.util.regex.Pattern NAME_PATTERN = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.core.utils.ValidationUtils INSTANCE = null;
    
    private ValidationUtils() {
        super();
    }
    
    /**
     * Validate parent/guardian name
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.core.utils.ValidationUtils.ValidationResult validateParentName(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return null;
    }
    
    /**
     * Validate child name
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.core.utils.ValidationUtils.ValidationResult validateChildName(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return null;
    }
    
    /**
     * Validate Bangladesh phone number (11 digits)
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.core.utils.ValidationUtils.ValidationResult validatePhoneNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String phone) {
        return null;
    }
    
    /**
     * Validate consent checkbox
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.core.utils.ValidationUtils.ValidationResult validateConsent(boolean consentGiven) {
        return null;
    }
    
    /**
     * Validate all onboarding form fields
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.porakhela.core.utils.ValidationUtils.ValidationResult> validateOnboardingForm(@org.jetbrains.annotations.Nullable()
    java.lang.String parentName, @org.jetbrains.annotations.Nullable()
    java.lang.String parentPhone, @org.jetbrains.annotations.Nullable()
    java.lang.String childName, boolean consentGiven) {
        return null;
    }
    
    /**
     * Check if all validation results are valid
     */
    public final boolean areAllValid(@org.jetbrains.annotations.NotNull()
    java.util.List<com.porakhela.core.utils.ValidationUtils.ValidationResult> validationResults) {
        return false;
    }
    
    /**
     * Get first error message from validation results
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFirstErrorMessage(@org.jetbrains.annotations.NotNull()
    java.util.List<com.porakhela.core.utils.ValidationUtils.ValidationResult> validationResults) {
        return null;
    }
    
    /**
     * Format phone number for display (add +880 prefix)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatPhoneForDisplay(@org.jetbrains.annotations.NotNull()
    java.lang.String phone) {
        return null;
    }
    
    /**
     * Clean phone number (remove +880 prefix if present)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String cleanPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String phone) {
        return null;
    }
    
    /**
     * Validation result data class
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t\u00a8\u0006\u0012"}, d2 = {"Lcom/porakhela/core/utils/ValidationUtils$ValidationResult;", "", "isValid", "", "errorMessage", "", "(ZLjava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    public static final class ValidationResult {
        private final boolean isValid = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String errorMessage = null;
        
        public ValidationResult(boolean isValid, @org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage) {
            super();
        }
        
        public final boolean isValid() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getErrorMessage() {
            return null;
        }
        
        public final boolean component1() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.porakhela.core.utils.ValidationUtils.ValidationResult copy(boolean isValid, @org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}