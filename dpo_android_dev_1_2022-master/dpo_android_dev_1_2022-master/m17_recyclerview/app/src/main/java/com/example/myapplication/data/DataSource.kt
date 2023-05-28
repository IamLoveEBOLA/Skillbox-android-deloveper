package com.example.myapplication.data

import com.example.myapplication.data.data_classes.Photos

interface DataSource {
    suspend fun getPhoto(): Photos
}