package com.androiddev.astrohelpme.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.utils.extns.fullScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity @Inject constructor() : AppCompatActivity() {

    @Inject
    lateinit var appPreference: AppPreference // Inject AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fullScreen() // Makes the activity fullscreen
        delayAndLaunchNextActivity()
    }

    private fun delayAndLaunchNextActivity() {
        // Delay execution using Handler.postDelayed (efficient way to handle delays)
        android.os.Handler(mainLooper).postDelayed({
            val nextActivityIntent = if (appPreference.isLoggedIn()) {
                Intent(this@SplashActivity, DashboardActivity::class.java)
            } else {
                Intent(this@SplashActivity, AuthActivity::class.java)
            }
            startActivity(nextActivityIntent)
            finish() // Close SplashActivity
        }, 2500) // Delay for 2.5 seconds
    }
}
