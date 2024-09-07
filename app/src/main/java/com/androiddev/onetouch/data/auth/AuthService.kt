package com.androiddev.onetouch.data.auth


import com.androiddev.onetouch.data.response.loginresponse.ApiResponse

import retrofit2.Response
import retrofit2.http.*


interface AuthService {

    @GET("get_user_by_phone/{phone}/{status}/{isd}")
    suspend fun appLogin(
        @Path("phone") phoneNumber: String,
        @Path("status") status: String,
        @Path("isd") isd: String
    ): Response<ApiResponse>

}
