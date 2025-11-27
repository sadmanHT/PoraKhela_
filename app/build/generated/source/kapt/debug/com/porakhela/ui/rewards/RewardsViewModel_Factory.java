package com.porakhela.ui.rewards;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class RewardsViewModel_Factory implements Factory<RewardsViewModel> {
  @Override
  public RewardsViewModel get() {
    return newInstance();
  }

  public static RewardsViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RewardsViewModel newInstance() {
    return new RewardsViewModel();
  }

  private static final class InstanceHolder {
    private static final RewardsViewModel_Factory INSTANCE = new RewardsViewModel_Factory();
  }
}
