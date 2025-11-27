package com.porakhela.data.local.dao;

/**
 * Data Access Object for reward-related operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u000eH\'J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000eH\'J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\'J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010 \u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010!J\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010%\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010&\u001a\u00020\u00032\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fH\u00a7@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010*\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006+"}, d2 = {"Lcom/porakhela/data/local/dao/RewardDao;", "", "clearAllRewards", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRedemption", "redemption", "Lcom/porakhela/data/model/RewardRedemption;", "(Lcom/porakhela/data/model/RewardRedemption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReward", "reward", "Lcom/porakhela/data/model/Reward;", "(Lcom/porakhela/data/model/Reward;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAvailableRewards", "Lkotlinx/coroutines/flow/Flow;", "", "getAllRedemptions", "getPendingApprovalRedemptions", "getPendingRedemptionsCount", "", "getRedemptionById", "redemptionId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRedemptionsByStatus", "status", "Lcom/porakhela/data/model/RedemptionStatus;", "getRedemptionsInTimeRange", "startTime", "endTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRewardById", "rewardId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRewardsWithRedemptionStatus", "(Lcom/porakhela/data/model/RedemptionStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRedemption", "insertReward", "insertRewards", "rewards", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRedemption", "updateReward", "app_release"})
@androidx.room.Dao()
public abstract interface RewardDao {
    
    @androidx.room.Query(value = "SELECT * FROM rewards WHERE is_available = 1 ORDER BY cost ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.Reward>> getAllAvailableRewards();
    
    @androidx.room.Query(value = "SELECT * FROM rewards WHERE id = :rewardId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRewardById(int rewardId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.data.model.Reward> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRewards(@org.jetbrains.annotations.NotNull()
    java.util.List<com.porakhela.data.model.Reward> rewards, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM rewards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAllRewards(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM redemptions ORDER BY requestedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.RewardRedemption>> getAllRedemptions();
    
    @androidx.room.Query(value = "SELECT * FROM redemptions WHERE status = :status ORDER BY requestedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.RewardRedemption>> getRedemptionsByStatus(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RedemptionStatus status);
    
    @androidx.room.Query(value = "SELECT * FROM redemptions WHERE status = \'PENDING_APPROVAL\' ORDER BY requestedAt ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.RewardRedemption>> getPendingApprovalRedemptions();
    
    @androidx.room.Query(value = "SELECT * FROM redemptions WHERE id = :redemptionId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRedemptionById(long redemptionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.data.model.RewardRedemption> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRedemption(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RewardRedemption redemption, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRedemption(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RewardRedemption redemption, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRedemption(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RewardRedemption redemption, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM redemptions WHERE status IN (\'PENDING_APPROVAL\', \'APPROVED\', \'PROCESSING\')")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> getPendingRedemptionsCount();
    
    @androidx.room.Query(value = "\n        SELECT * FROM redemptions \n        WHERE requestedAt >= :startTime AND requestedAt <= :endTime \n        ORDER BY requestedAt DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRedemptionsInTimeRange(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.porakhela.data.model.RewardRedemption>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT r.* \n        FROM rewards r \n        INNER JOIN redemptions rd ON r.id = rd.rewardId \n        WHERE rd.status = :status \n        ORDER BY rd.requestedAt DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRewardsWithRedemptionStatus(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RedemptionStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.porakhela.data.model.Reward>> $completion);
}