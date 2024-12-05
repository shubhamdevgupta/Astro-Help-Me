package com.androiddev.astrohelpme.data.response.registerResponse

data class RegisterAstroResponse(
    val message: String,
    val profile: Profile,
    val success: Boolean
)