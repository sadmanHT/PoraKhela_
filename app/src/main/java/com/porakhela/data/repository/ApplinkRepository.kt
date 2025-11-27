package com.porakhela.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.porakhela.data.api.ApplinkApiService
import com.porakhela.data.models.*
import com.porakhela.utils.PorapointsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Applink API operations with local state management
 */
@Singleton
class ApplinkRepository @Inject constructor(
    private val context: Context,
    private val apiService: ApplinkApiService,
    private val porapointsManager: PorapointsManager
) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("applink_prefs", Context.MODE_PRIVATE)
    
    /**
     * CAAS Subscription Management
     */
    suspend fun startSubscription(userId: String): Result<SubscriptionResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = SubscriptionRequest(
                    user_id = userId,
                    plan_type = "premium",
                    payment_method = "mobile_money"
                )
                
                val response = apiService.startSubscription(request)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val subscriptionData = response.body()?.data
                    if (subscriptionData != null) {
                        updateLocalSubscriptionStatus(subscriptionData.status == "active")
                        Timber.i("Subscription started: ${subscriptionData.subscription_id}")
                        Result.success(subscriptionData)
                    } else {
                        Result.failure(Exception("No subscription data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to start subscription"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error starting subscription")
                Result.failure(e)
            }
        }
    }
    
    suspend fun getSubscriptionStatus(userId: String): Result<SubscriptionResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getSubscriptionStatus(userId)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val subscriptionData = response.body()?.data
                    if (subscriptionData != null) {
                        updateLocalSubscriptionStatus(subscriptionData.status == "active")
                        Result.success(subscriptionData)
                    } else {
                        Result.failure(Exception("No subscription data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to get subscription status"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error getting subscription status")
                Result.failure(e)
            }
        }
    }
    
    /**
     * SMS Notifications
     */
    suspend fun sendLearningReport(parentPhone: String, reportData: Map<String, String>): Result<SmsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = SmsRequest(
                    recipient_phone = parentPhone,
                    message_type = "learning_report",
                    content = reportData,
                    template_id = "learning_report_template"
                )
                
                val response = apiService.sendSms(request)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val smsData = response.body()?.data
                    if (smsData != null) {
                        Timber.i("Learning report SMS sent: ${smsData.sms_id}")
                        Result.success(smsData)
                    } else {
                        Result.failure(Exception("No SMS data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to send SMS"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error sending learning report SMS")
                Result.failure(e)
            }
        }
    }
    
    suspend fun getSmsStatus(smsId: String): Result<SmsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getSmsStatus(smsId)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val smsData = response.body()?.data
                    if (smsData != null) {
                        Result.success(smsData)
                    } else {
                        Result.failure(Exception("No SMS data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to get SMS status"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error getting SMS status")
                Result.failure(e)
            }
        }
    }
    
    /**
     * Rewards Redemption
     */
    suspend fun redeemReward(userId: String, rewardType: String, pointsRequired: Int): Result<RewardsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                // Check if user has enough points locally first
                val currentPoints = porapointsManager.getCurrentPorapoints()
                if (currentPoints < pointsRequired) {
                    return@withContext Result.failure(Exception("Insufficient points: $currentPoints (need $pointsRequired)"))
                }
                
                val request = RewardsRedeemRequest(
                    user_id = userId,
                    reward_type = rewardType,
                    points_required = pointsRequired,
                    redemption_data = mapOf(
                        "phone_number" to "+256700123456", // This would come from user profile
                        "amount" to when (rewardType) {
                            "airtime" -> "5000"
                            "data_bundle" -> "1GB"
                            else -> "1"
                        }
                    )
                )
                
                val response = apiService.redeemReward(request)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val rewardData = response.body()?.data
                    if (rewardData != null) {
                        // Deduct points from local storage
                        try {
                            porapointsManager.spendPorapoints(pointsRequired)
                            Timber.i("Reward redeemed: ${rewardData.redemption_id}, Points deducted: $pointsRequired")
                            Result.success(rewardData)
                        } catch (e: Exception) {
                            Timber.e(e, "Error deducting points after successful redemption")
                            Result.failure(Exception("Redemption successful but failed to update local points"))
                        }
                    } else {
                        Result.failure(Exception("No reward data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to redeem reward"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error redeeming reward")
                Result.failure(e)
            }
        }
    }
    
    suspend fun getRewardsHistory(userId: String): Result<List<RewardsResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRewardsHistory(userId)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val historyData = response.body()?.data
                    if (historyData != null) {
                        Result.success(historyData)
                    } else {
                        Result.failure(Exception("No history data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to get rewards history"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error getting rewards history")
                Result.failure(e)
            }
        }
    }
    
    /**
     * OTP Verification
     */
    suspend fun sendOtp(phoneNumber: String, verificationType: String = "login"): Result<Map<String, Any>> {
        return withContext(Dispatchers.IO) {
            try {
                val request = mapOf(
                    "phone_number" to phoneNumber,
                    "verification_type" to verificationType
                )
                
                val response = apiService.sendOtp(request)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val otpData = response.body()?.data
                    if (otpData != null) {
                        Result.success(otpData)
                    } else {
                        Result.failure(Exception("No OTP data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "Failed to send OTP"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error sending OTP")
                Result.failure(e)
            }
        }
    }
    
    suspend fun verifyOtp(phoneNumber: String, otpCode: String, sessionId: String? = null): Result<OtpResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = OtpRequest(
                    phone_number = phoneNumber,
                    otp_code = otpCode,
                    verification_type = "login",
                    session_id = sessionId
                )
                
                val response = apiService.verifyOtp(request)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val verificationData = response.body()?.data
                    if (verificationData != null) {
                        Result.success(verificationData)
                    } else {
                        Result.failure(Exception("No verification data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "OTP verification failed"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error verifying OTP")
                Result.failure(e)
            }
        }
    }
    
    /**
     * USSD Control
     */
    suspend fun executeUssd(userId: String, ussdCode: String, action: String): Result<UssdResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = UssdRequest(
                    user_id = userId,
                    ussd_code = ussdCode,
                    action = action,
                    parameters = emptyMap()
                )
                
                val response = apiService.executeUssd(request)
                if (response.isSuccessful && response.body()?.status == "success") {
                    val ussdData = response.body()?.data
                    if (ussdData != null) {
                        Result.success(ussdData)
                    } else {
                        Result.failure(Exception("No USSD data in response"))
                    }
                } else {
                    val errorMsg = response.body()?.message ?: "USSD execution failed"
                    Result.failure(Exception(errorMsg))
                }
            } catch (e: Exception) {
                Timber.e(e, "Error executing USSD")
                Result.failure(e)
            }
        }
    }
    
    /**
     * Helper methods
     */
    private fun updateLocalSubscriptionStatus(isActive: Boolean) {
        prefs.edit().putBoolean("subscription_active", isActive).apply()
        Timber.d("Local subscription status updated: $isActive")
    }
    
    fun getLocalSubscriptionStatus(): Boolean {
        return prefs.getBoolean("subscription_active", false)
    }
    
    private fun buildLearningReportMessage(reportData: Map<String, String>): String {
        val studentName = reportData["student_name"] ?: "Your child"
        val lessonsCompleted = reportData["lessons_completed"] ?: "0"
        val totalScore = reportData["total_score"] ?: "0"
        val week = reportData["week"] ?: "this week"
        
        return "📚 Porakhela Learning Report 📚\n\n" +
                "Student: $studentName\n" +
                "Period: $week\n" +
                "Lessons Completed: $lessonsCompleted\n" +
                "Total Score: $totalScore points\n\n" +
                "Keep up the great learning! 🌟\n\n" +
                "- Porakhela Team"
    }
}