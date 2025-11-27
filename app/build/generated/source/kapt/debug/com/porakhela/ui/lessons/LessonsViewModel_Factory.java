package com.porakhela.ui.lessons;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LessonsViewModel_Factory implements Factory<LessonsViewModel> {
  @Override
  public LessonsViewModel get() {
    return newInstance();
  }

  public static LessonsViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LessonsViewModel newInstance() {
    return new LessonsViewModel();
  }

  private static final class InstanceHolder {
    private static final LessonsViewModel_Factory INSTANCE = new LessonsViewModel_Factory();
  }
}
