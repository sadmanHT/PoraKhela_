package com.porakhela.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.porakhela.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    
    private val viewModel: OnboardingViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
        setupObservers()
    }
    
    private fun setupClickListeners(view: View) {
        try {
            view.findViewById<View>(R.id.btn_get_started)?.setOnClickListener {
                Timber.d("Starting main app flow")
                (activity as? OnboardingActivity)?.completeOnboarding()
            }
        } catch (e: Exception) {
            Timber.w("Could not find btn_get_started view")
        }
    }
    
    private fun setupObservers() {
        // Setup observers
    }
}