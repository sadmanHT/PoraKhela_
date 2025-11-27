package com.porakhela.ui.main;

/**
 * ViewModel for MainActivity
 * Manages main activity state and navigation
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/porakhela/ui/main/MainViewModel;", "Lcom/porakhela/core/components/BaseViewModel;", "()V", "_currentTab", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/porakhela/ui/main/MainViewModel$CurrentTab;", "currentTab", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentTab", "()Lkotlinx/coroutines/flow/StateFlow;", "setCurrentTab", "", "tab", "CurrentTab", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainViewModel extends com.porakhela.core.components.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.ui.main.MainViewModel.CurrentTab> _currentTab = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.main.MainViewModel.CurrentTab> currentTab = null;
    
    @javax.inject.Inject()
    public MainViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.main.MainViewModel.CurrentTab> getCurrentTab() {
        return null;
    }
    
    public final void setCurrentTab(@org.jetbrains.annotations.NotNull()
    com.porakhela.ui.main.MainViewModel.CurrentTab tab) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/porakhela/ui/main/MainViewModel$CurrentTab;", "", "(Ljava/lang/String;I)V", "HOME", "LESSONS", "REWARDS", "PROFILE", "app_debug"})
    public static enum CurrentTab {
        /*public static final*/ HOME /* = new HOME() */,
        /*public static final*/ LESSONS /* = new LESSONS() */,
        /*public static final*/ REWARDS /* = new REWARDS() */,
        /*public static final*/ PROFILE /* = new PROFILE() */;
        
        CurrentTab() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.porakhela.ui.main.MainViewModel.CurrentTab> getEntries() {
            return null;
        }
    }
}