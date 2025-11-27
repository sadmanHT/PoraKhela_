package com.porakhela.ui.onboarding;

/**
 * ViewModel for onboarding flow
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\tJ&\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\tJ\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0007J\u0006\u0010\u001d\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/porakhela/ui/onboarding/OnboardingViewModel;", "Lcom/porakhela/core/components/BaseViewModel;", "onboardingPreferences", "Lcom/porakhela/data/local/OnboardingPreferences;", "(Lcom/porakhela/data/local/OnboardingPreferences;)V", "_currentPage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_onboardingCompleted", "", "currentPage", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentPage", "()Lkotlinx/coroutines/flow/StateFlow;", "onboardingCompleted", "getOnboardingCompleted", "checkOnboardingStatus", "", "getOnboardingData", "Lcom/porakhela/data/local/OnboardingData;", "isOnboardingCompleted", "saveOnboardingData", "parentName", "", "parentPhone", "childName", "consentGiven", "setCurrentPage", "page", "skipOnboarding", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class OnboardingViewModel extends com.porakhela.core.components.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.OnboardingPreferences onboardingPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _currentPage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> currentPage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _onboardingCompleted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> onboardingCompleted = null;
    
    @javax.inject.Inject()
    public OnboardingViewModel(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.OnboardingPreferences onboardingPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getCurrentPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getOnboardingCompleted() {
        return null;
    }
    
    private final void checkOnboardingStatus() {
    }
    
    public final void setCurrentPage(int page) {
    }
    
    public final void saveOnboardingData(@org.jetbrains.annotations.NotNull()
    java.lang.String parentName, @org.jetbrains.annotations.NotNull()
    java.lang.String parentPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String childName, boolean consentGiven) {
    }
    
    public final void skipOnboarding() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.OnboardingData getOnboardingData() {
        return null;
    }
    
    public final boolean isOnboardingCompleted() {
        return false;
    }
}