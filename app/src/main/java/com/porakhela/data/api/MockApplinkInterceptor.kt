package com.porakhela.data.api

import com.porakhela.data.models.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 * Mock interceptor for Applink API responses
 * Simulates realistic API responses with error handling and delays
 */
class MockApplinkInterceptor : Interceptor {
    
    companion object {
        // Error simulation controls - accessible from DeveloperTools
        var simulateError = false
        var errorCode = 500
        var simulateTimeout = false
        var responseDelay = 0L
    }
    
    private val json = Json { 
        ignoreUnknownKeys = true 
        prettyPrint = true
    }
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()
        
        Timber.d("MockApplinkInterceptor: Intercepting request to $url")
        
        // Add response delay if configured
        if (responseDelay > 0) {
            Thread.sleep(responseDelay)
        }
        
        // Simulate timeout if configured
        if (simulateTimeout) {
            throw java.net.SocketTimeoutException("Mock timeout simulation")
        }
        
        // Simulate error responses if configured
        if (simulateError) {
            return createErrorResponse(originalRequest, errorCode)
        }
        
        // Route to appropriate response based on URL
        val responseBody = when {
            url.contains("/subscription/start") -> createSubscriptionStartResponse()
            url.contains("/subscription/status") -> createSubscriptionStatusResponse()
            url.contains("/sms/send") -> createSmsSendResponse()
            url.contains("/sms/status") -> createSmsStatusResponse()
            url.contains("/rewards/redeem") -> createRewardsRedeemResponse()
            url.contains("/rewards/history") -> createRewardsHistoryResponse()
            url.contains("/otp/send") -> createOtpSendResponse()
            url.contains("/otp/verify") -> createOtpVerifyResponse()
            url.contains("/ussd/execute") -> createUssdExecuteResponse()
            url.contains("/ussd/session") -> createUssdSessionResponse()
            else -> createGenericSuccessResponse()
        }
        
