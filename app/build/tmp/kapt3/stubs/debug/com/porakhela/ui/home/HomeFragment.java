package com.porakhela.ui.home;

/**
 * Home fragment displaying dashboard with subject cards and user stats
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\n2\f\u00102\u001a\b\u0012\u0004\u0012\u00020003H\u0002J\u0010\u00104\u001a\u0002002\u0006\u00105\u001a\u00020\nH\u0002J\b\u00106\u001a\u000200H\u0002J\u0010\u00107\u001a\u0002002\u0006\u00108\u001a\u000209H\u0002J&\u0010:\u001a\u0004\u0018\u00010\n2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\b\u0010A\u001a\u000200H\u0016J\u001a\u0010B\u001a\u0002002\u0006\u00105\u001a\u00020\n2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\b\u0010C\u001a\u000200H\u0002J\b\u0010D\u001a\u000200H\u0002J\b\u0010E\u001a\u000200H\u0002J\b\u0010F\u001a\u000200H\u0002J\b\u0010G\u001a\u000200H\u0002J\u0010\u0010H\u001a\u0002002\u0006\u0010I\u001a\u00020JH\u0002J\u001a\u0010K\u001a\u000200*\u00020L2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020003H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010 \u001a\u00020!X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020!X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u00020%8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b.\u0010\u001f\u001a\u0004\b,\u0010-\u00a8\u0006N"}, d2 = {"Lcom/porakhela/ui/home/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "applinkRepository", "Lcom/porakhela/data/repository/ApplinkRepository;", "getApplinkRepository", "()Lcom/porakhela/data/repository/ApplinkRepository;", "setApplinkRepository", "(Lcom/porakhela/data/repository/ApplinkRepository;)V", "cardEnglish", "Landroid/view/View;", "cardMath", "cardScience", "cardSocialStudies", "porapointsManager", "Lcom/porakhela/utils/PorapointsManager;", "getPorapointsManager", "()Lcom/porakhela/utils/PorapointsManager;", "setPorapointsManager", "(Lcom/porakhela/utils/PorapointsManager;)V", "streakManager", "Lcom/porakhela/data/tracking/StreakManager;", "getStreakManager", "()Lcom/porakhela/data/tracking/StreakManager;", "setStreakManager", "(Lcom/porakhela/data/tracking/StreakManager;)V", "streakViewModel", "Lcom/porakhela/ui/streak/StreakViewModel;", "getStreakViewModel", "()Lcom/porakhela/ui/streak/StreakViewModel;", "streakViewModel$delegate", "Lkotlin/Lazy;", "tvDailyStreak", "Landroid/widget/TextView;", "tvPorapointsValue", "tvWelcome", "userPreferences", "Lcom/porakhela/data/local/UserPreferences;", "getUserPreferences", "()Lcom/porakhela/data/local/UserPreferences;", "setUserPreferences", "(Lcom/porakhela/data/local/UserPreferences;)V", "viewModel", "Lcom/porakhela/ui/home/HomeViewModel;", "getViewModel", "()Lcom/porakhela/ui/home/HomeViewModel;", "viewModel$delegate", "animateCardPress", "", "card", "onComplete", "Lkotlin/Function0;", "initializeViews", "view", "loadUserData", "navigateToSubjectCategory", "subject", "", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "openDeveloperTools", "setupDeveloperTools", "setupObservers", "setupStreakDisplay", "setupSubjectCards", "updateStreakUI", "streakInfo", "Lcom/porakhela/data/tracking/StreakInfo;", "doOnEnd", "Landroid/animation/AnimatorSet;", "action", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy streakViewModel$delegate = null;
    @javax.inject.Inject()
    public com.porakhela.data.local.UserPreferences userPreferences;
    @javax.inject.Inject()
    public com.porakhela.data.repository.ApplinkRepository applinkRepository;
    @javax.inject.Inject()
    public com.porakhela.utils.PorapointsManager porapointsManager;
    @javax.inject.Inject()
    public com.porakhela.data.tracking.StreakManager streakManager;
    private android.widget.TextView tvWelcome;
    private android.widget.TextView tvPorapointsValue;
    private android.widget.TextView tvDailyStreak;
    private android.view.View cardMath;
    private android.view.View cardEnglish;
    private android.view.View cardScience;
    private android.view.View cardSocialStudies;
    
    public HomeFragment() {
        super();
    }
    
    private final com.porakhela.ui.home.HomeViewModel getViewModel() {
        return null;
    }
    
    private final com.porakhela.ui.streak.StreakViewModel getStreakViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.UserPreferences getUserPreferences() {
        return null;
    }
    
    public final void setUserPreferences(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.UserPreferences p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.repository.ApplinkRepository getApplinkRepository() {
        return null;
    }
    
    public final void setApplinkRepository(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.ApplinkRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.utils.PorapointsManager getPorapointsManager() {
        return null;
    }
    
    public final void setPorapointsManager(@org.jetbrains.annotations.NotNull()
    com.porakhela.utils.PorapointsManager p0) {
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
    
    private final void initializeViews(android.view.View view) {
    }
    
    private final void setupSubjectCards() {
    }
    
    private final void animateCardPress(android.view.View card, kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    private final void doOnEnd(android.animation.AnimatorSet $this$doOnEnd, kotlin.jvm.functions.Function0<kotlin.Unit> action) {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupStreakDisplay() {
    }
    
    private final void updateStreakUI(com.porakhela.data.tracking.StreakInfo streakInfo) {
    }
    
    private final void loadUserData() {
    }
    
    private final void setupDeveloperTools() {
    }
    
    private final void openDeveloperTools() {
    }
    
    private final void navigateToSubjectCategory(java.lang.String subject) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
}