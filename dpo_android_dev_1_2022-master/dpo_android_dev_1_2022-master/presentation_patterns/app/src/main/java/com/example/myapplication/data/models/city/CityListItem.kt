package com.example.myapplication.data.models.city

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class CityListItem(
    @Json(name = "AdministrativeArea")
    val administrativeArea: AdministrativeArea,
    @Json(name = "Country")
    val country: Country,
    @Json(name = "DataSets")
    val dataSets: List<String>,
    @Json(name = "EnglishName")
    val englishName: String,
    @Json(name = "GeoPosition")
    val geoPosition: GeoPosition,
    @Json(name = "IsAlias")
    val isAlias: Boolean,
    @Json(name = "Key")
    val key: String,
    @Json(name = "LocalizedName")
    val localizedName: String,
    @Json(name = "PrimaryPostalCode")
    val primaryPostalCode: String,
    @Json(name = "Rank")
    val rank: Int,
    @Json(name = "Region")
    val region: Region,
    @Json(name = "SupplementalAdminAreas")
    val supplementalAdminAreas: List<SupplementalAdminArea>,
    @Json(name = "TimeZone")
    val timeZone: TimeZone,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Version")
    val version: Int
): Parcelable