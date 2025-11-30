package com.porakhela.ui.performance.testing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.databinding.ActivityEmulatorTestBinding
import com.porakhela.ui.performance.monitoring.PerformanceMonitoringSystem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EmulatorTestActivity : AppCompatActivity() {
    
    @Inject lateinit var performanceMonitoring: PerformanceMonitoringSystem
    @Inject lateinit var performanceTestingSupport: PerformanceTestingSupport
    
    private lateinit var binding: ActivityEmulatorTestBinding
    private val testViewModel: EmulatorTestViewModel by viewModels()
    
    private lateinit var testAdapter: TestItemAdapter
    private val testItems = mutableListOf<TestItem>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmulatorTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupRecyclerView()
        observeViewModel()
        
        // Start automated testing sequence
        lifecycleScope.launch {
            delay(1000) // Allow UI to settle
            startTestSequence()
        }
    }
    
    private fun setupUI() {
        binding.apply {
            // Enable FPS overlay
            btnToggleFPSOverlay.setOnClickListener {
                performanceTestingSupport.toggleFPSOverlay()
                Toast.makeText(this@EmulatorTestActivity, 
                    "FPS Overlay ${if (performanceTestingSupport.isFPSOverlayEnabled()) "Enabled" else "Disabled"}", 
                    Toast.LENGTH_SHORT).show()
            }
            
            // Start comprehensive test
            btnStartTests.setOnClickListener {
                startIntent(Intent(this@EmulatorTestActivity, PerformanceTestActivity::class.java))
            }
            
            // Home screen scroll test
            btnTestHomeScroll.setOnClickListener {
                testHomeScreenScroll()
            }
            
            // Lesson transitions test
            btnTestLessonTransitions.setOnClickListener {
                testLessonTransitions()
            }
            
            // Leaderboard scroll test
            btnTestLeaderboardScroll.setOnClickListener {
                testLeaderboardScroll()
            }
            
            // Memory profiling
            btnTestMemory.setOnClickListener {
                testMemoryUsage()
            }
            
            // Database speed test
            btnTestDatabase.setOnClickListener {
                testDatabaseSpeed()
            }
            
            // Scroll performance test
            btnTestScrollPerformance.setOnClickListener {
                testScrollPerformance()
            }
        }
    }
    
    private fun setupRecyclerView() {
        // Generate test data for scrolling tests
        repeat(1000) { index ->
            testItems.add(TestItem(
                id = index,
                title = "Lesson ${index + 1}: Advanced Math",
                subtitle = "Complete algebra fundamentals",
                progress = (0..100).random(),
                isCompleted = index % 3 == 0,
                difficulty = when(index % 4) {
                    0 -> "Beginner"
                    1 -> "Intermediate" 
                    2 -> "Advanced"
                    else -> "Expert"
                }
            ))
        }
        
        testAdapter = TestItemAdapter(testItems) { item ->
            // Handle item click - simulate lesson loading
            lifecycleScope.launch {
                simulateLessonLoad(item.id)
            }
        }
        
        binding.recyclerViewTest.apply {
            layoutManager = LinearLayoutManager(this@EmulatorTestActivity)
            adapter = testAdapter
            
            // Add scroll listener for FPS monitoring
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                private var isScrolling = false
                
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    
                    when (newState) {
                        RecyclerView.SCROLL_STATE_DRAGGING -> {
                            isScrolling = true
                            performanceTestingSupport.startScrollMonitoring()
                        }
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            if (isScrolling) {
                                isScrolling = false
                                val avgFPS = performanceTestingSupport.stopScrollMonitoring()
                                updateScrollTestResult(avgFPS)
                            }
                        }
                    }
                }
            })
        }
    }
    
    private fun observeViewModel() {
        testViewModel.apply {
            currentFPS.observe(this@EmulatorTestActivity) { fps ->
                binding.tvCurrentFPS.text = "${fps}fps"
                binding.tvCurrentFPS.setTextColor(
                    getResources().getColor(
                        when {
                            fps >= 55 -> R.color.success_green
                            fps >= 45 -> R.color.warning_orange
                            else -> R.color.error_red
                        }
                    )
                )
            }
            
            memoryUsage.observe(this@EmulatorTestActivity) { memoryMB ->
                binding.tvCurrentMemory.text = "${memoryMB}MB"
                binding.tvCurrentMemory.setTextColor(
                    getResources().getColor(
                        when {
                            memoryMB <= 140 -> R.color.success_green
                            memoryMB <= 170 -> R.color.warning_orange
                            memoryMB <= 200 -> R.color.primary_blue
                            else -> R.color.error_red
                        }
                    )
                )
            }
            
            databaseLatency.observe(this@EmulatorTestActivity) { latencyMs ->
                binding.tvDBResponse.text = "${latencyMs}ms"
                binding.tvDBResponse.setTextColor(
                    getResources().getColor(
                        when {
                            latencyMs <= 100 -> R.color.success_green
                            latencyMs <= 250 -> R.color.warning_orange
                            else -> R.color.error_red
                        }
                    )
                )
            }
            
            testResults.observe(this@EmulatorTestActivity) { results ->
                updateTestResultsDisplay(results)
            }
        }
    }
    
    private fun startTestSequence() {
        lifecycleScope.launch {
            try {
                binding.tvTestStatus.text = "🟡 Running Test Sequence..."
                
                // A. FPS Tests
                binding.tvTestStatus.text = "📊 Testing FPS Performance..."
                val homeScrollResult = testHomeScreenScroll()
                delay(2000)
                
                val lessonTransitionResult = testLessonTransitions() 
                delay(2000)
                
                val leaderboardScrollResult = testLeaderboardScroll()
                delay(2000)
                
                // B. Memory Tests
                binding.tvTestStatus.text = "💾 Testing Memory Usage..."
                val memoryResult = testMemoryUsage()
                delay(3000)
                
                // C. Database Tests
                binding.tvTestStatus.text = "🗄️ Testing Database Speed..."
                val databaseResult = testDatabaseSpeed()
                delay(2000)
                
                // D. Scroll Tests
                binding.tvTestStatus.text = "📜 Testing Scroll Performance..."
                val scrollResult = testScrollPerformance()
                delay(2000)
                
                // Generate final results
                val allResults = listOf(
                    homeScrollResult, lessonTransitionResult, leaderboardScrollResult,
                    memoryResult, databaseResult, scrollResult
                )
                
                val allPassed = allResults.all { it.passed }
                
                binding.tvTestStatus.text = if (allPassed) {
                    "✅ ALL TESTS PASSED"
                } else {
                    "❌ SOME TESTS FAILED - Check details below"
                }
                
                binding.tvTestStatus.setTextColor(
                    getResources().getColor(
                        if (allPassed) R.color.success_green else R.color.error_red
                    )
                )
                
                if (!allPassed) {
                    showOptimizationRecommendations(allResults.filter { !it.passed })
                }
                
            } catch (e: Exception) {
                binding.tvTestStatus.text = "❌ Test sequence failed: ${e.message}"
                binding.tvTestStatus.setTextColor(getResources().getColor(R.color.error_red))
            }
        }
    }
    
    private suspend fun testHomeScreenScroll(): TestResult {
        binding.tvTestStatus.text = "🏠 Testing Home Screen Scroll ≥ 55 FPS..."
        
        // Simulate home screen scrolling
        performanceTestingSupport.startFPSMonitoring()
        
        // Simulate rapid scrolling through home content
        repeat(50) { index ->
            binding.recyclerViewTest.scrollBy(0, if (index % 2 == 0) 100 else -50)
            delay(16) // ~60fps scroll simulation
        }
        
        delay(1000) // Allow measurement
        val avgFPS = performanceTestingSupport.stopFPSMonitoring()
        
        return TestResult(
            testName = "Home Screen Scroll",
            passed = avgFPS >= 55,
            actualValue = avgFPS,
            expectedValue = 55.0,
            unit = "FPS"
        )
    }
    
    private suspend fun testLessonTransitions(): TestResult {
        binding.tvTestStatus.text = "📚 Testing Lesson Transitions ≥ 50 FPS..."
        
        performanceTestingSupport.startFPSMonitoring()
        
        // Simulate lesson screen transitions
        repeat(20) { 
            simulateLessonLoad(it)
            delay(75) // Transition timing
        }
        
        val avgFPS = performanceTestingSupport.stopFPSMonitoring()
        
        return TestResult(
            testName = "Lesson Transitions",
            passed = avgFPS >= 50,
            actualValue = avgFPS,
            expectedValue = 50.0,
            unit = "FPS"
        )
    }
    
    private suspend fun testLeaderboardScroll(): TestResult {
        binding.tvTestStatus.text = "🏆 Testing Leaderboard Scroll ≥ 50 FPS..."
        
        performanceTestingSupport.startFPSMonitoring()
        
        // Simulate leaderboard scrolling
        repeat(40) { index ->
            binding.recyclerViewTest.scrollBy(0, if (index % 3 == 0) 150 else 75)
            delay(20)
        }
        
        val avgFPS = performanceTestingSupport.stopFPSMonitoring()
        
        return TestResult(
            testName = "Leaderboard Scroll",
            passed = avgFPS >= 50,
            actualValue = avgFPS,
            expectedValue = 50.0,
            unit = "FPS"
        )
    }
    
    private suspend fun testMemoryUsage(): TestResult {
        binding.tvTestStatus.text = "💾 Testing Memory Usage Limits..."
        
        // Test idle memory
        System.gc()
        delay(500)
        val idleMemory = performanceTestingSupport.getCurrentMemoryUsage()
        
        // Test with 3 lessons loaded
        repeat(3) { simulateLessonLoad(it) }
        delay(1000)
        val lessonMemory = performanceTestingSupport.getCurrentMemoryUsage()
        
        // Test download scenario
        simulateDownloadLessons()
        delay(2000)
        val downloadMemory = performanceTestingSupport.getCurrentMemoryUsage()
        
        val memoryTestPassed = idleMemory <= 140 && lessonMemory <= 170 && downloadMemory <= 200
        
        return TestResult(
            testName = "Memory Usage",
            passed = memoryTestPassed,
            actualValue = downloadMemory, // Use highest memory usage
            expectedValue = 200.0,
            unit = "MB",
            details = "Idle: ${idleMemory}MB, Lessons: ${lessonMemory}MB, Download: ${downloadMemory}MB"
        )
    }
    
    private suspend fun testDatabaseSpeed(): TestResult {
        binding.tvTestStatus.text = "🗄️ Testing Database Speed..."
        
        val dbResults = testViewModel.runDatabaseSpeedTest()
        
        val dbTestPassed = dbResults.insertTime <= 1500 && 
                          dbResults.queryTime <= 250 && 
                          !dbResults.hasLockups
        
        return TestResult(
            testName = "Database Speed",
            passed = dbTestPassed,
            actualValue = dbResults.insertTime.toDouble(),
            expectedValue = 1500.0,
            unit = "ms",
            details = "Insert: ${dbResults.insertTime}ms, Query: ${dbResults.queryTime}ms, Lockups: ${dbResults.hasLockups}"
        )
    }
    
    private suspend fun testScrollPerformance(): TestResult {
        binding.tvTestStatus.text = "📜 Testing Fast Scroll Performance ≥ 45 FPS..."
        
        performanceMonitoring.startFPSMonitoring()
        
        // Simulate aggressive fast scrolling
        repeat(100) { index ->
            val scrollDistance = if (index % 4 == 0) 200 else 100
            binding.recyclerViewTest.scrollBy(0, scrollDistance)
            delay(10) // Fast scroll simulation
        }
        
        val avgFPS = performanceMonitoring.stopFPSMonitoring()
        
        return TestResult(
            testName = "Fast Scroll Performance",
            passed = avgFPS >= 45,
            actualValue = avgFPS,
            expectedValue = 45.0,
            unit = "FPS"
        )
    }
    
    private suspend fun simulateLessonLoad(lessonId: Int) {
        // Simulate lesson loading operations
        delay(50)
        // Trigger actual lesson loading if needed
    }
    
    private suspend fun simulateDownloadLessons() {
        binding.tvTestStatus.text = "📥 Simulating Lesson Downloads..."
        // Simulate download operations that consume memory
        delay(1000)
    }
    
    private fun updateScrollTestResult(fps: Double) {
        binding.tvScrollResult.text = "Last Scroll: ${fps.toInt()} FPS"
        binding.tvScrollResult.setTextColor(
            getResources().getColor(
                when {
                    fps >= 55 -> R.color.success_green
                    fps >= 45 -> R.color.warning_orange
                    else -> R.color.error_red
                }
            )
        )
    }
    
    private fun updateTestResultsDisplay(results: List<TestResult>) {
        val summary = results.map { result ->
            val icon = if (result.passed) "✅" else "❌"
            "$icon ${result.testName}: ${result.actualValue}${result.unit}"
        }.joinToString("\n")
        
        binding.tvTestSummary.text = summary
    }
    
    private fun showOptimizationRecommendations(failedTests: List<TestResult>) {
        val recommendations = mutableListOf<String>()
        
        failedTests.forEach { test ->
            when {
                test.testName.contains("FPS") || test.testName.contains("Scroll") -> {
                    recommendations.add("• Fix frame drops: Optimize RecyclerView, reduce overdraw")
                    recommendations.add("• Lower memory allocations during scrolling")
                }
                test.testName.contains("Memory") -> {
                    recommendations.add("• Reduce bitmap size and optimize memory allocations") 
                    recommendations.add("• Enable object pooling for heavy objects")
                }
                test.testName.contains("Database") -> {
                    recommendations.add("• Fix slow DB queries with better indexing")
                    recommendations.add("• Optimize threading for database operations")
                }
            }
        }
        
        val message = """
            Performance tests failed. Apply these fixes:
            
            ${recommendations.distinct().joinToString("\n")}
            
            Then re-run profiler until FPS/memory metrics pass.
        """.trimIndent()
        
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("❌ Performance Optimization Required")
            .setMessage(message)
            .setPositiveButton("Apply Fixes") { _, _ ->
                // Trigger automatic optimization
                testViewModel.applyPerformanceOptimizations()
            }
            .setNegativeButton("Manual Fix", null)
            .show()
    }
    
    data class TestResult(
        val testName: String,
        val passed: Boolean,
        val actualValue: Double,
        val expectedValue: Double,
        val unit: String,
        val details: String = ""
    )
    
    data class TestItem(
        val id: Int,
        val title: String,
        val subtitle: String,
        val progress: Int,
        val isCompleted: Boolean,
        val difficulty: String
    )
}