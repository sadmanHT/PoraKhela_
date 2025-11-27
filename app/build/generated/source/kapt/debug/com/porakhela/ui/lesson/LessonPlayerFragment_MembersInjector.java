package com.porakhela.ui.lesson;

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
public final class LessonPlayerFragment_MembersInjector implements MembersInjector<LessonPlayerFragment> {
  private final Provider<TimeTracker> timeTrackerProvider;

  private final Provider<StreakManager> streakManagerProvider;

  public LessonPlayerFragment_MembersInjector(Provider<TimeTracker> timeTrackerProvider,
      Provider<StreakManager> streakManagerProvider) {
    this.timeTrackerProvider = timeTrackerProvider;
    this.streakManagerProvider = streakManagerProvider;
  }

  public static MembersInjector<LessonPlayerFragment> create(
      Provider<TimeTracker> timeTrackerProvider, Provider<StreakManager> streakManagerProvider) {
    return new LessonPlayerFragment_MembersInjector(timeTrackerProvider, streakManagerProvider);
  }

  @Override
  public void injectMembers(LessonPlayerFragment instance) {
    injectTimeTracker(instance, timeTrackerProvider.get());
    injectStreakManager(instance, streakManagerProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.lesson.LessonPlayerFragment.timeTracker")
  public static void injectTimeTracker(LessonPlayerFragment instance, TimeTracker timeTracker) {
    instance.timeTracker = timeTracker;
  }

  @InjectedFieldSignature("com.porakhela.ui.lesson.LessonPlayerFragment.streakManager")
  public static void injectStreakManager(LessonPlayerFragment instance,
      StreakManager streakManager) {
    instance.streakManager = streakManager;
  }
}
