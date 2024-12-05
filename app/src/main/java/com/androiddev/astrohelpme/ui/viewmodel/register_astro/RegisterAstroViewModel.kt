package com.androiddev.astrohelpme.ui.viewmodel.register_astro

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddev.astrohelpme.data.auth.RegisterAstro
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.data.response.registerResponse.RegisterAstroResponse
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterAstroViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference
) : BaseViewModel() {

    var user_id = appPreference.userId
    var name = AppConstants.EMPTY
    var gender = AppConstants.EMPTY
    var phone_number = AppConstants.EMPTY
    var email = AppConstants.EMPTY
    var experience = AppConstants.EMPTY
    var city = AppConstants.EMPTY
    var short_bio = AppConstants.EMPTY

    var isLoading = MutableLiveData(false)

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)

    private val _registerAstroResponse = MutableLiveData<Resource<RegisterAstroResponse>>()
    val registerAstroResponseObserver: MutableLiveData<Resource<RegisterAstroResponse>>
        get() = _registerAstroResponse
    fun clearFields() {
        name = ""
        gender = ""
        phone_number = ""
        email = ""
        experience = ""
        city = ""
        short_bio=""
    }

    fun onSubmitClick(view: View) {
        errorMsgObserver.value = AppConstants.EMPTY
        if (!validateLoginInput()) return@onSubmitClick
        _registerAstroResponse.value = Resource.Loading<Nothing>()
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiRequest {
                        authRepository.registerAstro(
                            RegisterAstro(
                                user_id,
                                name,
                                gender,
                                "+91",
                                phone_number,
                                email,
                                experience,
                                city,
                                "India",
                                short_bio
                            )
                        )
                    }
                }
                _registerAstroResponse.value = Resource.Success(response)
            } catch (e: Exception) {
                _registerAstroResponse.value = Resource.Failure(e)
            }
        }
    }

    fun validateLoginInput(): Boolean {
        val message = if (name.isNotEmpty()) {
            if (gender.isNotEmpty()) {
                if (phone_number.isNotEmpty() && phone_number.length == 10) {
                    if (email.isNotEmpty()) {
                        if (experience.isNotEmpty()) {
                            return true
                        } else "Please Enter Your Astrology Experience"
                    } else "Please Enter Valid Email Address"
                } else "Please Enter Valid Mobile NUmber"
            } else "select your gender"
        } else "please enter full name"
        errorMsgObserver.value = message
        return false
    }


}