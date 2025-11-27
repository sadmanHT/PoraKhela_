package com.porakhela.data.repository;

import com.porakhela.data.local.ParentDashboardDatabase;
import com.porakhela.data.local.SecureParentPreferences;
import com.porakhela.data.local.UserPreferences;
import com.porakhela.utils.PorapointsManager;
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
public final class ParentDashboardRepository_Factory implements Factory<ParentDashboardRepository> {
  private final Provider<SecureParentPreferences> securePrefsProvider;

  private final Provider<ParentDashboardDatabase> databaseProvider;

  private final Provider<PorapointsManager> porapointsManagerProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public ParentDashboardRepository_Factory(Provider<SecureParentPreferences> securePrefsProvider,
      Provider<ParentDashboardDatabase> databaseProvider,
      Provider<PorapointsManager> porapointsManagerProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.securePrefsProvider = securePrefsProvider;
    this.databaseProvider = databaseProvider;
    this.porapointsManagerProvider = porapointsManagerProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public ParentDashboardRepository get() {
    return newInstance(securePrefsProvider.get(), databaseProvider.get(), porapointsManagerProvider.get(), userPreferencesProvider.get());
  }

  public static ParentDashboardRepository_Factory create(
      Provider<SecureParentPreferences> securePrefsProvider,
      Provider<ParentDashboardDatabase> databaseProvider,
      Provider<PorapointsManager> porapointsManagerProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new ParentDashboardRepository_Factory(securePrefsProvider, databaseProvider, porapointsManagerProvider, userPreferencesProvider);
  }

  public static ParentDashboardRepository newInstance(SecureParentPreferences securePrefs,
      ParentDashboardDatabase database, PorapointsManager porapointsManager,
      UserPreferences userPreferences) {
    return new ParentDashboardRepository(securePrefs, database, porapointsManager, userPreferences);
  }
}
