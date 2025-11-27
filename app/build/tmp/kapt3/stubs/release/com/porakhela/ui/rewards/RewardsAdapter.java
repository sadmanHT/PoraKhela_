package com.porakhela.ui.rewards;

/**
 * Adapter for displaying rewards in a grid layout
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0013\u0014B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\tR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/porakhela/ui/rewards/RewardsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/porakhela/data/model/Reward;", "Lcom/porakhela/ui/rewards/RewardsAdapter$RewardViewHolder;", "onRewardClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "userBalance", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateUserBalance", "balance", "RewardDiffCallback", "RewardViewHolder", "app_release"})
public final class RewardsAdapter extends androidx.recyclerview.widget.ListAdapter<com.porakhela.data.model.Reward, com.porakhela.ui.rewards.RewardsAdapter.RewardViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.porakhela.data.model.Reward, kotlin.Unit> onRewardClick = null;
    private int userBalance = 0;
    
    public RewardsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.porakhela.data.model.Reward, kotlin.Unit> onRewardClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.porakhela.ui.rewards.RewardsAdapter.RewardViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.porakhela.ui.rewards.RewardsAdapter.RewardViewHolder holder, int position) {
    }
    
    public final void updateUserBalance(int balance) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/porakhela/ui/rewards/RewardsAdapter$RewardDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/porakhela/data/model/Reward;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    static final class RewardDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.porakhela.data.model.Reward> {
        
        public RewardDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.porakhela.data.model.Reward oldItem, @org.jetbrains.annotations.NotNull()
        com.porakhela.data.model.Reward newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.porakhela.data.model.Reward oldItem, @org.jetbrains.annotations.NotNull()
        com.porakhela.data.model.Reward newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J*\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0\u0015J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/porakhela/ui/rewards/RewardsAdapter$RewardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "buttonRedeem", "Landroid/widget/Button;", "imageIcon", "Landroid/widget/ImageView;", "textApprovalRequired", "Landroid/widget/TextView;", "textCost", "textDescription", "textName", "bind", "", "reward", "Lcom/porakhela/data/model/Reward;", "userBalance", "", "onRewardClick", "Lkotlin/Function1;", "getIconForCategory", "category", "", "app_release"})
    public static final class RewardViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView imageIcon = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView textName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView textDescription = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView textCost = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.Button buttonRedeem = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView textApprovalRequired = null;
        
        public RewardViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.porakhela.data.model.Reward reward, int userBalance, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.porakhela.data.model.Reward, kotlin.Unit> onRewardClick) {
        }
        
        private final int getIconForCategory(java.lang.String category) {
            return 0;
        }
    }
}