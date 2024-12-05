package com.androiddev.astrohelpme.ui.viewmodel.matchmaking

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.MatchMakingRequest
import com.androiddev.astrohelpme.data.repository.MainRepository
import com.androiddev.astrohelpme.data.response.matchmaking.MatchMakingResponse
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MatchMakingiViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : BaseViewModel() {

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)
    var m_day = 0
    var m_month = 0
    var m_year = 1111
    var m_hour = 0
    var m_min = 0
    var m_lat = 0.0
    var m_lon = 0.0
    var m_tzone = 0.0

    var f_day = 0
    var f_month = 0
    var f_year = 1111
    var f_hour = 0
    var f_min = 0
    var f_lat = 0.0
    var f_lon = 0.0
    var f_tzone = 0.0

    private val _matchMakingResponse = MutableLiveData<Resource<MatchMakingResponse>>()
    val matchMakingObserver: MutableLiveData<Resource<MatchMakingResponse>>
        get() = _matchMakingResponse


    fun onNextClick(view: View) {
        Log.d(
            "MYTAG",
            "onNextClick: male data--" + m_day + m_month + m_year + m_hour + m_min + m_lat + m_lon + m_tzone + " female----" + f_day + f_month + f_year + f_hour + f_min + f_lat + f_lon + f_tzone
        )
        if (!validateLoginInput()) return@onNextClick
        errorMsgObserver.value = AppConstants.EMPTY
        _matchMakingResponse.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.getMatchMaking(
                            MatchMakingRequest(m_day, m_month, m_year, m_hour, m_min, m_lat, m_lon, m_tzone, f_day, f_month, f_year, f_hour, f_min, f_lat, f_lon, f_tzone)
                        )
                    }
                }
                _matchMakingResponse.value = Resource.Success(response)
            } catch (e: Exception) {
                _matchMakingResponse.value = Resource.Failure(e)
            }
        }
    }

    private fun validateLoginInput(): Boolean {
        val message = if (m_day != 0 && m_month != 0 && m_year != 0) {
            if (m_hour != 0 && m_min != 0) {
                if (f_day != 0 && f_month != 0 && f_year != 0) {
                    if (f_hour != 0 && f_min != 0) {
                        return true
                    } else "Please Select Valid Female Time of Birth"
                } else "Please Select Valid Female Date of Birth"
            } else "Please Select Valid Male Time of Birth"
        } else "Please Select Valid Male Date of Birth"
        errorMsgObserver.value = message
        return false
    }

}