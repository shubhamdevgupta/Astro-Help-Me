package com.androiddev.astrohelpme.data.auth


import com.androiddev.astrohelpme.data.response.SignupResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("register/send-otp")
    suspend fun userSignup(@Body userSignup: UserSignup): Response<SignupResponse>

}

class UserSignup internal constructor(
    @SerializedName("mobile_number")
    val mobileNumber: String
)
