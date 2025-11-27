package com.porakhela.ui.parent;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\u0006\u0010\u0015\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\u0006\u0010\u0019\u001a\u00020\u0011J\u0006\u0010\u001a\u001a\u00020\u0011J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\u000e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006$"}, d2 = {"Lcom/porakhela/ui/parent/ParentDashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/porakhela/data/repository/ParentDashboardRepository;", "(Lcom/porakhela/data/repository/ParentDashboardRepository;)V", "_authState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/porakhela/ui/parent/AuthState;", "_uiState", "Lcom/porakhela/ui/parent/ParentDashboardUiState;", "authState", "Lkotlinx/coroutines/flow/StateFlow;", "getAuthState", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "authenticatePin", "", "pin", "", "checkInitialAuthState", "clearError", "clearShakeAnimation", "createPin", "loadDashboardData", "logout", "refreshData", "startCooldownTimer", "updateApprovalRequired", "required", "", "updateContentLock", "enabled", "updateDailyLimit", "minutes", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ParentDashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.ParentDashboardRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.ui.parent.ParentDashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.parent.ParentDashboardUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.ui.parent.AuthState> _authState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.parent.AuthState> authState = null;
    
    @javax.inject.Inject()
    public ParentDashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.ParentDashboardRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.parent.ParentDashboardUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.parent.AuthState> getAuthState() {
        return null;
    }
    
    private final void checkInitialAuthState() {
    }
    
    public final void createPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
    }
    
    public final void authenticatePin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
    }
    
    private final void startCooldownTimer() {
    }
    
    public final void clearShakeAnimation() {
    }
    
    public final void logout() {
    }
    
    private final void loadDashboardData() {
    }
    
    public final void updateDailyLimit(int minutes) {
    }
    
    public final void updateContentLock(boolean enabled) {
    }
    
    public final void updateApprovalRequired(boolean required) {
    }
    
    public final void refreshData() {
    }
    
    public final void clearError() {
    }
}