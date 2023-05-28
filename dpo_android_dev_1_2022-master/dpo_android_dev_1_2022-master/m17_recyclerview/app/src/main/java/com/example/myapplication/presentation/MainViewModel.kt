package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.data_classes.Photos
import com.example.myapplication.domain.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
):ViewModel() {

    private val _photos = MutableStateFlow<Photos?>(null)
    val photos = _photos.asStateFlow()

    fun reloadPhotos(){
        viewModelScope.launch {
            _photos.value = getPhotoUseCase.execute()
        }
    }
}