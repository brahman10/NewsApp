package com.example.newsappassignment.network

import com.example.newsappassignment.data.ParentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("everything")
    suspend fun getAllFeed(@Query("apiKey") apiKey:String,@Query("q") search:String):ParentResponse
}