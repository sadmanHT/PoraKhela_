package com.porakhela.core.utils;

/**
 * Utility class for network-related operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lcom/porakhela/core/utils/NetworkUtils;", "", "()V", "isNetworkAvailable", "", "context", "Landroid/content/Context;", "isWifiConnected", "app_debug"})
public final class NetworkUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.core.utils.NetworkUtils INSTANCE = null;
    
    private NetworkUtils() {
        super();
    }
    
    /**
     * Check if device has active internet connection
     * @param context Application context
     * @return true if connected to internet, false otherwise
     */
    public final boolean isNetworkAvailable(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Check if device is connected to WiFi
     */
    public final boolean isWifiConnected(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}