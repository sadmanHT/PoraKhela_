package com.porakhela.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LessonRepository_Factory implements Factory<LessonRepository> {
  private final Provider<Context> contextProvider;

  public LessonRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LessonRepository get() {
    return newInstance(contextProvider.get());
  }

  public static LessonRepository_Factory create(Provider<Context> contextProvider) {
    return new LessonRepository_Factory(contextProvider);
  }

  public static LessonRepository newInstance(Context context) {
    return new LessonRepository(context);
  }
}
