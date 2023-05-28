package com.example.myapplication.data.data_classes.characters

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Origin(
    @SerializedName("name") var name : String? =null,
    @SerializedName("url") var url : String? = null
): Parcelable
