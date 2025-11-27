package com.porakhela.di;

import com.porakhela.data.local.StreakPreferences;
import com.porakhela.data.tracking.TimeTracker;
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
public final class DataModule_ProvideTimeTrackerFactory implements Factory<TimeTracker> {
  private final Provider<StreakPreferences> streakPreferencesProvider;

  public DataModule_ProvideTimeTrackerFactory(
      Provider<StreakPreferences> streakPreferencesProvider) {
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  @Override
  public TimeTracker get() {
    return provideTimeTracker(streakPreferencesProvider.get());
  }

  public static DataModule_ProvideTimeTrackerFactory create(
      Provider<StreakPreferences> streakPreferencesProvider) {
    return new DataModule_ProvideTimeTrackerFactory(streakPreferencesProvider);
  }

  public static TimeTracker provideTimeTracker(StreakPreferences streakPreferences) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideTimeTracker(streakPreferences));
  }
}
