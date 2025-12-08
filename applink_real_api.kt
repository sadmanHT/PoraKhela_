package com.porakhela.data.api

import com.porakhela.data.models.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Production Applink API Service
 * Real implementation for production use
 */
interface ApplinkRealApiService {
    
    // ==================== OTP APIs ====================
    
    /**
     * Send OTP to phone number
     * @param request OTP send request with phone number
     */
    @POST(ApplinkConfig.Endpoints.OTP_SEND)
    suspend fun sendOtp(
        @Body request: OtpSendRequest
    ): Response<ApplinkApiResponse<OtpSendResponse>>
    
    /**
     * Verify OTP code
     * @param request OTP verification request with code and session
     */
    @POST(ApplinkConfig.Endpoints.OTP_VERIFY)
    suspend fun verifyOtp(
        @Body request: OtpVerifyRequest
    ): Response<ApplinkApiResponse<OtpVerifyResponse>>
    
    // ==================== SMS APIs ====================
    
    /**
     * Send SMS to phone number
     * @param request SMS send request
     */
    @POST(ApplinkConfig.Endpoints.SMS_SEND)
    suspend fun sendSms(
        @Body request: SmsSendRequest
    ): Response<ApplinkApiResponse<SmsSendResponse>>
    
    /**
     * Check SMS delivery status
     * @param messageId SMS message ID from send response
     */
    @GET(ApplinkConfig.Endpoints.SMS_STATUS)
    suspend fun getSmsStatus(
        @Path("messageId") messageId: String
    ): Response<ApplinkApiResponse<SmsStatusResponse>>
    
    // ==================== Subscription (CAAS) APIs ====================
    
    /**
     * Start new subscription
     * @param request Subscription start request
     */
    @POST(ApplinkConfig.Endpoints.SUBSCRIPTION_START)
    suspend fun startSubscription(
        @Body request: SubscriptionStartRequest
    ): Response<ApplinkApiResponse<SubscriptionResponse>>
    
    /**
     * Check subscription status
     * @param subscriptionId Subscription ID
     */
    @GET(ApplinkConfig.Endpoints.SUBSCRIPTION_STATUS)
    suspend fun getSubscriptionStatus(
        @Path("subscriptionId") subscriptionId: String
    ): Response<ApplinkApiResponse<SubscriptionResponse>>
    
    /**
     * Cancel subscription
     * @param request Subscription cancellation request
     */
    @POST(ApplinkConfig.Endpoints.SUBSCRIPTION_CANCEL)
    suspend fun cancelSubscription(
        @Body request: SubscriptionCancelRequest
    ): Response<ApplinkApiResponse<SubscriptionCancelResponse>>
    
    /**
     * Renew subscription
     * @param request Subscription renewal request
     */
    @POST(ApplinkConfig.Endpoints.SUBSCRIPTION_RENEW)
    suspend fun renewSubscription(
        @Body request: SubscriptionRenewRequest
    ): Response<ApplinkApiResponse<SubscriptionResponse>>
    
    // ==================== USSD APIs (Optional) ====================
    
    /**
     * Execute USSD command
     * @param request USSD execution request
     */
    @POST(ApplinkConfig.Endpoints.USSD_EXECUTE)
    suspend fun executeUssd(
        @Body request: UssdRequest
    ): Response<ApplinkApiResponse<UssdResponse>>
    
    /**
     * Get USSD session status
     * @param sessionId USSD session ID
     */
    @GET(ApplinkConfig.Endpoints.USSD_SESSION)
    suspend fun getUssdSession(
        @Path("sessionId") sessionId: String
    ): Response<ApplinkApiResponse<UssdResponse>>
}
