package com.example.myapplication.data

import com.example.myapplication.data.data_classes.Photos
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DataSourceImpRetrofit : DataSource {
    object RetrofitServeces {
        private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
        private val gson = GsonBuilder().setLenient().create()
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val searchPhotosApi: SearchPhotosApi = retrofit.create(
            SearchPhotosApi::class.java
        )

        interface SearchPhotosApi {
            @GET("photos?sol=1000&api_key=sxOCCeR7BK19IfBYiGdve1w7GcR1dk7BijogrhHN")
            //@GET("photos?sol=1000&api_key=DEMO_KEY")
            suspend fun getPhoto(): Photos
        }


    }

    override suspend fun getPhoto(): Photos {
        return RetrofitServeces.searchPhotosApi.getPhoto()

    }
}