package com.porakhela.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * DAO for lesson log database operations
 */
@Dao
interface LessonLogDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessonLog(log: LessonLog)
    
    @Query("SELECT * FROM lesson_logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<LessonLog>>
    
    @Query("SELECT * FROM lesson_logs WHERE date(timestamp/1000, 'unixepoch') = :date ORDER BY timestamp DESC")
    suspend fun getLogsForDate(date: String): List<LessonLog>
    
    @Query("""SELECT date(timestamp/1000, 'unixepoch') as date,
                     COUNT(*) as lessonsCompleted,
                     SUM(pointsEarned) as totalPointsEarned,
                     SUM(durationMinutes) as totalTimeMinutes
              FROM lesson_logs 
              WHERE date(timestamp/1000, 'unixepoch') >= date('now', '-30 days')
              GROUP BY date(timestamp/1000, 'unixepoch')
              ORDER BY date DESC""")
    suspend fun getDailyReports(): List<DailyReportRaw>
    
    @Query("SELECT COUNT(*) FROM lesson_logs")
    suspend fun getTotalLessonsCount(): Int
    
    @Query("SELECT timestamp FROM lesson_logs ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastLessonTimestamp(): Long?
    
    @Query("SELECT SUM(durationMinutes) FROM lesson_logs WHERE date(timestamp/1000, 'unixepoch') = date('now')")
    suspend fun getTodayScreenTime(): Int?
    
    @Query("DELETE FROM lesson_logs WHERE timestamp < :cutoffTime")
    suspend fun deleteOldLogs(cutoffTime: Long)
}

/**
 * Raw data class for daily report query results
 */
data class DailyReportRaw(
    val date: String,
    val lessonsCompleted: Int,
    val totalPointsEarned: Int,
    val totalTimeMinutes: Int
)