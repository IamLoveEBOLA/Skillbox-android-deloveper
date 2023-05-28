package com.example.myapplication.domain

import com.example.myapplication.data.PhotosRepository
import com.example.myapplication.data.data_classes.Photos
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    val photosRepository: PhotosRepository
){
    suspend fun execute(): Photos {
        return photosRepository.getPhoto()
    }
}