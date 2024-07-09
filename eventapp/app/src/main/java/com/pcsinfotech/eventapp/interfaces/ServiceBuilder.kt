package com.pcsinfotech.eventapp.interfaces

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    //Base URL
    private const val URL = "https://mock.apidog.com/"

    //Create okHttp client - Helps to create retrofit builder
    private  val OkHttp : OkHttpClient.Builder = OkHttpClient.Builder()

    //Instantiate Retrofit Builder
    private val builder : Retrofit.Builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        OkHttp.build())

    //Create Retrofit Instance
    private val retrofit : Retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}