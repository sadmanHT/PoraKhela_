package com.porakhela.core.components;

/**
 * Base Fragment providing common functionality for all fragments
 * Includes ViewBinding setup and error handling
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u000f\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014J0\u0010\u0016\u001a\u00020\u0012\"\u0004\b\u0002\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u00020\u00120\u001bH\u0004Jv\u0010\u001c\u001a\u00020\u0012\"\u0004\b\u0002\u0010\u00172\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001d0\u00192\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001f2\u0016\b\u0002\u0010 \u001a\u0010\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001b2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001b2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001fH\u0004J&\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0012H\u0016J\u001a\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010/\u001a\u00020\u0012H\u0014R\u0012\u0010\t\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00028\u00008DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00028\u0001X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u00a8\u00060"}, d2 = {"Lcom/porakhela/core/components/BaseFragment;", "VB", "Landroidx/databinding/ViewDataBinding;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "layoutId", "", "(I)V", "_binding", "Landroidx/databinding/ViewDataBinding;", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "viewModel", "getViewModel", "()Landroidx/lifecycle/ViewModel;", "handleInitializationError", "", "error", "", "initializeUI", "observeStateFlow", "T", "stateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "onStateChanged", "Lkotlin/Function1;", "observeUiState", "Lcom/porakhela/core/utils/UiState;", "onLoading", "Lkotlin/Function0;", "onSuccess", "onError", "", "onIdle", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupObservers", "app_debug"})
public abstract class BaseFragment<VB extends androidx.databinding.ViewDataBinding, VM extends androidx.lifecycle.ViewModel> extends androidx.fragment.app.Fragment {
    private final int layoutId = 0;
    @org.jetbrains.annotations.Nullable()
    private VB _binding;
    
    public BaseFragment(@androidx.annotation.LayoutRes()
    int layoutId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final VB getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected abstract VM getViewModel();
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Initialize UI components - override in child fragments
     */
    protected void initializeUI() {
    }
    
    /**
     * Set up data observers - override in child fragments
     */
    protected void setupObservers() {
    }
    
    /**
     * Handle errors that occur during fragment initialization
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
    public void onDestroyView() {
    }
}