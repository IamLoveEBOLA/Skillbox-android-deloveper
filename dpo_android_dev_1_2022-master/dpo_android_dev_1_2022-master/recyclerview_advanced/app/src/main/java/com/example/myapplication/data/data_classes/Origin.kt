package com.example.myapplication.data.data_classes

import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name") var name : String? =null,
    @SerializedName("url") var url : String? = null
)
