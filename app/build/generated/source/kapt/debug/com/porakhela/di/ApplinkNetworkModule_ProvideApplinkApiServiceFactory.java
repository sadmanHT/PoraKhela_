package com.porakhela.di;

import com.porakhela.data.api.ApplinkApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class ApplinkNetworkModule_ProvideApplinkApiServiceFactory implements Factory<ApplinkApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApplinkNetworkModule_ProvideApplinkApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApplinkApiService get() {
    return provideApplinkApiService(retrofitProvider.get());
  }

  public static ApplinkNetworkModule_ProvideApplinkApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new ApplinkNetworkModule_ProvideApplinkApiServiceFactory(retrofitProvider);
  }

  public static ApplinkApiService provideApplinkApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApplinkNetworkModule.INSTANCE.provideApplinkApiService(retrofit));
  }
}
