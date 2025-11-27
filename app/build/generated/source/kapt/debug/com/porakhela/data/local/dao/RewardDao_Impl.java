package com.porakhela.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.porakhela.data.model.RedemptionStatus;
import com.porakhela.data.model.Reward;
import com.porakhela.data.model.RewardRedemption;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
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
public final class RewardDao_Impl implements RewardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Reward> __insertionAdapterOfReward;

  private final EntityInsertionAdapter<RewardRedemption> __insertionAdapterOfRewardRedemption;

  private final EntityDeletionOrUpdateAdapter<Reward> __deletionAdapterOfReward;

  private final EntityDeletionOrUpdateAdapter<RewardRedemption> __deletionAdapterOfRewardRedemption;

  private final EntityDeletionOrUpdateAdapter<Reward> __updateAdapterOfReward;

  private final EntityDeletionOrUpdateAdapter<RewardRedemption> __updateAdapterOfRewardRedemption;

  private final SharedSQLiteStatement __preparedStmtOfClearAllRewards;

  public RewardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReward = new EntityInsertionAdapter<Reward>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `rewards` (`id`,`name`,`cost`,`description`,`icon_resource`,`category`,`approval_required`,`is_available`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Reward entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getCost());
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getIconResource() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getIconResource());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        final int _tmp = entity.getApprovalRequired() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.isAvailable() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
      }
    };
    this.__insertionAdapterOfRewardRedemption = new EntityInsertionAdapter<RewardRedemption>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `redemptions` (`id`,`rewardId`,`rewardName`,`cost`,`status`,`requestedAt`,`approvedAt`,`redeemedAt`,`parentApprovalRequired`,`apiTransactionId`,`errorMessage`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RewardRedemption entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRewardId());
        if (entity.getRewardName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRewardName());
        }
        statement.bindLong(4, entity.getCost());
        statement.bindString(5, __RedemptionStatus_enumToString(entity.getStatus()));
        statement.bindLong(6, entity.getRequestedAt());
        if (entity.getApprovedAt() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getApprovedAt());
        }
        if (entity.getRedeemedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRedeemedAt());
        }
        final int _tmp = entity.getParentApprovalRequired() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getApiTransactionId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getApiTransactionId());
        }
        if (entity.getErrorMessage() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getErrorMessage());
        }
      }
    };
    this.__deletionAdapterOfReward = new EntityDeletionOrUpdateAdapter<Reward>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `rewards` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Reward entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfRewardRedemption = new EntityDeletionOrUpdateAdapter<RewardRedemption>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `redemptions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RewardRedemption entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfReward = new EntityDeletionOrUpdateAdapter<Reward>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `rewards` SET `id` = ?,`name` = ?,`cost` = ?,`description` = ?,`icon_resource` = ?,`category` = ?,`approval_required` = ?,`is_available` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Reward entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getCost());
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        if (entity.getIconResource() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getIconResource());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        final int _tmp = entity.getApprovalRequired() ? 1 : 0;
        statement.bindLong(7, _tmp);
        final int _tmp_1 = entity.isAvailable() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        statement.bindLong(9, entity.getId());
      }
    };
    this.__updateAdapterOfRewardRedemption = new EntityDeletionOrUpdateAdapter<RewardRedemption>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `redemptions` SET `id` = ?,`rewardId` = ?,`rewardName` = ?,`cost` = ?,`status` = ?,`requestedAt` = ?,`approvedAt` = ?,`redeemedAt` = ?,`parentApprovalRequired` = ?,`apiTransactionId` = ?,`errorMessage` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RewardRedemption entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRewardId());
        if (entity.getRewardName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRewardName());
        }
        statement.bindLong(4, entity.getCost());
        statement.bindString(5, __RedemptionStatus_enumToString(entity.getStatus()));
        statement.bindLong(6, entity.getRequestedAt());
        if (entity.getApprovedAt() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getApprovedAt());
        }
        if (entity.getRedeemedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRedeemedAt());
        }
        final int _tmp = entity.getParentApprovalRequired() ? 1 : 0;
        statement.bindLong(9, _tmp);
        if (entity.getApiTransactionId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getApiTransactionId());
        }
        if (entity.getErrorMessage() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getErrorMessage());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfClearAllRewards = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM rewards";
        return _query;
      }
    };
  }

  @Override
  public Object insertRewards(final List<Reward> rewards,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReward.insert(rewards);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertReward(final Reward reward, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReward.insert(reward);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRedemption(final RewardRedemption redemption,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRewardRedemption.insertAndReturnId(redemption);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReward(final Reward reward, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfReward.handle(reward);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRedemption(final RewardRedemption redemption,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRewardRedemption.handle(redemption);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateReward(final Reward reward, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReward.handle(reward);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRedemption(final RewardRedemption redemption,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRewardRedemption.handle(redemption);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllRewards(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllRewards.acquire();
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
          __preparedStmtOfClearAllRewards.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Reward>> getAllAvailableRewards() {
    final String _sql = "SELECT * FROM rewards WHERE is_available = 1 ORDER BY cost ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rewards"}, new Callable<List<Reward>>() {
      @Override
      @NonNull
      public List<Reward> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconResource = CursorUtil.getColumnIndexOrThrow(_cursor, "icon_resource");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "approval_required");
          final int _cursorIndexOfIsAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "is_available");
          final List<Reward> _result = new ArrayList<Reward>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Reward _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIconResource;
            if (_cursor.isNull(_cursorIndexOfIconResource)) {
              _tmpIconResource = null;
            } else {
              _tmpIconResource = _cursor.getString(_cursorIndexOfIconResource);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfApprovalRequired);
            _tmpApprovalRequired = _tmp != 0;
            final boolean _tmpIsAvailable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAvailable);
            _tmpIsAvailable = _tmp_1 != 0;
            _item = new Reward(_tmpId,_tmpName,_tmpCost,_tmpDescription,_tmpIconResource,_tmpCategory,_tmpApprovalRequired,_tmpIsAvailable);
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
  public Object getRewardById(final int rewardId, final Continuation<? super Reward> $completion) {
    final String _sql = "SELECT * FROM rewards WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, rewardId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Reward>() {
      @Override
      @Nullable
      public Reward call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconResource = CursorUtil.getColumnIndexOrThrow(_cursor, "icon_resource");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "approval_required");
          final int _cursorIndexOfIsAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "is_available");
          final Reward _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIconResource;
            if (_cursor.isNull(_cursorIndexOfIconResource)) {
              _tmpIconResource = null;
            } else {
              _tmpIconResource = _cursor.getString(_cursorIndexOfIconResource);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfApprovalRequired);
            _tmpApprovalRequired = _tmp != 0;
            final boolean _tmpIsAvailable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAvailable);
            _tmpIsAvailable = _tmp_1 != 0;
            _result = new Reward(_tmpId,_tmpName,_tmpCost,_tmpDescription,_tmpIconResource,_tmpCategory,_tmpApprovalRequired,_tmpIsAvailable);
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
  public Flow<List<RewardRedemption>> getAllRedemptions() {
    final String _sql = "SELECT * FROM redemptions ORDER BY requestedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"redemptions"}, new Callable<List<RewardRedemption>>() {
      @Override
      @NonNull
      public List<RewardRedemption> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<RewardRedemption> _result = new ArrayList<RewardRedemption>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardRedemption _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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
  public Flow<List<RewardRedemption>> getRedemptionsByStatus(final RedemptionStatus status) {
    final String _sql = "SELECT * FROM redemptions WHERE status = ? ORDER BY requestedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __RedemptionStatus_enumToString(status));
    return CoroutinesRoom.createFlow(__db, false, new String[] {"redemptions"}, new Callable<List<RewardRedemption>>() {
      @Override
      @NonNull
      public List<RewardRedemption> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<RewardRedemption> _result = new ArrayList<RewardRedemption>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardRedemption _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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
  public Flow<List<RewardRedemption>> getPendingApprovalRedemptions() {
    final String _sql = "SELECT * FROM redemptions WHERE status = 'PENDING_APPROVAL' ORDER BY requestedAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"redemptions"}, new Callable<List<RewardRedemption>>() {
      @Override
      @NonNull
      public List<RewardRedemption> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<RewardRedemption> _result = new ArrayList<RewardRedemption>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardRedemption _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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
  public Object getRedemptionById(final long redemptionId,
      final Continuation<? super RewardRedemption> $completion) {
    final String _sql = "SELECT * FROM redemptions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, redemptionId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RewardRedemption>() {
      @Override
      @Nullable
      public RewardRedemption call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final RewardRedemption _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _result = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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
  public Flow<Integer> getPendingRedemptionsCount() {
    final String _sql = "SELECT COUNT(*) FROM redemptions WHERE status IN ('PENDING_APPROVAL', 'APPROVED', 'PROCESSING')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"redemptions"}, new Callable<Integer>() {
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
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getRedemptionsInTimeRange(final long startTime, final long endTime,
      final Continuation<? super List<RewardRedemption>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM redemptions \n"
            + "        WHERE requestedAt >= ? AND requestedAt <= ? \n"
            + "        ORDER BY requestedAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RewardRedemption>>() {
      @Override
      @NonNull
      public List<RewardRedemption> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<RewardRedemption> _result = new ArrayList<RewardRedemption>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardRedemption _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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
  public Object getRewardsWithRedemptionStatus(final RedemptionStatus status,
      final Continuation<? super List<Reward>> $completion) {
    final String _sql = "\n"
            + "        SELECT r.* \n"
            + "        FROM rewards r \n"
            + "        INNER JOIN redemptions rd ON r.id = rd.rewardId \n"
            + "        WHERE rd.status = ? \n"
            + "        ORDER BY rd.requestedAt DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __RedemptionStatus_enumToString(status));
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Reward>>() {
      @Override
      @NonNull
      public List<Reward> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconResource = CursorUtil.getColumnIndexOrThrow(_cursor, "icon_resource");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "approval_required");
          final int _cursorIndexOfIsAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "is_available");
          final List<Reward> _result = new ArrayList<Reward>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Reward _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIconResource;
            if (_cursor.isNull(_cursorIndexOfIconResource)) {
              _tmpIconResource = null;
            } else {
              _tmpIconResource = _cursor.getString(_cursorIndexOfIconResource);
            }
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfApprovalRequired);
            _tmpApprovalRequired = _tmp != 0;
            final boolean _tmpIsAvailable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsAvailable);
            _tmpIsAvailable = _tmp_1 != 0;
            _item = new Reward(_tmpId,_tmpName,_tmpCost,_tmpDescription,_tmpIconResource,_tmpCategory,_tmpApprovalRequired,_tmpIsAvailable);
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
  public Object getPendingRedemptionsForUSSD(
      final Continuation<? super List<RewardRedemption>> $completion) {
    final String _sql = "SELECT * FROM redemptions WHERE status = 'PENDING_APPROVAL' ORDER BY requestedAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RewardRedemption>>() {
      @Override
      @NonNull
      public List<RewardRedemption> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<RewardRedemption> _result = new ArrayList<RewardRedemption>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardRedemption _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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
  public Object getRedemptionsByRewardId(final int rewardId,
      final Continuation<? super List<RewardRedemption>> $completion) {
    final String _sql = "SELECT * FROM redemptions WHERE rewardId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, rewardId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RewardRedemption>>() {
      @Override
      @NonNull
      public List<RewardRedemption> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRewardId = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardId");
          final int _cursorIndexOfRewardName = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardName");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "cost");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfRequestedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "requestedAt");
          final int _cursorIndexOfApprovedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "approvedAt");
          final int _cursorIndexOfRedeemedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "redeemedAt");
          final int _cursorIndexOfParentApprovalRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "parentApprovalRequired");
          final int _cursorIndexOfApiTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "apiTransactionId");
          final int _cursorIndexOfErrorMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "errorMessage");
          final List<RewardRedemption> _result = new ArrayList<RewardRedemption>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RewardRedemption _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpRewardId;
            _tmpRewardId = _cursor.getInt(_cursorIndexOfRewardId);
            final String _tmpRewardName;
            if (_cursor.isNull(_cursorIndexOfRewardName)) {
              _tmpRewardName = null;
            } else {
              _tmpRewardName = _cursor.getString(_cursorIndexOfRewardName);
            }
            final int _tmpCost;
            _tmpCost = _cursor.getInt(_cursorIndexOfCost);
            final RedemptionStatus _tmpStatus;
            _tmpStatus = __RedemptionStatus_stringToEnum(_cursor.getString(_cursorIndexOfStatus));
            final long _tmpRequestedAt;
            _tmpRequestedAt = _cursor.getLong(_cursorIndexOfRequestedAt);
            final Long _tmpApprovedAt;
            if (_cursor.isNull(_cursorIndexOfApprovedAt)) {
              _tmpApprovedAt = null;
            } else {
              _tmpApprovedAt = _cursor.getLong(_cursorIndexOfApprovedAt);
            }
            final Long _tmpRedeemedAt;
            if (_cursor.isNull(_cursorIndexOfRedeemedAt)) {
              _tmpRedeemedAt = null;
            } else {
              _tmpRedeemedAt = _cursor.getLong(_cursorIndexOfRedeemedAt);
            }
            final boolean _tmpParentApprovalRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfParentApprovalRequired);
            _tmpParentApprovalRequired = _tmp != 0;
            final String _tmpApiTransactionId;
            if (_cursor.isNull(_cursorIndexOfApiTransactionId)) {
              _tmpApiTransactionId = null;
            } else {
              _tmpApiTransactionId = _cursor.getString(_cursorIndexOfApiTransactionId);
            }
            final String _tmpErrorMessage;
            if (_cursor.isNull(_cursorIndexOfErrorMessage)) {
              _tmpErrorMessage = null;
            } else {
              _tmpErrorMessage = _cursor.getString(_cursorIndexOfErrorMessage);
            }
            _item = new RewardRedemption(_tmpId,_tmpRewardId,_tmpRewardName,_tmpCost,_tmpStatus,_tmpRequestedAt,_tmpApprovedAt,_tmpRedeemedAt,_tmpParentApprovalRequired,_tmpApiTransactionId,_tmpErrorMessage);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __RedemptionStatus_enumToString(@NonNull final RedemptionStatus _value) {
    switch (_value) {
      case PENDING_APPROVAL: return "PENDING_APPROVAL";
      case APPROVED: return "APPROVED";
      case PROCESSING: return "PROCESSING";
      case COMPLETED: return "COMPLETED";
      case FAILED: return "FAILED";
      case REJECTED: return "REJECTED";
      case CANCELLED: return "CANCELLED";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private RedemptionStatus __RedemptionStatus_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "PENDING_APPROVAL": return RedemptionStatus.PENDING_APPROVAL;
      case "APPROVED": return RedemptionStatus.APPROVED;
      case "PROCESSING": return RedemptionStatus.PROCESSING;
      case "COMPLETED": return RedemptionStatus.COMPLETED;
      case "FAILED": return RedemptionStatus.FAILED;
      case "REJECTED": return RedemptionStatus.REJECTED;
      case "CANCELLED": return RedemptionStatus.CANCELLED;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
