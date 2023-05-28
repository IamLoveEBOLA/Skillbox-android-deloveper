package com.example.myapplication.data.models.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Minimum(
    @Json(name = "Unit")
    val unit: String,
    @Json(name = "UnitType")
    val unitType: Int,
    @Json(name = "Value")
    val value: Float
)