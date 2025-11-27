package com.porakhela.ui.lesson;

/**
 * Fragment displaying lesson completion with trophy animation and statistics
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0002J\b\u0010!\u001a\u00020\u001eH\u0002J\b\u0010\"\u001a\u00020\u001eH\u0002J\"\u0010#\u001a\u0014\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u001eH\u0002J&\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u00132\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u001a\u00102\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00103\u001a\u00020\u001eH\u0002J\b\u00104\u001a\u00020\u001eH\u0002J\b\u00105\u001a\u00020\u001eH\u0002J\b\u00106\u001a\u00020\u001eH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/porakhela/ui/lesson/LessonCompletionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "args", "Lcom/porakhela/ui/lesson/LessonCompletionFragmentArgs;", "getArgs", "()Lcom/porakhela/ui/lesson/LessonCompletionFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "btnBackToCategories", "Landroid/widget/Button;", "btnContinueLearning", "porapointsManager", "Lcom/porakhela/utils/PorapointsManager;", "getPorapointsManager", "()Lcom/porakhela/utils/PorapointsManager;", "setPorapointsManager", "(Lcom/porakhela/utils/PorapointsManager;)V", "trophyContainer", "Landroid/view/ViewGroup;", "tvAccuracy", "Landroid/widget/TextView;", "tvCongratulations", "tvCorrectCount", "tvMotivationalMessage", "tvPorapointsEarned", "tvTimeTaken", "tvTotalQuestions", "tvTrophy", "animateButtons", "", "animateStatsCards", "animateTextEntrance", "animateTrophyEntrance", "displayCompletionData", "getTrophyAndTitle", "Lkotlin/Triple;", "", "accuracy", "", "initializeViews", "view", "Landroid/view/View;", "navigateBack", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "playAgain", "playCompletionAnimation", "playConfettiAnimation", "setupClickListeners", "app_release"})
public final class LessonCompletionFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @javax.inject.Inject()
    public com.porakhela.utils.PorapointsManager porapointsManager;
    private android.view.ViewGroup trophyContainer;
    private android.widget.TextView tvTrophy;
    private android.widget.TextView tvCongratulations;
    private android.widget.TextView tvMotivationalMessage;
    private android.widget.TextView tvPorapointsEarned;
    private android.widget.TextView tvCorrectCount;
    private android.widget.TextView tvTotalQuestions;
    private android.widget.TextView tvAccuracy;
    private android.widget.TextView tvTimeTaken;
    private android.widget.Button btnContinueLearning;
    private android.widget.Button btnBackToCategories;
    
    public LessonCompletionFragment() {
        super();
    }
    
    private final com.porakhela.ui.lesson.LessonCompletionFragmentArgs getArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.utils.PorapointsManager getPorapointsManager() {
        return null;
    }
    
    public final void setPorapointsManager(@org.jetbrains.annotations.NotNull()
    com.porakhela.utils.PorapointsManager p0) {
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
    
    private final void setupClickListeners() {
    }
    
    private final void displayCompletionData() {
    }
    
    private final kotlin.Triple<java.lang.String, java.lang.String, java.lang.String> getTrophyAndTitle(int accuracy) {
        return null;
    }
    
    private final void playCompletionAnimation() {
    }
    
    private final void animateTrophyEntrance() {
    }
    
    private final void animateTextEntrance() {
    }
    
    private final void animateStatsCards() {
    }
    
    private final void animateButtons() {
    }
    
    private final void playConfettiAnimation() {
    }
    
    private final void navigateBack() {
    }
    
    private final void playAgain() {
    }
}