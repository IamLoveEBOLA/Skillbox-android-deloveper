package com.example.myapplication.data.place_info_data_classes

import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("geometry"   ) var geometry   : String?           = null ,
    @SerializedName("attributes" ) var attributes : ArrayList<String> = arrayListOf()
)
