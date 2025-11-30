package com.porakhela.ui.performance

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ADVANCED PERFORMANCE HARDENING - Step 4/6: THREADING & COROUTINE OPTIMIZATION
 * 
 * Optimized for Bangladesh low-end devices (৳800-৳2000 price range)
 * Target: Eliminate main thread blocking and maximize concurrent processing
 * 
 * Key Features:
 * - Custom dispatchers optimized for low-end device CPU cores
 * - Intelligent task prioritization and queue management
 * - Memory-efficient coroutine scope management
 * - Background processing with main thread protection
 * - Smart batching and debouncing for frequent operations
 * 
 * Threading Strategy:
 * - IO operations: Dedicated thread pool for database/network
 * - CPU operations: Limited thread pool matching device cores
 * - Main thread: Only UI updates and quick operations
 * - Background: Long-running tasks with proper lifecycle management
 */

@Singleton
class OptimizedCoroutineManager @Inject constructor() {

    companion object {
        // Thread pool sizes optimized for low-end devices (typically 4 cores or less)
        private const val IO_THREAD_POOL_SIZE = 4  // Conservative for low-end devices
        private const val CPU_THREAD_POOL_SIZE = 2 // Half of typical cores for stability
        private const val BACKGROUND_THREAD_POOL_SIZE = 2 // Long-running tasks
        
        // Operation timeouts to prevent ANRs
        private const val QUICK_OPERATION_TIMEOUT = 1000L // 1 second
        private const val STANDARD_OPERATION_TIMEOUT = 5000L // 5 seconds
        private const val BACKGROUND_OPERATION_TIMEOUT = 30000L // 30 seconds
    }

    // Optimized dispatchers for different types of work
    val optimizedIODispatcher = Executors.newFixedThreadPool(IO_THREAD_POOL_SIZE) { runnable ->
        Thread(runnable, "PorakhelaIO-${System.currentTimeMillis()}").apply {
            priority = Thread.NORM_PRIORITY
            isDaemon = true
        }
    }.asCoroutineDispatcher()

    private val optimizedCPUDispatcher = Executors.newFixedThreadPool(CPU_THREAD_POOL_SIZE) { runnable ->
        Thread(runnable, "PorakhelaCPU-${System.currentTimeMillis()}").apply {
            priority = Thread.NORM_PRIORITY - 1 // Slightly lower priority
            isDaemon = true
        }
    }.asCoroutineDispatcher()

    private val backgroundDispatcher = Executors.newFixedThreadPool(BACKGROUND_THREAD_POOL_SIZE) { runnable ->
        Thread(runnable, "PorakhelaBG-${System.currentTimeMillis()}").apply {
            priority = Thread.MIN_PRIORITY + 2 // Lower priority for background tasks
            isDaemon = true
        }
    }.asCoroutineDispatcher()

    // Supervisor scopes for different operation types
    private val ioScope = CoroutineScope(optimizedIODispatcher + SupervisorJob())
    private val cpuScope = CoroutineScope(optimizedCPUDispatcher + SupervisorJob())
    val backgroundScope = CoroutineScope(backgroundDispatcher + SupervisorJob()) // Make public for BackgroundTaskScheduler

    // Synchronization for thread-safe operations
    private val mutex = Mutex()

    /**
     * Execute database operations with optimized IO dispatcher
     * Ensures database calls never block the main thread
     */
    suspend fun <T> executeIOOperation(
        operation: suspend () -> T,
        timeout: Long = STANDARD_OPERATION_TIMEOUT
    ): T? = withContext(optimizedIODispatcher) {
        try {
            withTimeout(timeout) {
                operation()
            }
        } catch (e: TimeoutCancellationException) {
            Timber.w("IO operation timed out after ${timeout}ms")
            null
        } catch (e: Exception) {
            Timber.e(e, "Error in IO operation")
            null
        }
    }

