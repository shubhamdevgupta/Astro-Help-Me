package com.androiddev.astrohelpme.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.LoginData
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.LoginResponse
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference,
) : BaseViewModel() {
    var mobileNumber = AppConstants.EMPTY
    var password = AppConstants.EMPTY
    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)

    private val _loginObserver = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: MutableLiveData<Resource<LoginResponse>>
        get() = _loginObserver

    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        _loginObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        authRepository.loginData(LoginData(mobileNumber, password))
                    }
                }
                setLoginCheck(response)
                Log.d("MYTAG", "onSubmitClick: " + response)
                _loginObserver.value = Resource.Success(response)
            } catch (e: Exception) {
                _loginObserver.value = Resource.Failure(e)
            }
        }
    }

    private fun setLoginCheck(response: LoginResponse) {

    }

    private fun validateLoginInput(): Boolean {
        return true
    }

}