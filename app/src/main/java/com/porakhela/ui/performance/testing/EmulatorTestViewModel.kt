package com.porakhela.ui.performance.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.ui.performance.DatabaseOptimizer
import com.porakhela.ui.performance.monitoring.PerformanceMonitoringSystem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class EmulatorTestViewModel @Inject constructor(
    private val performanceMonitoring: PerformanceMonitoringSystem,
    private val performanceTestingSupport: PerformanceTestingSupport,
    private val databaseTestingSupport: DatabaseTestingSupport
) : ViewModel() {
    
    private val _currentFPS = MutableLiveData<Int>()
    val currentFPS: LiveData<Int> = _currentFPS
    
    private val _memoryUsage = MutableLiveData<Double>()
    val memoryUsage: LiveData<Double> = _memoryUsage
    
    private val _databaseLatency = MutableLiveData<Long>()
    val databaseLatency: LiveData<Long> = _databaseLatency
    
    private val _testResults = MutableLiveData<List<TestResult>>()
    val testResults: LiveData<List<TestResult>> = _testResults
    
    private val _isTestingInProgress = MutableLiveData<Boolean>()
    val isTestingInProgress: LiveData<Boolean> = _isTestingInProgress
    
    init {
        startRealtimeMonitoring()
    }
    
    private fun startRealtimeMonitoring() {
        viewModelScope.launch {
            while (true) {
                try {
                    // Update FPS
                    val fps = performanceTestingSupport.getCurrentFPS()
                    _currentFPS.postValue(fps.toInt())
                    
                    // Update memory usage
                    val memoryMB = performanceTestingSupport.getCurrentMemoryUsage()
                    _memoryUsage.postValue(memoryMB)
                    
                    // Update database latency
                    val latency = measureDatabaseLatency()
                    _databaseLatency.postValue(latency)
                    
                    kotlinx.coroutines.delay(1000) // Update every second
                    
                } catch (e: Exception) {
                    // Handle monitoring errors gracefully
                    kotlinx.coroutines.delay(2000)
                }
            }
        }
    }
    
    private suspend fun measureDatabaseLatency(): Long = withContext(Dispatchers.IO) {
        measureTimeMillis {
            databaseTestingSupport.performQuickQuery()
        }
    }
    
    fun runDatabaseSpeedTest(): DatabaseTestResult {
        var insertTime = 0L
        var queryTime = 0L
        var hasLockups = false
        
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Test 1: 100 Room inserts
                insertTime = measureTimeMillis {
                    databaseTestingSupport.performBulkInserts(100)
                }
                
                // Test 2: Query 100 rows
                queryTime = measureTimeMillis {
                    databaseTestingSupport.performBulkQuery(100)
                }
                
                // Test 3: 50 concurrent writes
                val concurrentWriteTime = measureTimeMillis {
                    databaseTestingSupport.performConcurrentWrites(50)
                }
                
                // Consider lockup if operations take too long
                hasLockups = insertTime > 5000 || queryTime > 1000 || concurrentWriteTime > 10000
                
            } catch (e: Exception) {
                hasLockups = true
            }
        }
        
        return DatabaseTestResult(
            insertTime = insertTime,
            queryTime = queryTime,
            hasLockups = hasLockups
        )
    }
    
    fun applyPerformanceOptimizations() {
        viewModelScope.launch {
            _isTestingInProgress.value = true
            
            try {
                // Apply database optimizations
                withContext(Dispatchers.IO) {
                    databaseTestingSupport.optimizeDatabase()
                    databaseTestingSupport.rebuildIndices()
                }
                
                // Clear memory caches
                performanceTestingSupport.clearCaches()
                
                // Optimize coroutine management
                performanceTestingSupport.optimizeThreadPools()
                
                // Force garbage collection
                System.gc()
                
                kotlinx.coroutines.delay(2000) // Allow optimizations to take effect
                
            } finally {
                _isTestingInProgress.value = false
            }
        }
    }
    
    data class TestResult(
        val testName: String,
        val passed: Boolean,
        val actualValue: Double,
        val expectedValue: Double,
        val unit: String,
        val details: String = ""
    )
    
    data class DatabaseTestResult(
        val insertTime: Long,
        val queryTime: Long,
        val hasLockups: Boolean
    )
}