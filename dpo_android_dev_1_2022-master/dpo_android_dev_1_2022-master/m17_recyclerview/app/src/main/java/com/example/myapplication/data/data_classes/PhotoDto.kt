package com.example.myapplication.data.data_classes

import com.example.myapplication.entity.Photo
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id") override var id: Int? = null ,
    @SerializedName("sol") override var sol: Int? = null ,
    @SerializedName("camera") override var camera: Camera? = Camera() ,
    @SerializedName("img_src") override var imgSrc: String? = null ,
    @SerializedName("earth_date") override var earthDate: String? = null ,
    @SerializedName("rover") override var rover: Rover? = Rover() ,
) : Photo
