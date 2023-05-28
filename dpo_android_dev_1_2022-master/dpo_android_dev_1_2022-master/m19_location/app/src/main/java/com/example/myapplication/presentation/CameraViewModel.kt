package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.SavePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    val savePhotoUseCase: SavePhotoUseCase ,
):ViewModel() {
    fun savePhoto(uri:String,date:String){
        viewModelScope.launch {
            savePhotoUseCase.execute(uri, date)
        }
    }
}