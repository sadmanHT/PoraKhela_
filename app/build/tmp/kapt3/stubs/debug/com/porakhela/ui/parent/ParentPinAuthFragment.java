package com.porakhela.ui.parent;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0002J&\u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001bH\u0016J\u001a\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010 \u001a\u00020\u000fH\u0002J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0007H\u0002J\b\u0010\"\u001a\u00020\u000fH\u0002J\b\u0010#\u001a\u00020\u000fH\u0002J\u0010\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006("}, d2 = {"Lcom/porakhela/ui/parent/ParentPinAuthFragment;", "Landroidx/fragment/app/Fragment;", "()V", "currentPin", "", "pinDots", "", "Landroid/view/View;", "viewModel", "Lcom/porakhela/ui/parent/ParentDashboardViewModel;", "getViewModel", "()Lcom/porakhela/ui/parent/ParentDashboardViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "addDigit", "", "digit", "clearPin", "disableNumberPad", "enableNumberPad", "observeAuthState", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onViewCreated", "view", "removeDigit", "setupViews", "submitPin", "updatePinDots", "updateUI", "state", "Lcom/porakhela/ui/parent/AuthState;", "Companion", "app_debug"})
public final class ParentPinAuthFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_PIN = "current_pin";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentPin = "";
    private java.util.List<? extends android.view.View> pinDots;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.ui.parent.ParentPinAuthFragment.Companion Companion = null;
    
    public ParentPinAuthFragment() {
        super();
    }
    
    private final com.porakhela.ui.parent.ParentDashboardViewModel getViewModel() {
        return null;
    }
    
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
    
    private final void setupViews(android.view.View view) {
    }
    
    private final void observeAuthState() {
    }
    
    private final void updateUI(com.porakhela.ui.parent.AuthState state) {
    }
    
    private final void addDigit(java.lang.String digit) {
    }
    
    private final void removeDigit() {
    }
    
    private final void clearPin() {
    }
    
    private final void updatePinDots() {
    }
    
    private final void submitPin() {
    }
    
    private final void disableNumberPad() {
    }
    
    private final void enableNumberPad() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/porakhela/ui/parent/ParentPinAuthFragment$Companion;", "", "()V", "KEY_CURRENT_PIN", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}