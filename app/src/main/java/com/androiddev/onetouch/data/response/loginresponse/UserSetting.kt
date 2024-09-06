package com.androiddev.onetouch.data.response.loginresponse

data class UserSetting(
    val billingType: String,
    val gstNumber: Any,
    val referral_code: String,
    val userNasPortId: String
)