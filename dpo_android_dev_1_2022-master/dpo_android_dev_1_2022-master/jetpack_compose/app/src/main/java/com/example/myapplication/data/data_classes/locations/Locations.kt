package com.example.myapplication.data.data_classes.locations

import com.google.gson.annotations.SerializedName

data class Locations(
    @SerializedName("info") var info : InfoLocations? = InfoLocations(),
    @SerializedName("results") var results : ArrayList<ResultsLocation> = arrayListOf()
)
