package com.androiddev.onetouch.utils.helper

import android.app.ActivityManager
import android.content.Context
import com.androiddev.onetouch.data.local.AppPreference
import java.util.*


class AutoLogoutUtil constructor(
    private val context: Context,
    private val appPreference: AppPreference
) {
    private var timer: Timer? = null
    var isSessionTimeout = false
    fun startUserSession() {
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                isSessionTimeout = true
            }
        }, 60000 * 10.toLong())
    }

    fun cancelTimer() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    fun isAppInBackground(context: Context): Boolean {
        var isInBackground = true
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var runningProcesses: List<ActivityManager.RunningAppProcessInfo>? = null
        runningProcesses = am.runningAppProcesses
        if (runningProcesses != null) {
            for (processInfo in runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (activeProcess in processInfo.pkgList) {
                        if (activeProcess == context.packageName) {
                            isInBackground = false
                        }
                    }
                }
            }
        }
        return isInBackground
    }
    /*fun logout() {
        isSessionTimeout = false
        removeSession()
        val intent = Intent(context, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
    private fun removeSession(){
        appPreference.user = User()
        appPreference.isLoginCheck = false
        appPreference.password = AppConstants.EMPTY
        appPreference.deviceId = AppConstants.EMPTY
        appPreference.mobile = AppConstants.EMPTY
        appPreference.loginCount = 2
    }*/
}

