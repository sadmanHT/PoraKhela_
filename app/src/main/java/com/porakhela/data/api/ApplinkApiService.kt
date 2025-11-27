package com.porakhela.data.api

import com.porakhela.data.models.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Applink API Service interface for all external integrations
 */
interface ApplinkApiService {
    
    /**
     * Subscription Management (CAAS)
     */
    @POST("subscription/start")
    suspend fun startSubscription(
        @Body request: SubscriptionRequest
    ): Response<ApplinkApiResponse<SubscriptionResponse>>
    
    @GET("subscription/status/{userId}")
    suspend fun getSubscriptionStatus(
        @Path("userId") userId: String
    ): Response<ApplinkApiResponse<SubscriptionResponse>>
    
    @POST("subscription/cancel")
    suspend fun cancelSubscription(
        @Body request: Map<String, String>
    ): Response<ApplinkApiResponse<Map<String, Any>>>
    
    /**
     * SMS Notifications
     */
    @POST("sms/send")
    suspend fun sendSms(
        @Body request: SmsRequest
    ): Response<ApplinkApiResponse<SmsResponse>>
    
    @GET("sms/status/{smsId}")
    suspend fun getSmsStatus(
        @Path("smsId") smsId: String
    ): Response<ApplinkApiResponse<SmsResponse>>
    
    /**
     * Rewards Redemption
     */
    @POST("rewards/redeem")
    suspend fun redeemReward(
        @Body request: RewardsRedeemRequest
    ): Response<ApplinkApiResponse<RewardsResponse>>
    
    @GET("rewards/history/{userId}")
    suspend fun getRewardsHistory(
        @Path("userId") userId: String,
        @Query("limit") limit: Int = 20
    ): Response<ApplinkApiResponse<List<RewardsResponse>>>
    
    /**
     * OTP Verification
     */
    @POST("otp/send")
    suspend fun sendOtp(
        @Body request: Map<String, String>
    ): Response<ApplinkApiResponse<Map<String, Any>>>
    
    @POST("otp/verify")
    suspend fun verifyOtp(
        @Body request: OtpRequest
    ): Response<ApplinkApiResponse<OtpResponse>>
    
    /**
     * USSD Control
     */
    @POST("ussd/execute")
    suspend fun executeUssd(
        @Body request: UssdRequest
    ): Response<ApplinkApiResponse<UssdResponse>>
    
    @GET("ussd/session/{sessionId}")
    suspend fun getUssdSession(
        @Path("sessionId") sessionId: String
    ): Response<ApplinkApiResponse<UssdResponse>>
}