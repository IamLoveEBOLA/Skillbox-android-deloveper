package com.example.myapplication.data.models.city

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TimeZone(
    @Json(name = "Code")
    val code: String,
    @Json(name = "GmtOffset")
    val gmtOffset: Double,
    @Json(name = "IsDaylightSaving")
    val isDaylightSaving: Boolean,
    @Json(name = "Name")
    val name: String,
    @Json(name = "NextOffsetChange")
    val nextOffsetChange: String? = null
): Parcelable