package com.porakhela.ui.performance.testing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.databinding.ItemTestLessonBinding

class TestItemAdapter(
    private val items: List<EmulatorTestActivity.TestItem>,
    private val onItemClick: (EmulatorTestActivity.TestItem) -> Unit
) : RecyclerView.Adapter<TestItemAdapter.TestItemViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestItemViewHolder {
        val binding = ItemTestLessonBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return TestItemViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: TestItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
    
    override fun getItemCount() = items.size
    
    inner class TestItemViewHolder(
        private val binding: ItemTestLessonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: EmulatorTestActivity.TestItem) {
            binding.apply {
                tvLessonTitle.text = item.title
                tvLessonSubtitle.text = item.subtitle
                tvLessonProgress.text = "${item.progress}%"
                tvDifficulty.text = item.difficulty
                
                // Set completion status
                if (item.isCompleted) {
                    ivCompletionStatus.setImageResource(android.R.drawable.checkbox_on_background)
                    ivCompletionStatus.setColorFilter(
                        root.context.getColor(com.porakhela.R.color.success_green)
                    )
                } else {
                    ivCompletionStatus.setImageResource(android.R.drawable.checkbox_off_background)
                    ivCompletionStatus.setColorFilter(
                        root.context.getColor(com.porakhela.R.color.text_secondary)
                    )
                }
                
                // Set difficulty color
                val difficultyColor = when (item.difficulty) {
                    "Beginner" -> com.porakhela.R.color.success_green
                    "Intermediate" -> com.porakhela.R.color.warning_orange
                    "Advanced" -> com.porakhela.R.color.primary_blue
                    "Expert" -> com.porakhela.R.color.error_red
                    else -> com.porakhela.R.color.text_secondary
                }
                
                tvDifficulty.setTextColor(root.context.getColor(difficultyColor))
                
                // Set progress bar
                progressBar.progress = item.progress
                
                // Click listener
                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}