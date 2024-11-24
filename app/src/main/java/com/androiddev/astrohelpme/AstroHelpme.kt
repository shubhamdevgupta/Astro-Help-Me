package com.androiddev.astrohelpme

import android.app.Application
import android.content.pm.PackageManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AstroHelpme : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    private fun getCurrentAppVersion(astroHelpme: AstroHelpme): String? {
        var currentVersion: String? = "0"
        try {
            currentVersion = astroHelpme.packageManager.getPackageInfo(
                astroHelpme.packageName, 0
            ).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return currentVersion
    }

}