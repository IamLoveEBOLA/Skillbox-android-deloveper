package com.example.myapplication.data

import com.example.myapplication.data.data_classes.Photos
import javax.inject.Inject

class PhotosRepository @Inject constructor() {
    val dataSource:DataSource = DataSourceImpRetrofit()
    suspend fun getPhoto():Photos{
        return dataSource.getPhoto()
    }

}