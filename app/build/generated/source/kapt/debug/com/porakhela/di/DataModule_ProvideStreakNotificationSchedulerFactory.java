package com.porakhela.di;

import android.content.Context;
import com.porakhela.core.notifications.StreakNotificationScheduler;
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
public final class DataModule_ProvideStreakNotificationSchedulerFactory implements Factory<StreakNotificationScheduler> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideStreakNotificationSchedulerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StreakNotificationScheduler get() {
    return provideStreakNotificationScheduler(contextProvider.get());
  }

  public static DataModule_ProvideStreakNotificationSchedulerFactory create(
      Provider<Context> contextProvider) {
    return new DataModule_ProvideStreakNotificationSchedulerFactory(contextProvider);
  }

  public static StreakNotificationScheduler provideStreakNotificationScheduler(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideStreakNotificationScheduler(context));
  }
}