    /**
     * Execute CPU-intensive operations with optimized CPU dispatcher
     * Ideal for data processing, transformations, and calculations
     */
    suspend fun <T> executeCPUOperation(
        operation: suspend () -> T,
        timeout: Long = STANDARD_OPERATION_TIMEOUT
    ): T? = withContext(optimizedCPUDispatcher) {
        try {
            withTimeout(timeout) {
                operation()
            }
        } catch (e: TimeoutCancellationException) {
            Timber.w("CPU operation timed out after ${timeout}ms")
            null
        } catch (e: Exception) {
            Timber.e(e, "Error in CPU operation")
            null
        }
    }

    /**
     * Execute background operations with low priority
     * Perfect for analytics, caching, and maintenance tasks
     */
    fun executeBackgroundOperation(
        operation: suspend () -> Unit,
        timeout: Long = BACKGROUND_OPERATION_TIMEOUT
    ) {
        backgroundScope.launch {
            try {
                withTimeout(timeout) {
                    operation()
                }
            } catch (e: TimeoutCancellationException) {
                Timber.w("Background operation timed out after ${timeout}ms")
            } catch (e: Exception) {
                Timber.e(e, "Error in background operation")
            }
        }
    }

    /**
     * Execute quick operations that should complete fast
     * Used for immediate response requirements
     */
    suspend fun <T> executeQuickOperation(
        operation: suspend () -> T
    ): T? = withContext(optimizedIODispatcher) {
        try {
            withTimeout(QUICK_OPERATION_TIMEOUT) {
                operation()
            }
        } catch (e: TimeoutCancellationException) {
            Timber.w("Quick operation timed out after ${QUICK_OPERATION_TIMEOUT}ms")
            null
        } catch (e: Exception) {
            Timber.e(e, "Error in quick operation")
            null
        }
    }

    /**
     * Thread-safe operation execution with mutex protection
     * Ensures critical operations don't conflict
     */
    suspend fun <T> executeThreadSafeOperation(
        operation: suspend () -> T
    ): T? = withContext(optimizedIODispatcher) {
        try {
            mutex.withLock {
                operation()
            }
        } catch (e: Exception) {
            Timber.e(e, "Error in thread-safe operation")
            null
        }
    }

    /**
     * Cleanup resources when no longer needed
     */
    fun cleanup() {
        ioScope.cancel()
        cpuScope.cancel()
        backgroundScope.cancel()
        
        optimizedIODispatcher.close()
        optimizedCPUDispatcher.close()
        backgroundDispatcher.close()
        
        Timber.d("Optimized coroutine manager cleaned up")
    }
}

/**
 * Debounced operation manager to prevent excessive operations
 * Critical for search, validation, and real-time updates
 */
@Singleton
class DebouncedOperationManager @Inject constructor(
    private val coroutineManager: OptimizedCoroutineManager
) {

    companion object {
        private const val DEFAULT_DEBOUNCE_MS = 300L
        private const val SEARCH_DEBOUNCE_MS = 500L
        private const val VALIDATION_DEBOUNCE_MS = 1000L
    }

    private val activeJobs = mutableMapOf<String, Job>()

    /**
     * Debounced search operation to prevent excessive database queries
     */
    fun debouncedSearch(
        key: String,
        delayMs: Long = SEARCH_DEBOUNCE_MS,
        operation: suspend () -> Unit
    ) {
        // Cancel previous job for this key
        activeJobs[key]?.cancel()
        
        // Start new debounced operation
        activeJobs[key] = CoroutineScope(Dispatchers.Main).launch {
            delay(delayMs)
            coroutineManager.executeIOOperation { operation() }
        }
    }

    /**
     * Debounced validation to prevent excessive validation calls
     */
    fun debouncedValidation(
        key: String,
        delayMs: Long = VALIDATION_DEBOUNCE_MS,
        operation: suspend () -> Unit
    ) {
        activeJobs[key]?.cancel()
        
        activeJobs[key] = CoroutineScope(Dispatchers.Main).launch {
            delay(delayMs)
            coroutineManager.executeCPUOperation { operation() }
        }
    }

    /**
     * Cancel debounced operation
     */
    fun cancelOperation(key: String) {
        activeJobs[key]?.cancel()
        activeJobs.remove(key)
    }

    /**
     * Cancel all active debounced operations
     */
    fun cancelAllOperations() {
        activeJobs.values.forEach { it.cancel() }
        activeJobs.clear()
        Timber.d("Cancelled all debounced operations")
    }
}

