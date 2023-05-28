package com.example.myapplication.data.models.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Night(
    @Json(name = "HasPrecipitation")
    val hasPrecipitation: Boolean,
    @Json(name = "Icon")
    val icon: Int,
    @Json(name = "IconPhrase")
    val iconPhrase: String
)