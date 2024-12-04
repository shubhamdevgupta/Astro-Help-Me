package com.androiddev.astrohelpme.data.response.matchmaking

data class Female(
    val is_mars_manglik_cancelled: Boolean,
    val is_present: Boolean,
    val manglik_cancel_rule: List<Any>,
    val manglik_present_rule: ManglikPresentRule,
    val manglik_report: String,
    val manglik_status: String,
    val percentage_manglik_after_cancellation: Double,
    val percentage_manglik_present: Double
)