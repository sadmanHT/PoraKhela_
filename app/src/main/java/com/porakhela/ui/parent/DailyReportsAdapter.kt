package com.porakhela.ui.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.data.local.DailyLearningReport
import java.text.SimpleDateFormat
import java.util.*

class DailyReportsAdapter : ListAdapter<DailyLearningReport, DailyReportsAdapter.ReportViewHolder>(
    DailyReportsDiffCallback()
) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_report, parent, false)
        return ReportViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private val tvLessons: TextView = itemView.findViewById(R.id.tv_lessons)
        private val tvPoints: TextView = itemView.findViewById(R.id.tv_points)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        
        private val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        private val outputFormatter = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
        
        fun bind(report: DailyLearningReport) {
            // Format date
            try {
                val date = inputFormatter.parse(report.date)
                tvDate.text = if (date != null) outputFormatter.format(date) else report.date
            } catch (e: Exception) {
                tvDate.text = report.date
            }
            
            // Set lesson count
            tvLessons.text = "${report.lessonsCompleted} lesson${if (report.lessonsCompleted != 1) "s" else ""}"
            
            // Set points earned
            tvPoints.text = "+${report.totalPointsEarned} points"
            
            // Set time spent
            val hours = report.totalTimeMinutes / 60
            val minutes = report.totalTimeMinutes % 60
            tvTime.text = when {
                hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
                hours > 0 -> "${hours}h"
                else -> "${minutes}m"
            }
        }
    }
    
    class DailyReportsDiffCallback : DiffUtil.ItemCallback<DailyLearningReport>() {
        override fun areItemsTheSame(
            oldItem: DailyLearningReport,
            newItem: DailyLearningReport
        ): Boolean {
            return oldItem.date == newItem.date
        }
        
        override fun areContentsTheSame(
            oldItem: DailyLearningReport,
            newItem: DailyLearningReport
        ): Boolean {
            return oldItem == newItem
        }
    }
}