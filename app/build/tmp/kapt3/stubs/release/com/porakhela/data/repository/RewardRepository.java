package com.porakhela.data.repository;

/**
 * Repository for managing reward data from local JSON and database
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00160\u0015J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00160\u0015J\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00160\u0015J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0015J\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00160\u00152\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00112\u0006\u0010 \u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010#J\"\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00160\u000fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b%\u0010#J\"\u0010&\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\'\u001a\u0004\u0018\u00010(H\u0086@\u00a2\u0006\u0002\u0010)J*\u0010*\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010(H\u0086@\u00a2\u0006\u0002\u0010,R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006."}, d2 = {"Lcom/porakhela/data/repository/RewardRepository;", "", "context", "Landroid/content/Context;", "rewardDao", "Lcom/porakhela/data/local/dao/RewardDao;", "(Landroid/content/Context;Lcom/porakhela/data/local/dao/RewardDao;)V", "gson", "Lcom/google/gson/Gson;", "approveRedemption", "", "redemptionId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createRedemption", "Lkotlin/Result;", "reward", "Lcom/porakhela/data/model/Reward;", "createRedemption-gIAlu-s", "(Lcom/porakhela/data/model/Reward;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAvailableRewards", "Lkotlinx/coroutines/flow/Flow;", "", "getAllRedemptions", "Lcom/porakhela/data/model/RewardRedemption;", "getPendingApprovalRedemptions", "getPendingRedemptionsCount", "", "getRedemptionsByStatus", "status", "Lcom/porakhela/data/model/RedemptionStatus;", "getRewardById", "rewardId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initializeRewardsIfNeeded", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadRewardsFromAssets", "loadRewardsFromAssets-IoAF18A", "rejectRedemption", "reason", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRedemptionStatus", "errorMessage", "(JLcom/porakhela/data/model/RedemptionStatus;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"})
public final class RewardRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.dao.RewardDao rewardDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String REWARDS_JSON_FILE = "rewards.json";
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.repository.RewardRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public RewardRepository(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.dao.RewardDao rewardDao) {
        super();
    }
    
    /**
     * Get all available rewards from database
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.Reward>> getAllAvailableRewards() {
        return null;
    }
    
    /**
     * Get reward by ID
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRewardById(int rewardId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.data.model.Reward> $completion) {
        return null;
    }
    
    /**
     * Initialize rewards data if database is empty
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initializeRewardsIfNeeded(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Update redemption status
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRedemptionStatus(long redemptionId, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RedemptionStatus status, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Get all redemptions
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.RewardRedemption>> getAllRedemptions() {
        return null;
    }
    
    /**
     * Get redemptions by status
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.RewardRedemption>> getRedemptionsByStatus(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.RedemptionStatus status) {
        return null;
    }
    
    /**
     * Get pending approval redemptions for parent dashboard
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.porakhela.data.model.RewardRedemption>> getPendingApprovalRedemptions() {
        return null;
    }
    
    /**
     * Get count of pending redemptions
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getPendingRedemptionsCount() {
        return null;
    }
    
    /**
     * Approve a redemption (parent action)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object approveRedemption(long redemptionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Reject a redemption (parent action)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object rejectRedemption(long redemptionId, @org.jetbrains.annotations.Nullable()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/porakhela/data/repository/RewardRepository$Companion;", "", "()V", "REWARDS_JSON_FILE", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}