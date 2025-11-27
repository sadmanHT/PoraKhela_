package com.porakhela.di;

import com.porakhela.data.api.MockApplinkInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ApplinkNetworkModule_ProvideMockApplinkInterceptorFactory implements Factory<MockApplinkInterceptor> {
  @Override
  public MockApplinkInterceptor get() {
    return provideMockApplinkInterceptor();
  }

  public static ApplinkNetworkModule_ProvideMockApplinkInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MockApplinkInterceptor provideMockApplinkInterceptor() {
    return Preconditions.checkNotNullFromProvides(ApplinkNetworkModule.INSTANCE.provideMockApplinkInterceptor());
  }

  private static final class InstanceHolder {
    private static final ApplinkNetworkModule_ProvideMockApplinkInterceptorFactory INSTANCE = new ApplinkNetworkModule_ProvideMockApplinkInterceptorFactory();
  }
}
