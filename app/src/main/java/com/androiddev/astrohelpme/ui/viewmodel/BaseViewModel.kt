package com.androiddev.astrohelpme.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.androiddev.astrohelpme.utils.api.Exceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel() : ViewModel() {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody().toString()
            val message = StringBuilder()
            try {
                message.append(JSONObject(error).getString("message"))
            } catch (e: JSONException) {
            }
            message.append("Error Code: ${response.code()}")
            throw Exceptions.ApiException(message.toString())
        }
    }
}