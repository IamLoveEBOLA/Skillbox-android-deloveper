package com.example.myapplication.data.models.city

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class GeoPosition(
    @Json(name = "Elevation")
    val elevation: Elevation,
    @Json(name = "Latitude")
    val latitude: Double,
    @Json(name = "Longitude")
    val longitude: Double
): Parcelable