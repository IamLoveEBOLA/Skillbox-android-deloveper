package com.example.myapplication.data.data_classes.locations

import com.google.gson.annotations.SerializedName

data class ResultsLocation(
    @SerializedName("id") var id: Int? = null ,
    @SerializedName("name") var name: String? = null ,
    @SerializedName("type") var type: String? = null ,
    @SerializedName("dimension") var dimension: String? = null ,
    @SerializedName("residents") var residents: ArrayList<String> = arrayListOf() ,
    @SerializedName("url") var url: String? = null ,
    @SerializedName("created") var created: String? = null


)
