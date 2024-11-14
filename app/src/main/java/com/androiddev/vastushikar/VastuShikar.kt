package com.androiddev.vastushikar

import android.app.Application
import android.content.pm.PackageManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VastuShikar : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    private fun getCurrentAppVersion(vastuShikar: VastuShikar): String? {
        var currentVersion: String? = "0"
        try {
            currentVersion = vastuShikar.packageManager.getPackageInfo(
                vastuShikar.packageName, 0
            ).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return currentVersion
    }

}