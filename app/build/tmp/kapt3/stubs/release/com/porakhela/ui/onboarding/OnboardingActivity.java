package com.porakhela.ui.onboarding;

/**
 * Main onboarding activity with ViewPager2 flow
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0017J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\fH\u0014J\b\u0010\u0015\u001a\u00020\fH\u0002J\b\u0010\u0016\u001a\u00020\fH\u0002J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\fH\u0002J\b\u0010\u001b\u001a\u00020\fH\u0002J\b\u0010\u001c\u001a\u00020\fH\u0002J\b\u0010\u001d\u001a\u00020\fH\u0002J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006 "}, d2 = {"Lcom/porakhela/ui/onboarding/OnboardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "pagerAdapter", "Lcom/porakhela/ui/onboarding/adapters/OnboardingPagerAdapter;", "viewModel", "Lcom/porakhela/ui/onboarding/OnboardingViewModel;", "getViewModel", "()Lcom/porakhela/ui/onboarding/OnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "completeOnboarding", "", "disableContinueButton", "enableContinueButton", "navigateToMainApp", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNextClicked", "onSkipClicked", "onTrimMemory", "level", "", "setupNavigationButtons", "setupObservers", "setupPageIndicator", "setupViewPager", "updateNavigationButtons", "position", "app_release"})
public final class OnboardingActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.porakhela.ui.onboarding.adapters.OnboardingPagerAdapter pagerAdapter;
    
    public OnboardingActivity() {
        super();
    }
    
    private final com.porakhela.ui.onboarding.OnboardingViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupViewPager() {
    }
    
    private final void setupPageIndicator() {
    }
    
    private final void setupNavigationButtons() {
    }
    
    private final void updateNavigationButtons(int position) {
    }
    
    private final void onSkipClicked() {
    }
    
    private final void onNextClicked() {
    }
    
    private final void setupObservers() {
    }
    
    private final void navigateToMainApp() {
    }
    
    /**
     * Complete the onboarding process and navigate to main app
     */
    public final void completeOnboarding() {
    }
    
    @java.lang.Override()
    @java.lang.Deprecated()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onTrimMemory(int level) {
    }
    
    /**
     * Called by consent fragment when form is valid
     */
    public final void enableContinueButton() {
    }
    
    /**
     * Called by consent fragment when form becomes invalid
     */
    public final void disableContinueButton() {
    }
}