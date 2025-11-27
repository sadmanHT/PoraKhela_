package com.porakhela.ui.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.porakhela.data.models.SubjectData
import com.porakhela.data.repository.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for SubjectCategoryFragment
 */
@HiltViewModel
class SubjectCategoryViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository
) : ViewModel() {
    
    private val _subjectData = MutableStateFlow<SubjectData?>(null)
    val subjectData: StateFlow<SubjectData?> = _subjectData
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    /**
     * Load categories for a specific subject
     */
    fun loadSubjectCategories(subject: String) {
        if (subject.isBlank()) {
            _errorMessage.value = "Invalid subject name"
            return
        }
        
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null
                
                Timber.d("Loading categories for subject: $subject")
                
                val result = subjectRepository.getSubjectData(subject)
                
                result.fold(
                    onSuccess = { data ->
                        _subjectData.value = data
                        _errorMessage.value = null
                        Timber.d("Successfully loaded ${data.categories.size} categories for $subject")
                    },
                    onFailure = { exception ->
                        _errorMessage.value = "Failed to load $subject categories: ${exception.message}"
                        Timber.e(exception, "Failed to load subject data for $subject")
                    }
                )
            } catch (e: Exception) {
                _errorMessage.value = "Unexpected error: ${e.message}"
                Timber.e(e, "Unexpected error loading subject $subject")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Check if a subject is available
     */
    fun isSubjectAvailable(subject: String): Boolean {
        return try {
            subjectRepository.isSubjectAvailable(subject)
        } catch (e: Exception) {
            Timber.e(e, "Error checking subject availability")
            false
        }
    }
    
    /**
     * Get all available subjects
     */
    fun getAvailableSubjects(): List<String> {
        return try {
            subjectRepository.getAvailableSubjects()
        } catch (e: Exception) {
            Timber.e(e, "Error getting available subjects")
            emptyList()
        }
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }
}