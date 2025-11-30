package com.porakhela.ui.performance.testing

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.porakhela.databinding.ActivityTestLauncherBinding

class TestLauncherActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityTestLauncherBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.apply {
            btnFullTestSuite.setOnClickListener {
                startActivity(Intent(this@TestLauncherActivity, PerformanceTestActivity::class.java))
            }
            
            btnInteractiveTests.setOnClickListener {
                startActivity(Intent(this@TestLauncherActivity, EmulatorTestActivity::class.java))
            }
            
            btnInstructions.setOnClickListener {
                // Show instructions dialog or open documentation
                showInstructionsDialog()
            }
        }
    }
    
    private fun showInstructionsDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("🇧🇩 Performance Test Instructions")
            .setMessage("""
                STRICT EMULATOR TEST REQUIREMENTS:
                
                A. FPS Tests:
                • Home scroll ≥ 55 FPS
                • Lesson transitions ≥ 50 FPS  
                • Leaderboard scroll ≥ 50 FPS
                
                B. Memory Tests:
                • Idle < 140MB
                • 3 Lessons < 170MB
                • Download < 200MB
                
                C. Database Tests:
                • 100 inserts < 1.5s
                • 100 queries < 250ms
                • 50 concurrent writes, no lockups
                
                D. Scroll Test:
                • Fast lesson scroll ≥ 45 FPS
                
                IF ANY TEST FAILS:
                Apply fixes → Re-run until ALL pass
                
                Target: ৳800-৳2000 Bangladesh devices
            """.trimIndent())
            .setPositiveButton("Start Tests") { _, _ ->
                startActivity(Intent(this, EmulatorTestActivity::class.java))
            }
            .setNegativeButton("Close", null)
            .show()
    }
}