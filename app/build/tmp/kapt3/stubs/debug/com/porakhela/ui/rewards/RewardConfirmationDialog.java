package com.porakhela.ui.rewards;

/**
 * Confirmation dialog for reward redemption
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0014\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u001a\u0010\u0013\u001a\u00020\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007J \u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\nH\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/porakhela/ui/rewards/RewardConfirmationDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onCancelRedemption", "Lkotlin/Function0;", "", "onConfirmRedemption", "Lkotlin/Function1;", "Lcom/porakhela/data/model/Reward;", "getIconForCategory", "", "category", "", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "setOnCancelRedemption", "callback", "setOnConfirmRedemption", "setupViews", "view", "Landroid/view/View;", "reward", "userBalance", "Companion", "app_debug"})
public final class RewardConfirmationDialog extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_REWARD_ID = "reward_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_REWARD_NAME = "reward_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_REWARD_COST = "reward_cost";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_REWARD_DESCRIPTION = "reward_description";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_REWARD_CATEGORY = "reward_category";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_REWARD_APPROVAL_REQUIRED = "reward_approval_required";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_USER_BALANCE = "user_balance";
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.porakhela.data.model.Reward, kotlin.Unit> onConfirmRedemption;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onCancelRedemption;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.ui.rewards.RewardConfirmationDialog.Companion Companion = null;
    
    public RewardConfirmationDialog() {
        super();
    }
    
    public final void setOnConfirmRedemption(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.porakhela.data.model.Reward, kotlin.Unit> callback) {
    }
    
    public final void setOnCancelRedemption(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setupViews(android.view.View view, com.porakhela.data.model.Reward reward, int userBalance) {
    }
    
    private final int getIconForCategory(java.lang.String category) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/porakhela/ui/rewards/RewardConfirmationDialog$Companion;", "", "()V", "ARG_REWARD_APPROVAL_REQUIRED", "", "ARG_REWARD_CATEGORY", "ARG_REWARD_COST", "ARG_REWARD_DESCRIPTION", "ARG_REWARD_ID", "ARG_REWARD_NAME", "ARG_USER_BALANCE", "newInstance", "Lcom/porakhela/ui/rewards/RewardConfirmationDialog;", "reward", "Lcom/porakhela/data/model/Reward;", "userBalance", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.porakhela.ui.rewards.RewardConfirmationDialog newInstance(@org.jetbrains.annotations.NotNull()
        com.porakhela.data.model.Reward reward, int userBalance) {
            return null;
        }
    }
}