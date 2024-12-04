package com.androiddev.astrohelpme.data.repository

import com.androiddev.astrohelpme.data.auth.AuthService
import com.androiddev.astrohelpme.data.auth.LoginData
import com.androiddev.astrohelpme.data.auth.OTPVerify
import com.androiddev.astrohelpme.data.auth.SetPassword
import com.androiddev.astrohelpme.data.auth.UserSignup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {
    suspend fun userSignUp(userData: UserSignup) = authService.userSignup(userData)

    suspend fun otpVerify(otpVerify: OTPVerify) = authService.otpVerify(otpVerify)

    suspend fun setPassword(setPassword: SetPassword) = authService.setPassword(setPassword)

    suspend fun loginData(loginData: LoginData) = authService.apiLogin(loginData)


}