package com.porakhela.data.local;

import android.content.Context;
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
public final class SecureParentPreferences_Factory implements Factory<SecureParentPreferences> {
  private final Provider<Context> contextProvider;

  public SecureParentPreferences_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SecureParentPreferences get() {
    return newInstance(contextProvider.get());
  }

  public static SecureParentPreferences_Factory create(Provider<Context> contextProvider) {
    return new SecureParentPreferences_Factory(contextProvider);
  }

  public static SecureParentPreferences newInstance(Context context) {
    return new SecureParentPreferences(context);
  }
}
