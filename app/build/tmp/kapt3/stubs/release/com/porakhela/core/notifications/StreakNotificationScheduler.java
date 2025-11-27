package com.porakhela.core.notifications;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/porakhela/core/notifications/StreakNotificationScheduler;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "workManager", "Landroidx/work/WorkManager;", "cancelNotifications", "", "scheduleDaily8PMNotification", "app_release"})
public final class StreakNotificationScheduler {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.work.WorkManager workManager = null;
    
    @javax.inject.Inject()
    public StreakNotificationScheduler(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void scheduleDaily8PMNotification() {
    }
    
    public final void cancelNotifications() {
    }
}