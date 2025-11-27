package com.porakhela.ui.splash;

import com.porakhela.data.local.OnboardingPreferences;
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
public final class SplashActivity_MembersInjector implements MembersInjector<SplashActivity> {
  private final Provider<OnboardingPreferences> onboardingPreferencesProvider;

  public SplashActivity_MembersInjector(
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    this.onboardingPreferencesProvider = onboardingPreferencesProvider;
  }

  public static MembersInjector<SplashActivity> create(
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    return new SplashActivity_MembersInjector(onboardingPreferencesProvider);
  }

  @Override
  public void injectMembers(SplashActivity instance) {
    injectOnboardingPreferences(instance, onboardingPreferencesProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.splash.SplashActivity.onboardingPreferences")
  public static void injectOnboardingPreferences(SplashActivity instance,
      OnboardingPreferences onboardingPreferences) {
    instance.onboardingPreferences = onboardingPreferences;
  }
}
