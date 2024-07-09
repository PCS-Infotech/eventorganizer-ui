package com.pcsinfotech.eventapp.interfaces

import com.pcsinfotech.eventapp.modals.IsoCodeGetter
import com.pcsinfotech.eventapp.modals.IsoCodeModal
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/m1/544580-506543-default/v1/isoCodes")
    fun getIsoCodes() : Call<IsoCodeGetter>
}
