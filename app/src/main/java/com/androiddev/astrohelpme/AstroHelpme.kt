package com.androiddev.astrohelpme

import android.app.Application
import android.content.pm.PackageManager
import com.sendbird.android.exception.SendbirdException
import com.sendbird.android.handler.InitResultHandler
import com.sendbird.uikit.SendbirdUIKit
import com.sendbird.uikit.adapter.SendbirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AstroHelpme : Application() {
    override fun onCreate() {
        super.onCreate()
        SendbirdUIKit.init(object : SendbirdUIKitAdapter {
            override fun getAppId(): String {
                return "7965B80D-FA84-4D4F-A0F1-76D38EF52F3D" // Specify your Sendbird application ID.
            }

            override fun getAccessToken(): String {
                return "cc2fd991709113c56ceb073f26b0139122e23d56" // Specify your user's access token.
            }

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String {
                        return "shubham"
                        // Use the ID of a user you've created on the dashboard.
                    }

                    override fun getNickname(): String {
                        return "Shubham" // Specify your user nickname. Optional.
                    }

                    override fun getProfileUrl(): String {
                        return ""
                    }
                }
            }

            override fun getInitResultHandler(): InitResultHandler {
                return object : InitResultHandler {
                    override fun onMigrationStarted() {
                        // DB migration has started.
                    }

                    override fun onInitFailed(e: SendbirdException) {
                        // If DB migration fails, this method is called.
                    }

                    override fun onInitSucceed() {
                        // If DB migration is successful, this method is called and you can proceed to the next step.
                        // In the sample app, the `LiveData` class notifies you on the initialization progress
                        // And observes the `MutableLiveData<InitState> initState` value in `SplashActivity()`.
                        // If successful, the `LoginActivity` screen
                        // Or the `HomeActivity` screen will show.....!!!!!@@@@@
                    }
                }
            }
        }, this)
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
