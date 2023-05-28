package com.example.myapplication.data.retrofit

import com.example.myapplication.data.models.city.CityListItem
import com.example.myapplication.data.models.weather.AccuWeather
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

class RetrofitService {
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val responseCityWeather: GetCityWeather = retrofit.create(GetCityWeather::class.java)

    val responseCityList: GetCityList = retrofit.create(GetCityList::class.java)

    interface GetCityWeather {

        @Headers("Accept: application/json", "Content-Type: application/json")

        @GET("/forecasts/v1/daily/1day/{key}")
        suspend fun response(
            @Path("key") locationKey: String,
            @Query("apikey") apikey: String = API_KEY,
        ): Response<AccuWeather>
    }

    interface GetCityList {
        @Headers("Accept: application/json", "Content-Type: application/json")

        @GET("/locations/v1/cities/search")
        suspend fun response(
            @Query("apikey") apikey: String = API_KEY,
            @Query("q") q: String,
            @Query("language") language: String = "ru-ru"
        ): Response<List<CityListItem>>
    }

    companion object {
        const val BASE_URL = "http://dataservice.accuweather.com"
        const val API_KEY = "zmx4WtTdOiLB6zCClu1xc381YB6pWUHO"

    }

}