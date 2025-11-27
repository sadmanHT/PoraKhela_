package com.porakhela.data.local;

/**
 * Room database for Parent Dashboard lesson logging and rewards
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/porakhela/data/local/ParentDashboardDatabase;", "Landroidx/room/RoomDatabase;", "()V", "lessonLogDao", "Lcom/porakhela/data/local/LessonLogDao;", "rewardDao", "Lcom/porakhela/data/local/dao/RewardDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.porakhela.data.local.LessonLog.class, com.porakhela.data.model.Reward.class, com.porakhela.data.model.RewardRedemption.class}, version = 2, exportSchema = false)
public abstract class ParentDashboardDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.porakhela.data.local.ParentDashboardDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.local.ParentDashboardDatabase.Companion Companion = null;
    
    public ParentDashboardDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.porakhela.data.local.LessonLogDao lessonLogDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.porakhela.data.local.dao.RewardDao rewardDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/porakhela/data/local/ParentDashboardDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/porakhela/data/local/ParentDashboardDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.porakhela.data.local.ParentDashboardDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}