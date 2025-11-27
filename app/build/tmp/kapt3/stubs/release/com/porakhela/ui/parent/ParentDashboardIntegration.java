package com.porakhela.ui.parent;

/**
 * Helper class for integrating lesson completion logging with Parent Dashboard
 * Use this in your lesson completion flows to automatically log progress
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J:\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/porakhela/ui/parent/ParentDashboardIntegration;", "", "repository", "Lcom/porakhela/data/repository/ParentDashboardRepository;", "(Lcom/porakhela/data/repository/ParentDashboardRepository;)V", "logLessonCompletion", "", "lessonId", "", "pointsEarned", "", "durationMinutes", "subjectName", "categoryName", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testLessonLogging", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class ParentDashboardIntegration {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.ParentDashboardRepository repository = null;
    
    @javax.inject.Inject()
    public ParentDashboardIntegration(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.ParentDashboardRepository repository) {
        super();
    }
    
    /**
     * Call this method whenever a lesson is completed
     * This will automatically log the lesson for parent dashboard tracking
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logLessonCompletion(@org.jetbrains.annotations.NotNull()
    java.lang.String lessonId, int pointsEarned, int durationMinutes, @org.jetbrains.annotations.NotNull()
    java.lang.String subjectName, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Quick test method to verify lesson logging is working
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object testLessonLogging(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}