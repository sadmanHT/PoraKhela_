package com.porakhela.ui.splash;

/**
 * ViewModel for splash screen
 * Handles app initialization and determines navigation flow
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/porakhela/ui/splash/SplashViewModel;", "Lcom/porakhela/core/components/BaseViewModel;", "()V", "checkFirstLaunch", "", "initialize", "", "initializeServices", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SplashViewModel extends com.porakhela.core.components.BaseViewModel {
    
    @javax.inject.Inject()
    public SplashViewModel() {
        super();
    }
    
    public final void initialize() {
    }
    
    private final boolean checkFirstLaunch() {
        return false;
    }
    
    private final java.lang.Object initializeServices(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}