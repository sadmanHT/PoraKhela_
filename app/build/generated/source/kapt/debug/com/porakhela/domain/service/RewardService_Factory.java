package com.porakhela.domain.service;

import com.porakhela.data.api.RewardApiService;
import com.porakhela.data.local.StreakPreferences;
import com.porakhela.data.repository.RewardRepository;
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
public final class RewardService_Factory implements Factory<RewardService> {
  private final Provider<RewardRepository> rewardRepositoryProvider;

  private final Provider<RewardApiService> rewardApiServiceProvider;

  private final Provider<StreakPreferences> streakPreferencesProvider;

  public RewardService_Factory(Provider<RewardRepository> rewardRepositoryProvider,
      Provider<RewardApiService> rewardApiServiceProvider,
      Provider<StreakPreferences> streakPreferencesProvider) {
    this.rewardRepositoryProvider = rewardRepositoryProvider;
    this.rewardApiServiceProvider = rewardApiServiceProvider;
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  @Override
  public RewardService get() {
    return newInstance(rewardRepositoryProvider.get(), rewardApiServiceProvider.get(), streakPreferencesProvider.get());
  }

  public static RewardService_Factory create(Provider<RewardRepository> rewardRepositoryProvider,
      Provider<RewardApiService> rewardApiServiceProvider,
      Provider<StreakPreferences> streakPreferencesProvider) {
    return new RewardService_Factory(rewardRepositoryProvider, rewardApiServiceProvider, streakPreferencesProvider);
  }

  public static RewardService newInstance(RewardRepository rewardRepository,
      RewardApiService rewardApiService, StreakPreferences streakPreferences) {
    return new RewardService(rewardRepository, rewardApiService, streakPreferences);
  }
}
