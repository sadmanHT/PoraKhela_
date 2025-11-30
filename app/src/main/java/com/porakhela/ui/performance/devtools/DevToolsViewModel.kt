package com.porakhela.ui.performance.devtools

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.ui.performance.monitoring.PerformanceMonitoringSystem
import com.porakhela.ui.performance.monitoring.PerformanceMetrics
import com.porakhela.ui.performance.monitoring.QueryEvent
import com.porakhela.ui.performance.ObjectPoolManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * DEVTOOLS VIEWMODEL - Performance Data Management
 * 
 * Manages performance monitoring data and provides analytics
 * Target: Real-time insights with historical trend analysis
 * 
 * Features:
 * - Real-time performance metrics streaming
 * - Historical data aggregation and analysis
 * - Performance bottleneck detection algorithms
 * - Data export for detailed analysis
 * - Memory-efficient data handling with object pooling
 */

@HiltViewModel
class DevToolsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val performanceMonitoringSystem: PerformanceMonitoringSystem,
    private val objectPoolManager: ObjectPoolManager
) : ViewModel() {
    
    private val stringBuilder = objectPoolManager.acquireStringBuilder()
    
    // Real-time performance metrics
    private val _performanceMetrics = MutableStateFlow<PerformanceMetrics?>(null)
    val performanceMetrics = _performanceMetrics.asStateFlow()
    
    // Slow queries monitoring
    private val _slowQueries = MutableStateFlow<List<QueryEvent>>(emptyList())
    val slowQueries = _slowQueries.asStateFlow()
    
    // Performance analysis
    private val _performanceAnalysis = MutableStateFlow<PerformanceAnalysis?>(null)
    val performanceAnalysis = _performanceAnalysis.asStateFlow()
    
    // Monitoring state
    private val _isMonitoring = MutableStateFlow(false)
    val isMonitoring = _isMonitoring.asStateFlow()
    
    // Historical data
    private val performanceHistory = mutableListOf<PerformanceMetrics>()
    
    override fun onCleared() {
        super.onCleared()
        objectPoolManager.releaseStringBuilder(stringBuilder)
    }
    
    /**
     * Start performance monitoring and data collection
     */
    fun startMonitoring() {
        _isMonitoring.value = true
        
        viewModelScope.launch {
            while (_isMonitoring.value) {
                try {
                    // Collect current metrics
                    val metrics = performanceMonitoringSystem.getCurrentMetrics()
                    _performanceMetrics.value = metrics
                    
                    // Add to history
                    performanceHistory.add(metrics)
                    
                    // Keep history manageable (last 10 minutes)
                    if (performanceHistory.size > 600) { // 10 minutes at 1-second intervals
                        performanceHistory.removeFirstOrNull()
                    }
                    
                    // Update slow queries
                    updateSlowQueries()
                    
                    // Analyze performance trends
                    analyzePerformance()
                    
                    kotlinx.coroutines.delay(1000) // Update every second
                } catch (e: Exception) {
                    Timber.e(e, \"Error collecting performance metrics\")
                }
            }
        }\n        \n        Timber.d(\"Performance monitoring started\")\n    }\n    \n    /**\n     * Stop performance monitoring\n     */\n    fun stopMonitoring() {\n        _isMonitoring.value = false\n        Timber.d(\"Performance monitoring stopped\")\n    }\n    \n    /**\n     * Clear all collected performance data\n     */\n    fun clearPerformanceData() {\n        performanceHistory.clear()\n        _performanceMetrics.value = null\n        _slowQueries.value = emptyList()\n        _performanceAnalysis.value = null\n        Timber.d(\"Performance data cleared\")\n    }\n    \n    /**\n     * Export performance data to file for analysis\n     */\n    fun exportPerformanceData() {\n        viewModelScope.launch {\n            try {\n                val exportFile = createExportFile()\n                writePerformanceDataToFile(exportFile)\n                Timber.d(\"Performance data exported to: ${exportFile.absolutePath}\")\n            } catch (e: Exception) {\n                Timber.e(e, \"Failed to export performance data\")\n            }\n        }\n    }\n    \n    /**\n     * Get performance summary statistics\n     */\n    fun getPerformanceSummary(): PerformanceSummary {\n        if (performanceHistory.isEmpty()) {\n            return PerformanceSummary(\n                averageFps = 0f,\n                minFps = 0f,\n                maxFps = 0f,\n                averageMemoryUsage = 0f,\n                maxMemoryUsage = 0f,\n                totalQueries = 0L,\n                slowQueriesCount = 0L,\n                totalFrameDrops = 0L\n            )\n        }\n        \n        val fpsList = performanceHistory.map { it.fps }\n        val memoryList = performanceHistory.map { it.memoryUsageMB }\n        \n        return PerformanceSummary(\n            averageFps = fpsList.average().toFloat(),\n            minFps = fpsList.minOrNull() ?: 0f,\n            maxFps = fpsList.maxOrNull() ?: 0f,\n            averageMemoryUsage = memoryList.average().toFloat(),\n            maxMemoryUsage = memoryList.maxOrNull() ?: 0f,\n            totalQueries = performanceHistory.lastOrNull()?.databaseQueryCount ?: 0L,\n            slowQueriesCount = _slowQueries.value.size.toLong(),\n            totalFrameDrops = performanceHistory.lastOrNull()?.frameDropCount ?: 0L\n        )\n    }\n    \n    /**\n     * Detect performance bottlenecks\n     */\n    fun detectBottlenecks(): List<PerformanceBottleneck> {\n        val bottlenecks = mutableListOf<PerformanceBottleneck>()\n        \n        if (performanceHistory.isEmpty()) return bottlenecks\n        \n        val recentMetrics = performanceHistory.takeLast(60) // Last minute\n        \n        // Check FPS issues\n        val averageFps = recentMetrics.map { it.fps }.average()\n        if (averageFps < 55f) {\n            bottlenecks.add(\n                PerformanceBottleneck(\n                    type = \"Low FPS\",\n                    severity = if (averageFps < 30f) \"HIGH\" else \"MEDIUM\",\n                    description = \"Average FPS is ${String.format(\"%.1f\", averageFps)}, below optimal 60 FPS\",\n                    recommendation = \"Check for heavy UI operations, optimize RecyclerView adapters, reduce overdraw\"\n                )\n            )\n        }\n        \n        // Check memory issues\n        val maxMemoryUsage = recentMetrics.maxOfOrNull { it.memoryUsageMB } ?: 0f\n        val totalMemory = recentMetrics.firstOrNull()?.totalMemoryMB ?: 1f\n        val memoryPercent = maxMemoryUsage / totalMemory\n        \n        if (memoryPercent > 0.85f) {\n            bottlenecks.add(\n                PerformanceBottleneck(\n                    type = \"High Memory Usage\",\n                    severity = if (memoryPercent > 0.95f) \"HIGH\" else \"MEDIUM\",\n                    description = \"Memory usage is ${String.format(\"%.1f\", memoryPercent * 100)}% of available memory\",\n                    recommendation = \"Check for memory leaks, optimize image loading, use object pooling\"\n                )\n            )\n        }\n        \n        // Check database performance\n        val averageQueryDuration = recentMetrics.map { it.averageQueryDuration }.average()\n        if (averageQueryDuration > 30f) {\n            bottlenecks.add(\n                PerformanceBottleneck(\n                    type = \"Slow Database Queries\",\n                    severity = if (averageQueryDuration > 100f) \"HIGH\" else \"MEDIUM\",\n                    description = \"Average query duration is ${String.format(\"%.1f\", averageQueryDuration)}ms\",\n                    recommendation = \"Add database indices, optimize query structure, use batch operations\"\n                )\n            )\n        }\n        \n        // Check frame drops\n        val recentFrameDrops = recentMetrics.lastOrNull()?.frameDropCount ?: 0L\n        if (recentFrameDrops > 10) {\n            bottlenecks.add(\n                PerformanceBottleneck(\n                    type = \"Frame Drops\",\n                    severity = if (recentFrameDrops > 50) \"HIGH\" else \"MEDIUM\",\n                    description = \"$recentFrameDrops frame drops detected\",\n                    recommendation = \"Reduce UI complexity, optimize animations, check for blocking operations on main thread\"\n                )\n            )\n        }\n        \n        return bottlenecks\n    }\n    \n    // PRIVATE HELPER METHODS\n    \n    private fun updateSlowQueries() {\n        // In a real implementation, this would get slow queries from the database monitor\n        // For now, we'll simulate some slow queries\n        val simulatedSlowQueries = listOf(\n            QueryEvent(\n                timestamp = System.currentTimeMillis(),\n                query = \"SELECT * FROM lessons WHERE user_id = ? ORDER BY created_at DESC\",\n                durationMs = 85L,\n                isSlow = true\n            ),\n            QueryEvent(\n                timestamp = System.currentTimeMillis() - 30000,\n                query = \"UPDATE user_progress SET completed = 1 WHERE lesson_id IN (...)\",\n                durationMs = 120L,\n                isSlow = true\n            )\n        )\n        \n        _slowQueries.value = simulatedSlowQueries\n    }\n    \n    private fun analyzePerformance() {\n        val analysis = PerformanceAnalysis(\n            summary = getPerformanceSummary(),\n            bottlenecks = detectBottlenecks(),\n            trends = calculatePerformanceTrends(),\n            recommendations = generateRecommendations()\n        )\n        \n        _performanceAnalysis.value = analysis\n    }\n    \n    private fun calculatePerformanceTrends(): PerformanceTrends {\n        if (performanceHistory.size < 2) {\n            return PerformanceTrends(\n                fpstrend = \"STABLE\",\n                memoryTrend = \"STABLE\",\n                queryTrend = \"STABLE\"\n            )\n        }\n        \n        val recentHistory = performanceHistory.takeLast(60) // Last minute\n        val olderHistory = performanceHistory.dropLast(60).takeLast(60) // Previous minute\n        \n        if (olderHistory.isEmpty()) {\n            return PerformanceTrends(\n                fpstrend = \"STABLE\",\n                memoryTrend = \"STABLE\",\n                queryTrend = \"STABLE\"\n            )\n        }\n        \n        val recentAvgFps = recentHistory.map { it.fps }.average()\n        val olderAvgFps = olderHistory.map { it.fps }.average()\n        \n        val recentAvgMemory = recentHistory.map { it.memoryUsageMB }.average()\n        val olderAvgMemory = olderHistory.map { it.memoryUsageMB }.average()\n        \n        val recentAvgQuery = recentHistory.map { it.averageQueryDuration }.average()\n        val olderAvgQuery = olderHistory.map { it.averageQueryDuration }.average()\n        \n        return PerformanceTrends(\n            fpstrend = when {\n                recentAvgFps > olderAvgFps + 2 -> \"IMPROVING\"\n                recentAvgFps < olderAvgFps - 2 -> \"DECLINING\"\n                else -> \"STABLE\"\n            },\n            memoryTrend = when {\n                recentAvgMemory > olderAvgMemory + 5 -> \"INCREASING\"\n                recentAvgMemory < olderAvgMemory - 5 -> \"DECREASING\"\n                else -> \"STABLE\"\n            },\n            queryTrend = when {\n                recentAvgQuery > olderAvgQuery + 10 -> \"SLOWING\"\n                recentAvgQuery < olderAvgQuery - 10 -> \"IMPROVING\"\n                else -> \"STABLE\"\n            }\n        )\n    }\n    \n    private fun generateRecommendations(): List<String> {\n        val recommendations = mutableListOf<String>()\n        \n        val bottlenecks = detectBottlenecks()\n        \n        bottlenecks.forEach { bottleneck ->\n            recommendations.add(\"${bottleneck.type}: ${bottleneck.recommendation}\")\n        }\n        \n        if (recommendations.isEmpty()) {\n            recommendations.add(\"Performance is optimal! Continue monitoring for any changes.\")\n        }\n        \n        return recommendations\n    }\n    \n    private fun createExportFile(): File {\n        val timestamp = SimpleDateFormat(\"yyyyMMdd_HHmmss\", Locale.getDefault()).format(Date())\n        val fileName = \"performance_data_$timestamp.csv\"\n        return File(context.getExternalFilesDir(null), fileName)\n    }\n    \n    private suspend fun writePerformanceDataToFile(file: File) {\n        stringBuilder.clear()\n        \n        // CSV Header\n        stringBuilder.append(\"Timestamp,FPS,Memory(MB),Total Memory(MB),Query Count,Avg Query Duration(ms),Frame Drops\\n\")\n        \n        // Data rows\n        performanceHistory.forEach { metrics ->\n            stringBuilder.append(\"${System.currentTimeMillis()},\")\n            stringBuilder.append(\"${metrics.fps},\")\n            stringBuilder.append(\"${metrics.memoryUsageMB},\")\n            stringBuilder.append(\"${metrics.totalMemoryMB},\")\n            stringBuilder.append(\"${metrics.databaseQueryCount},\")\n            stringBuilder.append(\"${metrics.averageQueryDuration},\")\n            stringBuilder.append(\"${metrics.frameDropCount}\\n\")\n        }\n        \n        // Write to file\n        FileWriter(file).use { writer ->\n            writer.write(stringBuilder.toString())\n        }\n    }\n}\n\n// DATA CLASSES FOR PERFORMANCE ANALYSIS\n\ndata class PerformanceSummary(\n    val averageFps: Float,\n    val minFps: Float,\n    val maxFps: Float,\n    val averageMemoryUsage: Float,\n    val maxMemoryUsage: Float,\n    val totalQueries: Long,\n    val slowQueriesCount: Long,\n    val totalFrameDrops: Long\n)\n\ndata class PerformanceBottleneck(\n    val type: String,\n    val severity: String, // HIGH, MEDIUM, LOW\n    val description: String,\n    val recommendation: String\n)\n\ndata class PerformanceTrends(\n    val fpstrend: String, // IMPROVING, DECLINING, STABLE\n    val memoryTrend: String, // INCREASING, DECREASING, STABLE\n    val queryTrend: String // IMPROVING, SLOWING, STABLE\n)\n\ndata class PerformanceAnalysis(\n    val summary: PerformanceSummary,\n    val bottlenecks: List<PerformanceBottleneck>,\n    val trends: PerformanceTrends,\n    val recommendations: List<String>\n)