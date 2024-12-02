package com.androiddev.astrohelpme.data.auth


import com.androiddev.astrohelpme.data.response.LoginResponse
import com.androiddev.astrohelpme.data.response.OtpVerifyResponse
import com.androiddev.astrohelpme.data.response.SetPassResponse
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

    @POST("register/set-password")
    suspend fun setPassword(@Body setPassword: SetPassword): Response<SetPassResponse>

    @POST("login")
    suspend fun apiLogin(@Body loginData: LoginData): Response<LoginResponse>
}

class UserSignup internal constructor(
    @SerializedName("mobile_number")
    val mobileNumber: String
)

class LoginData internal constructor(
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String
)

class OTPVerify internal constructor(
    @SerializedName("user_id")
    val usreId: String,

    @SerializedName("otp")
    val otp: String
)

class SetPassword internal constructor(
    @SerializedName("user_id")
    val usreId: String,

    @SerializedName("password")
    val password: String,
    @SerializedName("set_password")
    val set_password: String

)
