package com.porakhela.core.components;

/**
 * Base Activity providing common functionality for all activities
 * Includes ViewBinding setup and error handling
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u000f\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014J0\u0010\u0017\u001a\u00020\u0013\"\u0004\b\u0002\u0010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00130\u001cH\u0004Jv\u0010\u001d\u001a\u00020\u0013\"\u0004\b\u0002\u0010\u00182\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u001e0\u001a2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010 2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001c2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001c2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010 H\u0004J\u0012\u0010%\u001a\u00020\u00132\b\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\b\u0010(\u001a\u00020\u0013H\u0014J\b\u0010)\u001a\u00020\u0013H\u0014R\u001c\u0010\t\u001a\u00028\u0000X\u0084.\u00a2\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00028\u0001X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006*"}, d2 = {"Lcom/porakhela/core/components/BaseActivity;", "VB", "Landroidx/databinding/ViewDataBinding;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "layoutId", "", "(I)V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "viewModel", "getViewModel", "()Landroidx/lifecycle/ViewModel;", "handleInitializationError", "", "error", "", "initializeUI", "observeStateFlow", "T", "stateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "onStateChanged", "Lkotlin/Function1;", "observeUiState", "Lcom/porakhela/core/utils/UiState;", "onLoading", "Lkotlin/Function0;", "onSuccess", "onError", "", "onIdle", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupObservers", "app_release"})
public abstract class BaseActivity<VB extends androidx.databinding.ViewDataBinding, VM extends androidx.lifecycle.ViewModel> extends androidx.appcompat.app.AppCompatActivity {
    private final int layoutId = 0;
    protected VB binding;
    
    public BaseActivity(@androidx.annotation.LayoutRes()
    int layoutId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final VB getBinding() {
        return null;
    }
    
    protected final void setBinding(@org.jetbrains.annotations.NotNull()
    VB p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected abstract VM getViewModel();
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Initialize UI components - override in child activities
     */
    protected void initializeUI() {
    }
    
    /**
     * Set up data observers - override in child activities
     */
    protected void setupObservers() {
    }
    
    /**
     * Handle errors that occur during activity initialization
     */
    protected void handleInitializationError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable error) {
    }
    
    /**
     * Helper method to observe UI state changes
     */
    protected final <T extends java.lang.Object>void observeUiState(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<? extends com.porakhela.core.utils.UiState<? extends T>> stateFlow, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLoading, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> onSuccess, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onIdle) {
    }
    
    /**
     * Helper method to observe simple state flows
     */
    protected final <T extends java.lang.Object>void observeStateFlow(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<? extends T> stateFlow, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> onStateChanged) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
}