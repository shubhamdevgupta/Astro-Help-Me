package com.androiddev.vastushikar.data.response.loginresponse
data class ApiResponse(
    val user: User,
    val userSetting: UserSetting,
    val currentBillingCycleUsage: CurrentBillingCycleUsage,
    val billingDetails: BillingDetails,
    val staticIpMac: List<StaticIpMac>,
    val location: Location
)


data class User(
    val id: String,
    val username: String,
    val accountId: String,
    val lastName: String,
    val groupId: String,
    val status: String,
    val name: String,
    val phone: String,
    val email: String,
    val address: String?,
    val addressLine1: String,
    val addressLine2: String,
    val addressCity: String?,
    val addressPin: String?,
    val addressState: String?,
    val comments: String?,
    val activationTime: String,
    val createdTime: String,
    val expirationTime: String,
    val installationTime: String,
    val billingId: String?,
    val type: String,
    val modified: String,
    val countryCode: String,
    val altPhone: String,
    val altEmail: String?,
    val companyName: String,
    val anySecurity: String?,
    val installationType: String,
    val wireLength: String?,
    val routerCondition: String?,
    val ott: String?,
    val groupName: String,
    val profileId: String,
    val profileName: String,
    val subPlanId: String,
    val subPlanName: String,
    val installationAddressLine1: String?,
    val installationAddressLine2: String?,
    val installationAddressCity: String?,
    val installationAddressPin: String?,
    val installationAddressState: String?,
    val installationAddressCountry: String?
)

data class UserSetting(
    val gstNumber: String?,
    val referralCode: String,
    val userNasPortId: String,
    val billingType: String
)

data class CurrentBillingCycleUsage(
    val bandwidthTemplateId: String,
    val bandwidthTemplateName: String,
    val bandwidthTemplateMikrotik: String,
    val bandwidthTemplateUpload: String,
    val bandwidthTemplateDownload: String,
    val billingResetType: String,
    val billingStartDate: String,
    val billingEndDate: String,
    val button: String?,
    val uploadDataUsage: String,
    val downloadDataUsage: String,
    val totalDataUsage: Long,
    val usedBandwidthQuota: Int,
    val totalBandwidthQuota: Int
)

data class BillingDetails(
    val lastOrderDate: String,
    val lastInvoiceDate: String,
    val lastPaymentDate: String,
    val statusId: String,
    val totalOwed: Int,
    val lifetimeRevenue: Int
)

data class StaticIpMac(
    val staticIpAddress: String?,
    val staticIpBoundMac: String
)

data class Location(
    val latitude: String,
    val longitude: String
)