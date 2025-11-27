package com.porakhela.core.notifications;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class StreakNotificationWorker_AssistedFactory_Impl implements StreakNotificationWorker_AssistedFactory {
  private final StreakNotificationWorker_Factory delegateFactory;

  StreakNotificationWorker_AssistedFactory_Impl(StreakNotificationWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public StreakNotificationWorker create(Context arg0, WorkerParameters arg1) {
    return delegateFactory.get(arg0, arg1);
  }

  public static Provider<StreakNotificationWorker_AssistedFactory> create(
      StreakNotificationWorker_Factory delegateFactory) {
    return InstanceFactory.create(new StreakNotificationWorker_AssistedFactory_Impl(delegateFactory));
  }
}
