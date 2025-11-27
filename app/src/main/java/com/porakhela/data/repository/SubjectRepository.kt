package com.porakhela.data.repository

import android.content.Context
import com.porakhela.data.models.SubjectData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for loading subject data from local JSON assets
 * Implements offline-first architecture
 */
@Singleton
class SubjectRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    
    /**
     * Load subject data from assets
     */
    suspend fun getSubjectData(subject: String): Result<SubjectData> = withContext(Dispatchers.IO) {
        try {
            val fileName = "${subject}_categories.json"
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val subjectData = json.decodeFromString<SubjectData>(jsonString)
            
            Timber.d("Successfully loaded ${subjectData.categories.size} categories for $subject")
            Result.success(subjectData)
        } catch (e: Exception) {
            Timber.e(e, "Failed to load subject data for: $subject")
            Result.failure(e)
        }
    }
    
    /**
     * Get all available subjects
     */
    fun getAvailableSubjects(): List<String> {
        return listOf("math", "english", "science", "social_studies")
    }
    
    /**
     * Check if subject data exists
     */
    fun isSubjectAvailable(subject: String): Boolean {
        return try {
            val fileName = "${subject}_categories.json"
            context.assets.open(fileName).close()
            true
        } catch (e: Exception) {
            false
        }
    }
}