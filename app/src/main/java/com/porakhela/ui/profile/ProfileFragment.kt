package com.porakhela.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.porakhela.R
import com.porakhela.ui.parent.ParentDashboardActivity
import com.porakhela.ui.ussd.USSDSimulationActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    
    private val viewModel: ProfileViewModel by viewModels()
    private var lastNavigationTime = 0L
    private val navigationThrottleMs = 1000L // 1 second throttle
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
        setupObservers()
    }
    
    private fun setupClickListeners(view: View) {
        try {
            view.findViewById<View>(R.id.btn_parent_zone)?.setOnClickListener {
                Timber.d("Navigate to parent dashboard")
                openParentDashboard()
            }
            
            // USSD Simulation trigger for inclusivity bridge
            view.findViewById<View>(R.id.btn_ussd_simulation)?.setOnClickListener {
                Timber.d("Navigate to USSD simulation")
                openUSSDSimulation()
            }
            
            view.findViewById<View>(R.id.btn_settings)?.setOnClickListener {
                Timber.d("Navigate to settings")
                // Navigate to settings
            }
            
            view.findViewById<View>(R.id.btn_help)?.setOnClickListener {
                Timber.d("Navigate to help")
                // Navigate to help
            }
        } catch (e: Exception) {
            Timber.w("Could not find one or more profile buttons")
        }
    }
    
    private fun openParentDashboard() {
        try {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastNavigationTime < navigationThrottleMs) {
                Timber.d("Navigation throttled - too many rapid attempts")
                return
            }
            lastNavigationTime = currentTime
            
            val intent = Intent(requireContext(), ParentDashboardActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "Error opening parent dashboard")
        }
    }
    
    private fun openUSSDSimulation() {
        try {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastNavigationTime < navigationThrottleMs) {
                Timber.d("Navigation throttled - too many rapid attempts")
                return
            }
            lastNavigationTime = currentTime
            
            Timber.d("🔌 Launching USSD Simulation - *123# Porakhela Menu")
            val intent = Intent(requireContext(), USSDSimulationActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Timber.e(e, "Error opening USSD simulation")
        }
    }
    
    private fun setupObservers() {
        // Setup observers
    }
}