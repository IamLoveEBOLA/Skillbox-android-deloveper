package com.example.myapplication.data.data_classes

import com.google.gson.annotations.SerializedName

data class Camera (

    @SerializedName("id") var id :Int? = null,
    @SerializedName("name") var name :String? = null,
    @SerializedName("rover_id") var roverId :Int? = null,
    @SerializedName("full_name") var fullName :String? = null
)