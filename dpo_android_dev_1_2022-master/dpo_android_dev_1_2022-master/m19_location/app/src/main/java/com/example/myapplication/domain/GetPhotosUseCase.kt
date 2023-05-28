package com.example.myapplication.domain

import com.example.myapplication.data.Photo
import com.example.myapplication.data.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    val photosRepository: PhotoRepository
) {
    fun execute(): Flow<List<Photo>> {
        return photosRepository.getAllPhotos()
    }
}