package com.androiddev.astrohelpme.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.SetPassword
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.SetPassResponse
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

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var password: String = AppConstants.EMPTY
    var setPassword: String = AppConstants.EMPTY
    var isLoading = MutableLiveData(false)

    private val _setPassResponse = MutableLiveData<Resource<SetPassResponse>>()
    val setPassResponseObserver: MutableLiveData<Resource<SetPassResponse>>
        get() = _setPassResponse

    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
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

}