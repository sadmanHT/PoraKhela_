package com.porakhela.data.tracking;

import com.porakhela.data.local.StreakPreferences;
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
public final class TimeTracker_Factory implements Factory<TimeTracker> {
  private final Provider<StreakPreferences> streakPreferencesProvider;

  public TimeTracker_Factory(Provider<StreakPreferences> streakPreferencesProvider) {
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  @Override
  public TimeTracker get() {
    return newInstance(streakPreferencesProvider.get());
  }

  public static TimeTracker_Factory create(Provider<StreakPreferences> streakPreferencesProvider) {
    return new TimeTracker_Factory(streakPreferencesProvider);
  }

  public static TimeTracker newInstance(StreakPreferences streakPreferences) {
    return new TimeTracker(streakPreferences);
  }
}
