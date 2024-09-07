package com.androiddev.onetouch.data.auth


import com.androiddev.onetouch.data.response.loginresponse.ApiResponse

import retrofit2.Response
import retrofit2.http.*


interface AuthService {

    @GET("get_user_by_phone")
    suspend fun appLogin(
        @Path("phoneNumber") phoneNumber: String,
        @Header("Authorization") authToken: String
    ): Response<LoginResponse>
    /*

        @POST("http://10.10.10.158/spotapiservices/api/SpotPeServices/GetState")
        suspend fun getState(): Response<BankResponseData>

        @POST("TokenGenerateAPI")
        @FormUrlEncoded
        suspend fun getToken(@FieldMap params: Map<String?, String?>?): Response<TokenData>

        @POST("RegisterAgentAPI")
        @FormUrlEncoded
        suspend fun registerUser(@FieldMap params: Map<String?, String?>?): Response<RegisterUser>

        @POST("AuthenticationChallengeAPI")
        @FormUrlEncoded
        suspend fun authChallengeApi(@FieldMap params: Map<String?, String?>?): Response<AuthChallengeResponse>

        @POST("SendOtpAPI")
        @FormUrlEncoded
        suspend fun sendOtpAPI(@FieldMap params: Map<String?, String?>?): Response<SendOtpResponse>

        @POST("VerifyOtpAPI")
        @FormUrlEncoded
        suspend fun verifyOtpAPI(@FieldMap params: Map<String?, String?>?): Response<VerifyOtpResponse>

        @POST("BiometricAuthenticationAPI")
        @FormUrlEncoded
        suspend fun biometricAuth(@FieldMap params: Map<String?, String?>?): Response<BiometricResponse>

        @GET("BankIINListAPI")
        suspend fun fetchBankList(
            @Query("ChannelType") channelType: String,
            @Query("Authorization") authorization: String
        ): Response<BankListResponse>

        @GET("TransactionTypeListAPI")
        suspend fun fetchTransType(
            @Query("ChannelType") channelType: String,
            @Query("Authorization") authorization: String
        ): Response<TransTypeResponse>

        @POST("TransactionAPI")
        @FormUrlEncoded
        suspend fun aepsTransfer(@FieldMap params: Map<String?, String?>?): Response<AepsTransResponse>
    */


}
