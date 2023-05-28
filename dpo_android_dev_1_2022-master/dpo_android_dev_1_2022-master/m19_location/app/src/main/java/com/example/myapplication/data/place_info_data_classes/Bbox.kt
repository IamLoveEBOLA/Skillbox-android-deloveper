package com.example.myapplication.data.place_info_data_classes

import com.google.gson.annotations.SerializedName

data class Bbox(
    @SerializedName("lat_max") var latmax : Double?=null,
    @SerializedName("lat_min") var latmin : Double?=null,
    @SerializedName("lot_max") var lotmax : Double?=null,
    @SerializedName("lot_min") var lotmin : Double?=null
)
