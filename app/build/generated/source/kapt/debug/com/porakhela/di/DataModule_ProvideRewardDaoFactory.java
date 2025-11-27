package com.porakhela.di;

import com.porakhela.data.local.ParentDashboardDatabase;
import com.porakhela.data.local.dao.RewardDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideRewardDaoFactory implements Factory<RewardDao> {
  private final Provider<ParentDashboardDatabase> databaseProvider;

  public DataModule_ProvideRewardDaoFactory(Provider<ParentDashboardDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RewardDao get() {
    return provideRewardDao(databaseProvider.get());
  }

  public static DataModule_ProvideRewardDaoFactory create(
      Provider<ParentDashboardDatabase> databaseProvider) {
    return new DataModule_ProvideRewardDaoFactory(databaseProvider);
  }

  public static RewardDao provideRewardDao(ParentDashboardDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideRewardDao(database));
  }
}