        return Response.Builder()
            .request(originalRequest)
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(responseBody.toResponseBody("application/json".toMediaType()))
            .build()
    }
    
    private fun createErrorResponse(request: Request, errorCode: Int): Response {
        val errorResponse = ApplinkApiResponse<Any>(
            status = "error",
            code = errorCode,
            message = when (errorCode) {
                400 -> "Bad request - Invalid parameters"
                401 -> "Unauthorized - Authentication required"
                403 -> "Forbidden - Access denied"
                404 -> "Not found - Endpoint does not exist"
                429 -> "Too many requests - Rate limit exceeded"
                500 -> "Internal server error - Something went wrong"
                503 -> "Service unavailable - Try again later"
                else -> "Unknown error occurred"
            },
            data = null
        )
        
        val errorBody = json.encodeToString(errorResponse)
        
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(errorCode)
            .message("Error")
            .body(errorBody.toResponseBody("application/json".toMediaType()))
            .build()
    }
    
    private fun createSubscriptionStartResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "Subscription activated successfully",
            data = SubscriptionResponse(
                subscription_id = "sub_${generateId()}",
                user_id = "user_123",
                plan_type = "premium",
                status = "active",
                start_date = dateFormat.format(Date()),
                end_date = dateFormat.format(Date(System.currentTimeMillis() + 2592000000)), // 30 days
                amount = 25000.0,
                currency = "UGX"
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createSubscriptionStatusResponse(): String {
        val statuses = listOf("active", "expired", "pending")
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "Subscription status retrieved",
            data = SubscriptionResponse(
                subscription_id = "sub_123",
                user_id = "user_123",
                plan_type = "premium",
                status = statuses.random(),
                start_date = dateFormat.format(Date(System.currentTimeMillis() - 86400000)), // 1 day ago
                end_date = dateFormat.format(Date(System.currentTimeMillis() + 2505600000)), // 29 days
                amount = 25000.0,
                currency = "UGX"
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createSmsSendResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "SMS sent successfully",
            data = SmsResponse(
                sms_id = "sms_${generateId()}",
                recipient_phone = "+256700123456",
                status = "sent",
                sent_at = dateFormat.format(Date()),
                delivery_status = "pending"
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createSmsStatusResponse(): String {
        val statuses = listOf("delivered", "pending", "failed")
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "SMS status retrieved",
            data = SmsResponse(
                sms_id = "sms_123",
                recipient_phone = "+256700123456",
                status = "sent",
                sent_at = dateFormat.format(Date()),
                delivery_status = statuses.random()
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createRewardsRedeemResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "Reward redeemed successfully",
            data = RewardsResponse(
                redemption_id = "redeem_${generateId()}",
                user_id = "user_123",
                reward_type = "airtime",
                points_deducted = 100,
                status = "completed",
                redeemed_at = dateFormat.format(Date()),
                details = mapOf(
                    "airtime_amount" to "5000",
                    "currency" to "UGX",
                    "phone_number" to "+256700123456",
                    "transaction_id" to "tx_${generateId()}"
                )
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createRewardsHistoryResponse(): String {
        val rewards = listOf(
            RewardsResponse(
                redemption_id = "redeem_001",
                user_id = "user_123",
                reward_type = "airtime",
                points_deducted = 100,
                status = "completed",
                redeemed_at = dateFormat.format(Date(System.currentTimeMillis() - 86400000)), // 1 day ago
                details = mapOf("amount" to "5000", "currency" to "UGX")
            ),
            RewardsResponse(
                redemption_id = "redeem_002",
                user_id = "user_123",
                reward_type = "data_bundle",
                points_deducted = 150,
                status = "completed",
                redeemed_at = dateFormat.format(Date(System.currentTimeMillis() - 172800000)), // 2 days ago
                details = mapOf("data_amount" to "1GB", "validity" to "30 days")
            )
        )
        
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "Rewards history retrieved",
            data = rewards
        )
        return json.encodeToString(response)
    }
    
    private fun createOtpSendResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "OTP sent successfully",
            data = mapOf(
                "session_id" to "otp_session_${generateId()}",
                "phone_number" to "+256700123456",
                "expires_at" to dateFormat.format(Date(System.currentTimeMillis() + 300000)), // 5 minutes
                "delivery_status" to "sent"
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createOtpVerifyResponse(): String {
        val isValid = Random.nextBoolean() // 50% chance of success for realistic testing
        val response = ApplinkApiResponse(
            status = if (isValid) "success" else "error",
            code = if (isValid) 200 else 400,
            message = if (isValid) "OTP verified successfully" else "Invalid OTP code",
            data = OtpResponse(
                verification_id = "verify_${generateId()}",
                phone_number = "+256700123456",
                status = if (isValid) "verified" else "failed",
                verified_at = if (isValid) dateFormat.format(Date()) else null,
                attempts_remaining = if (isValid) 3 else 2
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createUssdExecuteResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "USSD command executed",
            data = UssdResponse(
                session_id = "ussd_${generateId()}",
                user_id = "user_123",
                response_message = "Welcome to Porakhela Premium! Choose: 1) Check Balance 2) Buy Data 3) Subscribe",
                next_step = "await_user_input",
                session_active = true
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createUssdSessionResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "USSD session status retrieved",
            data = UssdResponse(
                session_id = "ussd_123",
                user_id = "user_123",
                response_message = "Session active. Last command: *155*1#",
                next_step = "session_complete",
                session_active = false
            )
        )
        return json.encodeToString(response)
    }
    
    private fun createGenericSuccessResponse(): String {
        val response = ApplinkApiResponse(
            status = "success",
            code = 200,
            message = "Mock API Response - Operation completed successfully",
            data = mapOf(
                "timestamp" to dateFormat.format(Date()),
                "mock" to true,
                "endpoint" to "generic"
            )
        )
        return json.encodeToString(response)
    }
    
    private fun generateId(): String {
        return UUID.randomUUID().toString().take(8)
    }
}