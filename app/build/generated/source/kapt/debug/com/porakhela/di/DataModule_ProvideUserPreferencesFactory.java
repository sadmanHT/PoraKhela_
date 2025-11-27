package com.porakhela.di;

import android.content.Context;
import com.porakhela.data.local.UserPreferences;
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
public final class DataModule_ProvideUserPreferencesFactory implements Factory<UserPreferences> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideUserPreferencesFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public UserPreferences get() {
    return provideUserPreferences(contextProvider.get());
  }

  public static DataModule_ProvideUserPreferencesFactory create(Provider<Context> contextProvider) {
    return new DataModule_ProvideUserPreferencesFactory(contextProvider);
  }

  public static UserPreferences provideUserPreferences(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideUserPreferences(context));
  }
}
