package com.porakhela.ui.lesson;

import com.porakhela.data.local.UserPreferences;
import com.porakhela.data.repository.LessonRepository;
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
public final class LessonPlayerViewModel_Factory implements Factory<LessonPlayerViewModel> {
  private final Provider<LessonRepository> lessonRepositoryProvider;

  private final Provider<UserPreferences> userPreferencesProvider;

  public LessonPlayerViewModel_Factory(Provider<LessonRepository> lessonRepositoryProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    this.lessonRepositoryProvider = lessonRepositoryProvider;
    this.userPreferencesProvider = userPreferencesProvider;
  }

  @Override
  public LessonPlayerViewModel get() {
    return newInstance(lessonRepositoryProvider.get(), userPreferencesProvider.get());
  }

  public static LessonPlayerViewModel_Factory create(
      Provider<LessonRepository> lessonRepositoryProvider,
      Provider<UserPreferences> userPreferencesProvider) {
    return new LessonPlayerViewModel_Factory(lessonRepositoryProvider, userPreferencesProvider);
  }

  public static LessonPlayerViewModel newInstance(LessonRepository lessonRepository,
      UserPreferences userPreferences) {
    return new LessonPlayerViewModel(lessonRepository, userPreferences);
  }
}
