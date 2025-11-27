package com.porakhela.ui.subject

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.data.models.SubjectCategory
import com.porakhela.utils.HapticUtils

/**
 * RecyclerView adapter for displaying subject categories
 * Handles category display with animations and click interactions
 */
class CategoryAdapter(
    private val onCategoryClick: (SubjectCategory) -> Unit
) : ListAdapter<SubjectCategory, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_card, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryCard: LinearLayout = itemView.findViewById(R.id.category_card)
        private val tvCategoryIcon: TextView = itemView.findViewById(R.id.tv_category_icon)
        private val tvCategoryName: TextView = itemView.findViewById(R.id.tv_category_name)
        private val tvCategoryDescription: TextView = itemView.findViewById(R.id.tv_category_description)
        private val tvLessonCount: TextView = itemView.findViewById(R.id.tv_lesson_count)

        fun bind(category: SubjectCategory) {
            tvCategoryIcon.text = category.iconEmoji
            tvCategoryName.text = category.name
            tvCategoryDescription.text = category.description
            tvLessonCount.text = "${category.lessons.size} lessons"
            
            // Set card background color
            try {
                val color = Color.parseColor(category.colorHex)
                categoryCard.setBackgroundColor(color)
            } catch (e: Exception) {
                // Fallback to default color if parsing fails
                categoryCard.setBackgroundResource(R.drawable.rounded_category_card)
            }
            
            // Handle locked state
            if (category.isLocked) {
                categoryCard.alpha = 0.6f
                tvCategoryName.text = "${category.name} 🔒"
            } else {
                categoryCard.alpha = 1.0f
            }
            
            // Set click listener
            categoryCard.setOnClickListener {
                HapticUtils.mediumTap(it)
                onCategoryClick(category)
            }
        }
    }
}

/**
 * DiffUtil callback for efficient RecyclerView updates
 */
class CategoryDiffCallback : DiffUtil.ItemCallback<SubjectCategory>() {
    override fun areItemsTheSame(oldItem: SubjectCategory, newItem: SubjectCategory): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: SubjectCategory, newItem: SubjectCategory): Boolean {
        return oldItem == newItem
    }
}