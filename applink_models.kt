package com.porakhela.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ==================== OTP Models ====================

@Serializable
data class OtpSendRequest(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("country_code") val countryCode: String = "+880", // Bangladesh
    @SerialName("message_template") val messageTemplate: String? = null,
    @SerialName("expires_in") val expiresIn: Int = 300 // 5 minutes
)

@Serializable
data class OtpSendResponse(
    @SerialName("session_id") val sessionId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("expires_at") val expiresAt: Long,
    @SerialName("otp_length") val otpLength: Int = 6,
    @SerialName("retry_allowed") val retryAllowed: Boolean = true,
    @SerialName("retry_count") val retryCount: Int = 0,
    @SerialName("max_retries") val maxRetries: Int = 3
)

@Serializable
data class OtpVerifyRequest(
    @SerialName("session_id") val sessionId: String,
    @SerialName("otp_code") val otpCode: String,
    @SerialName("phone_number") val phoneNumber: String
)

@Serializable
data class OtpVerifyResponse(
    @SerialName("verified") val verified: Boolean,
    @SerialName("session_id") val sessionId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("verified_at") val verifiedAt: Long,
    @SerialName("user_id") val userId: String? = null
)

// ==================== SMS Models ====================

@Serializable
data class SmsSendRequest(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("message") val message: String,
    @SerialName("sender_id") val senderId: String = "PoraKhela",
    @SerialName("country_code") val countryCode: String = "+880",
    @SerialName("priority") val priority: String = "normal", // normal, high
    @SerialName("schedule_at") val scheduleAt: Long? = null // Unix timestamp for scheduled SMS
)

@Serializable
data class SmsSendResponse(
    @SerialName("message_id") val messageId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("status") val status: String, // sent, pending, failed
    @SerialName("sent_at") val sentAt: Long,
    @SerialName("cost") val cost: Double? = null,
    @SerialName("currency") val currency: String = "BDT"
)

@Serializable
data class SmsStatusResponse(
    @SerialName("message_id") val messageId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("status") val status: String, // delivered, pending, failed, expired
    @SerialName("sent_at") val sentAt: Long,
    @SerialName("delivered_at") val deliveredAt: Long? = null,
    @SerialName("error_code") val errorCode: String? = null,
    @SerialName("error_message") val errorMessage: String? = null
)

// ==================== Subscription Models ====================

@Serializable
data class SubscriptionStartRequest(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("plan_type") val planType: String, // daily, weekly, monthly
    @SerialName("user_id") val userId: String,
    @SerialName("auto_renew") val autoRenew: Boolean = true,
    @SerialName("payment_method") val paymentMethod: String = "carrier_billing" // carrier_billing, mobile_money
)

@Serializable
data class SubscriptionResponse(
    @SerialName("subscription_id") val subscriptionId: String,
    @SerialName("user_id") val userId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("plan_type") val planType: String,
    @SerialName("status") val status: String, // active, pending, cancelled, expired
    @SerialName("start_date") val startDate: Long,
    @SerialName("end_date") val endDate: Long,
    @SerialName("auto_renew") val autoRenew: Boolean,
    @SerialName("amount") val amount: Double,
    @SerialName("currency") val currency: String = "BDT",
    @SerialName("next_billing_date") val nextBillingDate: Long? = null,
    @SerialName("payment_method") val paymentMethod: String
)

@Serializable
data class SubscriptionCancelRequest(
    @SerialName("subscription_id") val subscriptionId: String,
    @SerialName("user_id") val userId: String,
    @SerialName("reason") val reason: String? = null,
    @SerialName("immediate") val immediate: Boolean = false // Cancel immediately or at period end
)

@Serializable
data class SubscriptionCancelResponse(
    @SerialName("subscription_id") val subscriptionId: String,
    @SerialName("status") val status: String, // cancelled
    @SerialName("cancelled_at") val cancelledAt: Long,
    @SerialName("refund_amount") val refundAmount: Double? = null,
    @SerialName("refund_status") val refundStatus: String? = null
)

@Serializable
data class SubscriptionRenewRequest(
    @SerialName("subscription_id") val subscriptionId: String,
    @SerialName("user_id") val userId: String,
    @SerialName("plan_type") val planType: String? = null // Optional: change plan type
)

// ==================== USSD Models ====================

@Serializable
data class UssdRequest(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("ussd_code") val ussdCode: String,
    @SerialName("session_id") val sessionId: String? = null,
    @SerialName("user_input") val userInput: String? = null
)

@Serializable
data class UssdResponse(
    @SerialName("session_id") val sessionId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("response_text") val responseText: String,
    @SerialName("status") val status: String, // continue, completed, failed
    @SerialName("requires_input") val requiresInput: Boolean,
    @SerialName("timestamp") val timestamp: Long
)

// ==================== Common Response Wrapper ====================

@Serializable
data class ApplinkApiResponse<T>(
    @SerialName("status") val status: String, // success, error
    @SerialName("code") val code: Int,
    @SerialName("message") val message: String,
    @SerialName("data") val data: T? = null,
    @SerialName("error") val error: ApplinkError? = null,
    @SerialName("timestamp") val timestamp: Long
)

@Serializable
data class ApplinkError(
    @SerialName("code") val code: String,
    @SerialName("message") val message: String,
    @SerialName("details") val details: Map<String, String>? = null
)
