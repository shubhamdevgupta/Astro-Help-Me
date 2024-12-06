package com.androiddev.astrohelpme.data.local

import android.content.SharedPreferences
import com.androiddev.astrohelpme.utils.helper.AppConstants
import javax.inject.Inject

class AppPreference @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var deviceId: String
        get() = getString(DEVICE_ID)
        set(value) = setString(value, DEVICE_ID)
    var mobile: String
        get() = getString(MOBILE)
        set(value) = setString(value, MOBILE)
    var userId: String
        get() = getString(USER_ID)
        set(value) = setString(value, USER_ID)
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

    // New property for language preference
    var language: String
        get() = getString(DEFAULT_LANGUAGE)
        set(value) = setString(value, DEFAULT_LANGUAGE)

    // STRING DATA
    private fun setString(value: String, tag: String) =
        sharedPreferences.edit().putString(tag, value).apply()

    private fun getString(tag: String) =
        sharedPreferences.getString(tag, AppConstants.EMPTY) ?: ""

    // BOOLEAN DATA
    private fun setBoolean(value: Boolean, tag: String) =
        sharedPreferences.edit().putBoolean(tag, value).apply()

    private fun getBoolean(tag: String) = sharedPreferences.getBoolean(tag, false)

    // INTEGER DATA
    private fun setInteger(value: Int, tag: String) =
        sharedPreferences.edit().putInt(tag, value).apply()

    private fun getInteger(tag: String, defaultValue: Int = 0) =
        sharedPreferences.getInt(tag, defaultValue)

    // LOGIN LOGIC
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    // TAGS
    companion object {
        const val USER = "user"
        const val DEVICE_ID = "device_id"
        const val MOBILE = "mobile"
        const val USER_ID = "user_id"
        const val PASSWORD = "password"
        const val LOGIN_COUNT = "login_count"
        const val IS_LOGIN_CHECK = "is_login_check"
        const val IS_TRANSACTION_API = "is_transaction_api"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"

        // Language keys
        private const val KEY_LANGUAGE = "language"
        private const val DEFAULT_LANGUAGE = "en" // English as default
    }
}
