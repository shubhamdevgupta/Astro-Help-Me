package com.androiddev.onetouch.data.repository

import com.androiddev.onetouch.data.auth.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {
    suspend fun appLogin(phone: String,status:String,id:String) = authService.appLogin(phone,status,id)
    /*
    suspend fun biometricAuth(data: Map<String?, String?>?) = authService.biometricAuth(data)
    suspend fun fetchBankList(channelType: String, token: String) =
        authService.fetchBankList(channelType, token)

    suspend fun fetchTransType(channelType: String, token: String) =
        authService.fetchTransType(channelType, token)

    suspend fun getState() = authService.getState()


    suspend fun aepsTransfer(data: Map<String?, String?>?) = authService.aepsTransfer(data)
    suspend fun getToken(data: Map<String?, String?>?) = authService.getToken(data)
    suspend fun registerUser(data: Map<String?, String?>?) = authService.registerUser(data)
    suspend fun authChallengeApi(data: Map<String?, String?>?) = authService.authChallengeApi(data)
    suspend fun sendOtpAPI(data: Map<String?, String?>?) = authService.sendOtpAPI(data)
    suspend fun verifyOtpAPI(data: Map<String?, String?>?) = authService.verifyOtpAPI(data)

*/
}