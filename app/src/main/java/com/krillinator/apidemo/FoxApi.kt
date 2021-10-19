package com.krillinator.apidemo

import retrofit2.Call
import retrofit2.http.GET

interface FoxApi {
    @GET("floof")
    fun getInfo(): Call<Fox>
}