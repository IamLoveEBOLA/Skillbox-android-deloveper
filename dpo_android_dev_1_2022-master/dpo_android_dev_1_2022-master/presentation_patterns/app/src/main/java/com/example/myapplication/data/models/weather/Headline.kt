package com.example.myapplication.data.models.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Headline(
    @Json(name = "Category")
    val category: String,
    @Json(name = "EffectiveDate")
    val effectiveDate: String,
    @Json(name = "EffectiveEpochDate")
    val effectiveEpochDate: Int,
    @Json(name = "EndDate")
    val endDate: String?,
    @Json(name = "EndEpochDate")
    val endEpochDate: Int?,
    @Json(name = "Link")
    val link: String,
    @Json(name = "MobileLink")
    val mobileLink: String,
    @Json(name = "Severity")
    val severity: Int,
    @Json(name = "Text")
    val text: String
)