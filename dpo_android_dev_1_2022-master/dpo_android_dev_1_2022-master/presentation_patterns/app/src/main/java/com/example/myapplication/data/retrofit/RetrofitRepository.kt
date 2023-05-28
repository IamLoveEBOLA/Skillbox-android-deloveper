package com.example.myapplication.data.retrofit

import android.util.Log
import com.example.myapplication.data.models.city.CityListItem
import com.example.myapplication.data.models.weather.DailyForecast
import java.lang.Exception

class RetrofitRepository(
    private val retrofitService:RetrofitService
) {

    suspend fun getCityList(cityName:String):List<CityListItem>?{
        return try {
            val response =
                retrofitService.responseCityList.response(q = cityName)
            response.body()
        } catch (e:Exception) {
            Log.d("TAG" , "retrofitServiceError :  ${e.message}")
            null
        }
    }
    suspend fun getCityWeather(cityKey : String): DailyForecast?{

        return try{
            val response =
                retrofitService.responseCityWeather.response(locationKey = cityKey)
            response.body()!!.dailyForecasts[0]
        } catch (e: Exception) {
            Log.d("TAG", "retrofitServiceError: ${e.message}")
            null
        }
    }
}