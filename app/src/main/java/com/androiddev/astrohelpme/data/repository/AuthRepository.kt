package com.androiddev.astrohelpme.data.repository

import com.androiddev.astrohelpme.data.auth.AuthService
import com.androiddev.astrohelpme.data.auth.UserSignup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {
    suspend fun userSignUp(phone: UserSignup) = authService.userSignup(phone)


}