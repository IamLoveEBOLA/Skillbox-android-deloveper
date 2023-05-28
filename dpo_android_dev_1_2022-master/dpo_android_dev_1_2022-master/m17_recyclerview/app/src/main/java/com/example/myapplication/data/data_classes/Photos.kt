package com.example.myapplication.data.data_classes

import com.google.gson.annotations.SerializedName

data class Photos(

    @SerializedName("photos") var photDos: ArrayList<PhotoDto> = arrayListOf()
)
