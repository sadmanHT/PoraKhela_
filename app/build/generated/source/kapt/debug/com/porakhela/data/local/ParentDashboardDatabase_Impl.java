package com.porakhela.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.porakhela.data.local.dao.RewardDao;
import com.porakhela.data.local.dao.RewardDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ParentDashboardDatabase_Impl extends ParentDashboardDatabase {
  private volatile LessonLogDao _lessonLogDao;

  private volatile RewardDao _rewardDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `lesson_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `lessonId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `pointsEarned` INTEGER NOT NULL, `durationMinutes` INTEGER NOT NULL, `childName` TEXT NOT NULL, `subjectName` TEXT NOT NULL, `categoryName` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `rewards` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `cost` INTEGER NOT NULL, `description` TEXT, `icon_resource` TEXT, `category` TEXT NOT NULL, `approval_required` INTEGER NOT NULL, `is_available` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `redemptions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `rewardId` INTEGER NOT NULL, `rewardName` TEXT NOT NULL, `cost` INTEGER NOT NULL, `status` TEXT NOT NULL, `requestedAt` INTEGER NOT NULL, `approvedAt` INTEGER, `redeemedAt` INTEGER, `parentApprovalRequired` INTEGER NOT NULL, `apiTransactionId` TEXT, `errorMessage` TEXT, FOREIGN KEY(`rewardId`) REFERENCES `rewards`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b43922aed273da3a5a7b52f21093aa24')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `lesson_logs`");
        db.execSQL("DROP TABLE IF EXISTS `rewards`");
        db.execSQL("DROP TABLE IF EXISTS `redemptions`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLessonLogs = new HashMap<String, TableInfo.Column>(8);
        _columnsLessonLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("lessonId", new TableInfo.Column("lessonId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("pointsEarned", new TableInfo.Column("pointsEarned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("childName", new TableInfo.Column("childName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("subjectName", new TableInfo.Column("subjectName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonLogs.put("categoryName", new TableInfo.Column("categoryName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLessonLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLessonLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLessonLogs = new TableInfo("lesson_logs", _columnsLessonLogs, _foreignKeysLessonLogs, _indicesLessonLogs);
        final TableInfo _existingLessonLogs = TableInfo.read(db, "lesson_logs");
        if (!_infoLessonLogs.equals(_existingLessonLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "lesson_logs(com.porakhela.data.local.LessonLog).\n"
                  + " Expected:\n" + _infoLessonLogs + "\n"
                  + " Found:\n" + _existingLessonLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsRewards = new HashMap<String, TableInfo.Column>(8);
        _columnsRewards.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("cost", new TableInfo.Column("cost", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("icon_resource", new TableInfo.Column("icon_resource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("approval_required", new TableInfo.Column("approval_required", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRewards.put("is_available", new TableInfo.Column("is_available", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRewards = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRewards = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRewards = new TableInfo("rewards", _columnsRewards, _foreignKeysRewards, _indicesRewards);
        final TableInfo _existingRewards = TableInfo.read(db, "rewards");
        if (!_infoRewards.equals(_existingRewards)) {
          return new RoomOpenHelper.ValidationResult(false, "rewards(com.porakhela.data.model.Reward).\n"
                  + " Expected:\n" + _infoRewards + "\n"
                  + " Found:\n" + _existingRewards);
        }
        final HashMap<String, TableInfo.Column> _columnsRedemptions = new HashMap<String, TableInfo.Column>(11);
        _columnsRedemptions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("rewardId", new TableInfo.Column("rewardId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("rewardName", new TableInfo.Column("rewardName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("cost", new TableInfo.Column("cost", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("requestedAt", new TableInfo.Column("requestedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("approvedAt", new TableInfo.Column("approvedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("redeemedAt", new TableInfo.Column("redeemedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("parentApprovalRequired", new TableInfo.Column("parentApprovalRequired", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("apiTransactionId", new TableInfo.Column("apiTransactionId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedemptions.put("errorMessage", new TableInfo.Column("errorMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRedemptions = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysRedemptions.add(new TableInfo.ForeignKey("rewards", "CASCADE", "NO ACTION", Arrays.asList("rewardId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesRedemptions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRedemptions = new TableInfo("redemptions", _columnsRedemptions, _foreignKeysRedemptions, _indicesRedemptions);
        final TableInfo _existingRedemptions = TableInfo.read(db, "redemptions");
        if (!_infoRedemptions.equals(_existingRedemptions)) {
          return new RoomOpenHelper.ValidationResult(false, "redemptions(com.porakhela.data.model.RewardRedemption).\n"
                  + " Expected:\n" + _infoRedemptions + "\n"
                  + " Found:\n" + _existingRedemptions);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "b43922aed273da3a5a7b52f21093aa24", "727113b1a4e0257660b3cf8f8231f89a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "lesson_logs","rewards","redemptions");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `lesson_logs`");
      _db.execSQL("DELETE FROM `rewards`");
      _db.execSQL("DELETE FROM `redemptions`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LessonLogDao.class, LessonLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RewardDao.class, RewardDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LessonLogDao lessonLogDao() {
    if (_lessonLogDao != null) {
      return _lessonLogDao;
    } else {
      synchronized(this) {
        if(_lessonLogDao == null) {
          _lessonLogDao = new LessonLogDao_Impl(this);
        }
        return _lessonLogDao;
      }
    }
  }

  @Override
  public RewardDao rewardDao() {
    if (_rewardDao != null) {
      return _rewardDao;
    } else {
      synchronized(this) {
        if(_rewardDao == null) {
          _rewardDao = new RewardDao_Impl(this);
        }
        return _rewardDao;
      }
    }
  }
}
