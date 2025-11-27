package com.porakhela.core.components;

/**
 * Base ViewModel providing common functionality for all ViewModels in the app
 * Includes error handling and loading state management
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0004J5\u0010\u001b\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u00072\u001c\u0010\u001d\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001f\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001eH\u0004\u00a2\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u0017H\u0004J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0007H\u0004J\u001b\u0010$\u001a\u00020\u0017\"\u0004\b\u0000\u0010%2\u0006\u0010&\u001a\u0002H%H\u0004\u00a2\u0006\u0002\u0010\'R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006("}, d2 = {"Lcom/porakhela/core/components/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_errorMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isLoading", "", "_uiState", "Lcom/porakhela/core/utils/UiState;", "", "errorMessage", "Lkotlinx/coroutines/flow/StateFlow;", "getErrorMessage", "()Lkotlinx/coroutines/flow/StateFlow;", "exceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "isLoading", "uiState", "getUiState", "clearError", "", "handleError", "throwable", "", "launchWithErrorHandling", "showLoading", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(ZLkotlin/jvm/functions/Function1;)V", "resetState", "setLoading", "loading", "setSuccess", "T", "data", "(Ljava/lang/Object;)V", "app_debug"})
public abstract class BaseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.core.utils.UiState<java.lang.Object>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.core.utils.UiState<java.lang.Object>> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    
    /**
     * Global exception handler for coroutines launched in this ViewModel
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineExceptionHandler exceptionHandler = null;
    
    public BaseViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.core.utils.UiState<java.lang.Object>> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    /**
     * Global exception handler for coroutines launched in this ViewModel
     */
    @org.jetbrains.annotations.NotNull()
    protected final kotlinx.coroutines.CoroutineExceptionHandler getExceptionHandler() {
        return null;
    }
    
    /**
     * Launch a coroutine with built-in error handling
     */
    protected final void launchWithErrorHandling(boolean showLoading, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
    }
    
    /**
     * Set loading state
     */
    protected final void setLoading(boolean loading) {
    }
    
    /**
     * Handle errors consistently across ViewModels
     */
    protected final void handleError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable) {
    }
    
    /**
     * Clear any current error state
     */
    public final void clearError() {
    }
    
    /**
     * Set success state with data
     */
    protected final <T extends java.lang.Object>void setSuccess(T data) {
    }
    
    /**
     * Reset to idle state
     */
    protected final void resetState() {
    }
}