package com.porakhela.ui.rewards;

/**
 * ViewModel for the Rewards Center
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fJ\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u001bH\u0002J\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u001bH\u0002J\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\fJ\u0006\u0010 \u001a\u00020\u001bR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012\u00a8\u0006!"}, d2 = {"Lcom/porakhela/ui/rewards/RewardsCenterViewModel;", "Lcom/porakhela/core/components/BaseViewModel;", "rewardRepository", "Lcom/porakhela/data/repository/RewardRepository;", "rewardService", "Lcom/porakhela/domain/service/RewardService;", "(Lcom/porakhela/data/repository/RewardRepository;Lcom/porakhela/domain/service/RewardService;)V", "_redemptionResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/porakhela/domain/service/RewardService$RedemptionResult;", "_rewards", "", "Lcom/porakhela/data/model/Reward;", "_userBalance", "", "redemptionResult", "Lkotlinx/coroutines/flow/StateFlow;", "getRedemptionResult", "()Lkotlinx/coroutines/flow/StateFlow;", "rewards", "getRewards", "userBalance", "getUserBalance", "canAffordReward", "", "reward", "clearRedemptionResult", "", "initializeRewards", "loadRewards", "loadUserBalance", "redeemReward", "refresh", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RewardsCenterViewModel extends com.porakhela.core.components.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.RewardRepository rewardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.domain.service.RewardService rewardService = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.porakhela.data.model.Reward>> _rewards = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.porakhela.data.model.Reward>> rewards = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _userBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> userBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.domain.service.RewardService.RedemptionResult> _redemptionResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.domain.service.RewardService.RedemptionResult> redemptionResult = null;
    
    @javax.inject.Inject()
    public RewardsCenterViewModel(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.RewardRepository rewardRepository, @org.jetbrains.annotations.NotNull()
    com.porakhela.domain.service.RewardService rewardService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.porakhela.data.model.Reward>> getRewards() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getUserBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.domain.service.RewardService.RedemptionResult> getRedemptionResult() {
        return null;
    }
    
    /**
     * Load available rewards from repository
     */
    public final void loadRewards() {
    }
    
    /**
     * Initialize rewards data if needed
     */
    private final void initializeRewards() {
    }
    
    /**
     * Load user's current Porapoints balance
     */
    private final void loadUserBalance() {
    }
    
    /**
     * Attempt to redeem a reward
     */
    public final void redeemReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward) {
    }
    
    /**
     * Clear redemption result after handling
     */
    public final void clearRedemptionResult() {
    }
    
    /**
     * Check if user can afford a specific reward
     */
    public final boolean canAffordReward(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.model.Reward reward) {
        return false;
    }
    
    /**
     * Refresh all data
     */
    public final void refresh() {
    }
}