package com.porakhela.data.api;

/**
 * Mock interceptor for Applink API responses
 * Simulates realistic API responses with error handling and delays
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/porakhela/data/api/MockApplinkInterceptor;", "Lokhttp3/Interceptor;", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "json", "Lkotlinx/serialization/json/Json;", "createErrorResponse", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "errorCode", "", "createGenericSuccessResponse", "", "createOtpSendResponse", "createOtpVerifyResponse", "createRewardsHistoryResponse", "createRewardsRedeemResponse", "createSmsSendResponse", "createSmsStatusResponse", "createSubscriptionStartResponse", "createSubscriptionStatusResponse", "createUssdExecuteResponse", "createUssdSessionResponse", "generateId", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "app_release"})
public final class MockApplinkInterceptor implements okhttp3.Interceptor {
    private static boolean simulateError = false;
    private static int errorCode = 500;
    private static boolean simulateTimeout = false;
    private static long responseDelay = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.serialization.json.Json json = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.api.MockApplinkInterceptor.Companion Companion = null;
    
    public MockApplinkInterceptor() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull()
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
    
    private final okhttp3.Response createErrorResponse(okhttp3.Request request, int errorCode) {
        return null;
    }
    
    private final java.lang.String createSubscriptionStartResponse() {
        return null;
    }
    
    private final java.lang.String createSubscriptionStatusResponse() {
        return null;
    }
    
    private final java.lang.String createSmsSendResponse() {
        return null;
    }
    
    private final java.lang.String createSmsStatusResponse() {
        return null;
    }
    
    private final java.lang.String createRewardsRedeemResponse() {
        return null;
    }
    
    private final java.lang.String createRewardsHistoryResponse() {
        return null;
    }
    
    private final java.lang.String createOtpSendResponse() {
        return null;
    }
    
    private final java.lang.String createOtpVerifyResponse() {
        return null;
    }
    
    private final java.lang.String createUssdExecuteResponse() {
        return null;
    }
    
    private final java.lang.String createUssdSessionResponse() {
        return null;
    }
    
    private final java.lang.String createGenericSuccessResponse() {
        return null;
    }
    
    private final java.lang.String generateId() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014\u00a8\u0006\u0018"}, d2 = {"Lcom/porakhela/data/api/MockApplinkInterceptor$Companion;", "", "()V", "errorCode", "", "getErrorCode", "()I", "setErrorCode", "(I)V", "responseDelay", "", "getResponseDelay", "()J", "setResponseDelay", "(J)V", "simulateError", "", "getSimulateError", "()Z", "setSimulateError", "(Z)V", "simulateTimeout", "getSimulateTimeout", "setSimulateTimeout", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean getSimulateError() {
            return false;
        }
        
        public final void setSimulateError(boolean p0) {
        }
        
        public final int getErrorCode() {
            return 0;
        }
        
        public final void setErrorCode(int p0) {
        }
        
        public final boolean getSimulateTimeout() {
            return false;
        }
        
        public final void setSimulateTimeout(boolean p0) {
        }
        
        public final long getResponseDelay() {
            return 0L;
        }
        
        public final void setResponseDelay(long p0) {
        }
    }
}