package com.androiddev.astrohelpme.data.response

data class LoginResponse(
    val access_token: String,
    val status_code: Int,
    val token_type: String,
    val user: User
)