package com.androiddev.astrohelpme.data.auth

data class AstroDetailsRequest(
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val min: Int,
    val lat: Double,
    val lon: Double,
    val tzone: Double
)
