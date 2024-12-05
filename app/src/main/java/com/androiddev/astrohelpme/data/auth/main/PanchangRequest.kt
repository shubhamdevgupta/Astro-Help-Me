package com.androiddev.astrohelpme.data.auth.main

import java.io.Serializable

data class PanchangRequest(
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val min: Int,
    val lat: Double,
    val lon: Double,
    val tzone: Double,
) : Serializable