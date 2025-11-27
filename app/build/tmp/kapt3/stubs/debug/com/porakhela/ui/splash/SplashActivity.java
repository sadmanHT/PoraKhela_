package com.porakhela.ui.splash;

/**
 * Splash screen activity that shows app logo and initializes the application
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0017J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0014J\b\u0010\u001b\u001a\u00020\u0012H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lcom/porakhela/ui/splash/SplashActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onboardingPreferences", "Lcom/porakhela/data/local/OnboardingPreferences;", "getOnboardingPreferences", "()Lcom/porakhela/data/local/OnboardingPreferences;", "setOnboardingPreferences", "(Lcom/porakhela/data/local/OnboardingPreferences;)V", "splashHandler", "Landroid/os/Handler;", "viewModel", "Lcom/porakhela/ui/splash/SplashViewModel;", "getViewModel", "()Lcom/porakhela/ui/splash/SplashViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkOnboardingStatusAndNavigate", "", "initializeApp", "navigateToMain", "navigateToOnboarding", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupObservers", "app_debug"})
public final class SplashActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler splashHandler = null;
    @javax.inject.Inject()
    public com.porakhela.data.local.OnboardingPreferences onboardingPreferences;
    
    public SplashActivity() {
        super();
    }
    
    private final com.porakhela.ui.splash.SplashViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.OnboardingPreferences getOnboardingPreferences() {
        return null;
    }
    
    public final void setOnboardingPreferences(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.OnboardingPreferences p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeApp() {
    }
    
    private final void setupObservers() {
    }
    
    private final void checkOnboardingStatusAndNavigate() {
    }
    
    private final void navigateToMain() {
    }
    
    private final void navigateToOnboarding() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    @java.lang.Deprecated()
    public void onBackPressed() {
    }
}