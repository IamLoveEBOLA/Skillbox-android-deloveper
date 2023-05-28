package com.example.myapplication.data.models.city

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "EnglishName")
    val englishName: String,
    @Json(name = "ID")
    val iD: String,
    @Json(name = "LocalizedName")
    val localizedName: String
): Parcelable