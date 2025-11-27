package com.porakhela.ui.streak;

import com.porakhela.data.tracking.StreakManager;
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
public final class StreakViewModel_Factory implements Factory<StreakViewModel> {
  private final Provider<StreakManager> streakManagerProvider;

  public StreakViewModel_Factory(Provider<StreakManager> streakManagerProvider) {
    this.streakManagerProvider = streakManagerProvider;
  }

  @Override
  public StreakViewModel get() {
    return newInstance(streakManagerProvider.get());
  }

  public static StreakViewModel_Factory create(Provider<StreakManager> streakManagerProvider) {
    return new StreakViewModel_Factory(streakManagerProvider);
  }

  public static StreakViewModel newInstance(StreakManager streakManager) {
    return new StreakViewModel(streakManager);
  }
}
