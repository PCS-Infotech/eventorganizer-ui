package com.pcsinfotech.eventapp.http.services

import android.util.Log
import com.pcsinfotech.eventapp.http.ServiceBuilder
import com.pcsinfotech.eventapp.http.interfaces.IsocodeAPI
import com.pcsinfotech.eventapp.http.models.HttpIsoCode
import com.pcsinfotech.eventapp.http.models.HttpIsoCodes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HttpIsoCodeService {
    suspend fun getIsoCodes():ArrayList<HttpIsoCode> {
            val api: IsocodeAPI = ServiceBuilder.buildService(IsocodeAPI::class.java)
            var isoCodeArrayList = CoroutineScope(Dispatchers.IO).async{
                var isoCodeArrayList: ArrayList<HttpIsoCode> = ArrayList();
                val requestCall: Call<HttpIsoCodes> = api.getIsoCodes()
                try {
                    val res: Response<HttpIsoCodes> = requestCall.execute()
                    var response: HttpIsoCodes? = res.body()

                    if (response != null) {
                        isoCodeArrayList = response.isoCodes
                    }
                } catch (ex: Exception) {
                    Log.e("Exception", ex.toString())
                    ex.printStackTrace();
                }
                return@async isoCodeArrayList;
            }
        return isoCodeArrayList.await()
    }
}