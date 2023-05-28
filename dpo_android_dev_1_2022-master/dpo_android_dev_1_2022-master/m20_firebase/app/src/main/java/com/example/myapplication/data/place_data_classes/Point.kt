package com.example.myapplication.data.place_data_classes

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("lon") var lon : Double?=null,
    @SerializedName("lat") var lat : Double?=null
)