/**
 * Flow optimization utilities for reactive programming
 * Optimized for minimal memory usage and smooth UI updates
 */
object FlowOptimizer {

    /**
     * Create optimized flow with background processing
     */
    fun <T> createOptimizedFlow(
        coroutineManager: OptimizedCoroutineManager,
        producer: suspend () -> T
    ): Flow<T> = flow {
        coroutineManager.executeIOOperation { 
            emit(producer())
        }
    }.flowOn(Dispatchers.IO)

    /**
     * Optimize flow for UI consumption with debouncing
     */
    fun <T> Flow<T>.optimizeForUI(
        debounceMs: Long = 300L
    ): Flow<T> = this
        .debounce(debounceMs)
        .distinctUntilChanged()
        .flowOn(Dispatchers.Default)

    /**
     * Batch flow emissions to reduce UI update frequency
     */
    fun <T> Flow<T>.batchEmissions(
        timeWindowMs: Long = 100L,
        maxBatchSize: Int = 10
    ): Flow<List<T>> = this
        .buffer(maxBatchSize)
        .chunked(timeWindowMs)

    /**
     * Safe flow collection with error handling
     */
    fun <T> Flow<T>.collectSafely(
        scope: CoroutineScope,
        onError: (Throwable) -> Unit = { Timber.e(it, "Flow collection error") },
        onEach: suspend (T) -> Unit
    ) {
        scope.launch {
            catch { exception ->
                onError(exception)
            }.collect { value ->
                onEach(value)
            }
        }
    }

    /**
     * Memory-efficient chunked flow processing
     */
    private fun <T> Flow<T>.chunked(timeWindowMs: Long): Flow<List<T>> = flow {
        val buffer = mutableListOf<T>()
        var lastEmission = System.currentTimeMillis()
        
        collect { value ->
            buffer.add(value)
            val now = System.currentTimeMillis()
            
            if (now - lastEmission >= timeWindowMs || buffer.size >= 20) {
                emit(buffer.toList())
                buffer.clear()
                lastEmission = now
            }
        }
        
        if (buffer.isNotEmpty()) {
            emit(buffer.toList())
        }
    }
}

/**
 * Background task scheduler for periodic operations
 * Optimized for battery and performance efficiency
 */
@Singleton 
class BackgroundTaskScheduler @Inject constructor(
    private val coroutineManager: OptimizedCoroutineManager
) {

    private val scheduledTasks = mutableMapOf<String, Job>()

    /**
     * Schedule periodic task with intelligent timing
     */
    fun schedulePeriodicTask(
        taskId: String,
        intervalMs: Long,
        task: suspend () -> Unit
    ) {
        // Cancel existing task with same ID
        scheduledTasks[taskId]?.cancel()
        
        scheduledTasks[taskId] = coroutineManager.backgroundScope.launch {
            while (isActive) {
                try {
                    task()
                } catch (e: Exception) {
                    Timber.e(e, "Error in scheduled task: $taskId")
                }
                delay(intervalMs)
            }
        }
        
        Timber.d("Scheduled background task: $taskId with ${intervalMs}ms interval")
    }

    /**
     * Cancel scheduled task
     */
    fun cancelTask(taskId: String) {
        scheduledTasks[taskId]?.cancel()
        scheduledTasks.remove(taskId)
        Timber.d("Cancelled scheduled task: $taskId")
    }

    /**
     * Cancel all scheduled tasks
     */
    fun cancelAllTasks() {
        scheduledTasks.values.forEach { it.cancel() }
        scheduledTasks.clear()
        Timber.d("Cancelled all scheduled tasks")
    }
}