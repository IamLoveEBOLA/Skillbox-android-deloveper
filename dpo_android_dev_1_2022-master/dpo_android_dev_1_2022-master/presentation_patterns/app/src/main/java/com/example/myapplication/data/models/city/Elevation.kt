package com.example.myapplication.data.models.city

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Elevation(
    @Json(name = "Imperial")
    val imperial: Imperial,
    @Json(name = "Metric")
    val metric: Metric
): Parcelable