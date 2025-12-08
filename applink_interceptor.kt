package com.porakhela.data.api

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.security.MessageDigest
import javax.inject.Inject

/**
 * Authentication Interceptor for Applink API
 * Adds API key, signature, and timestamp to all requests
 */
class ApplinkAuthInterceptor @Inject constructor() : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Get current timestamp
        val timestamp = System.currentTimeMillis().toString()
        
        // Generate signature (HMAC-SHA256 of timestamp + API_KEY + API_SECRET)
        val signature = generateSignature(timestamp)
        
        // Build new request with authentication headers
        val authenticatedRequest = originalRequest.newBuilder()
            .addHeader("X-API-Key", ApplinkConfig.API_KEY)
            .addHeader("X-Timestamp", timestamp)
            .addHeader("X-Signature", signature)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", "PoraKhela-Android/1.0")
            .build()
        
        Timber.d("Applink API Request: ${authenticatedRequest.method} ${authenticatedRequest.url}")
        
        return chain.proceed(authenticatedRequest)
    }
    
    /**
     * Generate HMAC-SHA256 signature
     * Format: SHA256(timestamp + API_KEY + API_SECRET)
     */
    private fun generateSignature(timestamp: String): String {
        val data = "$timestamp${ApplinkConfig.API_KEY}${ApplinkConfig.API_SECRET}"
        return sha256(data)
    }
    
    /**
     * SHA-256 hashing function
     */
    private fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

/**
 * Logging Interceptor for debugging
 * Logs request and response details
 */
class ApplinkLoggingInterceptor @Inject constructor() : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        
        Timber.d("→ ${request.method} ${request.url}")
        Timber.d("→ Headers: ${request.headers}")
        
        val response = chain.proceed(request)
        
        val duration = System.currentTimeMillis() - startTime
        val bodyString = response.peekBody(Long.MAX_VALUE).string()
        
        Timber.d("← ${response.code} ${request.url} (${duration}ms)")
        Timber.d("← Response: ${bodyString.take(500)}") // Log first 500 chars
        
        return response
    }
}

/**
 * Error Handling Interceptor
 * Handles common HTTP errors and retries
 */
class ApplinkErrorInterceptor @Inject constructor() : Interceptor {
    
    companion object {
        private const val MAX_RETRIES = 3
        private const val RETRY_DELAY_MS = 1000L
    }
    
    override fun intercept(chain: Interceptor.Chain): Response {
        var attempt = 0
        var response: Response? = null
        var lastException: Exception? = null
        
        while (attempt < MAX_RETRIES) {
            try {
                response = chain.proceed(chain.request())
                
                // If successful, return response
                if (response.isSuccessful) {
                    return response
                }
                
                // If client error (4xx), don't retry
                if (response.code in 400..499) {
                    Timber.w("Client error ${response.code}, not retrying")
                    return response
                }
                
                // If server error (5xx), retry
                if (response.code in 500..599) {
                    Timber.w("Server error ${response.code}, retrying (attempt ${attempt + 1})")
                    response.close()
                    attempt++
                    if (attempt < MAX_RETRIES) {
                        Thread.sleep(RETRY_DELAY_MS * attempt)
                    }
                    continue
                }
                
                return response
                
            } catch (e: Exception) {
                Timber.e(e, "Request failed (attempt ${attempt + 1})")
                lastException = e
                attempt++
                if (attempt < MAX_RETRIES) {
                    Thread.sleep(RETRY_DELAY_MS * attempt)
                }
            }
        }
        
        // If all retries failed, throw last exception or return last response
        lastException?.let { throw it }
        return response ?: throw IllegalStateException("No response received after $MAX_RETRIES attempts")
    }
}
