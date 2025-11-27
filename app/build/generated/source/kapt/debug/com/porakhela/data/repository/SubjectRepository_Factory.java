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
public final class SubjectRepository_Factory implements Factory<SubjectRepository> {
  private final Provider<Context> contextProvider;

  public SubjectRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SubjectRepository get() {
    return newInstance(contextProvider.get());
  }

  public static SubjectRepository_Factory create(Provider<Context> contextProvider) {
    return new SubjectRepository_Factory(contextProvider);
  }

  public static SubjectRepository newInstance(Context context) {
    return new SubjectRepository(context);
  }
}
