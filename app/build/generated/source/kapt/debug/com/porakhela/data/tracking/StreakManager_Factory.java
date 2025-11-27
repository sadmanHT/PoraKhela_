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
public final class StreakManager_Factory implements Factory<StreakManager> {
  private final Provider<StreakPreferences> streakPreferencesProvider;

  public StreakManager_Factory(Provider<StreakPreferences> streakPreferencesProvider) {
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  @Override
  public StreakManager get() {
    return newInstance(streakPreferencesProvider.get());
  }

  public static StreakManager_Factory create(
      Provider<StreakPreferences> streakPreferencesProvider) {
    return new StreakManager_Factory(streakPreferencesProvider);
  }

  public static StreakManager newInstance(StreakPreferences streakPreferences) {
    return new StreakManager(streakPreferences);
  }
}
