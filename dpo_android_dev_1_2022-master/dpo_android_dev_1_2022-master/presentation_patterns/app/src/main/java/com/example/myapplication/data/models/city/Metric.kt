package com.example.myapplication.data.models.city

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Metric(
    @Json(name = "Unit")
    val unit: String,
    @Json(name = "UnitType")
    val unitType: Int,
    @Json(name = "Value")
    val value: Double
): Parcelable