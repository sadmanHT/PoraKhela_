package com.porakhela.ui.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.data.repository.ChildProgress
import com.porakhela.data.tracking.StreakManager
import com.porakhela.ui.streak.StreakViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ParentDashboardFragment : Fragment() {
    
    private val viewModel: ParentDashboardViewModel by viewModels()
    private val streakViewModel: StreakViewModel by viewModels()
    private lateinit var reportsAdapter: DailyReportsAdapter
    
    @Inject
    lateinit var streakManager: StreakManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_dashboard, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViews(view)
        observeViewModel()
        viewModel.refreshData()
    }
    
    private fun setupViews(view: View) {
        // Setup logout button
        view.findViewById<ImageButton>(R.id.btn_logout).setOnClickListener {
            viewModel.logout()
            findNavController().navigateUp()
        }
        
        // Setup daily limit seekbar
        val seekbarDailyLimit = view.findViewById<SeekBar>(R.id.seekbar_daily_limit)
        val tvDailyLimit = view.findViewById<TextView>(R.id.tv_daily_limit)
        
        seekbarDailyLimit.max = 105 // 15 to 120 minutes
        seekbarDailyLimit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val minutes = progress + 15 // Minimum 15 minutes
                tvDailyLimit.text = "$minutes min"
                if (fromUser) {
                    viewModel.updateDailyLimit(minutes)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // Setup content lock switch
        view.findViewById<Switch>(R.id.switch_content_lock).setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateContentLock(isChecked)
        }
        
        // Setup approval required switch
        view.findViewById<Switch>(R.id.switch_approval_required).setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateApprovalRequired(isChecked)
        }
        
        // Setup RecyclerView for daily reports
        val rvDailyReports = view.findViewById<RecyclerView>(R.id.rv_daily_reports)
        reportsAdapter = DailyReportsAdapter()
        rvDailyReports.layoutManager = LinearLayoutManager(requireContext())
        rvDailyReports.adapter = reportsAdapter
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                updateUI(state)
            }
        }
    }
    
    private fun updateUI(state: ParentDashboardUiState) {
        val view = view ?: return
        
        // Update child progress
        state.childProgress?.let { progress ->
            updateChildProgress(view, progress)
        }
        
        // Update screen time controls
        updateScreenTimeControls(view, state)
        
        // Update learning reports
        updateLearningReports(view, state)
        
        // Update redemption approval
        view.findViewById<Switch>(R.id.switch_approval_required).isChecked = state.approvalRequired
        view.findViewById<Switch>(R.id.switch_content_lock).isChecked = state.contentLockEnabled
        
        // Handle errors
        if (state.showError) {
            Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }
    }
    
    private fun updateChildProgress(view: View, progress: ChildProgress) {
        view.findViewById<TextView>(R.id.tv_child_name).text = 
            if (progress.childName.isNotBlank()) progress.childName else "Child"
        
        view.findViewById<TextView>(R.id.tv_total_points).text = 
            "${progress.totalPorapoints} Porapoints"
        
        view.findViewById<TextView>(R.id.tv_daily_streak).text = 
            "${progress.dailyStreak} day streak"
        
        view.findViewById<TextView>(R.id.tv_lessons_completed).text = 
            "${progress.lessonsCompleted} lessons completed"
        
        val lastLessonText = if (progress.lastLessonTime != null) {
            val formatter = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
            "Last lesson: ${formatter.format(Date(progress.lastLessonTime))}"
        } else {
            "No lessons completed yet"
        }
        view.findViewById<TextView>(R.id.tv_last_lesson).text = lastLessonText
    }
    
    private fun updateScreenTimeControls(view: View, state: ParentDashboardUiState) {
        val seekbarDailyLimit = view.findViewById<SeekBar>(R.id.seekbar_daily_limit)
        val tvDailyLimit = view.findViewById<TextView>(R.id.tv_daily_limit)
        val tvTodayUsage = view.findViewById<TextView>(R.id.tv_today_usage)
        
        // Update daily limit slider (15-120 minutes range)
        seekbarDailyLimit.progress = state.dailyLimit - 15
        tvDailyLimit.text = "${state.dailyLimit} min"
        
        // Update today's usage
        tvTodayUsage.text = "${state.todayScreenTime} / ${state.dailyLimit} min"
        
        // Change color if over limit
        val usageColor = if (state.todayScreenTime > state.dailyLimit) {
            androidx.core.content.ContextCompat.getColor(requireContext(), R.color.error_color)
        } else {
            androidx.core.content.ContextCompat.getColor(requireContext(), R.color.text_primary)
        }
        tvTodayUsage.setTextColor(usageColor)
    }
    
    private fun updateLearningReports(view: View, state: ParentDashboardUiState) {
        val rvDailyReports = view.findViewById<RecyclerView>(R.id.rv_daily_reports)
        val tvNoReports = view.findViewById<TextView>(R.id.tv_no_reports)
        
        if (state.dailyReports.isEmpty()) {
            rvDailyReports.visibility = View.GONE
            tvNoReports.visibility = View.VISIBLE
        } else {
            rvDailyReports.visibility = View.VISIBLE
            tvNoReports.visibility = View.GONE
            reportsAdapter.submitList(state.dailyReports)
        }
    }
}