package com.example.myapplication.domain

import com.example.myapplication.data.PlacesRepository
import com.example.myapplication.data.place_data_classes.Place
import javax.inject.Inject

class GetPlacesListUseCase @Inject constructor(
    val placesRepository: PlacesRepository
) {
    suspend fun execute(lon_min:Double,lat_min:Double,lon_max:Double, lat_max:Double): List<Place>{
        return placesRepository.getPlaces(lon_min,lat_min,lon_max, lat_max)
    }
}