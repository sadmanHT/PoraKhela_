package com.porakhela.di;

import com.porakhela.data.local.LeaderboardCache;
import com.porakhela.data.local.UserPreferences;
import com.porakhela.data.repository.LeaderboardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideLeaderboardRepositoryFactory implements Factory<LeaderboardRepository> {
  private final Provider<LeaderboardCache> leaderboardCacheProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public DataModule_ProvideLeaderboardRepositoryFactory(
      Provider<LeaderboardCache> leaderboardCacheProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.leaderboardCacheProvider = leaderboardCacheProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public LeaderboardRepository get() {
    return provideLeaderboardRepository(leaderboardCacheProvider.get(), userPreferencesProvider.get());
  }

  public static DataModule_ProvideLeaderboardRepositoryFactory create(
      Provider<LeaderboardCache> leaderboardCacheProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new DataModule_ProvideLeaderboardRepositoryFactory(leaderboardCacheProvider, userPreferencesProvider);
  }

  public static LeaderboardRepository provideLeaderboardRepository(
      LeaderboardCache leaderboardCache, UserPreferences userPreferences) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideLeaderboardRepository(leaderboardCache, userPreferences));
  }
}
