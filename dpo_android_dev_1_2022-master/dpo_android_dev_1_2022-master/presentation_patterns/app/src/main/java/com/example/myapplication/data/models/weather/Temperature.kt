package com.example.myapplication.data.models.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temperature(
    @Json(name = "Maximum")
    val maximum: Maximum,
    @Json(name = "Minimum")
    val minimum: Minimum
)