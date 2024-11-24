package com.androiddev.astrohelpme.utils.extns

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.Gravity
import android.widget.Toast
import com.androiddev.astrohelpme.utils.helper.AESUtil
import com.androiddev.astrohelpme.utils.helper.AppUtil
import com.google.gson.Gson
import com.google.gson.JsonObject


fun HashMap<String, String>.toGsonJsonObject(): JsonObject {

    AppUtil.logger("Before : encrypting : $this")
    val gsonStringData = Gson().toJson(this).toString()
    val jsonObject = JsonObject()
    jsonObject.addProperty("andrdenpdata", AESUtil.encryptData(gsonStringData))
    return jsonObject

}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.customToast(message: String) {
    val t = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    t.setGravity(Gravity.CENTER, 0, 0)
    t.show()
}

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()


