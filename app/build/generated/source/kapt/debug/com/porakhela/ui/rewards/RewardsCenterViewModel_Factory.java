package com.porakhela.ui.rewards;

import com.porakhela.data.repository.RewardRepository;
import com.porakhela.domain.service.RewardService;
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
public final class RewardsCenterViewModel_Factory implements Factory<RewardsCenterViewModel> {
  private final Provider<RewardRepository> rewardRepositoryProvider;

  private final Provider<RewardService> rewardServiceProvider;

  public RewardsCenterViewModel_Factory(Provider<RewardRepository> rewardRepositoryProvider,
      Provider<RewardService> rewardServiceProvider) {
    this.rewardRepositoryProvider = rewardRepositoryProvider;
    this.rewardServiceProvider = rewardServiceProvider;
  }

  @Override
  public RewardsCenterViewModel get() {
    return newInstance(rewardRepositoryProvider.get(), rewardServiceProvider.get());
  }

  public static RewardsCenterViewModel_Factory create(
      Provider<RewardRepository> rewardRepositoryProvider,
      Provider<RewardService> rewardServiceProvider) {
    return new RewardsCenterViewModel_Factory(rewardRepositoryProvider, rewardServiceProvider);
  }

  public static RewardsCenterViewModel newInstance(RewardRepository rewardRepository,
      RewardService rewardService) {
    return new RewardsCenterViewModel(rewardRepository, rewardService);
  }
}
