package com.porakhela.ui.ussd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.porakhela.R
import timber.log.Timber

/**
 * USSD Launcher Activity - Entry point for USSD simulation
 * In production, this would be triggered from the Profile tab
 */
class USSDLauncherActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ussd_launcher)
        
        setupUI()
        
        Timber.d("🔌 USSD Launcher Activity Created")
    }
    
    private fun setupUI() {
        val launchButton = findViewById<Button>(R.id.btnLaunchUSSD)
        
        launchButton.setOnClickListener {
            launchUSSDSimulation()
        }
    }
    
    private fun launchUSSDSimulation() {
        try {
            val intent = Intent(this, USSDSimulationActivity::class.java)
            startActivity(intent)
            
            Timber.d("🚀 USSD Simulation Launched")
        } catch (e: Exception) {
            Timber.e(e, "❌ Failed to launch USSD simulation")
        }
    }
}