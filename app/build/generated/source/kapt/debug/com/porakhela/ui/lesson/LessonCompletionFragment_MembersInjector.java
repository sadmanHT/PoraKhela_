package com.porakhela.ui.lesson;

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
public final class LessonCompletionFragment_MembersInjector implements MembersInjector<LessonCompletionFragment> {
  private final Provider<PorapointsManager> porapointsManagerProvider;

  public LessonCompletionFragment_MembersInjector(
      Provider<PorapointsManager> porapointsManagerProvider) {
    this.porapointsManagerProvider = porapointsManagerProvider;
  }

  public static MembersInjector<LessonCompletionFragment> create(
      Provider<PorapointsManager> porapointsManagerProvider) {
    return new LessonCompletionFragment_MembersInjector(porapointsManagerProvider);
  }

  @Override
  public void injectMembers(LessonCompletionFragment instance) {
    injectPorapointsManager(instance, porapointsManagerProvider.get());
  }

  @InjectedFieldSignature("com.porakhela.ui.lesson.LessonCompletionFragment.porapointsManager")
  public static void injectPorapointsManager(LessonCompletionFragment instance,
      PorapointsManager porapointsManager) {
    instance.porapointsManager = porapointsManager;
  }
}
