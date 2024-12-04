package com.androiddev.astrohelpme.ui.viewmodel.astro_register

import android.util.Base64
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.AstroDetailsRequest
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.KundliResponse
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AstroRegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference,
) : BaseViewModel() {

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    val request = AstroDetailsRequest(
        day = 2,
        month = 2,
        year = 2000,
        hour = 2,
        min = 2,
        lat = 2.0,
        lon = 2.0,
        tzone = 5.5
    )
    private val _kundliResponse = MutableLiveData<Resource<KundliResponse>>()
    val kundliResponseObserver: MutableLiveData<Resource<KundliResponse>>
        get() = _kundliResponse

    val username = "635294"
    val password = "1a32b66fe5165c694607156bdb36a6c0793150ea"
    val authHeader = "Basic " + Base64.encodeToString(
        "$username:$password".toByteArray(),
        Base64.NO_WRAP
    )

    fun onNextClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        _kundliResponse.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        authRepository.getAstroDetails( request)
                    }
                }
                _kundliResponse.value = Resource.Success(response)
            } catch (e: Exception) {
                _kundliResponse.value = Resource.Failure(e)
            }
        }
    }

}