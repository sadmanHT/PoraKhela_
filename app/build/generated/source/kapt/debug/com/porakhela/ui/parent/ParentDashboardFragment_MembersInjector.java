package com.porakhela.ui.parent;

import com.porakhela.data.tracking.StreakManager;
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
public final class ParentDashboardFragment_MembersInjector implements MembersInjector<ParentDashboardFragment> {
  private final Provider<StreakManager> streakManagerProvider;

  public ParentDashboardFragment_MembersInjector(Provider<StreakManager> streakManagerProvider) {
    this.streakManagerProvider = streakManagerProvider;
  }

  public static MembersInjector<ParentDashboardFragment> create(
      Provider<StreakManager> streakManagerProvider) {
    return new ParentDashboardFragment_MembersInjector(streakManagerProvider);
  }

  @Override
  public void injectMembers(ParentDashboardFragment instance) {
    injectStreakManager(instance, streakManagerProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.parent.ParentDashboardFragment.streakManager")
  public static void injectStreakManager(ParentDashboardFragment instance,
      StreakManager streakManager) {
    instance.streakManager = streakManager;
  }
}
