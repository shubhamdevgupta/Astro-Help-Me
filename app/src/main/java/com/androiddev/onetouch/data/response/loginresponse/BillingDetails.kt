package com.androiddev.onetouch.data.response.loginresponse

data class BillingDetails(
    val lastInvoiceDate: String,
    val lastOrderDate: String,
    val lastPaymentDate: String,
    val lifetimeRevenue: Int,
    val statusId: String,
    val totalOwed: Int
)