package com.porakhela.core.notifications;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class StreakNotificationScheduler_Factory implements Factory<StreakNotificationScheduler> {
  private final Provider<Context> contextProvider;

  public StreakNotificationScheduler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StreakNotificationScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static StreakNotificationScheduler_Factory create(Provider<Context> contextProvider) {
    return new StreakNotificationScheduler_Factory(contextProvider);
  }

  public static StreakNotificationScheduler newInstance(Context context) {
    return new StreakNotificationScheduler(context);
  }
}
