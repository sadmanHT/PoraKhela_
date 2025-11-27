package com.porakhela.ui.lesson;

/**
 * ViewModel for Lesson Player with anti-cheat protection and scoring
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000eJ\u0006\u0010.\u001a\u00020,J\b\u0010/\u001a\u00020,H\u0002J\b\u00100\u001a\u00020,H\u0002J\u000e\u00101\u001a\u00020,2\u0006\u00102\u001a\u000203J\u0010\u00104\u001a\u00020,2\u0006\u00105\u001a\u000203H\u0002J\u0006\u00106\u001a\u00020,J\u0006\u00107\u001a\u00020,J\u0006\u00108\u001a\u00020\u001bJ\b\u00109\u001a\u00020,H\u0014J\u0006\u0010:\u001a\u00020,J\u0010\u0010;\u001a\u00020,2\u0006\u0010<\u001a\u00020\u000eH\u0002J\b\u0010=\u001a\u00020,H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u000e\u0010\"\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0018R\u000e\u0010(\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006>"}, d2 = {"Lcom/porakhela/ui/lesson/LessonPlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "lessonRepository", "Lcom/porakhela/data/repository/LessonRepository;", "userPreferences", "Lcom/porakhela/data/local/UserPreferences;", "(Lcom/porakhela/data/repository/LessonRepository;Lcom/porakhela/data/local/UserPreferences;)V", "MIN_QUESTION_TIME_MS", "", "QUESTION_TIME_LIMIT_MS", "_currentQuestion", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/porakhela/data/models/Question;", "_questionProgress", "", "_timerProgress", "_uiState", "Lcom/porakhela/ui/lesson/LessonPlayerState;", "correctAnswers", "currentLesson", "Lcom/porakhela/data/models/Lesson;", "currentQuestion", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentQuestion", "()Lkotlinx/coroutines/flow/StateFlow;", "currentQuestionIndex", "isUIReady", "", "lessonStartTime", "questionAnswers", "", "Lcom/porakhela/data/models/QuestionAnswer;", "questionProgress", "getQuestionProgress", "questionStartTime", "suspiciousActivityCount", "timerJob", "Lkotlinx/coroutines/Job;", "timerProgress", "getTimerProgress", "totalQuestions", "uiState", "getUiState", "answerQuestion", "", "selectedOption", "cancelActiveOperations", "completeLesson", "handleTimeUp", "loadLesson", "lessonId", "", "logSuspiciousActivity", "activity", "markUIReady", "nextQuestion", "onBackPressed", "onCleared", "skipQuestion", "startQuestion", "questionIndex", "startQuestionTimer", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LessonPlayerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.LessonRepository lessonRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.UserPreferences userPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.ui.lesson.LessonPlayerState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.lesson.LessonPlayerState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.data.models.Question> _currentQuestion = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.data.models.Question> currentQuestion = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _questionProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> questionProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _timerProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> timerProgress = null;
    private boolean isUIReady = false;
    private long questionStartTime = 0L;
    private int suspiciousActivityCount = 0;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private int totalQuestions = 0;
    private long lessonStartTime = 0L;
    @org.jetbrains.annotations.Nullable()
    private com.porakhela.data.models.Lesson currentLesson;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.porakhela.data.models.QuestionAnswer> questionAnswers;
    private final long MIN_QUESTION_TIME_MS = 1000L;
    private final long QUESTION_TIME_LIMIT_MS = 30000L;
    
    @javax.inject.Inject()
    public LessonPlayerViewModel(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.LessonRepository lessonRepository, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.UserPreferences userPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.lesson.LessonPlayerState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.data.models.Question> getCurrentQuestion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getQuestionProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTimerProgress() {
        return null;
    }
    
    /**
     * Load lesson and start the experience
     */
    public final void loadLesson(@org.jetbrains.annotations.NotNull()
    java.lang.String lessonId) {
    }
    
    /**
     * Mark UI as ready (anti-cheat protection)
     */
    public final void markUIReady() {
    }
    
    /**
     * Answer a question with anti-cheat validation
     */
    public final void answerQuestion(int selectedOption) {
    }
    
    /**
     * Move to next question or complete lesson
     */
    public final void nextQuestion() {
    }
    
    /**
     * Skip current question
     */
    public final void skipQuestion() {
    }
    
    /**
     * Handle back press (anti-cheat protection)
     */
    public final boolean onBackPressed() {
        return false;
    }
    
    /**
     * Start a specific question
     */
    private final void startQuestion(int questionIndex) {
    }
    
    /**
     * Start timer for current question with lifecycle awareness
     */
    private final void startQuestionTimer() {
    }
    
    /**
     * Handle timer expiration
     */
    private final void handleTimeUp() {
    }
    
    /**
     * Complete the lesson and calculate score with thread safety
     */
    private final void completeLesson() {
    }
    
    /**
     * Log suspicious activity for anti-cheat monitoring
     */
    private final void logSuspiciousActivity(java.lang.String activity) {
    }
    
    /**
     * Cancel active operations for cleanup
     */
    public final void cancelActiveOperations() {
    }
    
    /**
     * Clean up resources
     */
    @java.lang.Override()
    protected void onCleared() {
    }
}