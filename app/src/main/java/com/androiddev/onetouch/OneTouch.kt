package com.androiddev.onetouch

import android.app.Application
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OneTouch : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    private fun getCurrentAppVersion(oneTouch: OneTouch): String? {
        var currentVersion: String? = "0"
        try {
            currentVersion = oneTouch.packageManager.getPackageInfo(
                oneTouch.packageName, 0
            ).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return currentVersion
    }

}