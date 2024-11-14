package com.androiddev.vastushikar.data.auth


import com.androiddev.vastushikar.data.response.loginresponse.ApiResponse

import retrofit2.Response
import retrofit2.http.*


interface AuthService {

    @GET("get_user_by_phone/{phone}/{status}/{isd}")
    suspend fun loginbymobile(
        @Path("phone") phoneNumber: String,
        @Path("status") status: String,
        @Path("isd") isd: String
    ): Response<ApiResponse>
    @GET("get_user_by_username/{username}")
    suspend fun loginbyusername(
        @Path("username") userName: String,
    ): Response<ApiResponse>

}
