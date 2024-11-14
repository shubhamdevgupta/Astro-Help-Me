package com.androiddev.vastushikar.utils.extns

import android.app.Activity
import android.view.WindowManager

fun Activity.fullScreen() {
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}


