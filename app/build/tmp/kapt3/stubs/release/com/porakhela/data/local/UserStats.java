package com.porakhela.data.local;

/**
 * Data class holding comprehensive user statistics
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003JU\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u0010!\u001a\u00020\u0007J\u0006\u0010\"\u001a\u00020\u0003J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001J\t\u0010$\u001a\u00020\u0007H\u00d6\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006%"}, d2 = {"Lcom/porakhela/data/local/UserStats;", "", "porapoints", "", "dailyStreak", "totalLessonsCompleted", "favoriteSubject", "", "childName", "lastActivityDate", "wasActiveToday", "", "(IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getChildName", "()Ljava/lang/String;", "getDailyStreak", "()I", "getFavoriteSubject", "getLastActivityDate", "getPorapoints", "getTotalLessonsCompleted", "getWasActiveToday", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "getMotivationalMessage", "getUserLevel", "hashCode", "toString", "app_release"})
public final class UserStats {
    private final int porapoints = 0;
    private final int dailyStreak = 0;
    private final int totalLessonsCompleted = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String favoriteSubject = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String childName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastActivityDate = null;
    private final boolean wasActiveToday = false;
    
    public UserStats(int porapoints, int dailyStreak, int totalLessonsCompleted, @org.jetbrains.annotations.Nullable()
    java.lang.String favoriteSubject, @org.jetbrains.annotations.Nullable()
    java.lang.String childName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastActivityDate, boolean wasActiveToday) {
        super();
    }
    
    public final int getPorapoints() {
        return 0;
    }
    
    public final int getDailyStreak() {
        return 0;
    }
    
    public final int getTotalLessonsCompleted() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFavoriteSubject() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getChildName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastActivityDate() {
        return null;
    }
    
    public final boolean getWasActiveToday() {
        return false;
    }
    
    /**
     * Get a motivational message based on stats
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMotivationalMessage() {
        return null;
    }
    
    /**
     * Get user level based on total points
     */
    public final int getUserLevel() {
        return 0;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.local.UserStats copy(int porapoints, int dailyStreak, int totalLessonsCompleted, @org.jetbrains.annotations.Nullable()
    java.lang.String favoriteSubject, @org.jetbrains.annotations.Nullable()
    java.lang.String childName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastActivityDate, boolean wasActiveToday) {
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