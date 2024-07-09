package com.pcsinfotech.eventapp.modals

data class IsoCodeModal(
    val country: String,
    val isoCode: String
)

data class IsoCodeGetter(
    val success: Boolean,
    val errorCode: String,
    val errorMessage: String,
    val isoCodes: ArrayList<IsoCodeModal>
)