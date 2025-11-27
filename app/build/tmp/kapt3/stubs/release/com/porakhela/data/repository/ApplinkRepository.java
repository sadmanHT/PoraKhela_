package com.porakhela.data.repository;

/**
 * Repository for Applink API operations with local state management
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000eH\u0002J4\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J*\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u00102\u0006\u0010\u0012\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00102\u0006\u0010 \u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\u001dJ$\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00102\u0006\u0010\u0012\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b$\u0010\u001dJ4\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00102\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010&\u001a\u00020\f2\u0006\u0010\'\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b)\u0010*J8\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00102\u0006\u0010,\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u0010.J:\u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000e0\u00102\u0006\u00100\u001a\u00020\f2\b\b\u0002\u00101\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b2\u00103J$\u00104\u001a\b\u0012\u0004\u0012\u00020#0\u00102\u0006\u0010\u0012\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b5\u0010\u001dJ\u0010\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\u0018H\u0002J8\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00102\u0006\u00100\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b=\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006>"}, d2 = {"Lcom/porakhela/data/repository/ApplinkRepository;", "", "context", "Landroid/content/Context;", "apiService", "Lcom/porakhela/data/api/ApplinkApiService;", "porapointsManager", "Lcom/porakhela/utils/PorapointsManager;", "(Landroid/content/Context;Lcom/porakhela/data/api/ApplinkApiService;Lcom/porakhela/utils/PorapointsManager;)V", "prefs", "Landroid/content/SharedPreferences;", "buildLearningReportMessage", "", "reportData", "", "executeUssd", "Lkotlin/Result;", "Lcom/porakhela/data/models/UssdResponse;", "userId", "ussdCode", "action", "executeUssd-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalSubscriptionStatus", "", "getRewardsHistory", "", "Lcom/porakhela/data/models/RewardsResponse;", "getRewardsHistory-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSmsStatus", "Lcom/porakhela/data/models/SmsResponse;", "smsId", "getSmsStatus-gIAlu-s", "getSubscriptionStatus", "Lcom/porakhela/data/models/SubscriptionResponse;", "getSubscriptionStatus-gIAlu-s", "redeemReward", "rewardType", "pointsRequired", "", "redeemReward-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendLearningReport", "parentPhone", "sendLearningReport-0E7RQCE", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOtp", "phoneNumber", "verificationType", "sendOtp-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startSubscription", "startSubscription-gIAlu-s", "updateLocalSubscriptionStatus", "", "isActive", "verifyOtp", "Lcom/porakhela/data/models/OtpResponse;", "otpCode", "sessionId", "verifyOtp-BWLJW6A", "app_release"})
public final class ApplinkRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.api.ApplinkApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.utils.PorapointsManager porapointsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    
    @javax.inject.Inject()
    public ApplinkRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.api.ApplinkApiService apiService, @org.jetbrains.annotations.NotNull()
    com.porakhela.utils.PorapointsManager porapointsManager) {
        super();
    }
    
    /**
     * Helper methods
     */
    private final void updateLocalSubscriptionStatus(boolean isActive) {
    }
    
    public final boolean getLocalSubscriptionStatus() {
        return false;
    }
    
    private final java.lang.String buildLearningReportMessage(java.util.Map<java.lang.String, java.lang.String> reportData) {
        return null;
    }
}