package com.example.myapplication.domain

import com.example.myapplication.data.models.weather.DailyForecast
import com.example.myapplication.data.retrofit.RetrofitRepository

class GetCityWeatherUseCase(
    private val retrofitRepository: RetrofitRepository
) {
    suspend fun execute(city:String):DailyForecast?{
        return retrofitRepository.getCityWeather(cityKey = city)
    }
}