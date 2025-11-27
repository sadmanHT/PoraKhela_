package com.porakhela.di;

import android.content.Context;
import com.porakhela.data.local.StreakPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideStreakPreferencesFactory implements Factory<StreakPreferences> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideStreakPreferencesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StreakPreferences get() {
    return provideStreakPreferences(contextProvider.get());
  }

  public static DataModule_ProvideStreakPreferencesFactory create(
      Provider<Context> contextProvider) {
    return new DataModule_ProvideStreakPreferencesFactory(contextProvider);
  }

  public static StreakPreferences provideStreakPreferences(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideStreakPreferences(context));
  }
}
