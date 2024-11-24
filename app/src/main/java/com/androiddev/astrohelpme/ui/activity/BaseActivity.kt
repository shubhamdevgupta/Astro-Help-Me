package com.androiddev.astrohelpme.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.utils.extns.launchActivity
import com.androiddev.astrohelpme.utils.helper.AppUtil
import com.androiddev.astrohelpme.utils.helper.AutoLogoutUtil
import com.androiddev.astrohelpme.utils.helper.RootUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity (): AppCompatActivity(){

    @Inject
    lateinit var appPreference: AppPreference
    @Inject
    lateinit var autoLogoutUtil : AutoLogoutUtil

    override fun onResume() {
        super.onResume()
        if (RootUtil.isDeviceRooted)
            launchActivity(ErrorActivity::class.java, bundleOf(
                ErrorActivity.TYPE to ErrorActivity.ROOT,
                ErrorActivity.TITLE to resources.getString(R.string.rooted_device_title),
                ErrorActivity.DESCRIPTION to resources.getString(R.string.rooted_device_message)
            ),true)
       else{
            autoLogoutUtil.cancelTimer()
            if (autoLogoutUtil.isSessionTimeout) {
            //    autoLogoutUtil.logout()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (autoLogoutUtil.isAppInBackground(this)) {
            autoLogoutUtil.startUserSession()
        }
        AppUtil.logger("onStop called")
    }
}