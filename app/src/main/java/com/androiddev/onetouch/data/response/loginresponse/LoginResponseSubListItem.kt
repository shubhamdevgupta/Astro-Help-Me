package com.androiddev.onetouch.data.response.loginresponse

data class LoginResponseSubListItem(
    val User: User,
    val UserSetting: UserSetting,
    val billingDetails: BillingDetails,
    val currentBillingCycleUsage: CurrentBillingCycleUsage,
    val location: Location,
    val staticIpMac: List<StaticIpMac>
)