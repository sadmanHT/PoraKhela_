package com.porakhela.ui.onboarding;

import com.porakhela.data.local.OnboardingPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<OnboardingPreferences> onboardingPreferencesProvider;

  public OnboardingViewModel_Factory(
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    this.onboardingPreferencesProvider = onboardingPreferencesProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(onboardingPreferencesProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<OnboardingPreferences> onboardingPreferencesProvider) {
    return new OnboardingViewModel_Factory(onboardingPreferencesProvider);
  }

  public static OnboardingViewModel newInstance(OnboardingPreferences onboardingPreferences) {
    return new OnboardingViewModel(onboardingPreferences);
  }
}
