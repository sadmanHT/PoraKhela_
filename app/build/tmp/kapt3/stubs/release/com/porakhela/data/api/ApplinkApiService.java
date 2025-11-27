package com.porakhela.data.api;

/**
 * Applink API Service interface for all external integrations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00050\u00040\u00032\u0014\b\u0001\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ4\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00062\b\b\u0003\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u001b\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ<\u0010\u001f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00050\u00040\u00032\u0014\b\u0001\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J$\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020$H\u00a7@\u00a2\u0006\u0002\u0010%J$\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\'0\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020(H\u00a7@\u00a2\u0006\u0002\u0010)\u00a8\u0006*"}, d2 = {"Lcom/porakhela/data/api/ApplinkApiService;", "", "cancelSubscription", "Lretrofit2/Response;", "Lcom/porakhela/data/models/ApplinkApiResponse;", "", "", "request", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeUssd", "Lcom/porakhela/data/models/UssdResponse;", "Lcom/porakhela/data/models/UssdRequest;", "(Lcom/porakhela/data/models/UssdRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRewardsHistory", "", "Lcom/porakhela/data/models/RewardsResponse;", "userId", "limit", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSmsStatus", "Lcom/porakhela/data/models/SmsResponse;", "smsId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubscriptionStatus", "Lcom/porakhela/data/models/SubscriptionResponse;", "getUssdSession", "sessionId", "redeemReward", "Lcom/porakhela/data/models/RewardsRedeemRequest;", "(Lcom/porakhela/data/models/RewardsRedeemRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOtp", "sendSms", "Lcom/porakhela/data/models/SmsRequest;", "(Lcom/porakhela/data/models/SmsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startSubscription", "Lcom/porakhela/data/models/SubscriptionRequest;", "(Lcom/porakhela/data/models/SubscriptionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyOtp", "Lcom/porakhela/data/models/OtpResponse;", "Lcom/porakhela/data/models/OtpRequest;", "(Lcom/porakhela/data/models/OtpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface ApplinkApiService {
    
    /**
     * Subscription Management (CAAS)
     */
    @retrofit2.http.POST(value = "subscription/start")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object startSubscription(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.SubscriptionRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.SubscriptionResponse>>> $completion);
    
    @retrofit2.http.GET(value = "subscription/status/{userId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSubscriptionStatus(@retrofit2.http.Path(value = "userId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.SubscriptionResponse>>> $completion);
    
    @retrofit2.http.POST(value = "subscription/cancel")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object cancelSubscription(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<java.util.Map<java.lang.String, java.lang.Object>>>> $completion);
    
    /**
     * SMS Notifications
     */
    @retrofit2.http.POST(value = "sms/send")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendSms(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.SmsRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.SmsResponse>>> $completion);
    
    @retrofit2.http.GET(value = "sms/status/{smsId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSmsStatus(@retrofit2.http.Path(value = "smsId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String smsId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.SmsResponse>>> $completion);
    
    /**
     * Rewards Redemption
     */
    @retrofit2.http.POST(value = "rewards/redeem")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object redeemReward(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.RewardsRedeemRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.RewardsResponse>>> $completion);
    
    @retrofit2.http.GET(value = "rewards/history/{userId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRewardsHistory(@retrofit2.http.Path(value = "userId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<java.util.List<com.porakhela.data.models.RewardsResponse>>>> $completion);
    
    /**
     * OTP Verification
     */
    @retrofit2.http.POST(value = "otp/send")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendOtp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<java.util.Map<java.lang.String, java.lang.Object>>>> $completion);
    
    @retrofit2.http.POST(value = "otp/verify")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyOtp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.OtpRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.OtpResponse>>> $completion);
    
    /**
     * USSD Control
     */
    @retrofit2.http.POST(value = "ussd/execute")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object executeUssd(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.UssdRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.UssdResponse>>> $completion);
    
    @retrofit2.http.GET(value = "ussd/session/{sessionId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUssdSession(@retrofit2.http.Path(value = "sessionId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String sessionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.porakhela.data.models.ApplinkApiResponse<com.porakhela.data.models.UssdResponse>>> $completion);
    
    /**
     * Applink API Service interface for all external integrations
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}