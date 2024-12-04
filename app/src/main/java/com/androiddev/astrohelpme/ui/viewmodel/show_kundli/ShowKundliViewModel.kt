package com.androiddev.astrohelpme.ui.viewmodel.show_kundli

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.AstroDetailsRequest
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.MainRepository
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
class ShowKundliViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val appPreference: AppPreference,
) : BaseViewModel() {

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)
    var day = 0
    var month = 0
    var year = 1111
    var hour = 0
    var min = 0
    var lat = 0.0
    var lon = 0.0
    var tzone = 0.0

    private val _kundliResponse = MutableLiveData<Resource<KundliResponse>>()
    val kundliResponseObserver: MutableLiveData<Resource<KundliResponse>>
        get() = _kundliResponse


    fun onNextClick(view: View) {
        if (!validateLoginInput()) return@onNextClick
        errorMsgObserver.value = AppConstants.EMPTY
        _kundliResponse.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.getAstroDetails(
                            AstroDetailsRequest(
                                day,
                                month,
                                year,
                                hour,
                                min,
                                lat,
                                lon,
                                tzone
                            )
                        )
                    }
                }
                _kundliResponse.value = Resource.Success(response)
            } catch (e: Exception) {
                _kundliResponse.value = Resource.Failure(e)
            }
        }
    }

    private fun validateLoginInput(): Boolean {
        val message =
            if (day != 0 && month != 0 && year != 0) {
                if (min != 0 && hour != 0) {
                    return true
                } else "Please Select Valid Time of Birth"
            } else "Please Select Valid Date of Birth"
        errorMsgObserver.value = message
        return false
    }

}