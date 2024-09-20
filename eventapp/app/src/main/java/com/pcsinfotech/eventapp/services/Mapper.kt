package com.pcsinfotech.eventapp.services

import com.pcsinfotech.eventapp.http.models.HttpIsoCode
import com.pcsinfotech.eventapp.models.IsoCode

class Mapper {
    fun httpIsoCodesToIsoCodes(httpIsoCodes: ArrayList<HttpIsoCode>) : ArrayList<IsoCode> {
        var isoCodesList : ArrayList<IsoCode> = ArrayList<IsoCode>()
        if (httpIsoCodes.isNotEmpty()) {
            for (item in httpIsoCodes) {
                val isoCode : IsoCode = IsoCode(item.country, item.isoCode)
                isoCodesList.add(isoCode)
            }
        }
        return isoCodesList
    }
}