package com.pcsinfotech.eventapp.services

import com.pcsinfotech.eventapp.http.models.HttpIsoCode
import com.pcsinfotech.eventapp.http.services.HttpIsoCodeService
import com.pcsinfotech.eventapp.models.IsoCode


class IsoCodeService {
    fun getIsoCodes(): ArrayList<IsoCode> {
        var isoCodes : ArrayList<IsoCode>

        //Call Http Service to get the HttpIsoCodes.
        var httpIsoCodeService = HttpIsoCodeService()
        var httpIsoCodes : ArrayList<HttpIsoCode> = httpIsoCodeService.getIsoCodes()

        //Convert HttpIsoCodes to IsoCodes.
        var mapper : Mapper= Mapper()
        isoCodes = mapper.httpIsoCodesToIsoCodes(httpIsoCodes)


        return isoCodes
    }
}