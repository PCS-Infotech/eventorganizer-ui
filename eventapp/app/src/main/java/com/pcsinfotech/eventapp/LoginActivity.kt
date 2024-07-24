package com.pcsinfotech.eventapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pcsinfotech.eventapp.interfaces.ApiService
import com.pcsinfotech.eventapp.interfaces.RequestOtpService
import com.pcsinfotech.eventapp.interfaces.ServiceBuilder
import com.pcsinfotech.eventapp.modals.IsoCodeGetter
import com.pcsinfotech.eventapp.modals.RequestBodyForRequestOtp
import com.pcsinfotech.eventapp.modals.RequestOTPResponse
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
        postRequestOtp()

    }

    private fun loadIsoCode()
    {
        //calling the buildService function which is belongs to Service Builder object and passing the ApiService interface which returns the instance or object of the class which is in interface ApiService
        val apiGetIsoCodes : ApiService =  ServiceBuilder.buildService(ApiService::class.java)

        //calling the function getIsoCodes()(it will return the call object which will fetch the isoCode from the server) which is in interface using the object reference apiGetIsoCodes and storing in requestCall instance
        val requestCall : Call<IsoCodeGetter> = apiGetIsoCodes.getIsoCodes()

        //enqueue() - Asynchronously send the request and notify callback of its response or if an error occurred talking to the server, creating the request, or processing the response.
        requestCall.enqueue(object : Callback<IsoCodeGetter> {

            //Call - Synchronously send the http request and return its http response

            override fun onResponse(call: Call<IsoCodeGetter>, response: Response<IsoCodeGetter>) {
                //Status code will decide the success or failure
                if(response.isSuccessful){
                    val isoCodes : IsoCodeGetter = response.body()!!
                    Log.d("isoCodes ",isoCodes.isoCodes.count().toString())
                }
                else //Application level failure
                {
                    Toast.makeText(this@LoginActivity, "Failed to retrieve IsoCodes", Toast.LENGTH_SHORT).show()
                }
            }

            //Exception
            override fun onFailure(call: Call<IsoCodeGetter>, t: Throwable) {



            }


        })
    }

    //POST method for request OTP

    private fun postRequestOtp()
    {
        val requestOtpService : RequestOtpService =  ServiceBuilder.buildService(RequestOtpService::class.java)

        val requestBodyMessage = RequestBodyForRequestOtp("INDIA", "+91", "8001002222")
        val requestCall : Call<RequestOTPResponse> = requestOtpService.requestOTPParam(requestBodyMessage)

        //enqueue - Returns true if this call has been either executed or enqueued

        requestCall.enqueue(object : Callback<RequestOTPResponse> {

            //Call - Synchronously send the http request and return its http response

            override fun onResponse(call: Call<RequestOTPResponse>, response: Response<RequestOTPResponse>) {

                if(response.isSuccessful){
                    val requestOTPResponse : RequestOTPResponse = response.body()!!

                    if(requestOTPResponse.success) {
                        Toast.makeText(this@LoginActivity, requestOTPResponse.otpValidTimeoutInSecs, Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@LoginActivity, requestOTPResponse.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
                else //Application level failure
                {
                    Toast.makeText(this@LoginActivity, "Failed to post request otp", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RequestOTPResponse>, t: Throwable) {

            }


        })
    }


    }
