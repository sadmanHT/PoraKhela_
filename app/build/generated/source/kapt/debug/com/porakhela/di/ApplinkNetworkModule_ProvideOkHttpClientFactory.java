package com.porakhela.di;

import com.porakhela.data.api.MockApplinkInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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
public final class ApplinkNetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<HttpLoggingInterceptor> loggingInterceptorProvider;

  private final Provider<MockApplinkInterceptor> mockInterceptorProvider;

  public ApplinkNetworkModule_ProvideOkHttpClientFactory(
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<MockApplinkInterceptor> mockInterceptorProvider) {
    this.loggingInterceptorProvider = loggingInterceptorProvider;
    this.mockInterceptorProvider = mockInterceptorProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(loggingInterceptorProvider.get(), mockInterceptorProvider.get());
  }

  public static ApplinkNetworkModule_ProvideOkHttpClientFactory create(
      Provider<HttpLoggingInterceptor> loggingInterceptorProvider,
      Provider<MockApplinkInterceptor> mockInterceptorProvider) {
    return new ApplinkNetworkModule_ProvideOkHttpClientFactory(loggingInterceptorProvider, mockInterceptorProvider);
  }

  public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
      MockApplinkInterceptor mockInterceptor) {
    return Preconditions.checkNotNullFromProvides(ApplinkNetworkModule.INSTANCE.provideOkHttpClient(loggingInterceptor, mockInterceptor));
  }
}
