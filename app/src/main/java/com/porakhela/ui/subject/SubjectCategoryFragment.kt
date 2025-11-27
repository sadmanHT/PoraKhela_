package com.porakhela.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
// import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.porakhela.R
import com.porakhela.data.models.SubjectCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Fragment displaying categories for a specific subject
 */
@AndroidEntryPoint
class SubjectCategoryFragment : Fragment() {
    
    private val subject: String by lazy {
        arguments?.getString("subject") ?: "math"
    }
    private val viewModel: SubjectCategoryViewModel by viewModels()
    
    private lateinit var tvSubjectTitle: TextView
    private lateinit var tvSubjectDescription: TextView
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subject_category, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupRecyclerView()
        setupObservers()
        loadSubjectData()
        
        Timber.d("Subject category screen displayed for: $subject")
    }
    
    private fun initializeViews(view: View) {
        tvSubjectTitle = view.findViewById(R.id.tv_subject_title)
        tvSubjectDescription = view.findViewById(R.id.tv_subject_description)
        rvCategories = view.findViewById(R.id.rv_categories)
    }
    
    private fun setupRecyclerView() {
        categoryAdapter = CategoryAdapter { category ->
            onCategoryClicked(category)
        }
        
        rvCategories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }
    
    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.subjectData.collect { subjectData ->
                    if (subjectData != null) {
                        displaySubjectData(subjectData)
                        Timber.d("Subject data loaded successfully")
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error setting up observers")
                showError("Failed to load subject data: ${e.message}")
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                viewModel.errorMessage.collect { error ->
                    error?.let {
                        showError(it)
                        Timber.e("Subject loading error: $it")
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error in error message observer")
            }
        }
    }
    
    private fun loadSubjectData() {
        viewModel.loadSubjectCategories(subject)
    }
    
    private fun displaySubjectData(subjectData: com.porakhela.data.models.SubjectData) {
        tvSubjectTitle.text = "${subjectData.iconEmoji} ${subjectData.displayName}"
        tvSubjectDescription.text = subjectData.description
        
        categoryAdapter.submitList(subjectData.categories)
        
        Timber.d("Displayed ${subjectData.categories.size} categories for ${subjectData.subject}")
    }
    
    private fun onCategoryClicked(category: SubjectCategory) {
        if (category.isLocked) {
            showLockedCategoryMessage(category)
        } else {
            navigateToLessons(category)
        }
        
        Timber.d("Category clicked: ${category.name}")
    }
    
    private fun showLockedCategoryMessage(category: SubjectCategory) {
        // TODO: Show dialog or message about locked category
        Timber.i("Category ${category.name} is locked. Required level: ${category.requiredLevel}")
    }
    
    private fun navigateToLessons(category: SubjectCategory) {
        try {
            // Map category to lesson ID based on subject
            val lessonId = when (subject.lowercase()) {
                "math", "mathematics" -> "math_basic_arithmetic"
                "english" -> "english_vocabulary"
                "science" -> "science_animals"
                "social studies" -> "social_studies_geography"
                else -> "math_basic_arithmetic" // Default fallback
            }
            
            val action = SubjectCategoryFragmentDirections.actionSubjectCategoryToLessonPlayer(lessonId)
            findNavController().navigate(action)
            
            Timber.d("Navigating to lesson: $lessonId for category: ${category.name}")
        } catch (e: Exception) {
            Timber.e(e, "Failed to navigate to lesson player")
            showError("Failed to start lesson: ${e.message}")
        }
    }
    
    private fun showError(message: String) {
        try {
            // Create a simple toast or snackbar for error display
            tvSubjectDescription.text = "Error: $message"
            Timber.e("Displaying error: $message")
        } catch (e: Exception) {
            Timber.e(e, "Failed to show error message")
        }
    }
}