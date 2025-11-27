package com.porakhela.di;

import android.content.Context;
import com.porakhela.data.local.ParentDashboardDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideParentDashboardDatabaseFactory implements Factory<ParentDashboardDatabase> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideParentDashboardDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ParentDashboardDatabase get() {
    return provideParentDashboardDatabase(contextProvider.get());
  }

  public static DataModule_ProvideParentDashboardDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DataModule_ProvideParentDashboardDatabaseFactory(contextProvider);
  }

  public static ParentDashboardDatabase provideParentDashboardDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideParentDashboardDatabase(context));
  }
}
