package com.porakhela.data.repository;

/**
 * Repository for Parent Dashboard operations
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0006\u0010\u001d\u001a\u00020\u0018J\u000e\u0010\u001e\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0006\u0010\u001f\u001a\u00020\u0010J\u0006\u0010 \u001a\u00020\u0010J\u0006\u0010!\u001a\u00020\u0010J\u0006\u0010\"\u001a\u00020\u0010J:\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020\u00122\b\b\u0002\u0010)\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0010J\u000e\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020\u0010J\u000e\u0010/\u001a\u00020$2\u0006\u00100\u001a\u00020\u0018J\u000e\u00101\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/porakhela/data/repository/ParentDashboardRepository;", "", "securePrefs", "Lcom/porakhela/data/local/SecureParentPreferences;", "database", "Lcom/porakhela/data/local/ParentDashboardDatabase;", "porapointsManager", "Lcom/porakhela/utils/PorapointsManager;", "userPreferences", "Lcom/porakhela/data/local/UserPreferences;", "(Lcom/porakhela/data/local/SecureParentPreferences;Lcom/porakhela/data/local/ParentDashboardDatabase;Lcom/porakhela/utils/PorapointsManager;Lcom/porakhela/data/local/UserPreferences;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "lessonLogDao", "Lcom/porakhela/data/local/LessonLogDao;", "authenticatePin", "", "pin", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChildProgress", "Lcom/porakhela/data/repository/ChildProgress;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCooldownRemainingSeconds", "", "getDailyLimit", "getDailyReports", "", "Lcom/porakhela/data/local/DailyLearningReport;", "getFailedAttempts", "getTodayScreenTime", "hasParentPin", "isApprovalRequired", "isContentLockEnabled", "isInCooldown", "logLessonCompletion", "", "lessonId", "pointsEarned", "durationMinutes", "subjectName", "categoryName", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setApprovalRequired", "required", "setContentLock", "enabled", "setDailyLimit", "minutes", "setParentPin", "app_debug"})
public final class ParentDashboardRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.SecureParentPreferences securePrefs = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.ParentDashboardDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.utils.PorapointsManager porapointsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.UserPreferences userPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.LessonLogDao lessonLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    
    @javax.inject.Inject()
    public ParentDashboardRepository(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.SecureParentPreferences securePrefs, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.ParentDashboardDatabase database, @org.jetbrains.annotations.NotNull()
    com.porakhela.utils.PorapointsManager porapointsManager, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.UserPreferences userPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object authenticatePin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    public final void setParentPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin) {
    }
    
    public final boolean hasParentPin() {
        return false;
    }
    
    public final int getFailedAttempts() {
        return 0;
    }
    
    public final boolean isInCooldown() {
        return false;
    }
    
    public final int getCooldownRemainingSeconds() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getChildProgress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.data.repository.ChildProgress> $completion) {
        return null;
    }
    
    public final void setDailyLimit(int minutes) {
    }
    
    public final int getDailyLimit() {
        return 0;
    }
    
    public final void setContentLock(boolean enabled) {
    }
    
    public final boolean isContentLockEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTodayScreenTime(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDailyReports(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.porakhela.data.local.DailyLearningReport>> $completion) {
        return null;
    }
    
    public final void setApprovalRequired(boolean required) {
    }
    
    public final boolean isApprovalRequired() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logLessonCompletion(@org.jetbrains.annotations.NotNull()
    java.lang.String lessonId, int pointsEarned, int durationMinutes, @org.jetbrains.annotations.NotNull()
    java.lang.String subjectName, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}