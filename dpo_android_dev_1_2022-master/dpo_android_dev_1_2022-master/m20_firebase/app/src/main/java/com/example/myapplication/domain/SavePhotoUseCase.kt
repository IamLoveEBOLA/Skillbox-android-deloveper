package com.example.myapplication.domain

import com.example.myapplication.data.PhotoRepository
import javax.inject.Inject

class SavePhotoUseCase @Inject constructor(
    val photoRepository: PhotoRepository
) {
    suspend fun execute(uri:String,date:String){
        photoRepository.savePhoto(uri, date)
    }
}