package com.pcsinfotech.eventapp.interfaces

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    //Base URL
    private const val URL = "https://mock.apidog.com/"

    //Logger
    private val logger= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Create okHttp client using okHttp builder constructor - Helps to create retrofit builder and add the logging interceptor to get the log
    private  val OkHttp : OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

    //create an object for Retrofit Builder using Retrofit builder constructor and pass the base URL, integrate the GSON to convert json to destination object and vice versa and add thr client okHttp
    private val builder : Retrofit.Builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        OkHttp.build())

    //Create Retrofit Instance
    private val retrofit : Retrofit = builder.build()

    //It is a generic function which help us to call the corresponding interface
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}