package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.place_data_classes.Place
import com.example.myapplication.data.place_info_data_classes.PlaceWithInfo
import com.example.myapplication.domain.GetPlaceInfo
import com.example.myapplication.domain.GetPlacesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    val getPlacesListUseCase: GetPlacesListUseCase,
    val getPlaceInfo: GetPlaceInfo
):ViewModel() {
    private val _places = MutableStateFlow<List<Place>?>(null)
    val places = _places.asStateFlow()

    private val _placeWithInfo = MutableStateFlow<PlaceWithInfo?>(null)
    val placeWithInfo = _placeWithInfo.asStateFlow()


    fun buttonOnClick(lat_max:Double,lat_min:Double,lon_max:Double,lon_min:Double){
        viewModelScope.launch {
            _places.value=getPlacesListUseCase.execute(lon_min,lat_min,lon_max, lat_max)
        }
    }
    fun markerOnClick(xid : String){
        viewModelScope.launch {
            _placeWithInfo.value=null
            _placeWithInfo.value = getPlaceInfo.execute(xid)
        }
    }
}