package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getPhotosUseCase: GetPhotosUseCase
):ViewModel() {
    val allPhotos = getPhotosUseCase.execute()
}