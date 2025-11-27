package com.porakhela.ui.rewards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.data.model.Reward

/**
 * Adapter for displaying rewards in a grid layout
 */
class RewardsAdapter(
    private val onRewardClick: (Reward) -> Unit
) : ListAdapter<Reward, RewardsAdapter.RewardViewHolder>(RewardDiffCallback()) {
    
    private var userBalance: Int = 0
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reward_card, parent, false)
        return RewardViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.bind(getItem(position), userBalance, onRewardClick)
    }
    
    fun updateUserBalance(balance: Int) {
        userBalance = balance
        notifyDataSetChanged() // Update all items to reflect new balance
    }
    
    class RewardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageIcon: ImageView = itemView.findViewById(R.id.imageRewardIcon)
        private val textName: TextView = itemView.findViewById(R.id.textRewardName)
        private val textDescription: TextView = itemView.findViewById(R.id.textRewardDescription)
        private val textCost: TextView = itemView.findViewById(R.id.textRewardCost)
        private val buttonRedeem: Button = itemView.findViewById(R.id.buttonRedeem)
        private val textApprovalRequired: TextView = itemView.findViewById(R.id.textApprovalRequired)
        
        fun bind(reward: Reward, userBalance: Int, onRewardClick: (Reward) -> Unit) {
            // Set reward details
            textName.text = reward.name
            textDescription.text = reward.description ?: "Redeem this reward with your Porapoints"
            textCost.text = "${reward.cost} Points"
            
            // Set icon based on category
            val iconRes = getIconForCategory(reward.category)
            imageIcon.setImageResource(iconRes)
            
            // Show/hide approval required indicator
            if (reward.approvalRequired) {
                textApprovalRequired.visibility = View.VISIBLE
                textApprovalRequired.text = "Requires parent approval"
            } else {
                textApprovalRequired.visibility = View.GONE
            }
            
            // Configure redeem button based on user's balance
            val canAfford = userBalance >= reward.cost
            val isAvailable = reward.isAvailable
            
            buttonRedeem.apply {
                isEnabled = canAfford && isAvailable
                
                text = when {
                    !isAvailable -> "Unavailable"
                    !canAfford -> "Need ${reward.cost - userBalance} more"
                    else -> "Redeem"
                }
                
                // Set colors based on state
                val context = itemView.context
                when {
                    !isAvailable -> {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.disabled))
                        setTextColor(ContextCompat.getColor(context, R.color.on_disabled))
                    }
                    !canAfford -> {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.error))
                        setTextColor(ContextCompat.getColor(context, R.color.on_error))
                    }
                    else -> {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.primary))
                        setTextColor(ContextCompat.getColor(context, R.color.on_primary))
                    }
                }
                
                setOnClickListener {
                    if (canAfford && isAvailable) {
                        onRewardClick(reward)
                    }
                }
            }
            
            // Set card click listener
            itemView.setOnClickListener {
                if (canAfford && isAvailable) {
                    onRewardClick(reward)
                }
            }
        }
        
        private fun getIconForCategory(category: String): Int {
            return when (category.lowercase()) {
                "data" -> R.drawable.ic_data_pack
                "talktime" -> R.drawable.ic_talktime
                "achievement" -> R.drawable.ic_badge
                "education" -> R.drawable.ic_premium
                else -> R.drawable.ic_gift // Default icon
            }
        }
    }
    
    private class RewardDiffCallback : DiffUtil.ItemCallback<Reward>() {
        override fun areItemsTheSame(oldItem: Reward, newItem: Reward): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Reward, newItem: Reward): Boolean {
            return oldItem == newItem
        }
    }
}