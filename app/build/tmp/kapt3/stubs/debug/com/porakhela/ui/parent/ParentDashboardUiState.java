package com.porakhela.ui.parent;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\nH\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\nH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0010H\u00c6\u0003Jk\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001J\u0013\u0010)\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\nH\u00d6\u0001J\t\u0010,\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018\u00a8\u0006-"}, d2 = {"Lcom/porakhela/ui/parent/ParentDashboardUiState;", "", "isLoading", "", "childProgress", "Lcom/porakhela/data/repository/ChildProgress;", "dailyReports", "", "Lcom/porakhela/data/local/DailyLearningReport;", "dailyLimit", "", "contentLockEnabled", "approvalRequired", "todayScreenTime", "showError", "errorMessage", "", "(ZLcom/porakhela/data/repository/ChildProgress;Ljava/util/List;IZZIZLjava/lang/String;)V", "getApprovalRequired", "()Z", "getChildProgress", "()Lcom/porakhela/data/repository/ChildProgress;", "getContentLockEnabled", "getDailyLimit", "()I", "getDailyReports", "()Ljava/util/List;", "getErrorMessage", "()Ljava/lang/String;", "getShowError", "getTodayScreenTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class ParentDashboardUiState {
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final com.porakhela.data.repository.ChildProgress childProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.porakhela.data.local.DailyLearningReport> dailyReports = null;
    private final int dailyLimit = 0;
    private final boolean contentLockEnabled = false;
    private final boolean approvalRequired = false;
    private final int todayScreenTime = 0;
    private final boolean showError = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String errorMessage = null;
    
    public ParentDashboardUiState(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.porakhela.data.repository.ChildProgress childProgress, @org.jetbrains.annotations.NotNull()
    java.util.List<com.porakhela.data.local.DailyLearningReport> dailyReports, int dailyLimit, boolean contentLockEnabled, boolean approvalRequired, int todayScreenTime, boolean showError, @org.jetbrains.annotations.NotNull()
    java.lang.String errorMessage) {
        super();
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.porakhela.data.repository.ChildProgress getChildProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.porakhela.data.local.DailyLearningReport> getDailyReports() {
        return null;
    }
    
    public final int getDailyLimit() {
        return 0;
    }
    
    public final boolean getContentLockEnabled() {
        return false;
    }
    
    public final boolean getApprovalRequired() {
        return false;
    }
    
    public final int getTodayScreenTime() {
        return 0;
    }
    
    public final boolean getShowError() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public ParentDashboardUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.porakhela.data.repository.ChildProgress component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.porakhela.data.local.DailyLearningReport> component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.ui.parent.ParentDashboardUiState copy(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.porakhela.data.repository.ChildProgress childProgress, @org.jetbrains.annotations.NotNull()
    java.util.List<com.porakhela.data.local.DailyLearningReport> dailyReports, int dailyLimit, boolean contentLockEnabled, boolean approvalRequired, int todayScreenTime, boolean showError, @org.jetbrains.annotations.NotNull()
    java.lang.String errorMessage) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}