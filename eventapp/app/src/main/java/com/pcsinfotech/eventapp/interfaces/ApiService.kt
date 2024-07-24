package com.pcsinfotech.eventapp.interfaces

import com.pcsinfotech.eventapp.modals.IsoCodeGetter
import com.pcsinfotech.eventapp.modals.RequestBodyForRequestOtp
import com.pcsinfotech.eventapp.modals.RequestOTPResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    //end point to get and display the isoCode -- mocked cloud URL
    @GET("/m1/544580-506543-default/v1/isoCodes")
    //function which return the call object of IsoCodeGetter from modal
    fun getIsoCodes() : Call<IsoCodeGetter>
}


interface RequestOtpService {
    @POST("/m1/544580-506543-default/v1/requestOTP")
    fun requestOTPParam(@Body request: RequestBodyForRequestOtp): Call<RequestOTPResponse>


}
