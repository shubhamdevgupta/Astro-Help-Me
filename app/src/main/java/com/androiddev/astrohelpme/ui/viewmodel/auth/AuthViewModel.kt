package com.androiddev.astrohelpme.ui.viewmodel.auth

import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : BaseViewModel() {



}