package com.porakhela.data.repository;

import android.content.Context;
import com.porakhela.data.api.ApplinkApiService;
import com.porakhela.utils.PorapointsManager;
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
public final class ApplinkRepository_Factory implements Factory<ApplinkRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<ApplinkApiService> apiServiceProvider;

  private final Provider<PorapointsManager> porapointsManagerProvider;

  public ApplinkRepository_Factory(Provider<Context> contextProvider,
      Provider<ApplinkApiService> apiServiceProvider,
      Provider<PorapointsManager> porapointsManagerProvider) {
    this.contextProvider = contextProvider;
    this.apiServiceProvider = apiServiceProvider;
    this.porapointsManagerProvider = porapointsManagerProvider;
  }

  @Override
  public ApplinkRepository get() {
    return newInstance(contextProvider.get(), apiServiceProvider.get(), porapointsManagerProvider.get());
  }

  public static ApplinkRepository_Factory create(Provider<Context> contextProvider,
      Provider<ApplinkApiService> apiServiceProvider,
      Provider<PorapointsManager> porapointsManagerProvider) {
    return new ApplinkRepository_Factory(contextProvider, apiServiceProvider, porapointsManagerProvider);
  }

  public static ApplinkRepository newInstance(Context context, ApplinkApiService apiService,
      PorapointsManager porapointsManager) {
    return new ApplinkRepository(context, apiService, porapointsManager);
  }
}
