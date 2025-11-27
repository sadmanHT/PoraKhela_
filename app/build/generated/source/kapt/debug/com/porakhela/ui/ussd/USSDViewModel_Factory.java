package com.porakhela.ui.ussd;

import com.porakhela.data.local.StreakPreferences;
import com.porakhela.data.repository.RewardRepository;
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
public final class USSDViewModel_Factory implements Factory<USSDViewModel> {
  private final Provider<StreakPreferences> streakPreferencesProvider;

  private final Provider<RewardRepository> rewardRepositoryProvider;

  public USSDViewModel_Factory(Provider<StreakPreferences> streakPreferencesProvider,
      Provider<RewardRepository> rewardRepositoryProvider) {
    this.streakPreferencesProvider = streakPreferencesProvider;
    this.rewardRepositoryProvider = rewardRepositoryProvider;
  }

  @Override
  public USSDViewModel get() {
    return newInstance(streakPreferencesProvider.get(), rewardRepositoryProvider.get());
  }

  public static USSDViewModel_Factory create(Provider<StreakPreferences> streakPreferencesProvider,
      Provider<RewardRepository> rewardRepositoryProvider) {
    return new USSDViewModel_Factory(streakPreferencesProvider, rewardRepositoryProvider);
  }

  public static USSDViewModel newInstance(StreakPreferences streakPreferences,
      RewardRepository rewardRepository) {
    return new USSDViewModel(streakPreferences, rewardRepository);
  }
}
