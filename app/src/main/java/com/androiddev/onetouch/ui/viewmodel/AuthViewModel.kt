package com.androiddev.onetouch.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.onetouch.data.repository.AuthRepository
import com.androiddev.onetouch.data.response.loginresponse.ApiResponse
import com.androiddev.onetouch.utils.api.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : BaseViewModel() {

    var mobileNumber: String = ""
    var userName: String = ""
    private val _loginObserver = MutableLiveData<Resource<ApiResponse>>()
    val loginResponse: MutableLiveData<Resource<ApiResponse>>
        get() = _loginObserver

    fun onLoginClick(view: View) {
        _loginObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        repository.loginbymobile(
                            mobileNumber, "", ""
                        )
                    }
                }
                Log.d("MYTAG", "response: " + response.toString())
            } catch (e: Exception) {
                Log.d("MYTAG", "onerror: " + e.message)
                _loginObserver.value = Resource.Failure(e)
            }
        }
    }


}