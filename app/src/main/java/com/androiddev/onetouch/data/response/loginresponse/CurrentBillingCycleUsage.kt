package com.androiddev.onetouch.data.response.loginresponse

data class CurrentBillingCycleUsage(
    val bandwidthTemplateDownload: String,
    val bandwidthTemplateId: String,
    val bandwidthTemplateMikrotik: String,
    val bandwidthTemplateName: String,
    val bandwidthTemplateUpload: String,
    val billingEndDate: String,
    val billingResetType: String,
    val billingStartDate: String,
    val button: String,
    val downloadDataUsage: String,
    val totalBandwidthQuota: Int,
    val totalDataUsage: Long,
    val uploadDataUsage: String,
    val usedBandwidthQuota: Int
)