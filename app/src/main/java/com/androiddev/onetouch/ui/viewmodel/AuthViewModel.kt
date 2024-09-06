package com.androiddev.onetouch.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.onetouch.data.local.AppPreference
import com.androiddev.onetouch.data.repository.AuthRepository
import com.androiddev.onetouch.data.response.loginresponse.LoginResponse
import com.androiddev.onetouch.utils.api.Resource
import com.androiddev.onetouch.utils.helper.ApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : BaseViewModel() {

    var mobileNumber: String = "8650922082"
    var password: String = "Tramo@123"


    private val _loginObserver = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse: MutableLiveData<Resource<LoginResponse>>
        get() = _loginObserver

    fun onLoginClick(view: View) {
        _loginObserver.value=Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        repository.appLogin(
                            mobileNumber, password
                        )
                    }
                }
                Log.d("MYTAG", "response: " + response)
                _loginObserver.value=Resource.Success(response)
            } catch (e: Exception) {
                Log.d("MYTAG", "onerror: " + e.message)
                _loginObserver.value=Resource.Failure(e)
            }
        }
    }


}