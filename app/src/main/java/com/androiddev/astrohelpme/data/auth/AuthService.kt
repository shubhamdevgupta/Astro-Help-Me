package com.androiddev.astrohelpme.data.auth


import com.androiddev.astrohelpme.data.response.OtpVerifyResponse
import com.androiddev.astrohelpme.data.response.SignupResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("register/send-otp")
    suspend fun userSignup(@Body userSignup: UserSignup): Response<SignupResponse>

    @POST("register/verify-otp")
    suspend fun otpVerify(@Body otpVerify: OTPVerify): Response<OtpVerifyResponse>

}

class UserSignup internal constructor(
    @SerializedName("mobile_number")
    val mobileNumber: String
)

class OTPVerify internal constructor(
    @SerializedName("user_id")
    val usreId: String,

    @SerializedName("otp")
    val otp: String
)
