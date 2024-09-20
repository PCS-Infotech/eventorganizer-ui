package com.pcsinfotech.eventapp.http.models


data class HttpIsoCode(
    val country: String,
    val isoCode: String
)

data class HttpIsoCodes(
    val success: Boolean,
    val errorCode: String,
    val errorMessage: String,
    val isoCodes: ArrayList<HttpIsoCode>
)
