package com.porakhela.ui.parent

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.porakhela.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ParentDashboardActivity : AppCompatActivity() {
    
    private val viewModel: ParentDashboardViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dashboard)
        
        try {
            setupNavigation()
            observeAuthState()
        } catch (e: Exception) {
            Timber.e(e, "Error setting up parent dashboard")
            finish()
        }
    }
    
    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Navigation will be handled by auth state
    }
    
    private fun observeAuthState() {
        lifecycleScope.launch {
            viewModel.authState.collect { state ->
                // Navigation is handled within fragments
                // This activity just hosts the navigation
            }
        }
    }
    
    override fun onBackPressed() {
        try {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            val navController = navHostFragment?.navController
            
            // If we're on PIN auth screen, exit completely
            if (navController?.currentDestination?.id == R.id.parentPinAuthFragment) {
                finish()
                return
            }
            
            // For other screens, navigate back or exit
            if (navController?.popBackStack() != true) {
                finish()
            }
        } catch (e: Exception) {
            Timber.e(e, "Error handling back press")
            finish()
        }
    }
}