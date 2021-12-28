package com.example.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.*
import java.lang.StringBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadApi()
    }
   // git remote add origin https://github.com/HashBP/API-calling-with-Retrofit-Android.git
    fun loadApi() {

        val BaseUrl = "https://jsonplaceholder.typicode.com/"
        val request = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BaseUrl)
            .build()
            .create(RetrofitInterface::class.java)

        val call = request.post()
        call.enqueue(object : Callback<List<Cases>?> {
            override fun onResponse(call: Call<List<Cases>?>, response: Response<List<Cases>?>) {
                val resbody = response.body()!!
                val MyStringBuilder = StringBuilder()
                for (myData in resbody) {
                    MyStringBuilder.append(myData.body)
                    MyStringBuilder.append("\n")
                }
                TextView.text = MyStringBuilder
            }

            override fun onFailure(call: Call<List<Cases>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }
}
