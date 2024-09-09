package com.androiddev.onetouch.data.repository

import com.androiddev.onetouch.data.auth.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {
    suspend fun loginbymobile(phone: String, status: String, id: String) =
        authService.loginbymobile(phone, status, id)

    suspend fun loginbyusername(userName: String) = authService.loginbyusername(userName)

}