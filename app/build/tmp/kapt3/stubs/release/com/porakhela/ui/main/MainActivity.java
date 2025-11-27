package com.porakhela.ui.main;

/**
 * Main activity that hosts the app content and streak testing interface
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020.H\u0002J\u0012\u0010/\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u000101H\u0014J\b\u00102\u001a\u00020.H\u0014J\b\u00103\u001a\u00020.H\u0002J\b\u00104\u001a\u00020.H\u0002J\b\u00105\u001a\u00020.H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00198\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020%X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\'\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b)\u0010*\u00a8\u00066"}, d2 = {"Lcom/porakhela/ui/main/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnCompleteLesson", "Landroid/widget/Button;", "btnPauseTimer", "btnResumeTimer", "btnRewardsCenter", "btnSet2000Points", "btnSet300Points", "btnSet600Points", "btnStartTimer", "notificationScheduler", "Lcom/porakhela/core/notifications/StreakNotificationScheduler;", "getNotificationScheduler", "()Lcom/porakhela/core/notifications/StreakNotificationScheduler;", "setNotificationScheduler", "(Lcom/porakhela/core/notifications/StreakNotificationScheduler;)V", "streakManager", "Lcom/porakhela/data/tracking/StreakManager;", "getStreakManager", "()Lcom/porakhela/data/tracking/StreakManager;", "setStreakManager", "(Lcom/porakhela/data/tracking/StreakManager;)V", "streakPreferences", "Lcom/porakhela/data/local/StreakPreferences;", "getStreakPreferences", "()Lcom/porakhela/data/local/StreakPreferences;", "setStreakPreferences", "(Lcom/porakhela/data/local/StreakPreferences;)V", "timeTracker", "Lcom/porakhela/data/tracking/TimeTracker;", "getTimeTracker", "()Lcom/porakhela/data/tracking/TimeTracker;", "setTimeTracker", "(Lcom/porakhela/data/tracking/TimeTracker;)V", "tvStreakInfo", "Landroid/widget/TextView;", "tvTimeInfo", "viewModel", "Lcom/porakhela/ui/main/MainViewModel;", "getViewModel", "()Lcom/porakhela/ui/main/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initializeViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupClickListeners", "setupObservers", "updateUI", "app_release"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public com.porakhela.core.notifications.StreakNotificationScheduler notificationScheduler;
    @javax.inject.Inject()
    public com.porakhela.data.tracking.StreakManager streakManager;
    @javax.inject.Inject()
    public com.porakhela.data.tracking.TimeTracker timeTracker;
    @javax.inject.Inject()
    public com.porakhela.data.local.StreakPreferences streakPreferences;
    private android.widget.TextView tvStreakInfo;
    private android.widget.TextView tvTimeInfo;
    private android.widget.Button btnCompleteLesson;
    private android.widget.Button btnStartTimer;
    private android.widget.Button btnPauseTimer;
    private android.widget.Button btnResumeTimer;
    private android.widget.Button btnRewardsCenter;
    private android.widget.Button btnSet300Points;
    private android.widget.Button btnSet600Points;
    private android.widget.Button btnSet2000Points;
    
    public MainActivity() {
        super();
    }
    
    private final com.porakhela.ui.main.MainViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.core.notifications.StreakNotificationScheduler getNotificationScheduler() {
        return null;
    }
    
    public final void setNotificationScheduler(@org.jetbrains.annotations.NotNull()
    com.porakhela.core.notifications.StreakNotificationScheduler p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.StreakManager getStreakManager() {
        return null;
    }
    
    public final void setStreakManager(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.tracking.StreakManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.TimeTracker getTimeTracker() {
        return null;
    }
    
    public final void setTimeTracker(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.tracking.TimeTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.StreakPreferences getStreakPreferences() {
        return null;
    }
    
    public final void setStreakPreferences(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.StreakPreferences p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViews() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void updateUI() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void setupObservers() {
    }
}