package com.androiddev.onetouch.data.local

import android.content.SharedPreferences
import com.androiddev.onetouch.utils.helper.AppConstants
import javax.inject.Inject

class AppPreference @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var deviceId: String
        get() = getString(DEVICE_ID)
        set(value) = setString(value, DEVICE_ID)
    var mobile: String
        get() = getString(MOBILE)
        set(value) = setString(value, MOBILE)
    var password: String
        get() = getString(PASSWORD)
        set(value) = setString(value, PASSWORD)
    var loginCount: Int
        get() = getInteger(LOGIN_COUNT, 2)
        set(value) = setInteger(value, LOGIN_COUNT)
    var isApiTransaction: Boolean
        get() = getBoolean(IS_TRANSACTION_API)
        set(value) = setBoolean(value, IS_TRANSACTION_API)
    var isLoginCheck: Boolean
        get() = getBoolean(IS_LOGIN_CHECK)
        set(value) = setBoolean(value, IS_LOGIN_CHECK)
    /*
        var user: User
            get() {
                val strUser = getString(USER)
                return Gson().fromJson(strUser, User::class.java)
            }
            set(value) {
                val strUser = Gson().toJson(value).toString()
                setString(strUser, USER)
            }*/

    //STRING DATA
    private fun setString(value: String, tag: String) =
        sharedPreferences.edit().putString(tag, value).apply()

    private fun getString(tag: String) =
        sharedPreferences.getString(tag, AppConstants.EMPTY) ?: ""

    //BOOLEAN DATA
    private fun setBoolean(value: Boolean, tag: String) =
        sharedPreferences.edit().putBoolean(tag, value).apply()

    private fun getBoolean(tag: String) = sharedPreferences.getBoolean(tag, false)

    //INTEGER DATA
    private fun setInteger(value: Int, tag: String) =
        sharedPreferences.edit().putInt(tag, value).apply()

    private fun getInteger(tag: String, defaultValue: Int = 0) =
        sharedPreferences.getInt(tag, defaultValue)

    //TAGS
    companion object {
        const val USER = "user"
        const val DEVICE_ID = "device_id"
        const val MOBILE = "mobile"
        const val PASSWORD = "password"
        const val LOGIN_COUNT = "login_count"
        const val IS_LOGIN_CHECK = "is_login_check"
        const val IS_TRANSACTION_API = "is_transaction_api"
    }
}