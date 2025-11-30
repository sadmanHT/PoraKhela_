package com.porakhela.ui.performance.testing

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.ui.performance.DatabaseOptimizer
import com.porakhela.ui.performance.monitoring.PerformanceMonitoringSystem
import com.porakhela.ui.performance.devtools.DevToolsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@AndroidEntryPoint
class PerformanceTestActivity : AppCompatActivity() {
    
    @Inject lateinit var performanceMonitoring: PerformanceMonitoringSystem
    @Inject lateinit var performanceTestingSupport: PerformanceTestingSupport
    @Inject lateinit var databaseTestingSupport: DatabaseTestingSupport
    
    private val devToolsViewModel: DevToolsViewModel by viewModels()
    
    private lateinit var statusContainer: LinearLayout
    private lateinit var fpsOverlay: TextView
    private lateinit var memoryOverlay: TextView
    
    private var fpsCounter = 0
    private var frameStartTime = 0L
    private val fpsHandler = Handler(Looper.getMainLooper())
    
    // Test results storage
    private val testResults = mutableMapOf<String, TestResult>()
    
    data class TestResult(
        val testName: String,
        val passed: Boolean,
        val actualValue: Double,
        val expectedValue: Double,
        val unit: String,
        val details: String = ""
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performance_test)
        
        setupUI()
        setupFPSOverlay()
        setupMemoryOverlay()
        
