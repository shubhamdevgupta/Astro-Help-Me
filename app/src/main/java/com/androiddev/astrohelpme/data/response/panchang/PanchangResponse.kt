package com.androiddev.astrohelpme.data.response.panchang

import java.io.Serializable

data class PanchangResponse(
    val day: String,
    val karan: String,
    val nakshatra: String,
    val sunrise: String,
    val sunset: String,
    val tithi: String,
    val vedic_sunrise: String,
    val vedic_sunset: String,
    val yog: String
): Serializable