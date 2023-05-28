package com.example.myapplication.data.models.saved_city

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_city")
data class SavedCity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "city_name")
    val city_name: String,
    @ColumnInfo(name = "region")
    val region: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "latitude")
    val latitude: String,
    @ColumnInfo(name = "longitude")
    val longitude: String,
    @ColumnInfo(name = "t_min")
    val t_min: String,
    @ColumnInfo(name = "t_max")
    val t_max: String,
    @ColumnInfo(name = "date")
    val date: String
)