package com.porakhela.data.api;

/**
 * Mock API service for reward redemptions
 * Simulates real API calls with various response scenarios
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0006H\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007J.\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lcom/porakhela/data/api/RewardApiService;", "", "()V", "checkRedemptionStatus", "Lcom/porakhela/data/api/RewardApiService$RedemptionResponse;", "transactionId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateTransactionId", "getCurrentBalance", "", "userId", "redeemReward", "rewardId", "cost", "currentBalance", "(ILjava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "RedemptionResponse", "app_release"})
public final class RewardApiService {
    private static final long API_DELAY_MS = 2000L;
    private static final float SUCCESS_RATE = 0.7F;
    private static final float INSUFFICIENT_POINTS_RATE = 0.2F;
    private static final float SERVER_ERROR_RATE = 0.1F;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.api.RewardApiService.Companion Companion = null;
    
    @javax.inject.Inject()
    public RewardApiService() {
        super();
    }
    
    /**
     * Simulate API call to redeem a reward
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object redeemReward(int rewardId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, int cost, int currentBalance, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.data.api.RewardApiService.RedemptionResponse> $completion) {
        return null;
    }
    
    /**
     * Check redemption status (for polling)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkRedemptionStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.porakhela.data.api.RewardApiService.RedemptionResponse> $completion) {
        return null;
    }
    
    /**
     * Generate a mock transaction ID
     */
    private final java.lang.String generateTransactionId() {
        return null;
    }
    
    /**
     * Simulate getting user's current point balance from server
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCurrentBalance(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/porakhela/data/api/RewardApiService$Companion;", "", "()V", "API_DELAY_MS", "", "INSUFFICIENT_POINTS_RATE", "", "SERVER_ERROR_RATE", "SUCCESS_RATE", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    /**
     * Mock API response for reward redemption
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\fJH\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f\u00a8\u0006\u001f"}, d2 = {"Lcom/porakhela/data/api/RewardApiService$RedemptionResponse;", "", "success", "", "transactionId", "", "errorCode", "errorMessage", "balanceRemaining", "", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getBalanceRemaining", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getErrorCode", "()Ljava/lang/String;", "getErrorMessage", "getSuccess", "()Z", "getTransactionId", "component1", "component2", "component3", "component4", "component5", "copy", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/porakhela/data/api/RewardApiService$RedemptionResponse;", "equals", "other", "hashCode", "toString", "app_release"})
    public static final class RedemptionResponse {
        private final boolean success = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String transactionId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String errorCode = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String errorMessage = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer balanceRemaining = null;
        
        public RedemptionResponse(boolean success, @org.jetbrains.annotations.Nullable()
        java.lang.String transactionId, @org.jetbrains.annotations.Nullable()
        java.lang.String errorCode, @org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
        java.lang.Integer balanceRemaining) {
            super();
        }
        
        public final boolean getSuccess() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTransactionId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getErrorCode() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getErrorMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getBalanceRemaining() {
            return null;
        }
        
        public final boolean component1() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.porakhela.data.api.RewardApiService.RedemptionResponse copy(boolean success, @org.jetbrains.annotations.Nullable()
        java.lang.String transactionId, @org.jetbrains.annotations.Nullable()
        java.lang.String errorCode, @org.jetbrains.annotations.Nullable()
        java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
        java.lang.Integer balanceRemaining) {
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