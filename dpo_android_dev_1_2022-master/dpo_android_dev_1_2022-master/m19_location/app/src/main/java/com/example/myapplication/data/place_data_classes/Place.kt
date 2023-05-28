package com.example.myapplication.data.place_data_classes

import com.example.myapplication.data.place_data_classes.Point
import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("xid"      ) var xid      : String? = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("rate"     ) var rate     : Int?    = null,
    @SerializedName("osm"      ) var osm      : String? = null,
    @SerializedName("wikidata" ) var wikidata : String? = null,
    @SerializedName("kinds"    ) var kinds    : String? = null,
    @SerializedName("point"    ) var point    : Point?  = Point()

)
