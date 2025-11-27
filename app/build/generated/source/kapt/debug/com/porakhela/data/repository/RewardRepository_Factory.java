package com.porakhela.data.repository;

import android.content.Context;
import com.porakhela.data.local.dao.RewardDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class RewardRepository_Factory implements Factory<RewardRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<RewardDao> rewardDaoProvider;

  public RewardRepository_Factory(Provider<Context> contextProvider,
      Provider<RewardDao> rewardDaoProvider) {
    this.contextProvider = contextProvider;
    this.rewardDaoProvider = rewardDaoProvider;
  }

  @Override
  public RewardRepository get() {
    return newInstance(contextProvider.get(), rewardDaoProvider.get());
  }

  public static RewardRepository_Factory create(Provider<Context> contextProvider,
      Provider<RewardDao> rewardDaoProvider) {
    return new RewardRepository_Factory(contextProvider, rewardDaoProvider);
  }

  public static RewardRepository newInstance(Context context, RewardDao rewardDao) {
    return new RewardRepository(context, rewardDao);
  }
}
