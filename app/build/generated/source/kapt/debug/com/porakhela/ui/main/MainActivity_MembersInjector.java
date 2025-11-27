package com.porakhela.ui.main;

import com.porakhela.core.notifications.StreakNotificationScheduler;
import com.porakhela.data.local.StreakPreferences;
import com.porakhela.data.tracking.StreakManager;
import com.porakhela.data.tracking.TimeTracker;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<StreakNotificationScheduler> notificationSchedulerProvider;

  private final Provider<StreakManager> streakManagerProvider;

  private final Provider<TimeTracker> timeTrackerProvider;

  private final Provider<StreakPreferences> streakPreferencesProvider;

  public MainActivity_MembersInjector(
      Provider<StreakNotificationScheduler> notificationSchedulerProvider,
      Provider<StreakManager> streakManagerProvider, Provider<TimeTracker> timeTrackerProvider,
      Provider<StreakPreferences> streakPreferencesProvider) {
    this.notificationSchedulerProvider = notificationSchedulerProvider;
    this.streakManagerProvider = streakManagerProvider;
    this.timeTrackerProvider = timeTrackerProvider;
    this.streakPreferencesProvider = streakPreferencesProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<StreakNotificationScheduler> notificationSchedulerProvider,
      Provider<StreakManager> streakManagerProvider, Provider<TimeTracker> timeTrackerProvider,
      Provider<StreakPreferences> streakPreferencesProvider) {
    return new MainActivity_MembersInjector(notificationSchedulerProvider, streakManagerProvider, timeTrackerProvider, streakPreferencesProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectNotificationScheduler(instance, notificationSchedulerProvider.get());
    injectStreakManager(instance, streakManagerProvider.get());
    injectTimeTracker(instance, timeTrackerProvider.get());
    injectStreakPreferences(instance, streakPreferencesProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.main.MainActivity.notificationScheduler")
  public static void injectNotificationScheduler(MainActivity instance,
      StreakNotificationScheduler notificationScheduler) {
    instance.notificationScheduler = notificationScheduler;
  }

  @InjectedFieldSignature("com.porakhela.ui.main.MainActivity.streakManager")
  public static void injectStreakManager(MainActivity instance, StreakManager streakManager) {
    instance.streakManager = streakManager;
  }

  @InjectedFieldSignature("com.porakhela.ui.main.MainActivity.timeTracker")
  public static void injectTimeTracker(MainActivity instance, TimeTracker timeTracker) {
    instance.timeTracker = timeTracker;
  }

  @InjectedFieldSignature("com.porakhela.ui.main.MainActivity.streakPreferences")
  public static void injectStreakPreferences(MainActivity instance,
      StreakPreferences streakPreferences) {
    instance.streakPreferences = streakPreferences;
  }
}
