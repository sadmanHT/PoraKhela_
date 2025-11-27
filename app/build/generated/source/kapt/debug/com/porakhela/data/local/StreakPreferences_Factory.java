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
public final class StreakPreferences_Factory implements Factory<StreakPreferences> {
  private final Provider<Context> contextProvider;

  public StreakPreferences_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StreakPreferences get() {
    return newInstance(contextProvider.get());
  }

  public static StreakPreferences_Factory create(Provider<Context> contextProvider) {
    return new StreakPreferences_Factory(contextProvider);
  }

  public static StreakPreferences newInstance(Context context) {
    return new StreakPreferences(context);
  }
}
