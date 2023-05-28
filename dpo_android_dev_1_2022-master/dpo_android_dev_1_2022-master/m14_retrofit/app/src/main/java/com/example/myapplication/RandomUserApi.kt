package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface RandomUserApi {
    @GET("/api/")
    fun getRandomUser(): Call<ApiResponse>
}