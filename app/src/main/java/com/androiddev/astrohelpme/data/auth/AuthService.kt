package com.androiddev.astrohelpme.data.auth


import com.androiddev.astrohelpme.data.response.LoginResponse
import com.androiddev.astrohelpme.data.response.OtpVerifyResponse
import com.androiddev.astrohelpme.data.response.SetPassResponse
import com.androiddev.astrohelpme.data.response.SignupResponse
import com.androiddev.astrohelpme.data.response.registerResponse.RegisterAstroResponse
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
    @POST("store-profile")
    suspend fun registerAstro(@Body registerAstro: RegisterAstro): Response<RegisterAstroResponse>

}


class RegisterAstro internal constructor(
    @SerializedName("user_id")
    val user_id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("phone_prefix")
    val phone_prefix: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("experience")
    val experience: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("short_bio")
    val short_bio: String,
)

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
