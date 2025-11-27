package com.porakhela.ui.leaderboard;

import com.porakhela.data.local.UserPreferences;
import com.porakhela.data.repository.LeaderboardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class LeaderboardViewModel_Factory implements Factory<LeaderboardViewModel> {
  private final Provider<LeaderboardRepository> leaderboardRepositoryProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public LeaderboardViewModel_Factory(Provider<LeaderboardRepository> leaderboardRepositoryProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.leaderboardRepositoryProvider = leaderboardRepositoryProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public LeaderboardViewModel get() {
    return newInstance(leaderboardRepositoryProvider.get(), userPreferencesProvider.get());
  }

  public static LeaderboardViewModel_Factory create(
      Provider<LeaderboardRepository> leaderboardRepositoryProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new LeaderboardViewModel_Factory(leaderboardRepositoryProvider, userPreferencesProvider);
  }

  public static LeaderboardViewModel newInstance(LeaderboardRepository leaderboardRepository,
      UserPreferences userPreferences) {
    return new LeaderboardViewModel(leaderboardRepository, userPreferences);
  }
}
