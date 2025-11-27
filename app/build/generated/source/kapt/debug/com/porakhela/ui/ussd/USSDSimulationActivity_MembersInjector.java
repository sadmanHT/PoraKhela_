package com.porakhela.ui.ussd;

import com.porakhela.data.local.StreakPreferences;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class USSDSimulationActivity_MembersInjector implements MembersInjector<USSDSimulationActivity> {
  private final Provider<StreakPreferences> streakPreferencesProvider;

  public USSDSimulationActivity_MembersInjector(
      Provider<StreakPreferences> streakPreferencesProvider) {
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  public static MembersInjector<USSDSimulationActivity> create(
      Provider<StreakPreferences> streakPreferencesProvider) {
    return new USSDSimulationActivity_MembersInjector(streakPreferencesProvider);
  }

  @Override
  public void injectMembers(USSDSimulationActivity instance) {
    injectStreakPreferences(instance, streakPreferencesProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.ussd.USSDSimulationActivity.streakPreferences")
  public static void injectStreakPreferences(USSDSimulationActivity instance,
      StreakPreferences streakPreferences) {
    instance.streakPreferences = streakPreferences;
  }
}
