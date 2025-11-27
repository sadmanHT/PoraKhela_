package com.porakhela.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class ApplinkNetworkModule_ProvideHttpLoggingInterceptorFactory implements Factory<HttpLoggingInterceptor> {
  @Override
  public HttpLoggingInterceptor get() {
    return provideHttpLoggingInterceptor();
  }

  public static ApplinkNetworkModule_ProvideHttpLoggingInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    return Preconditions.checkNotNullFromProvides(ApplinkNetworkModule.INSTANCE.provideHttpLoggingInterceptor());
  }

  private static final class InstanceHolder {
    private static final ApplinkNetworkModule_ProvideHttpLoggingInterceptorFactory INSTANCE = new ApplinkNetworkModule_ProvideHttpLoggingInterceptorFactory();
  }
}
