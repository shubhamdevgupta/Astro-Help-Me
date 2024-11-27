package com.androiddev.astrohelpme.ui.viewmodel

import com.androiddev.astrohelpme.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : BaseViewModel() {

    var mobileNumber: String = ""
    var userName: String = ""


}