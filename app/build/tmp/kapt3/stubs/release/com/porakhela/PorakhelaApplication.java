package com.porakhela;

/**
 * Main Application class for Porakhela
 * Sets up dependency injection, logging, and crash handling
 */
@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\bH\u0002J\b\u0010\u000e\u001a\u00020\bH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/porakhela/PorakhelaApplication;", "Landroid/app/Application;", "()V", "globalExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getGlobalExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleGlobalException", "", "exception", "", "onCreate", "setupGlobalExceptionHandling", "setupStrictMode", "setupTimber", "ReleaseTree", "app_release"})
public final class PorakhelaApplication extends android.app.Application {
    
    /**
     * Global exception handler for uncaught coroutine exceptions
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineExceptionHandler globalExceptionHandler = null;
    
    public PorakhelaApplication() {
        super();
    }
    
    /**
     * Global exception handler for uncaught coroutine exceptions
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineExceptionHandler getGlobalExceptionHandler() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void setupTimber() {
    }
    
    private final void setupStrictMode() {
    }
    
    private final void setupGlobalExceptionHandling() {
    }
    
    private final void handleGlobalException(java.lang.Throwable exception) {
    }
    
    /**
     * Release tree that filters out verbose and debug logs in production
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J,\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014\u00a8\u0006\u000e"}, d2 = {"Lcom/porakhela/PorakhelaApplication$ReleaseTree;", "Ltimber/log/Timber$Tree;", "()V", "isLoggable", "", "tag", "", "priority", "", "log", "", "message", "t", "", "app_release"})
    static final class ReleaseTree extends timber.log.Timber.Tree {
        
        public ReleaseTree() {
            super();
        }
        
        @java.lang.Override()
        protected boolean isLoggable(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, int priority) {
            return false;
        }
        
        @java.lang.Override()
        protected void log(int priority, @org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable t) {
        }
    }
}