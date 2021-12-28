package com.example.testapi

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("posts")
    fun post() : Call<List<Cases>>
}