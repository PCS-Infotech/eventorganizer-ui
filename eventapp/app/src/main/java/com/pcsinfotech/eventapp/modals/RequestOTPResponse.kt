package com.pcsinfotech.eventapp.modals


data class RequestBodyForRequestOtp(
    val country: String,
    val isoCode: String,
    val mobile: String
)

data class RequestOTPResponse(
    val success: Boolean,
    val errorCode: String,
    val errorMessage: String,
    val otpValidTimeoutInSecs: Int
)
