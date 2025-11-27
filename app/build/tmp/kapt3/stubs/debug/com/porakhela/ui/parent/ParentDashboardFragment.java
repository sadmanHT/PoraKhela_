package com.porakhela.ui.parent;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0019H\u0002J\u0018\u0010#\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010&\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020(H\u0002J\u0018\u0010)\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020(H\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006+"}, d2 = {"Lcom/porakhela/ui/parent/ParentDashboardFragment;", "Landroidx/fragment/app/Fragment;", "()V", "reportsAdapter", "Lcom/porakhela/ui/parent/DailyReportsAdapter;", "streakManager", "Lcom/porakhela/data/tracking/StreakManager;", "getStreakManager", "()Lcom/porakhela/data/tracking/StreakManager;", "setStreakManager", "(Lcom/porakhela/data/tracking/StreakManager;)V", "streakViewModel", "Lcom/porakhela/ui/streak/StreakViewModel;", "getStreakViewModel", "()Lcom/porakhela/ui/streak/StreakViewModel;", "streakViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/porakhela/ui/parent/ParentDashboardViewModel;", "getViewModel", "()Lcom/porakhela/ui/parent/ParentDashboardViewModel;", "viewModel$delegate", "observeViewModel", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setupViews", "updateChildProgress", "progress", "Lcom/porakhela/data/repository/ChildProgress;", "updateLearningReports", "state", "Lcom/porakhela/ui/parent/ParentDashboardUiState;", "updateScreenTimeControls", "updateUI", "app_debug"})
public final class ParentDashboardFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy streakViewModel$delegate = null;
    private com.porakhela.ui.parent.DailyReportsAdapter reportsAdapter;
    @javax.inject.Inject()
    public com.porakhela.data.tracking.StreakManager streakManager;
    
    public ParentDashboardFragment() {
        super();
    }
    
    private final com.porakhela.ui.parent.ParentDashboardViewModel getViewModel() {
        return null;
    }
    
    private final com.porakhela.ui.streak.StreakViewModel getStreakViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.StreakManager getStreakManager() {
        return null;
    }
    
    public final void setStreakManager(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.tracking.StreakManager p0) {
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
    
    private final void observeViewModel() {
    }
    
    private final void updateUI(com.porakhela.ui.parent.ParentDashboardUiState state) {
    }
    
    private final void updateChildProgress(android.view.View view, com.porakhela.data.repository.ChildProgress progress) {
    }
    
    private final void updateScreenTimeControls(android.view.View view, com.porakhela.ui.parent.ParentDashboardUiState state) {
    }
    
    private final void updateLearningReports(android.view.View view, com.porakhela.ui.parent.ParentDashboardUiState state) {
    }
}