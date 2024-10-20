package com.pcsinfotech.eventapp.services

import com.pcsinfotech.eventapp.http.models.HttpIsoCode
import com.pcsinfotech.eventapp.http.services.HttpIsoCodeService
import com.pcsinfotech.eventapp.models.IsoCode
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait


class IsoCodeService {
    fun getIsoCodes(): ArrayList<IsoCode> {
        var isoCodes: ArrayList<IsoCode> = ArrayList()
        runBlocking {
            val httpIsoCodeService = HttpIsoCodeService()
            runBlocking {
                val httpIsoCodes = httpIsoCodeService.getIsoCodes()
                isoCodes = Mapper().httpIsoCodesToIsoCodes(httpIsoCodes)
            }
        };

        return isoCodes
    }
}