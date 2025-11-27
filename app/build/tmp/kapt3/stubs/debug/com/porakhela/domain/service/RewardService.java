package com.porakhela.domain.service;

/**
 * Service for handling reward redemption business logic
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001cB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J&\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/porakhela/domain/service/RewardService;", "", "rewardRepository", "Lcom/porakhela/data/repository/RewardRepository;", "rewardApiService", "Lcom/porakhela/data/api/RewardApiService;", "streakPreferences", "Lcom/porakhela/data/local/StreakPreferences;", "(Lcom/porakhela/data/repository/RewardRepository;Lcom/porakhela/data/api/RewardApiService;Lcom/porakhela/data/local/StreakPreferences;)V", "canAffordReward", "", "reward", "Lcom/porakhela/data/model/Reward;", "getCurrentBalance", "", "initializeRewards", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processApprovedRedemption", "Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "redemptionId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processRedemption", "currentBalance", "(JLcom/porakhela/data/model/Reward;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "redeemReward", "(Lcom/porakhela/data/model/Reward;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "RedemptionResult", "app_debug"})
public final class RewardService {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.RewardRepository rewardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.api.RewardApiService rewardApiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.StreakPreferences streakPreferences = null;
    
    @javax.inject.Inject()
    public RewardService(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.RewardRepository rewardRepository, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.api.RewardApiService rewardApiService, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.StreakPreferences streakPreferences) {
        super();
    }
    
    /**
     * Initiate reward redemption process
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object redeemReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.domain.service.RewardService.RedemptionResult> $completion) {
        return null;
    }
    
    /**
     * Process approved redemption (called after parent approval or for immediate redemptions)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object processApprovedRedemption(long redemptionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.domain.service.RewardService.RedemptionResult> $completion) {
        return null;
    }
    
    /**
     * Process the actual redemption with API call
     */
    private final java.lang.Object processRedemption(long redemptionId, com.porakhela.data.model.Reward reward, int currentBalance, kotlin.coroutines.Continuation<? super com.porakhela.domain.service.RewardService.RedemptionResult> $completion) {
        return null;
    }
    
    /**
     * Get user's current Porapoints balance
     */
    public final int getCurrentBalance() {
        return 0;
    }
    
    /**
     * Check if user can afford a reward
     */
    public final boolean canAffordReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward) {
        return false;
    }
    
    /**
     * Initialize rewards data
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initializeRewards(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Result of a redemption attempt
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "", "()V", "Error", "InsufficientPoints", "PendingApproval", "Success", "Lcom/porakhela/domain/service/RewardService$RedemptionResult$Error;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult$InsufficientPoints;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult$PendingApproval;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult$Success;", "app_debug"})
    public static abstract class RedemptionResult {
        
        private RedemptionResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/porakhela/domain/service/RewardService$RedemptionResult$Error;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "message", "", "retryable", "", "(Ljava/lang/String;Z)V", "getMessage", "()Ljava/lang/String;", "getRetryable", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.porakhela.domain.service.RewardService.RedemptionResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            private final boolean retryable = false;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message, boolean retryable) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            public final boolean getRetryable() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.porakhela.domain.service.RewardService.RedemptionResult.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message, boolean retryable) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/porakhela/domain/service/RewardService$RedemptionResult$InsufficientPoints;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "needed", "", "current", "(II)V", "getCurrent", "()I", "getNeeded", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
        public static final class InsufficientPoints extends com.porakhela.domain.service.RewardService.RedemptionResult {
            private final int needed = 0;
            private final int current = 0;
            
            public InsufficientPoints(int needed, int current) {
            }
            
            public final int getNeeded() {
                return 0;
            }
            
            public final int getCurrent() {
                return 0;
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int component2() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.porakhela.domain.service.RewardService.RedemptionResult.InsufficientPoints copy(int needed, int current) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/porakhela/domain/service/RewardService$RedemptionResult$PendingApproval;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "redemptionId", "", "(J)V", "getRedemptionId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
        public static final class PendingApproval extends com.porakhela.domain.service.RewardService.RedemptionResult {
            private final long redemptionId = 0L;
            
            public PendingApproval(long redemptionId) {
            }
            
            public final long getRedemptionId() {
                return 0L;
            }
            
            public final long component1() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.porakhela.domain.service.RewardService.RedemptionResult.PendingApproval copy(long redemptionId) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/porakhela/domain/service/RewardService$RedemptionResult$Success;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "transactionId", "", "newBalance", "", "(Ljava/lang/String;I)V", "getNewBalance", "()I", "getTransactionId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"})
        public static final class Success extends com.porakhela.domain.service.RewardService.RedemptionResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String transactionId = null;
            private final int newBalance = 0;
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.lang.String transactionId, int newBalance) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getTransactionId() {
                return null;
            }
            
            public final int getNewBalance() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            public final int component2() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.porakhela.domain.service.RewardService.RedemptionResult.Success copy(@org.jetbrains.annotations.NotNull()
            java.lang.String transactionId, int newBalance) {
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
    }
}