package com.androiddev.astrohelpme.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.utils.extns.fullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        fullScreen()
    }


}