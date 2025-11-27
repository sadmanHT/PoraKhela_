package com.porakhela.di;

import com.porakhela.data.local.StreakPreferences;
import com.porakhela.data.tracking.StreakManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideStreakManagerFactory implements Factory<StreakManager> {
  private final Provider<StreakPreferences> streakPreferencesProvider;

  public DataModule_ProvideStreakManagerFactory(
      Provider<StreakPreferences> streakPreferencesProvider) {
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  @Override
  public StreakManager get() {
    return provideStreakManager(streakPreferencesProvider.get());
  }

  public static DataModule_ProvideStreakManagerFactory create(
      Provider<StreakPreferences> streakPreferencesProvider) {
    return new DataModule_ProvideStreakManagerFactory(streakPreferencesProvider);
  }

  public static StreakManager provideStreakManager(StreakPreferences streakPreferences) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideStreakManager(streakPreferences));
  }
}
