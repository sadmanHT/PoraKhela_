package com.porakhela.ui.home;

import com.porakhela.data.local.UserPreferences;
import com.porakhela.data.repository.ApplinkRepository;
import com.porakhela.data.tracking.StreakManager;
import com.porakhela.utils.PorapointsManager;
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
public final class HomeFragment_MembersInjector implements MembersInjector<HomeFragment> {
  private final Provider<UserPreferences> userPreferencesProvider;

  private final Provider<ApplinkRepository> applinkRepositoryProvider;

  private final Provider<PorapointsManager> porapointsManagerProvider;

  private final Provider<StreakManager> streakManagerProvider;

  public HomeFragment_MembersInjector(Provider<UserPreferences> userPreferencesProvider,
      Provider<ApplinkRepository> applinkRepositoryProvider,
      Provider<PorapointsManager> porapointsManagerProvider,
      Provider<StreakManager> streakManagerProvider) {
    this.userPreferencesProvider = userPreferencesProvider;
    this.applinkRepositoryProvider = applinkRepositoryProvider;
    this.porapointsManagerProvider = porapointsManagerProvider;
    this.streakManagerProvider = streakManagerProvider;
  }

  public static MembersInjector<HomeFragment> create(
      Provider<UserPreferences> userPreferencesProvider,
      Provider<ApplinkRepository> applinkRepositoryProvider,
      Provider<PorapointsManager> porapointsManagerProvider,
      Provider<StreakManager> streakManagerProvider) {
    return new HomeFragment_MembersInjector(userPreferencesProvider, applinkRepositoryProvider, porapointsManagerProvider, streakManagerProvider);
  }

  @Override
  public void injectMembers(HomeFragment instance) {
    injectUserPreferences(instance, userPreferencesProvider.get());
    injectApplinkRepository(instance, applinkRepositoryProvider.get());
    injectPorapointsManager(instance, porapointsManagerProvider.get());
    injectStreakManager(instance, streakManagerProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.home.HomeFragment.userPreferences")
  public static void injectUserPreferences(HomeFragment instance, UserPreferences userPreferences) {
    instance.userPreferences = userPreferences;
  }

  @InjectedFieldSignature("com.porakhela.ui.home.HomeFragment.applinkRepository")
  public static void injectApplinkRepository(HomeFragment instance,
      ApplinkRepository applinkRepository) {
    instance.applinkRepository = applinkRepository;
  }

  @InjectedFieldSignature("com.porakhela.ui.home.HomeFragment.porapointsManager")
  public static void injectPorapointsManager(HomeFragment instance,
      PorapointsManager porapointsManager) {
    instance.porapointsManager = porapointsManager;
  }

  @InjectedFieldSignature("com.porakhela.ui.home.HomeFragment.streakManager")
  public static void injectStreakManager(HomeFragment instance, StreakManager streakManager) {
    instance.streakManager = streakManager;
  }
}
