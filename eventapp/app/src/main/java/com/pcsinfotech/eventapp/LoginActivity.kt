package com.pcsinfotech.eventapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pcsinfotech.eventapp.interfaces.ApiService
import com.pcsinfotech.eventapp.interfaces.ServiceBuilder
import com.pcsinfotech.eventapp.modals.IsoCodeGetter
import retrofit2.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadIsoCode()
    }

    private fun loadIsoCode()
    {
        val apiGetIsoCodes : ApiService =  ServiceBuilder.buildService(ApiService::class.java)
        val requestCall : Call<IsoCodeGetter> = apiGetIsoCodes.getIsoCodes()

        //enqueue - Returns true if this call has been either executed or enqueued

        requestCall.enqueue(object : Callback<IsoCodeGetter> {

            //Call - Synchronously send the http request and return its http response

            override fun onResponse(call: Call<IsoCodeGetter>, response: Response<IsoCodeGetter>) {

                if(response.isSuccessful){
                    val isoCodes : IsoCodeGetter = response.body()!!
                    Log.d("isoCodes ",isoCodes.isoCodes.count().toString())
                }
            }


            override fun onFailure(call: Call<IsoCodeGetter>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}