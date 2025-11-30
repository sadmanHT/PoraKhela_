package com.porakhela.ui.performance.devtools

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.ui.performance.monitoring.PerformanceMonitoringSystem
import com.porakhela.ui.performance.monitoring.QueryEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ADVANCED PERFORMANCE DEVTOOLS - Real-time Analysis Interface
 * 
 * Comprehensive performance debugging for Bangladesh low-end devices
 * Target: Detailed performance insights for optimization decisions
 * 
 * Features:
 * - Real-time FPS monitoring with frame drop analysis
 * - Memory usage graphs with leak detection
 * - Database query profiler with optimization hints
 * - Performance bottleneck identification
 * - Historical performance trends
 * - Export capabilities for further analysis
 * 
 * Usage: Debug builds only, minimal production impact
 */

@AndroidEntryPoint
class DevToolsActivity : AppCompatActivity() {
    
    @Inject
    lateinit var performanceMonitoringSystem: PerformanceMonitoringSystem
    
    private val devToolsViewModel: DevToolsViewModel by viewModels()
    
    // UI Components
    private lateinit var fpsText: TextView
    private lateinit var memoryText: TextView
    private lateinit var databaseText: TextView
    private lateinit var frameDropsText: TextView
    private lateinit var slowQueriesRecyclerView: RecyclerView
    private lateinit var startMonitoringButton: Button
    private lateinit var stopMonitoringButton: Button
    private lateinit var clearDataButton: Button
    private lateinit var exportDataButton: Button
    
    private lateinit var slowQueriesAdapter: SlowQueriesAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_tools)
        
        setupViews()
        setupRecyclerView()
        setupClickListeners()
        observePerformanceData()
        
        // Start monitoring automatically
        performanceMonitoringSystem.startMonitoring(this, enableOverlay = true)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        performanceMonitoringSystem.stopMonitoring()
    }
    
    private fun setupViews() {\n        fpsText = findViewById(R.id.fpsText)\n        memoryText = findViewById(R.id.memoryText)\n        databaseText = findViewById(R.id.databaseText)\n        frameDropsText = findViewById(R.id.frameDropsText)\n        slowQueriesRecyclerView = findViewById(R.id.slowQueriesRecyclerView)\n        startMonitoringButton = findViewById(R.id.startMonitoringButton)\n        stopMonitoringButton = findViewById(R.id.stopMonitoringButton)\n        clearDataButton = findViewById(R.id.clearDataButton)\n        exportDataButton = findViewById(R.id.exportDataButton)\n    }\n    \n    private fun setupRecyclerView() {\n        slowQueriesAdapter = SlowQueriesAdapter()\n        slowQueriesRecyclerView.apply {\n            layoutManager = LinearLayoutManager(this@DevToolsActivity)\n            adapter = slowQueriesAdapter\n        }\n    }\n    \n    private fun setupClickListeners() {\n        startMonitoringButton.setOnClickListener {\n            performanceMonitoringSystem.startMonitoring(this, enableOverlay = true)\n            devToolsViewModel.startMonitoring()\n            updateButtonStates(isMonitoring = true)\n        }\n        \n        stopMonitoringButton.setOnClickListener {\n            performanceMonitoringSystem.stopMonitoring()\n            devToolsViewModel.stopMonitoring()\n            updateButtonStates(isMonitoring = false)\n        }\n        \n        clearDataButton.setOnClickListener {\n            devToolsViewModel.clearPerformanceData()\n        }\n        \n        exportDataButton.setOnClickListener {\n            devToolsViewModel.exportPerformanceData()\n        }\n    }\n    \n    private fun observePerformanceData() {\n        lifecycleScope.launch {\n            devToolsViewModel.performanceMetrics.collect { metrics ->\n                updatePerformanceDisplay(metrics)\n            }\n        }\n        \n        lifecycleScope.launch {\n            devToolsViewModel.slowQueries.collect { queries ->\n                slowQueriesAdapter.updateQueries(queries)\n            }\n        }\n    }\n    \n    private fun updatePerformanceDisplay(metrics: com.porakhela.ui.performance.monitoring.PerformanceMetrics?) {\n        if (metrics == null) return\n        \n        fpsText.text = \"FPS: ${\"\".format(\"%.1f\", metrics.fps)}\"\n        memoryText.text = \"Memory: ${\"\".format(\"%.1f\", metrics.memoryUsageMB)}MB / ${\"\".format(\"%.1f\", metrics.totalMemoryMB)}MB\"\n        databaseText.text = \"Queries: ${metrics.databaseQueryCount} (avg: ${\"\".format(\"%.1f\", metrics.averageQueryDuration)}ms)\"\n        frameDropsText.text = \"Frame Drops: ${metrics.frameDropCount}\"\n        \n        // Color code FPS based on performance\n        val fpsColor = when {\n            metrics.fps >= 55f -> android.graphics.Color.GREEN\n            metrics.fps >= 30f -> android.graphics.Color.parseColor(\"#FFA500\") // Orange\n            else -> android.graphics.Color.RED\n        }\n        fpsText.setTextColor(fpsColor)\n        \n        // Color code memory based on usage\n        val memoryPercent = metrics.memoryUsageMB / metrics.totalMemoryMB\n        val memoryColor = when {\n            memoryPercent < 0.7f -> android.graphics.Color.GREEN\n            memoryPercent < 0.85f -> android.graphics.Color.parseColor(\"#FFA500\") // Orange\n            else -> android.graphics.Color.RED\n        }\n        memoryText.setTextColor(memoryColor)\n    }\n    \n    private fun updateButtonStates(isMonitoring: Boolean) {\n        startMonitoringButton.isEnabled = !isMonitoring\n        stopMonitoringButton.isEnabled = isMonitoring\n    }\n}\n\n/**\n * Adapter for displaying slow database queries\n */\nclass SlowQueriesAdapter : RecyclerView.Adapter<SlowQueriesAdapter.QueryViewHolder>() {\n    \n    private var queries = listOf<QueryEvent>()\n    \n    fun updateQueries(newQueries: List<QueryEvent>) {\n        queries = newQueries\n        notifyDataSetChanged()\n    }\n    \n    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): QueryViewHolder {\n        val view = android.view.LayoutInflater.from(parent.context)\n            .inflate(R.layout.item_slow_query, parent, false)\n        return QueryViewHolder(view)\n    }\n    \n    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {\n        holder.bind(queries[position])\n    }\n    \n    override fun getItemCount(): Int = queries.size\n    \n    class QueryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {\n        private val queryText: TextView = itemView.findViewById(R.id.queryText)\n        private val durationText: TextView = itemView.findViewById(R.id.durationText)\n        private val timestampText: TextView = itemView.findViewById(R.id.timestampText)\n        \n        fun bind(queryEvent: QueryEvent) {\n            queryText.text = queryEvent.query\n            durationText.text = \"${queryEvent.durationMs}ms\"\n            timestampText.text = java.text.SimpleDateFormat(\"HH:mm:ss\", java.util.Locale.getDefault())\n                .format(java.util.Date(queryEvent.timestamp))\n            \n            // Color code duration\n            val durationColor = when {\n                queryEvent.durationMs < 50L -> android.graphics.Color.GREEN\n                queryEvent.durationMs < 100L -> android.graphics.Color.parseColor(\"#FFA500\") // Orange\n                else -> android.graphics.Color.RED\n            }\n            durationText.setTextColor(durationColor)\n        }\n    }\n}