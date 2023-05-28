package com.example.myapplication.data

import com.example.myapplication.data.data_classes.locations.Locations
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class LocationsRepository @Inject constructor() {
    object RetrofitServices{
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val searchCharactersApi: SearchCharactersApi = retrofit.create(
            SearchCharactersApi::class.java
        )
        interface SearchCharactersApi{
            @GET("location/")
            suspend fun getCharacters(@Query("page")page : Int): Locations
        }

    }
    suspend fun getLocations(page: Int): Locations? {
        return RetrofitServices.searchCharactersApi.getCharacters(page)
    }
}