package com.porakhela.data.api;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class RewardApiService_Factory implements Factory<RewardApiService> {
  @Override
  public RewardApiService get() {
    return newInstance();
  }

  public static RewardApiService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RewardApiService newInstance() {
    return new RewardApiService();
  }

  private static final class InstanceHolder {
    private static final RewardApiService_Factory INSTANCE = new RewardApiService_Factory();
  }
}
