package com.androiddev.astrohelpme.ui.viewmodel.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.SetPassword
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.SetPassResponse
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SetPassViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference
) : BaseViewModel() {

    var password: String = AppConstants.EMPTY
    var setPassword: String = AppConstants.EMPTY

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)

    private val _setPassResponse = MutableLiveData<Resource<SetPassResponse>>()
    val setPassResponseObserver: MutableLiveData<Resource<SetPassResponse>>
        get() = _setPassResponse

    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        if (!validatePasswordInput()) return@onSubmitClick
        _setPassResponse.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        authRepository.setPassword(
                            SetPassword(
                                appPreference.userId,
                                password,
                                setPassword
                            )
                        )
                    }
                }
                Log.d("MYTAG", "onSubmitClick: button clickedd" + appPreference.userId)
                _setPassResponse.value = Resource.Success(response)
            } catch (e: Exception) {
                _setPassResponse.value = Resource.Failure(e)
            }
        }
    }

    fun validatePasswordInput(): Boolean {
        val passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        val message = when {
            password.isEmpty() || setPassword.isEmpty() -> {
                "Password fields cannot be empty."
            }
            !password.matches(passwordRegex.toRegex()) -> {
                "Password must be at least 8 characters long, contain one letter, one number, and one special character."
            }
            password != setPassword -> {
                "Passwords do not match."
            }

            else -> {
                return true // Validation passed
            }
        }
        errorMsgObserver.value = message
        Log.d("MYTAG", "validatePasswordInput: $message")
        return false // Validation failed
    }


}