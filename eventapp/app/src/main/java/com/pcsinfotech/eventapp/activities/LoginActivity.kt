package com.pcsinfotech.eventapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.pcsinfotech.eventapp.R
import com.pcsinfotech.eventapp.models.IsoCode
import com.pcsinfotech.eventapp.services.IsoCodeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var exposedDropDown : AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        exposedDropDown = findViewById(R.id.autoCompleteTextView)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadIsoCodes()
        // postRequestOtp()

    }

    private fun loadIsoCodes()
    {
        //var isoCodesList : ArrayList<IsoCode> = ArrayList()




            var isoCodeService = IsoCodeService()
            val isoCodes = isoCodeService.getIsoCodes()


                if (isoCodes.isNotEmpty()) {
                    updateDropdown(isoCodes)
                }
            else{
                    Log.d("Empty", "No data received")
            }


    }

      private fun updateDropdown(items: List<IsoCode>) {

            val adapter = ArrayAdapter(this@LoginActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items)
            val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
            autoCompleteTextView.setAdapter(adapter);
    }
}


/*    //POST method for request OTP

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
*/