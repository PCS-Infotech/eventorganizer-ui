package com.pcsinfotech.eventapp.http.interfaces

import com.pcsinfotech.eventapp.http.models.HttpIsoCodes
import com.pcsinfotech.eventapp.http.models.RequestBodyForRequestOtp
import com.pcsinfotech.eventapp.http.models.RequestOTPResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IsocodeAPI {

    //end point to get and display the isoCode -- mocked cloud URL
    @GET("/m1/544580-506543-default/v1/isoCodes")
    //function which return the call object of IsoCodeGetter from modal
    fun getIsoCodes() : Call<HttpIsoCodes>
}


interface OtpAPI {
    @POST("/m1/544580-506543-default/v1/requestOTP")
    fun requestOTPParam(@Body request: RequestBodyForRequestOtp): Call<RequestOTPResponse>


}
