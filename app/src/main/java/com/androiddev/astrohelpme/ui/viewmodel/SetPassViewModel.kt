package com.androiddev.astrohelpme.ui.viewmodel

import com.androiddev.astrohelpme.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetPassViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

}