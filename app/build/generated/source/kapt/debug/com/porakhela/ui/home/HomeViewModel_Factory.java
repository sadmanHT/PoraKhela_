package com.porakhela.ui.home;

import com.porakhela.data.local.UserPreferences;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<UserPreferences> userPreferencesProvider;

  public HomeViewModel_Factory(Provider<UserPreferences> userPreferencesProvider) {
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(userPreferencesProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<UserPreferences> userPreferencesProvider) {
    return new HomeViewModel_Factory(userPreferencesProvider);
  }

  public static HomeViewModel newInstance(UserPreferences userPreferences) {
    return new HomeViewModel(userPreferences);
  }
}
