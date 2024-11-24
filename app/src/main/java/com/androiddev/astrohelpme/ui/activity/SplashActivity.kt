package com.androiddev.astrohelpme.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.utils.extns.fullScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity @Inject constructor() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fullScreen()
        delayAndLaunchLoginActivity()
    }

    private fun delayAndLaunchLoginActivity() {
        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val loginIntent = Intent(this@SplashActivity, AuthActivity::class.java)
                    startActivity(loginIntent)
                    finish()
                }
            }
        }
        timer.start()
    }

}