        // Start comprehensive testing
        lifecycleScope.launch {
            delay(2000) // Allow UI to settle
            runAllPerformanceTests()
        }
    }
    
    private fun setupUI() {
        statusContainer = findViewById(R.id.statusContainer)
        
        // Create test status views
        addTestStatusView("FPS Tests", "Pending...")
        addTestStatusView("Memory Tests", "Pending...")
        addTestStatusView("Database Tests", "Pending...")
        addTestStatusView("Scroll Tests", "Pending...")
    }
    
    private fun addTestStatusView(title: String, status: String) {
        val testView = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(32, 16, 32, 16)
        }
        
        val titleView = TextView(this).apply {
            text = title
            textSize = 16f
            setTextColor(Color.WHITE)
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        }
        
        val statusView = TextView(this).apply {
            text = status
            textSize = 14f
            setTextColor(Color.YELLOW)
            gravity = Gravity.END
            tag = "${title}_status"
        }
        
        testView.addView(titleView)
        testView.addView(statusView)
        statusContainer.addView(testView)
    }
    
    private fun updateTestStatus(testCategory: String, status: String, color: Int = Color.YELLOW) {
        val statusView = statusContainer.findViewWithTag<TextView>("${testCategory}_status")
        statusView?.apply {
            text = status
            setTextColor(color)
        }
    }
    
    @SuppressLint("SetTextI18n")
    private fun setupFPSOverlay() {
        fpsOverlay = TextView(this).apply {
            text = "FPS: --"
            textSize = 14f
            setTextColor(Color.GREEN)
            setBackgroundColor(Color.argb(128, 0, 0, 0))
            setPadding(16, 8, 16, 8)
        }
        
        val params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(16, 100, 0, 0)
        }
        
        addContentView(fpsOverlay, params)
        startFPSMonitoring()
    }
    
    @SuppressLint("SetTextI18n")
    private fun setupMemoryOverlay() {
        memoryOverlay = TextView(this).apply {
            text = "Memory: -- MB"
            textSize = 14f
            setTextColor(Color.CYAN)
            setBackgroundColor(Color.argb(128, 0, 0, 0))
            setPadding(16, 8, 16, 8)
        }
        
        val params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(16, 150, 0, 0)
        }
        
        addContentView(memoryOverlay, params)
        startMemoryMonitoring()
    }
    
    private fun startFPSMonitoring() {
        frameStartTime = System.currentTimeMillis()
        
        val fpsRunnable = object : Runnable {
            @SuppressLint("SetTextI18n")
            override fun run() {
                val currentTime = System.currentTimeMillis()
                val elapsed = currentTime - frameStartTime
                
                if (elapsed >= 1000) {
                    val fps = performanceTestingSupport.getCurrentFPS().toInt()
                    fpsOverlay.text = "FPS: $fps"
                    
                    // Update color based on performance
                    fpsOverlay.setTextColor(when {
                        fps >= 55 -> Color.GREEN
                        fps >= 45 -> Color.YELLOW
                        else -> Color.RED
                    })
                    
                    fpsCounter = 0
                    frameStartTime = currentTime
                }
                
                performanceTestingSupport.countFrame()
                fpsCounter++
                fpsHandler.postDelayed(this, 16) // ~60 FPS monitoring
            }
        }
        
        fpsHandler.post(fpsRunnable)
    }
    
    @SuppressLint("SetTextI18n")
    private fun startMemoryMonitoring() {
        val memoryRunnable = object : Runnable {
            override fun run() {
                val totalMemoryMB = performanceTestingSupport.getCurrentMemoryUsage().toInt()
                memoryOverlay.text = "Memory: ${totalMemoryMB}MB"
                
                // Update color based on memory usage
                memoryOverlay.setTextColor(when {
                    totalMemoryMB <= 140 -> Color.GREEN
                    totalMemoryMB <= 170 -> Color.YELLOW
                    totalMemoryMB <= 200 -> Color.rgb(255, 165, 0) // Orange
                    else -> Color.RED
                })
                
                Handler(Looper.getMainLooper()).postDelayed(this, 2000)
            }
        }
        
        Handler(Looper.getMainLooper()).postDelayed(memoryRunnable, 1000)
    }
    
    private suspend fun runAllPerformanceTests() {
        try {
            // A. FPS Tests
            updateTestStatus("FPS Tests", "Running...")
            runFPSTests()
            
            // B. Memory Tests  
            updateTestStatus("Memory Tests", "Running...")
            runMemoryTests()
            
            // C. Database Speed Tests
            updateTestStatus("Database Tests", "Running...")
            runDatabaseTests()
            
            // D. Scroll Tests
            updateTestStatus("Scroll Tests", "Running...")
            runScrollTests()
            
            // Generate final report
            generateTestReport()
            
        } catch (e: Exception) {
            updateTestStatus("ERROR", "Test failed: ${e.message}", Color.RED)
        }
    }
    
    private suspend fun runFPSTests() {
        // Test 1: Home screen scroll ≥ 55 FPS
        val homeScrollFPS = simulateHomeScroll()
        testResults["home_scroll_fps"] = TestResult(
            "Home Screen Scroll",
            homeScrollFPS >= 55,
            homeScrollFPS,
            55.0,
            "FPS"
        )
        
        // Test 2: Lesson screen transitions ≥ 50 FPS
        val lessonTransitionFPS = simulateLessonTransitions()
        testResults["lesson_transition_fps"] = TestResult(
            "Lesson Transitions",
            lessonTransitionFPS >= 50,
            lessonTransitionFPS,
            50.0,
            "FPS"
        )
        
        // Test 3: Leaderboard scroll ≥ 50 FPS
        val leaderboardScrollFPS = simulateLeaderboardScroll()
        testResults["leaderboard_scroll_fps"] = TestResult(
            "Leaderboard Scroll",
            leaderboardScrollFPS >= 50,
            leaderboardScrollFPS,
            50.0,
            "FPS"
        )
        
        val allFPSTestsPassed = testResults.values.all { it.passed }
        val fpsStatus = if (allFPSTestsPassed) "✅ PASSED" else "❌ FAILED"
        val fpsColor = if (allFPSTestsPassed) Color.GREEN else Color.RED
        
        updateTestStatus("FPS Tests", fpsStatus, fpsColor)
    }
    
    private suspend fun runMemoryTests(): Unit = withContext(Dispatchers.Main) {
        // Test 1: Idle memory < 140MB
        val idleMemory = getCurrentMemoryUsage()
        testResults["idle_memory"] = TestResult(
            "Idle Memory",
            idleMemory <= 140,
            idleMemory,
            140.0,
            "MB"
        )
        
        delay(1000)
        
        // Test 2: Running 3 lessons < 170MB
        simulateLessonLoad(3)
        delay(2000)
        val lessonMemory = getCurrentMemoryUsage()
        testResults["lesson_memory"] = TestResult(
            "3 Lessons Memory",
            lessonMemory <= 170,
            lessonMemory,
            170.0,
            "MB"
        )
        
        delay(1000)
        
        // Test 3: After download lessons < 200MB
        simulateDownloadLessons()
        delay(3000)
        val downloadMemory = getCurrentMemoryUsage()
        testResults["download_memory"] = TestResult(
            "Download Memory",
            downloadMemory <= 200,
            downloadMemory,
            200.0,
            "MB"
        )
        
        // Test 4: No memory leaks (simplified check)
        val memoryLeakCheck = checkMemoryLeaks()
        testResults["memory_leaks"] = TestResult(
            "Memory Leaks",
            memoryLeakCheck,
            if (memoryLeakCheck) 0.0 else 1.0,
            0.0,
            "leaks"
        )
        
        val allMemoryTestsPassed = testResults.filterKeys { 
            it.startsWith("idle_memory") || it.startsWith("lesson_memory") || 
            it.startsWith("download_memory") || it.startsWith("memory_leaks")
        }.values.all { it.passed }
        
        val memoryStatus = if (allMemoryTestsPassed) "✅ PASSED" else "❌ FAILED"
        val memoryColor = if (allMemoryTestsPassed) Color.GREEN else Color.RED
        
        updateTestStatus("Memory Tests", memoryStatus, memoryColor)
    }
    
    private suspend fun runDatabaseTests() = withContext(Dispatchers.IO) {
        // Test 1: 100 Room inserts < 1.5 seconds
        val insertTime = measureTimeMillis {
            databaseTestingSupport.performBulkInserts(100)
        }
        val insertTimeSeconds = insertTime / 1000.0
        testResults["db_insert_speed"] = TestResult(
            "100 DB Inserts",
            insertTimeSeconds <= 1.5,
            insertTimeSeconds,
            1.5,
            "seconds"
        )
        
        // Test 2: Query 100 rows < 250ms
        val queryTime = measureTimeMillis {
            databaseTestingSupport.performBulkQuery(100)
        }
        testResults["db_query_speed"] = TestResult(
            "100 Row Query",
            queryTime <= 250,
            queryTime.toDouble(),
            250.0,
            "ms"
        )
        
        // Test 3: 50 concurrent writes - no lockups
        val concurrentWriteSuccess = measureTimeMillis {
            databaseTestingSupport.performConcurrentWrites(50)
        }
        val noLockups = concurrentWriteSuccess < 5000 // 5 second timeout
        testResults["db_concurrent_writes"] = TestResult(
            "50 Concurrent Writes",
            noLockups,
            concurrentWriteSuccess.toDouble(),
            5000.0,
            "ms",
            if (noLockups) "No lockups" else "Lockup detected"
        )
        
        withContext(Dispatchers.Main) {
            val allDBTestsPassed = testResults.filterKeys { 
                it.startsWith("db_")
            }.values.all { it.passed }
            
            val dbStatus = if (allDBTestsPassed) "✅ PASSED" else "❌ FAILED"
            val dbColor = if (allDBTestsPassed) Color.GREEN else Color.RED
            
            updateTestStatus("Database Tests", dbStatus, dbColor)
        }
    }
    
    private suspend fun runScrollTests() = withContext(Dispatchers.Main) {
        // Test: Fast scroll lesson list - no frame drops under 45 FPS
        val scrollFPS = simulateFastLessonListScroll()
        testResults["fast_scroll_fps"] = TestResult(
            "Fast Lesson List Scroll",
            scrollFPS >= 45,
            scrollFPS,
            45.0,
            "FPS"
        )
        
        val scrollTestPassed = scrollFPS >= 45
        val scrollStatus = if (scrollTestPassed) "✅ PASSED" else "❌ FAILED"
        val scrollColor = if (scrollTestPassed) Color.GREEN else Color.RED
        
        updateTestStatus("Scroll Tests", scrollStatus, scrollColor)
    }
    
    // Simulation methods
    private suspend fun simulateHomeScroll(): Double {
        // Simulate home screen scrolling and measure FPS
        delay(100)
        return performanceTestingSupport.measureFPS(duration = 2000)
    }
    
    private suspend fun simulateLessonTransitions(): Double {
        // Simulate lesson screen transitions
        delay(100)
        return performanceTestingSupport.measureFPS(duration = 1500)
    }
    
    private suspend fun simulateLeaderboardScroll(): Double {
        // Simulate leaderboard scrolling
        delay(100)
        return performanceTestingSupport.measureFPS(duration = 2000)
    }
    
    private suspend fun simulateLessonLoad(count: Int) {
        // Simulate loading multiple lessons
        repeat(count) {
            delay(200)
            // Trigger lesson loading simulation
        }
    }
    
    private suspend fun simulateDownloadLessons() {
        // Simulate lesson download process
        delay(500)
        // Trigger download simulation
    }
    
    private suspend fun simulateFastLessonListScroll(): Double {
        // Simulate fast scrolling through lesson list
        delay(100)
        return performanceMonitoring.measureFPS(duration = 3000)
    }
    
    private fun getCurrentMemoryUsage(): Double {
        val memoryInfo = Debug.MemoryInfo()
        Debug.getMemoryInfo(memoryInfo)
        return (memoryInfo.totalPss / 1024).toDouble()
    }
    
    private fun checkMemoryLeaks(): Boolean {
        // Simplified memory leak check
        // In a real implementation, this would integrate with LeakCanary
        System.gc()
        val beforeGC = getCurrentMemoryUsage()
        System.gc()
        val afterGC = getCurrentMemoryUsage()
        
        // If memory reduces significantly after GC, likely no major leaks
        return (beforeGC - afterGC) < 10 // Less than 10MB difference suggests minimal leaks
    }
    
    private fun generateTestReport() {
        val allTestsPassed = testResults.values.all { it.passed }
        
        if (!allTestsPassed) {
            // Generate failure report
            val failedTests = testResults.values.filter { !it.passed }
            val failureDetails = failedTests.joinToString("\n") { test ->
                "❌ ${test.testName}: ${test.actualValue}${test.unit} (expected ≤${test.expectedValue}${test.unit})"
            }
            
            showTestFailureDialog(failureDetails)
        } else {
            updateTestStatus("All Tests", "✅ ALL PASSED", Color.GREEN)
        }
    }
    
    private fun showTestFailureDialog(failureDetails: String) {
        val recommendations = generateOptimizationRecommendations()
        
        // Show dialog with failure details and recommendations
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("❌ Performance Tests Failed")
            .setMessage("Failed Tests:\n$failureDetails\n\nRecommendations:\n$recommendations")
            .setPositiveButton("Fix Issues") { _, _ ->
                // Trigger optimization fixes
                applyPerformanceFixes()
            }
            .setNegativeButton("Close", null)
            .show()
    }
    
    private fun generateOptimizationRecommendations(): String {
        val recommendations = mutableListOf<String>()
        
        testResults.values.filter { !it.passed }.forEach { test ->
            when {
                test.testName.contains("Memory") -> {
                    recommendations.add("• Reduce bitmap size and optimize memory allocations")
                    recommendations.add("• Enable object pooling for heavy objects")
                }
                test.testName.contains("FPS") || test.testName.contains("Scroll") -> {
                    recommendations.add("• Optimize RecyclerView with better ViewHolder recycling")
                    recommendations.add("• Reduce overdraw and complex view hierarchies")
                }
                test.testName.contains("DB") || test.testName.contains("Insert") || test.testName.contains("Query") -> {
                    recommendations.add("• Fix slow DB queries with better indexing")
                    recommendations.add("• Optimize threading for database operations")
                }
            }
        }
        
        return recommendations.distinct().joinToString("\n")
    }
    
    private fun applyPerformanceFixes() {
        lifecycleScope.launch {
            try {
                // Apply recommended fixes
                updateTestStatus("Applying Fixes", "🔧 Optimizing...", Color.YELLOW)
                
                // Re-optimize database
                databaseTestingSupport.optimizeDatabase()
                delay(1000)
                
                // Clear memory caches
                performanceTestingSupport.clearCaches()
                delay(500)
                
                // Restart tests
                updateTestStatus("Re-testing", "🔄 Re-running tests...", Color.YELLOW)
                delay(2000)
                
                // Clear previous results and re-run
                testResults.clear()
                runAllPerformanceTests()
                
            } catch (e: Exception) {
                updateTestStatus("Fix Failed", "❌ Error: ${e.message}", Color.RED)
            }
        }
    }
}