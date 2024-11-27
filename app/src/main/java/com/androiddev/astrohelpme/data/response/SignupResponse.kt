package com.androiddev.astrohelpme.data.response

data class SignupResponse(
    val message: String,
    val otp: Int,
    val user_id: Int
)