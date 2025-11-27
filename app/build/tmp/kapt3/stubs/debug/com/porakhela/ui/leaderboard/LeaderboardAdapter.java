package com.porakhela.ui.leaderboard;

/**
 * RecyclerView adapter for leaderboard entries
 * Features animations, rank highlighting, and trophy display for top 3
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/porakhela/ui/leaderboard/LeaderboardAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/porakhela/data/models/LeaderboardEntry;", "Lcom/porakhela/ui/leaderboard/LeaderboardAdapter$LeaderboardViewHolder;", "onItemClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "LeaderboardViewHolder", "app_debug"})
public final class LeaderboardAdapter extends androidx.recyclerview.widget.ListAdapter<com.porakhela.data.models.LeaderboardEntry, com.porakhela.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.porakhela.data.models.LeaderboardEntry, kotlin.Unit> onItemClick = null;
    
    public LeaderboardAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.porakhela.data.models.LeaderboardEntry, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.porakhela.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.porakhela.ui.leaderboard.LeaderboardAdapter.LeaderboardViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\f\u001a\u00020\u0006H\u0002J\b\u0010\r\u001a\u00020\u0006H\u0002J\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/porakhela/ui/leaderboard/LeaderboardAdapter$LeaderboardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/porakhela/databinding/ItemLeaderboardEntryBinding;", "(Lcom/porakhela/ui/leaderboard/LeaderboardAdapter;Lcom/porakhela/databinding/ItemLeaderboardEntryBinding;)V", "animateCurrentUserHighlight", "", "animateEntry", "animateItemClick", "animateRankChange", "isUp", "", "animateTopThreeShine", "animateTrophy", "bind", "entry", "Lcom/porakhela/data/models/LeaderboardEntry;", "bindBasicInfo", "bindHighlight", "bindRankChange", "bindRanking", "bindTrophy", "getAvatarResource", "", "avatar", "", "getTrophyResource", "trophyType", "Lcom/porakhela/data/models/TrophyType;", "setupClickListener", "app_debug"})
    public final class LeaderboardViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.porakhela.databinding.ItemLeaderboardEntryBinding binding = null;
        
        public LeaderboardViewHolder(@org.jetbrains.annotations.NotNull()
        com.porakhela.databinding.ItemLeaderboardEntryBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Bind basic user information
         */
        private final void bindBasicInfo(com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Bind ranking information
         */
        private final void bindRanking(com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Bind trophy display for top 3
         */
        private final void bindTrophy(com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Bind highlighting for current user
         */
        private final void bindHighlight(com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Bind rank change indicators
         */
        private final void bindRankChange(com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Setup click listener with haptic feedback
         */
        private final void setupClickListener(com.porakhela.data.models.LeaderboardEntry entry) {
        }
        
        /**
         * Animate entry appearance
         */
        private final void animateEntry() {
        }
        
        /**
         * Animate trophy with shine effect
         */
        private final void animateTrophy() {
        }
        
        /**
         * Animate current user highlight
         */
        private final void animateCurrentUserHighlight() {
        }
        
        /**
         * Animate top three shine effect
         */
        private final void animateTopThreeShine() {
        }
        
        /**
         * Animate rank change indicator
         */
        private final void animateRankChange(boolean isUp) {
        }
        
        /**
         * Animate item click feedback
         */
        private final void animateItemClick() {
        }
        
        /**
         * Get avatar resource based on avatar name
         */
        private final int getAvatarResource(java.lang.String avatar) {
            return 0;
        }
        
        /**
         * Get trophy resource based on trophy type
         */
        private final int getTrophyResource(com.porakhela.data.models.TrophyType trophyType) {
            return 0;
        }
    }
}