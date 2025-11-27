package com.porakhela.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LessonLogDao_Impl implements LessonLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LessonLog> __insertionAdapterOfLessonLog;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldLogs;

  public LessonLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLessonLog = new EntityInsertionAdapter<LessonLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `lesson_logs` (`id`,`lessonId`,`timestamp`,`pointsEarned`,`durationMinutes`,`childName`,`subjectName`,`categoryName`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LessonLog entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getLessonId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getLessonId());
        }
        statement.bindLong(3, entity.getTimestamp());
        statement.bindLong(4, entity.getPointsEarned());
        statement.bindLong(5, entity.getDurationMinutes());
        if (entity.getChildName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getChildName());
        }
        if (entity.getSubjectName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getSubjectName());
        }
        if (entity.getCategoryName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCategoryName());
        }
      }
    };
    this.__preparedStmtOfDeleteOldLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM lesson_logs WHERE timestamp < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertLessonLog(final LessonLog log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLessonLog.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldLogs(final long cutoffTime, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldLogs.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTime);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LessonLog>> getAllLogs() {
    final String _sql = "SELECT * FROM lesson_logs ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"lesson_logs"}, new Callable<List<LessonLog>>() {
      @Override
      @NonNull
      public List<LessonLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "lessonId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfPointsEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsEarned");
          final int _cursorIndexOfDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMinutes");
          final int _cursorIndexOfChildName = CursorUtil.getColumnIndexOrThrow(_cursor, "childName");
          final int _cursorIndexOfSubjectName = CursorUtil.getColumnIndexOrThrow(_cursor, "subjectName");
          final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
          final List<LessonLog> _result = new ArrayList<LessonLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LessonLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpLessonId;
            if (_cursor.isNull(_cursorIndexOfLessonId)) {
              _tmpLessonId = null;
            } else {
              _tmpLessonId = _cursor.getString(_cursorIndexOfLessonId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final int _tmpPointsEarned;
            _tmpPointsEarned = _cursor.getInt(_cursorIndexOfPointsEarned);
            final int _tmpDurationMinutes;
            _tmpDurationMinutes = _cursor.getInt(_cursorIndexOfDurationMinutes);
            final String _tmpChildName;
            if (_cursor.isNull(_cursorIndexOfChildName)) {
              _tmpChildName = null;
            } else {
              _tmpChildName = _cursor.getString(_cursorIndexOfChildName);
            }
            final String _tmpSubjectName;
            if (_cursor.isNull(_cursorIndexOfSubjectName)) {
              _tmpSubjectName = null;
            } else {
              _tmpSubjectName = _cursor.getString(_cursorIndexOfSubjectName);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            _item = new LessonLog(_tmpId,_tmpLessonId,_tmpTimestamp,_tmpPointsEarned,_tmpDurationMinutes,_tmpChildName,_tmpSubjectName,_tmpCategoryName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLogsForDate(final String date,
      final Continuation<? super List<LessonLog>> $completion) {
    final String _sql = "SELECT * FROM lesson_logs WHERE date(timestamp/1000, 'unixepoch') = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<LessonLog>>() {
      @Override
      @NonNull
      public List<LessonLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "lessonId");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfPointsEarned = CursorUtil.getColumnIndexOrThrow(_cursor, "pointsEarned");
          final int _cursorIndexOfDurationMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "durationMinutes");
          final int _cursorIndexOfChildName = CursorUtil.getColumnIndexOrThrow(_cursor, "childName");
          final int _cursorIndexOfSubjectName = CursorUtil.getColumnIndexOrThrow(_cursor, "subjectName");
          final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
          final List<LessonLog> _result = new ArrayList<LessonLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LessonLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpLessonId;
            if (_cursor.isNull(_cursorIndexOfLessonId)) {
              _tmpLessonId = null;
            } else {
              _tmpLessonId = _cursor.getString(_cursorIndexOfLessonId);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final int _tmpPointsEarned;
            _tmpPointsEarned = _cursor.getInt(_cursorIndexOfPointsEarned);
            final int _tmpDurationMinutes;
            _tmpDurationMinutes = _cursor.getInt(_cursorIndexOfDurationMinutes);
            final String _tmpChildName;
            if (_cursor.isNull(_cursorIndexOfChildName)) {
              _tmpChildName = null;
            } else {
              _tmpChildName = _cursor.getString(_cursorIndexOfChildName);
            }
            final String _tmpSubjectName;
            if (_cursor.isNull(_cursorIndexOfSubjectName)) {
              _tmpSubjectName = null;
            } else {
              _tmpSubjectName = _cursor.getString(_cursorIndexOfSubjectName);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            _item = new LessonLog(_tmpId,_tmpLessonId,_tmpTimestamp,_tmpPointsEarned,_tmpDurationMinutes,_tmpChildName,_tmpSubjectName,_tmpCategoryName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDailyReports(final Continuation<? super List<DailyReportRaw>> $completion) {
    final String _sql = "SELECT date(timestamp/1000, 'unixepoch') as date,\n"
            + "                     COUNT(*) as lessonsCompleted,\n"
            + "                     SUM(pointsEarned) as totalPointsEarned,\n"
            + "                     SUM(durationMinutes) as totalTimeMinutes\n"
            + "              FROM lesson_logs \n"
            + "              WHERE date(timestamp/1000, 'unixepoch') >= date('now', '-30 days')\n"
            + "              GROUP BY date(timestamp/1000, 'unixepoch')\n"
            + "              ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyReportRaw>>() {
      @Override
      @NonNull
      public List<DailyReportRaw> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfLessonsCompleted = 1;
          final int _cursorIndexOfTotalPointsEarned = 2;
          final int _cursorIndexOfTotalTimeMinutes = 3;
          final List<DailyReportRaw> _result = new ArrayList<DailyReportRaw>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyReportRaw _item;
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpLessonsCompleted;
            _tmpLessonsCompleted = _cursor.getInt(_cursorIndexOfLessonsCompleted);
            final int _tmpTotalPointsEarned;
            _tmpTotalPointsEarned = _cursor.getInt(_cursorIndexOfTotalPointsEarned);
            final int _tmpTotalTimeMinutes;
            _tmpTotalTimeMinutes = _cursor.getInt(_cursorIndexOfTotalTimeMinutes);
            _item = new DailyReportRaw(_tmpDate,_tmpLessonsCompleted,_tmpTotalPointsEarned,_tmpTotalTimeMinutes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalLessonsCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM lesson_logs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLastLessonTimestamp(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT timestamp FROM lesson_logs ORDER BY timestamp DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getLong(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTodayScreenTime(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT SUM(durationMinutes) FROM lesson_logs WHERE date(timestamp/1000, 'unixepoch') = date('now')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
