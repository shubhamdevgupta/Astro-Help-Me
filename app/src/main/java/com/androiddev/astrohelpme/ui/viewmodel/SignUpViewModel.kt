package com.androiddev.astrohelpme.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.UserSignup
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.SignupResponse
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference,
) : BaseViewModel() {

    var mobileNumber: String = AppConstants.EMPTY
    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    val isLoading = MutableLiveData(false) // Observable property for progress

    private val _signupObserver = MutableLiveData<Resource<SignupResponse>>()
    val signupResponseObserver: MutableLiveData<Resource<SignupResponse>>
        get() = _signupObserver


    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        isLoading.value = true
        if (!validateLoginInput()) return@onSubmitClick
        _signupObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        authRepository.userSignUp(UserSignup(mobileNumber))
                    }
                }
                Log.d("MYTAG", "onSubmitClick: button clickedd")
                _signupObserver.value = Resource.Success(response)
                setUpData(response)
            } catch (e: Exception) {
                isLoading.value = false
                _signupObserver.value = Resource.Failure(e)
            }
        }
    }

    private fun setUpData(response: SignupResponse) {
        appPreference.userId = response.user_id.toString()
        appPreference.mobile = mobileNumber
    }

    fun validateLoginInput(): Boolean {
        val message = if (mobileNumber.isNotEmpty() && mobileNumber.length == 10) {
            return true
        } else "Please Enter Valid Mobile Number"
        errorMsgObserver.value = message
        Log.d("MYTAG", "validateLoginInput: " + errorMsgObserver.value)
        return false
    }


}