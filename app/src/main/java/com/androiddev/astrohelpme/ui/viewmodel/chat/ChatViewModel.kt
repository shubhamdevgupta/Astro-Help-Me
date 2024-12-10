package com.androiddev.astrohelpme.ui.viewmodel.chat

import androidx.lifecycle.MutableLiveData
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.MainRepository
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import com.androiddev.astrohelpme.utils.helper.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val appPreference: AppPreference,
) : BaseViewModel(){

    var errorMsgObserver = MutableLiveData(AppConstants.EMPTY)
    var isLoading = MutableLiveData(false)
}