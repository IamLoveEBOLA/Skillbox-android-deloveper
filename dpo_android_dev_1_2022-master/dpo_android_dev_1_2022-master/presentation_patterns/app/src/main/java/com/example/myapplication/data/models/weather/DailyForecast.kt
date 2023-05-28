package com.example.myapplication.data.models.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyForecast(
    @Json(name = "Date")
    val date: String,
    @Json(name = "Day")
    val day: Day,
    @Json(name = "EpochDate")
    val epochDate: Int,
    @Json(name = "Link")
    val link: String,
    @Json(name = "MobileLink")
    val mobileLink: String,
    @Json(name = "Night")
    val night: Night,
    @Json(name = "Sources")
    val sources: List<String>,
    @Json(name = "Temperature")
    val temperature: Temperature
)