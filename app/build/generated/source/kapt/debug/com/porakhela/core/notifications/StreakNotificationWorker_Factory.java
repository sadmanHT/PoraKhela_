package com.porakhela.core.notifications;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.porakhela.data.tracking.StreakManager;
import dagger.internal.DaggerGenerated;
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
public final class StreakNotificationWorker_Factory {
  private final Provider<StreakManager> streakManagerProvider;

  public StreakNotificationWorker_Factory(Provider<StreakManager> streakManagerProvider) {
    this.streakManagerProvider = streakManagerProvider;
  }

  public StreakNotificationWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, streakManagerProvider.get());
  }

  public static StreakNotificationWorker_Factory create(
      Provider<StreakManager> streakManagerProvider) {
    return new StreakNotificationWorker_Factory(streakManagerProvider);
  }

  public static StreakNotificationWorker newInstance(Context context, WorkerParameters workerParams,
      StreakManager streakManager) {
    return new StreakNotificationWorker(context, workerParams, streakManager);
  }
}
