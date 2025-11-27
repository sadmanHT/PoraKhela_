package com.porakhela.ui.leaderboard;

/**
 * ViewModel for leaderboard system
 * Manages leaderboard data, UI state, and user interactions
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020 J\u0006\u0010\"\u001a\u00020\rJ\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00162\u0006\u0010$\u001a\u00020\tJ\b\u0010%\u001a\u00020 H\u0002J\u0006\u0010&\u001a\u00020 J\u000e\u0010\'\u001a\u00020 2\u0006\u0010$\u001a\u00020\tJ\u000e\u0010(\u001a\u00020 2\u0006\u0010$\u001a\u00020\tJ\u000e\u0010)\u001a\u00020 2\u0006\u0010$\u001a\u00020\tJ\u001e\u0010*\u001a\u00020 2\u0006\u0010$\u001a\u00020\t2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/porakhela/ui/leaderboard/LeaderboardViewModel;", "Landroidx/lifecycle/ViewModel;", "leaderboardRepository", "Lcom/porakhela/data/repository/LeaderboardRepository;", "userPreferences", "Lcom/porakhela/data/local/UserPreferences;", "(Lcom/porakhela/data/repository/LeaderboardRepository;Lcom/porakhela/data/local/UserPreferences;)V", "_currentType", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/porakhela/data/models/LeaderboardType;", "_currentUserRank", "", "_errorMessage", "", "_globalLeaderboard", "", "Lcom/porakhela/data/models/LeaderboardEntry;", "_monthlyLeaderboard", "_uiState", "Lcom/porakhela/ui/leaderboard/LeaderboardUiState;", "_weeklyLeaderboard", "currentType", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentType", "()Lkotlinx/coroutines/flow/StateFlow;", "currentUserRank", "getCurrentUserRank", "errorMessage", "getErrorMessage", "uiState", "getUiState", "clearAllCaches", "", "clearError", "getCacheStatus", "getLeaderboardForType", "type", "initializeUserInfo", "loadAllLeaderboards", "loadLeaderboard", "refreshLeaderboard", "selectLeaderboardType", "updateLeaderboardData", "leaderboard", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LeaderboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.LeaderboardRepository leaderboardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.local.UserPreferences userPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.ui.leaderboard.LeaderboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.leaderboard.LeaderboardUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.porakhela.data.models.LeaderboardEntry>> _weeklyLeaderboard = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.porakhela.data.models.LeaderboardEntry>> _monthlyLeaderboard = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.porakhela.data.models.LeaderboardEntry>> _globalLeaderboard = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _currentUserRank = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> currentUserRank = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.porakhela.data.models.LeaderboardType> _currentType = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.porakhela.data.models.LeaderboardType> currentType = null;
    
    @javax.inject.Inject()
    public LeaderboardViewModel(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.LeaderboardRepository leaderboardRepository, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.local.UserPreferences userPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.ui.leaderboard.LeaderboardUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getCurrentUserRank() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.porakhela.data.models.LeaderboardType> getCurrentType() {
        return null;
    }
    
    /**
     * Initialize user information
     */
    private final void initializeUserInfo() {
    }
    
    /**
     * Load all leaderboard types
     */
    public final void loadAllLeaderboards() {
    }
    
    /**
     * Load specific leaderboard type with proper error handling and race condition prevention
     */
    public final void loadLeaderboard(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.LeaderboardType type) {
    }
    
    /**
     * Refresh specific leaderboard with pull-to-refresh and improved error handling
     */
    public final void refreshLeaderboard(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.LeaderboardType type) {
    }
    
    /**
     * Select leaderboard type (for tab changes)
     */
    public final void selectLeaderboardType(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.LeaderboardType type) {
    }
    
    /**
     * Get leaderboard data for specific type as Flow
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.porakhela.data.models.LeaderboardEntry>> getLeaderboardForType(@org.jetbrains.annotations.NotNull()
    com.porakhela.data.models.LeaderboardType type) {
        return null;
    }
    
    /**
     * Update leaderboard data based on type
     */
    private final void updateLeaderboardData(com.porakhela.data.models.LeaderboardType type, java.util.List<com.porakhela.data.models.LeaderboardEntry> leaderboard) {
    }
    
    /**
     * Clear error message
     */
    public final void clearError() {
    }
    
    /**
     * Get cache status for debugging
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCacheStatus() {
        return null;
    }
    
    /**
     * Clear all caches (for testing/reset)
     */
    public final void clearAllCaches() {
    }
}