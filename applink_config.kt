package com.porakhela.data.api

/**
 * Applink API Configuration
 * Replace with your actual Applink API credentials
 */
object ApplinkConfig {
    
    // TODO: Replace with your actual Applink API credentials
    const val BASE_URL = "https://api.applink.com.bd/v1/" // Replace with actual Applink base URL
    const val API_KEY = "your_api_key_here" // Replace with your API key
    const val API_SECRET = "your_api_secret_here" // Replace with your API secret
    
    // Timeout configurations
    const val CONNECT_TIMEOUT = 30L // seconds
    const val READ_TIMEOUT = 30L // seconds
    const val WRITE_TIMEOUT = 30L // seconds
    
    // API Endpoints
    object Endpoints {
        // OTP Endpoints
        const val OTP_SEND = "otp/send"
        const val OTP_VERIFY = "otp/verify"
        
        // SMS Endpoints
        const val SMS_SEND = "sms/send"
        const val SMS_STATUS = "sms/status/{messageId}"
        
        // Subscription Endpoints (CAAS - Content as a Service)
        const val SUBSCRIPTION_START = "subscription/start"
        const val SUBSCRIPTION_STATUS = "subscription/status/{subscriptionId}"
        const val SUBSCRIPTION_CANCEL = "subscription/cancel"
        const val SUBSCRIPTION_RENEW = "subscription/renew"
        
        // USSD Endpoints (if needed)
        const val USSD_EXECUTE = "ussd/execute"
        const val USSD_SESSION = "ussd/session/{sessionId}"
    }
    
    // Subscription Plans
    object SubscriptionPlans {
        const val DAILY = "daily"
        const val WEEKLY = "weekly"
        const val MONTHLY = "monthly"
        
        // Prices in BDT (Bangladesh Taka)
        const val DAILY_PRICE = 5.0
        const val WEEKLY_PRICE = 30.0
        const val MONTHLY_PRICE = 100.0
    }
    
    // SMS Templates
    object SmsTemplates {
        fun learningReport(childName: String, points: Int, streak: Int): String {
            return "PoraKhela Update: $childName earned $points points today! " +
                    "Current streak: $streak days. Keep learning! 🎓"
        }
        
        fun subscriptionConfirmation(plan: String, validUntil: String): String {
            return "PoraKhela subscription activated! Plan: $plan, Valid until: $validUntil. " +
                    "Thank you for supporting your child's education! 📚"
        }
        
        fun lowPointsAlert(childName: String, points: Int): String {
            return "Alert: $childName has $points Porapoints remaining. " +
                    "Encourage them to complete more lessons! 🌟"
        }
    }
}
