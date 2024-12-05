package com.androiddev.astrohelpme.ui.viewmodel.numerlogy

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.main.NumerologyRequest
import com.androiddev.astrohelpme.data.repository.MainRepository
import com.androiddev.astrohelpme.data.response.numerology.NumerologyResponse
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NumerlogyViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {
    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)

    var day = 0
    var month = 0
    var year = 0
    var name = AppConstants.EMPTY

    private val _numerologyObserver = MutableLiveData<Resource<NumerologyResponse>>()
    val numerlogyResponse: MutableLiveData<Resource<NumerologyResponse>>
        get() = _numerologyObserver

    fun onNextClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        if (!validateLoginInput()) return@onNextClick
        _numerologyObserver.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        mainRepository.getNumerologyData(NumerologyRequest(day, month, year, name))
                    }
                }
                _numerologyObserver.value = Resource.Success(response)
            } catch (e: Exception) {
                _numerologyObserver.value = Resource.Failure(e)
            }
        }
    }

    fun validateLoginInput(): Boolean {
        val message = if (day!=0 && month!=0 && year!=0) {
            if (name.isNotEmpty()) {
                return true
            } else "Please Enter Valid Name"
        } else "Please Enter valid Date of Birth"
        errorMsgObserver.value = message
        return false
    }
}