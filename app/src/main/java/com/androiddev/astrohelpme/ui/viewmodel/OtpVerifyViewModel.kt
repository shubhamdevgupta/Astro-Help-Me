package com.androiddev.astrohelpme.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.OTPVerify
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.OtpVerifyResponse
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OtpVerifyViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference,
) : BaseViewModel() {
    var userId: String = AppConstants.EMPTY
    var otp: String = AppConstants.EMPTY
    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)


    private val _otpVerifyResponse = MutableLiveData<Resource<OtpVerifyResponse>>()
    val otpVerifyResponseObserver: MutableLiveData<Resource<OtpVerifyResponse>>
        get() = _otpVerifyResponse

    fun onNextClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        _otpVerifyResponse.value = Resource.Loading<Nothing>()
        Log.d("MYTAG", "onNextClick: " + userId + "    otp entered" + otp)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        authRepository.otpVerify(OTPVerify(userId, otp))
                    }
                }
                Log.d("MYTAG", "onSubmitClick: button clickedd" + userId)
                _otpVerifyResponse.value = Resource.Success(response)
            } catch (e: Exception) {
                _otpVerifyResponse.value = Resource.Failure(e)
            }
        }
    }

    fun validateLoginInput(): Boolean {
        val message = if (otp.isNotEmpty() && otp.length == 6) {
            if (userId.isNotEmpty()) {
                return true
            } else "user id not found"
        } else "mobile number not found"
        errorMsgObserver.value = message
        Log.d("MYTAG", "validateLoginInput: " + errorMsgObserver.value)
        return false
    }


}