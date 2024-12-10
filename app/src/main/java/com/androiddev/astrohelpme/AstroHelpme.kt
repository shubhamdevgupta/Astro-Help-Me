package com.androiddev.astrohelpme

import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.utils.helper.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AstroHelpme : Application() {

    @Inject
    lateinit var appPreference: AppPreference

    override fun onCreate() {
        super.onCreate()
        LocaleHelper.setLocale(this, appPreference.language)
        Log.d("MYTAG", "onCreate: app set after restart "+appPreference.language)
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