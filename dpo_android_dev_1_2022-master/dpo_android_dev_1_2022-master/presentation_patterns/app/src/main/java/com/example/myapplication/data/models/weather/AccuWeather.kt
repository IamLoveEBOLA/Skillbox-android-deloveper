package com.example.myapplication.data.models.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccuWeather(
    @Json(name = "DailyForecasts")
    val dailyForecasts: List<DailyForecast>,
    @Json(name = "Headline")
    val headline: Headline
)