package com.porakhela.data.models;

/**
 * Data model for leaderboard entries
 * Represents a child's ranking in the leaderboard system
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\tH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u000eH\u00c6\u0003Jw\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010,\u001a\u00020\t2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u0010.\u001a\u00020/J\b\u00100\u001a\u0004\u0018\u000101J\t\u00102\u001a\u00020\u0005H\u00d6\u0001J\u0006\u00103\u001a\u00020\tJ\t\u00104\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016\u00a8\u00065"}, d2 = {"Lcom/porakhela/data/models/LeaderboardEntry;", "", "childName", "", "points", "", "rank", "avatar", "isCurrentUser", "", "weeklyPoints", "monthlyPoints", "globalPoints", "lastUpdated", "Ljava/util/Date;", "previousRank", "rankChange", "(Ljava/lang/String;IILjava/lang/String;ZIIILjava/util/Date;II)V", "getAvatar", "()Ljava/lang/String;", "getChildName", "getGlobalPoints", "()I", "()Z", "getLastUpdated", "()Ljava/util/Date;", "getMonthlyPoints", "getPoints", "getPreviousRank", "getRank", "getRankChange", "getWeeklyPoints", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getRankChangeType", "Lcom/porakhela/data/models/RankChangeType;", "getTrophyType", "Lcom/porakhela/data/models/TrophyType;", "hashCode", "isTopThree", "toString", "app_debug"})
@androidx.room.Entity(tableName = "leaderboard_entries")
public final class LeaderboardEntry {
    @androidx.room.PrimaryKey()
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String childName = null;
    private final int points = 0;
    private final int rank = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String avatar = null;
    private final boolean isCurrentUser = false;
    private final int weeklyPoints = 0;
    private final int monthlyPoints = 0;
    private final int globalPoints = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Date lastUpdated = null;
    private final int previousRank = 0;
    private final int rankChange = 0;
    
    public LeaderboardEntry(@org.jetbrains.annotations.NotNull()
    java.lang.String childName, int points, int rank, @org.jetbrains.annotations.NotNull()
    java.lang.String avatar, boolean isCurrentUser, int weeklyPoints, int monthlyPoints, int globalPoints, @org.jetbrains.annotations.NotNull()
    java.util.Date lastUpdated, int previousRank, int rankChange) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChildName() {
        return null;
    }
    
    public final int getPoints() {
        return 0;
    }
    
    public final int getRank() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAvatar() {
        return null;
    }
    
    public final boolean isCurrentUser() {
        return false;
    }
    
    public final int getWeeklyPoints() {
        return 0;
    }
    
    public final int getMonthlyPoints() {
        return 0;
    }
    
    public final int getGlobalPoints() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Date getLastUpdated() {
        return null;
    }
    
    public final int getPreviousRank() {
        return 0;
    }
    
    public final int getRankChange() {
        return 0;
    }
    
    /**
     * Get rank change type for animations
     */
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.models.RankChangeType getRankChangeType() {
        return null;
    }
    
    /**
     * Check if this entry is in top 3
     */
    public final boolean isTopThree() {
        return false;
    }
    
    /**
     * Get trophy type for top 3 entries
     */
    @org.jetbrains.annotations.Nullable()
    public final com.porakhela.data.models.TrophyType getTrophyType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Date component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.models.LeaderboardEntry copy(@org.jetbrains.annotations.NotNull()
    java.lang.String childName, int points, int rank, @org.jetbrains.annotations.NotNull()
    java.lang.String avatar, boolean isCurrentUser, int weeklyPoints, int monthlyPoints, int globalPoints, @org.jetbrains.annotations.NotNull()
    java.util.Date lastUpdated, int previousRank, int rankChange) {
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