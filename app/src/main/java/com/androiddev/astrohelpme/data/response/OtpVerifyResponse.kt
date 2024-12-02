package com.androiddev.astrohelpme.data.response

data class OtpVerifyResponse(
    val message: String,
    val user_id: String,
    val status_code:Int
)