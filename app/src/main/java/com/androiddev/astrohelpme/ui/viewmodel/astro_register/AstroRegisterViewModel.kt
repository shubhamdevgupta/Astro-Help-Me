package com.androiddev.astrohelpme.ui.viewmodel.astro_register

import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.repository.AuthRepository
import com.androiddev.astrohelpme.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AstroRegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreference: AppPreference,
) : BaseViewModel() {

}