package com.example.myapplication.domain

import com.example.myapplication.data.PlacesRepository
import com.example.myapplication.data.place_info_data_classes.PlaceWithInfo
import javax.inject.Inject

class GetPlaceInfo @Inject constructor(
    private val placesRepository: PlacesRepository
) {
    suspend fun execute(xid : String):PlaceWithInfo{
        return placesRepository.getPlaceInfo(xid)
    }
}
