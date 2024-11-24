package com.androiddev.astrohelpme.utils.helper

import android.content.Context
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.utils.extns.toGsonJsonObject
import com.google.gson.JsonObject

class ApiData constructor(
    context: Context,
    private val appPreference: AppPreference
) {

    var deviceId = ""
    init {
        val testingDeviceId = "5e6acbe01acbc591"
        //  deviceId = testingDeviceId
        deviceId = AppUtil.getDeviceId(context)
    }

    fun data(dataMap: HashMap<String, String>? = null): JsonObject {

        return if (dataMap != null) {
            dataMap["loginid"] = appPreference.mobile
            dataMap["password"] = appPreference.password
            dataMap["deviceid"] = deviceId
            dataMap.toGsonJsonObject()
        } else {
            hashMapOf(
                "loginid" to appPreference.mobile,
                "password" to appPreference.password,
                "deviceid" to deviceId
            ).toGsonJsonObject()
        }
    }
}
