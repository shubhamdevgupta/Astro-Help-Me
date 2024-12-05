package com.androiddev.astrohelpme.ui.viewmodel.panchang

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.main.PanchangRequest
import com.androiddev.astrohelpme.data.repository.MainRepository
import com.androiddev.astrohelpme.data.response.panchang.PanchangResponse
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PanchangViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {
    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var min = 0
    var lat = 19.34
    var lon = 72.21
    var tzone = 5.5

    private val _panchangObserver = MutableLiveData<Resource<PanchangResponse>>()
    val panchangResponse: MutableLiveData<Resource<PanchangResponse>>
        get() = _panchangObserver

    fun onNextClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        if (!validateLoginInput()) return@onNextClick
        _panchangObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.getPanchangData(
                            PanchangRequest(
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
                _panchangObserver.value = Resource.Success(response)
            } catch (e: Exception) {
                _panchangObserver.value = Resource.Failure(e)
            }
        }
    }

    fun validateLoginInput(): Boolean {
        val message = if (day != 0 && month != 0 && year != 0) {
            if (hour != 0 && min != 0) {
                return true
            } else "Please Select Valid Time"
        } else "Please Select Valid Date"
        errorMsgObserver.value = message
        return false
    }
}