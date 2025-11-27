package com.porakhela.di;

import com.porakhela.data.local.LessonLogDao;
import com.porakhela.data.local.ParentDashboardDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideLessonLogDaoFactory implements Factory<LessonLogDao> {
  private final Provider<ParentDashboardDatabase> databaseProvider;

  public DataModule_ProvideLessonLogDaoFactory(Provider<ParentDashboardDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LessonLogDao get() {
    return provideLessonLogDao(databaseProvider.get());
  }

  public static DataModule_ProvideLessonLogDaoFactory create(
      Provider<ParentDashboardDatabase> databaseProvider) {
    return new DataModule_ProvideLessonLogDaoFactory(databaseProvider);
  }

  public static LessonLogDao provideLessonLogDao(ParentDashboardDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideLessonLogDao(database));
  }
}
