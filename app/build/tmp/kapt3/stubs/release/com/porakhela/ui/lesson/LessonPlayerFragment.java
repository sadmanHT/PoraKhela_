package com.porakhela.ui.lesson;

/**
 * Fragment for the gamified lesson player experience
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010>\u001a\u00020;2\u0006\u0010?\u001a\u00020\u0013H\u0002J\b\u0010@\u001a\u00020;H\u0002J\b\u0010A\u001a\u00020;H\u0002J\u0018\u0010B\u001a\u00020;2\u0006\u0010C\u001a\u00020 2\u0006\u0010D\u001a\u00020 H\u0002J\u0010\u0010E\u001a\u00020;2\u0006\u0010F\u001a\u00020GH\u0002J\u0018\u0010H\u001a\u00020;2\u0006\u0010C\u001a\u00020 2\u0006\u0010D\u001a\u00020 H\u0002J\u0010\u0010I\u001a\u00020;2\u0006\u0010J\u001a\u00020\u0011H\u0002J\u0010\u0010K\u001a\u00020;2\u0006\u0010F\u001a\u00020LH\u0002J&\u0010M\u001a\u0004\u0018\u00010\u00112\u0006\u0010N\u001a\u00020O2\b\u0010P\u001a\u0004\u0018\u00010Q2\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\b\u0010T\u001a\u00020;H\u0016J\u001a\u0010U\u001a\u00020;2\u0006\u0010J\u001a\u00020\u00112\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\b\u0010V\u001a\u00020;H\u0002J\u0010\u0010W\u001a\u00020;2\u0006\u0010J\u001a\u00020\u0011H\u0002J\b\u0010X\u001a\u00020;H\u0002J\u0010\u0010Y\u001a\u00020;2\u0006\u0010Z\u001a\u00020 H\u0002J\b\u0010[\u001a\u00020;H\u0002J\b\u0010\\\u001a\u00020;H\u0002J\u0010\u0010]\u001a\u00020;2\u0006\u0010^\u001a\u00020_H\u0002J\u0010\u0010`\u001a\u00020;2\u0006\u0010a\u001a\u00020\u0013H\u0002J\u0018\u0010b\u001a\u00020;2\u0006\u0010c\u001a\u00020 2\u0006\u0010d\u001a\u00020 H\u0002J\u0010\u0010e\u001a\u00020;2\u0006\u0010f\u001a\u00020 H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\'\u001a\u00020(8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020/X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020/X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020/X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u00105\u001a\u0002068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b9\u0010\u001c\u001a\u0004\b7\u00108\u00a8\u0006g"}, d2 = {"Lcom/porakhela/ui/lesson/LessonPlayerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "args", "Lcom/porakhela/ui/lesson/LessonPlayerFragmentArgs;", "getArgs", "()Lcom/porakhela/ui/lesson/LessonPlayerFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "btnNext", "Landroid/widget/Button;", "btnOptionA", "btnOptionB", "btnOptionC", "btnOptionD", "btnSkip", "confettiContainer", "Landroid/view/View;", "isAnswerSelected", "", "lastButtonPressTime", "", "loadingOverlay", "optionButtons", "", "getOptionButtons", "()Ljava/util/List;", "optionButtons$delegate", "Lkotlin/Lazy;", "progressBar", "Landroid/widget/ProgressBar;", "selectedOptionIndex", "", "streakManager", "Lcom/porakhela/data/tracking/StreakManager;", "getStreakManager", "()Lcom/porakhela/data/tracking/StreakManager;", "setStreakManager", "(Lcom/porakhela/data/tracking/StreakManager;)V", "timeTracker", "Lcom/porakhela/data/tracking/TimeTracker;", "getTimeTracker", "()Lcom/porakhela/data/tracking/TimeTracker;", "setTimeTracker", "(Lcom/porakhela/data/tracking/TimeTracker;)V", "timerProgress", "tvProgress", "Landroid/widget/TextView;", "tvQuestion", "tvQuestionBadge", "tvTimer", "vibrator", "Landroid/os/Vibrator;", "viewModel", "Lcom/porakhela/ui/lesson/LessonPlayerViewModel;", "getViewModel", "()Lcom/porakhela/ui/lesson/LessonPlayerViewModel;", "viewModel$delegate", "displayQuestion", "", "question", "Lcom/porakhela/data/models/Question;", "enableOptionButtons", "enabled", "handleBackNavigation", "handleBackPress", "handleCorrectAnswer", "selectedOption", "correctOption", "handleUIState", "state", "Lcom/porakhela/ui/lesson/LessonPlayerState;", "handleWrongAnswer", "initializeViews", "view", "navigateToCompletion", "Lcom/porakhela/ui/lesson/LessonPlayerState$Completed;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "playConfettiAnimation", "playShakeAnimation", "resetQuestionState", "selectOption", "optionIndex", "setupClickListeners", "setupObservers", "showError", "message", "", "showLoading", "show", "updateProgressDisplay", "questionNumber", "totalQuestions", "updateTimerDisplay", "progress", "app_release"})
public final class LessonPlayerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @javax.inject.Inject()
    public com.porakhela.data.tracking.TimeTracker timeTracker;
    @javax.inject.Inject()
    public com.porakhela.data.tracking.StreakManager streakManager;
    private android.widget.ProgressBar progressBar;
    private android.widget.ProgressBar timerProgress;
    private android.widget.TextView tvProgress;
    private android.widget.TextView tvTimer;
    private android.widget.TextView tvQuestionBadge;
    private android.widget.TextView tvQuestion;
    private android.widget.Button btnOptionA;
    private android.widget.Button btnOptionB;
    private android.widget.Button btnOptionC;
    private android.widget.Button btnOptionD;
    private android.widget.Button btnSkip;
    private android.widget.Button btnNext;
    private android.view.View loadingOverlay;
    private android.view.View confettiContainer;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy optionButtons$delegate = null;
    private boolean isAnswerSelected = false;
    private int selectedOptionIndex = -1;
    private android.os.Vibrator vibrator;
    private long lastButtonPressTime = 0L;
    
    public LessonPlayerFragment() {
        super();
    }
    
    private final com.porakhela.ui.lesson.LessonPlayerViewModel getViewModel() {
        return null;
    }
    
    private final com.porakhela.ui.lesson.LessonPlayerFragmentArgs getArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.TimeTracker getTimeTracker() {
        return null;
    }
    
    public final void setTimeTracker(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.tracking.TimeTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.tracking.StreakManager getStreakManager() {
        return null;
    }
    
    public final void setStreakManager(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.tracking.StreakManager p0) {
    }
    
    private final java.util.List<android.widget.Button> getOptionButtons() {
        return null;
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
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void initializeViews(android.view.View view) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupObservers() {
    }
    
    private final void handleUIState(com.porakhela.ui.lesson.LessonPlayerState state) {
    }
    
    private final void displayQuestion(com.porakhela.data.models.Question question) {
    }
    
    private final void selectOption(int optionIndex) {
    }
    
    private final void handleCorrectAnswer(int selectedOption, int correctOption) {
    }
    
    private final void handleWrongAnswer(int selectedOption, int correctOption) {
    }
    
    private final void playConfettiAnimation() {
    }
    
    private final void playShakeAnimation(android.view.View view) {
    }
    
    private final void updateProgressDisplay(int questionNumber, int totalQuestions) {
    }
    
    private final void updateTimerDisplay(int progress) {
    }
    
    private final void enableOptionButtons(boolean enabled) {
    }
    
    private final void resetQuestionState() {
    }
    
    private final void showLoading(boolean show) {
    }
    
    private final void showError(java.lang.String message) {
    }
    
    private final void navigateToCompletion(com.porakhela.ui.lesson.LessonPlayerState.Completed state) {
    }
    
    private final void handleBackNavigation() {
    }
    
    private final void handleBackPress() {
    }
}