package com.example.myapplication.data

import com.example.myapplication.data.data_classes.Characters
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class CharactersRepository @Inject constructor() {
    object RetrofitServices{
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val searchChartersApi:SearchChartersApi = retrofit.create(
            SearchChartersApi::class.java
        )
        interface SearchChartersApi{
            @GET("character/")
            suspend fun getCharacters(@Query("page")page : Int):Characters
        }
    }
    suspend fun getCharacters(page : Int): Characters?{
        return RetrofitServices.searchChartersApi.getCharacters(page)

    }
}