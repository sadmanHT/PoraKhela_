package com.porakhela.ui.rewards

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.porakhela.R
import com.porakhela.data.model.Reward

/**
 * Confirmation dialog for reward redemption
 */
class RewardConfirmationDialog : DialogFragment() {
    
    companion object {
        private const val ARG_REWARD_ID = "reward_id"
        private const val ARG_REWARD_NAME = "reward_name"
        private const val ARG_REWARD_COST = "reward_cost"
        private const val ARG_REWARD_DESCRIPTION = "reward_description"
        private const val ARG_REWARD_CATEGORY = "reward_category"
        private const val ARG_REWARD_APPROVAL_REQUIRED = "reward_approval_required"
        private const val ARG_USER_BALANCE = "user_balance"
        
        fun newInstance(reward: Reward, userBalance: Int): RewardConfirmationDialog {
            return RewardConfirmationDialog().apply {
                arguments = Bundle().apply {
                    putInt(ARG_REWARD_ID, reward.id)
                    putString(ARG_REWARD_NAME, reward.name)
                    putInt(ARG_REWARD_COST, reward.cost)
                    putString(ARG_REWARD_DESCRIPTION, reward.description)
                    putString(ARG_REWARD_CATEGORY, reward.category)
                    putBoolean(ARG_REWARD_APPROVAL_REQUIRED, reward.approvalRequired)
                    putInt(ARG_USER_BALANCE, userBalance)
                }
            }
        }
    }
    
    private var onConfirmRedemption: ((Reward) -> Unit)? = null
    private var onCancelRedemption: (() -> Unit)? = null
    
    fun setOnConfirmRedemption(callback: (Reward) -> Unit) {
        onConfirmRedemption = callback
    }
    
    fun setOnCancelRedemption(callback: () -> Unit) {
        onCancelRedemption = callback
    }
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arguments = arguments ?: throw IllegalArgumentException("Arguments are required")
        
        val reward = Reward(
            id = arguments.getInt(ARG_REWARD_ID),
            name = arguments.getString(ARG_REWARD_NAME) ?: "",
            cost = arguments.getInt(ARG_REWARD_COST),
            description = arguments.getString(ARG_REWARD_DESCRIPTION),
            category = arguments.getString(ARG_REWARD_CATEGORY) ?: "general",
            approvalRequired = arguments.getBoolean(ARG_REWARD_APPROVAL_REQUIRED),
            isAvailable = true
        )
        val userBalance = arguments.getInt(ARG_USER_BALANCE, 0)
        
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_reward_confirmation, null)
        
        setupViews(view, reward, userBalance)
        
        return MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .create()
    }
    
    private fun setupViews(view: View, reward: Reward, userBalance: Int) {
        val imageIcon = view.findViewById<ImageView>(R.id.imageRewardIcon)
        val textTitle = view.findViewById<TextView>(R.id.textDialogTitle)
        val textRewardName = view.findViewById<TextView>(R.id.textRewardName)
        val textRewardDescription = view.findViewById<TextView>(R.id.textRewardDescription)
        val textCost = view.findViewById<TextView>(R.id.textRewardCost)
        val textBalanceAfter = view.findViewById<TextView>(R.id.textBalanceAfter)
        val textApprovalNote = view.findViewById<TextView>(R.id.textApprovalNote)
        val buttonConfirm = view.findViewById<Button>(R.id.buttonConfirm)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)
        
        // Set reward details
        val iconRes = getIconForCategory(reward.category)
        imageIcon.setImageResource(iconRes)
        
        textTitle.text = "Confirm Redemption"
        textRewardName.text = reward.name
        textRewardDescription.text = reward.description ?: "Redeem this reward with your Porapoints"
        textCost.text = "Cost: ${reward.cost} Points"
        
        val balanceAfter = userBalance - reward.cost
        textBalanceAfter.text = "Balance after redemption: $balanceAfter Points"
        
        // Show approval note if required
        if (reward.approvalRequired) {
            textApprovalNote.visibility = View.VISIBLE
            textApprovalNote.text = "⚠️ This reward requires parent approval. You'll receive a notification once approved."
        } else {
            textApprovalNote.visibility = View.GONE
        }
        
        // Set button listeners
        buttonConfirm.setOnClickListener {
            onConfirmRedemption?.invoke(reward)
            dismiss()
        }
        
        buttonCancel.setOnClickListener {
            onCancelRedemption?.invoke()
            dismiss()
        }
    }
    
    private fun getIconForCategory(category: String): Int {
        return when (category.lowercase()) {
            "data" -> R.drawable.ic_data_pack
            "talktime" -> R.drawable.ic_talktime
            "achievement" -> R.drawable.ic_badge
            "education" -> R.drawable.ic_premium
            else -> R.drawable.ic_gift
        }
    }
}