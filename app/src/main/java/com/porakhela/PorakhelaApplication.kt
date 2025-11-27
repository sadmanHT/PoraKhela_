package com.porakhela

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

/**
 * Main Application class for Porakhela
 * Sets up dependency injection, logging, and crash handling
 */
@HiltAndroidApp
class PorakhelaApplication : Application() {
    
    /**
     * Global exception handler for uncaught coroutine exceptions
     */
    val globalExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, "Uncaught coroutine exception")
        // In production, you might want to send this to crash analytics
        handleGlobalException(exception)
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Set up logging
        setupTimber()
        
        // Set up StrictMode for development
        setupStrictMode()
        
        // Set up global exception handling
        setupGlobalExceptionHandling()
        
        Timber.i("Porakhela Application started successfully")
    }
    
    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            // Plant debug tree for development
            Timber.plant(Timber.DebugTree())
            Timber.d("Debug logging enabled")
        } else {
            // Plant release tree for production (could be crashlytics tree)
            Timber.plant(ReleaseTree())
            Timber.i("Release logging enabled")
        }
    }
    
    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .detectCustomSlowCalls()
                    .penaltyLog()
                    .build()
            )
            
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .detectLeakedRegistrationObjects()
                    .penaltyLog()
                    .build()
            )
            
            Timber.d("StrictMode enabled for development")
        }
    }
    
    private fun setupGlobalExceptionHandling() {
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        
        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            Timber.e(exception, "Uncaught exception in thread ${thread.name}")
            handleGlobalException(exception)
            
            // Call the default handler to ensure app terminates properly
            defaultHandler?.uncaughtException(thread, exception)
        }
    }
    
    private fun handleGlobalException(exception: Throwable) {
        // Log to analytics in production
        // For now, just ensure it's logged
        Timber.e(exception, "Global exception handled")
        
        // In production, you might want to:
        // - Send to crash reporting service (Crashlytics, Bugsnag, etc.)
        // - Store locally for later upload
        // - Show user-friendly error message
    }
    
    /**
     * Release tree that filters out verbose and debug logs in production
     */
    private class ReleaseTree : Timber.Tree() {
        override fun isLoggable(tag: String?, priority: Int): Boolean {
            // Only log INFO, WARN, and ERROR in production
            return priority >= android.util.Log.INFO
        }
        
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            // In production, you might want to send logs to a remote service
            // For now, just use regular logging
            when (priority) {
                android.util.Log.INFO -> android.util.Log.i(tag, message, t)
                android.util.Log.WARN -> android.util.Log.w(tag, message, t)
                android.util.Log.ERROR -> android.util.Log.e(tag, message, t)
            }
        }
    }
}