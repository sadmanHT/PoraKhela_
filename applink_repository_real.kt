package com.porakhela.data.repository

import com.porakhela.data.api.ApplinkConfig
import com.porakhela.data.api.ApplinkRealApiService
import com.porakhela.data.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Production Applink Repository Implementation
 * Handles all real API calls to Applink services
 */
@Singleton
class ApplinkRepositoryImpl @Inject constructor(
    private val apiService: ApplinkRealApiService
) {
    
    // ==================== OTP Operations ====================
    
    /**
     * Send OTP to phone number
     * @param phoneNumber Phone number (without country code)
     * @return Flow with Result containing OTP session info
     */
    fun sendOtp(phoneNumber: String): Flow<Result<OtpSendResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val request = OtpSendRequest(
                phoneNumber = phoneNumber,
                countryCode = "+880",
                expiresIn = 300 // 5 minutes
            )
            
            val response = apiService.sendOtp(request)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("OTP sent successfully: ${data.sessionId}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to send OTP: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to send OTP"
                Timber.e("OTP send failed: $errorMsg")
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception sending OTP")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    /**
     * Verify OTP code
     * @param sessionId Session ID from sendOtp response
     * @param otpCode OTP code entered by user
     * @param phoneNumber Phone number
     * @return Flow with Result containing verification status
     */
    fun verifyOtp(
        sessionId: String,
        otpCode: String,
        phoneNumber: String
    ): Flow<Result<OtpVerifyResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val request = OtpVerifyRequest(
                sessionId = sessionId,
                otpCode = otpCode,
                phoneNumber = phoneNumber
            )
            
            val response = apiService.verifyOtp(request)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    if (data.verified) {
                        Timber.d("OTP verified successfully")
                        emit(Result.Success(data))
                    } else {
                        Timber.w("OTP verification failed: Invalid code")
                        emit(Result.Error("Invalid OTP code"))
                    }
                } else {
                    emit(Result.Error("Failed to verify OTP: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to verify OTP"
                Timber.e("OTP verification failed: $errorMsg")
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception verifying OTP")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    // ==================== SMS Operations ====================
    
    /**
     * Send SMS to phone number
     * @param phoneNumber Phone number
     * @param message SMS message content
     * @return Flow with Result containing SMS send status
     */
    fun sendSms(
        phoneNumber: String,
        message: String
    ): Flow<Result<SmsSendResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val request = SmsSendRequest(
                phoneNumber = phoneNumber,
                message = message,
                senderId = "PoraKhela",
                countryCode = "+880",
                priority = "normal"
            )
            
            val response = apiService.sendSms(request)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("SMS sent successfully: ${data.messageId}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to send SMS: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to send SMS"
                Timber.e("SMS send failed: $errorMsg")
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception sending SMS")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    /**
     * Send learning report SMS to parent
     */
    fun sendLearningReportSms(
        parentPhone: String,
        childName: String,
        points: Int,
        streak: Int
    ): Flow<Result<SmsSendResponse>> {
        val message = ApplinkConfig.SmsTemplates.learningReport(childName, points, streak)
        return sendSms(parentPhone, message)
    }
    
    /**
     * Get SMS delivery status
     * @param messageId Message ID from sendSms response
     */
    fun getSmsStatus(messageId: String): Flow<Result<SmsStatusResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val response = apiService.getSmsStatus(messageId)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("SMS status retrieved: ${data.status}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to get SMS status: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to get SMS status"
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception getting SMS status")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    // ==================== Subscription Operations ====================
    
    /**
     * Start new subscription
     * @param phoneNumber User's phone number
     * @param planType Subscription plan (daily, weekly, monthly)
     * @param userId User ID
     * @return Flow with Result containing subscription details
     */
    fun startSubscription(
        phoneNumber: String,
        planType: String,
        userId: String,
        autoRenew: Boolean = true
    ): Flow<Result<SubscriptionResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val request = SubscriptionStartRequest(
                phoneNumber = phoneNumber,
                planType = planType,
                userId = userId,
                autoRenew = autoRenew,
                paymentMethod = "carrier_billing"
            )
            
            val response = apiService.startSubscription(request)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("Subscription started: ${data.subscriptionId}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to start subscription: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to start subscription"
                Timber.e("Subscription start failed: $errorMsg")
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception starting subscription")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    /**
     * Get subscription status
     * @param subscriptionId Subscription ID
     */
    fun getSubscriptionStatus(
        subscriptionId: String
    ): Flow<Result<SubscriptionResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val response = apiService.getSubscriptionStatus(subscriptionId)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("Subscription status: ${data.status}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to get subscription status: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to get subscription status"
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception getting subscription status")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    /**
     * Cancel subscription
     * @param subscriptionId Subscription ID
     * @param userId User ID
     * @param immediate Cancel immediately or at period end
     */
    fun cancelSubscription(
        subscriptionId: String,
        userId: String,
        reason: String? = null,
        immediate: Boolean = false
    ): Flow<Result<SubscriptionCancelResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val request = SubscriptionCancelRequest(
                subscriptionId = subscriptionId,
                userId = userId,
                reason = reason,
                immediate = immediate
            )
            
            val response = apiService.cancelSubscription(request)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("Subscription cancelled: ${data.subscriptionId}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to cancel subscription: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to cancel subscription"
                Timber.e("Subscription cancellation failed: $errorMsg")
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception cancelling subscription")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
    
    /**
     * Renew subscription
     * @param subscriptionId Subscription ID
     * @param userId User ID
     * @param newPlanType Optional: change plan type during renewal
     */
    fun renewSubscription(
        subscriptionId: String,
        userId: String,
        newPlanType: String? = null
    ): Flow<Result<SubscriptionResponse>> = flow {
        try {
            emit(Result.Loading)
            
            val request = SubscriptionRenewRequest(
                subscriptionId = subscriptionId,
                userId = userId,
                planType = newPlanType
            )
            
            val response = apiService.renewSubscription(request)
            
            if (response.isSuccessful && response.body()?.status == "success") {
                val data = response.body()?.data
                if (data != null) {
                    Timber.d("Subscription renewed: ${data.subscriptionId}")
                    emit(Result.Success(data))
                } else {
                    emit(Result.Error("Failed to renew subscription: No data"))
                }
            } else {
                val errorMsg = response.body()?.message ?: "Failed to renew subscription"
                emit(Result.Error(errorMsg))
            }
        } catch (e: Exception) {
            Timber.e(e, "Exception renewing subscription")
            emit(Result.Error(e.message ?: "Network error"))
        }
    }
}

/**
 * Result sealed class for API responses
 */
sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}
