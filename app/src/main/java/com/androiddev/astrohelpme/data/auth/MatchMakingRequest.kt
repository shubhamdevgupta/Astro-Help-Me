package com.androiddev.astrohelpme.data.auth

data class MatchMakingRequest(
    val m_day: Int,
    val m_month: Int,
    val m_year: Int,
    val m_hour: Int,
    val m_min: Int,
    val m_lat: Double,
    val m_lon: Double,
    val m_tzone: Double,
    val f_day: Int,
    val f_month: Int,
    val f_year: Int,
    val f_hour: Int,
    val f_min: Int,
    val f_lat: Double,
    val f_lon: Double,
    val f_tzone: Double
)