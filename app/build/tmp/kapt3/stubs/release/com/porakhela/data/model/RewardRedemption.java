package com.porakhela.data.model;

/**
 * Data class representing a reward redemption transaction
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\'\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u0012J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0007H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\nH\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010/\u001a\u00020\u000fH\u00c6\u0003J\u0084\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u00101J\u0013\u00102\u001a\u00020\u000f2\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0005H\u00d6\u0001J\t\u00105\u001a\u00020\u0007H\u00d6\u0001R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u00066"}, d2 = {"Lcom/porakhela/data/model/RewardRedemption;", "", "id", "", "rewardId", "", "rewardName", "", "cost", "status", "Lcom/porakhela/data/model/RedemptionStatus;", "requestedAt", "approvedAt", "redeemedAt", "parentApprovalRequired", "", "apiTransactionId", "errorMessage", "(JILjava/lang/String;ILcom/porakhela/data/model/RedemptionStatus;JLjava/lang/Long;Ljava/lang/Long;ZLjava/lang/String;Ljava/lang/String;)V", "getApiTransactionId", "()Ljava/lang/String;", "getApprovedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCost", "()I", "getErrorMessage", "getId", "()J", "getParentApprovalRequired", "()Z", "getRedeemedAt", "getRequestedAt", "getRewardId", "getRewardName", "getStatus", "()Lcom/porakhela/data/model/RedemptionStatus;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JILjava/lang/String;ILcom/porakhela/data/model/RedemptionStatus;JLjava/lang/Long;Ljava/lang/Long;ZLjava/lang/String;Ljava/lang/String;)Lcom/porakhela/data/model/RewardRedemption;", "equals", "other", "hashCode", "toString", "app_release"})
@androidx.room.Entity(tableName = "redemptions", foreignKeys = {@androidx.room.ForeignKey(entity = com.porakhela.data.model.Reward.class, parentColumns = {"id"}, childColumns = {"rewardId"}, onDelete = 5)})
public final class RewardRedemption {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final int rewardId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String rewardName = null;
    private final int cost = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.model.RedemptionStatus status = null;
    private final long requestedAt = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long approvedAt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long redeemedAt = null;
    private final boolean parentApprovalRequired = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String apiTransactionId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    
    public RewardRedemption(long id, int rewardId, @org.jetbrains.annotations.NotNull()
    java.lang.String rewardName, int cost, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RedemptionStatus status, long requestedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long approvedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long redeemedAt, boolean parentApprovalRequired, @org.jetbrains.annotations.Nullable()
    java.lang.String apiTransactionId, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final int getRewardId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRewardName() {
        return null;
    }
    
    public final int getCost() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.model.RedemptionStatus getStatus() {
        return null;
    }
    
    public final long getRequestedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getApprovedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getRedeemedAt() {
        return null;
    }
    
    public final boolean getParentApprovalRequired() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getApiTransactionId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.model.RedemptionStatus component5() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.porakhela.data.model.RewardRedemption copy(long id, int rewardId, @org.jetbrains.annotations.NotNull()
    java.lang.String rewardName, int cost, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RedemptionStatus status, long requestedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long approvedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long redeemedAt, boolean parentApprovalRequired, @org.jetbrains.annotations.Nullable()
    java.lang.String apiTransactionId, @org.jetbrains.annotations.Nullable()
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