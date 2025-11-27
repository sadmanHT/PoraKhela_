package com.porakhela.data.repository;

import com.porakhela.data.local.LeaderboardCache;
import com.porakhela.data.local.UserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class LeaderboardRepository_Factory implements Factory<LeaderboardRepository> {
  private final Provider<LeaderboardCache> leaderboardCacheProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public LeaderboardRepository_Factory(Provider<LeaderboardCache> leaderboardCacheProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.leaderboardCacheProvider = leaderboardCacheProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public LeaderboardRepository get() {
    return newInstance(leaderboardCacheProvider.get(), userPreferencesProvider.get());
  }

  public static LeaderboardRepository_Factory create(
      Provider<LeaderboardCache> leaderboardCacheProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new LeaderboardRepository_Factory(leaderboardCacheProvider, userPreferencesProvider);
  }

  public static LeaderboardRepository newInstance(LeaderboardCache leaderboardCache,
      UserPreferences userPreferences) {
    return new LeaderboardRepository(leaderboardCache, userPreferences);
  }
}
