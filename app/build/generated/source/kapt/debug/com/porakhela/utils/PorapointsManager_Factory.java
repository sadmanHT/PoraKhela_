package com.porakhela.utils;

import android.content.Context;
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
public final class PorapointsManager_Factory implements Factory<PorapointsManager> {
  private final Provider<Context> contextProvider;

  public PorapointsManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PorapointsManager get() {
    return newInstance(contextProvider.get());
  }

  public static PorapointsManager_Factory create(Provider<Context> contextProvider) {
    return new PorapointsManager_Factory(contextProvider);
  }

  public static PorapointsManager newInstance(Context context) {
    return new PorapointsManager(context);
  }
}
