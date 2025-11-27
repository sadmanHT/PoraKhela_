package com.porakhela.data.models

import kotlinx.serialization.Serializable

/**
 * Base API response wrapper for all Applink API calls
 */
@Serializable
data class ApplinkApiResponse<T>(
    val status: String,
    val code: Int,
    val message: String,
    val data: T? = null,
    val error: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Subscription request and response models
 */
@Serializable
data class SubscriptionRequest(
    val user_id: String,
    val plan_type: String = "premium",
    val payment_method: String = "mobile_money"
)

@Serializable
data class SubscriptionResponse(
    val subscription_id: String,
    val user_id: String,
    val plan_type: String,
    val status: String,
    val start_date: String,
    val end_date: String,
    val amount: Double,
    val currency: String = "UGX"
)

/**
 * SMS notification request and response models
 */
@Serializable
data class SmsRequest(
    val recipient_phone: String,
    val message_type: String,
    val content: Map<String, String>,
    val template_id: String? = null
)

@Serializable
data class SmsResponse(
    val sms_id: String,
    val recipient_phone: String,
    val status: String,
    val sent_at: String,
    val delivery_status: String = "pending"
)

/**
 * Rewards redemption request and response models
 */
@Serializable
data class RewardsRedeemRequest(
    val user_id: String,
    val reward_type: String,
    val points_required: Int,
    val redemption_data: Map<String, String>
)

@Serializable
data class RewardsResponse(
    val redemption_id: String,
    val user_id: String,
    val reward_type: String,
    val points_deducted: Int,
    val status: String,
    val redeemed_at: String,
    val details: Map<String, String>
)

/**
 * OTP verification request and response models
 */
@Serializable
data class OtpRequest(
    val phone_number: String,
    val otp_code: String,
    val verification_type: String,
    val session_id: String? = null
)

@Serializable
data class OtpResponse(
    val verification_id: String,
    val phone_number: String,
    val status: String,
    val verified_at: String?,
    val attempts_remaining: Int = 3
)

/**
 * USSD control request and response models
 */
@Serializable
data class UssdRequest(
    val user_id: String,
    val ussd_code: String,
    val action: String,
    val parameters: Map<String, String>
)

@Serializable
data class UssdResponse(
    val session_id: String,
    val user_id: String,
    val response_message: String,
    val next_step: String?,
    val session_active: Boolean
)