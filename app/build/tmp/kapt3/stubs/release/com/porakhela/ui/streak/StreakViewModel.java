package com.porakhela.ui.streak;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\u0010R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/porakhela/ui/streak/StreakViewModel;", "Landroidx/lifecycle/ViewModel;", "streakManager", "Lcom/porakhela/data/tracking/StreakManager;", "(Lcom/porakhela/data/tracking/StreakManager;)V", "_streakInfo", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/porakhela/data/tracking/StreakInfo;", "streakInfo", "Lkotlinx/coroutines/flow/StateFlow;", "getStreakInfo", "()Lkotlinx/coroutines/flow/StateFlow;", "getFormattedTimeToday", "", "getStreakMessage", "refreshStreakInfo", "", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StreakViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.tracking.StreakManager streakManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.data.tracking.StreakInfo> _streakInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.data.tracking.StreakInfo> streakInfo = null;
    
    @javax.inject.Inject()
    public StreakViewModel(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.tracking.StreakManager streakManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.data.tracking.StreakInfo> getStreakInfo() {
        return null;
    }
    
    public final void refreshStreakInfo() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedTimeToday() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStreakMessage() {
        return null;
    }
}