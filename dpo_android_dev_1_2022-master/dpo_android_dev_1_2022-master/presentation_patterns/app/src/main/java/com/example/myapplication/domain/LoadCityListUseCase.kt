package com.example.myapplication.domain

import com.example.myapplication.data.models.city.CityListItem
import com.example.myapplication.data.models.saved_city.SavedCity
import com.example.myapplication.data.retrofit.RetrofitRepository
import com.example.myapplication.data.room.RoomRepository
import kotlinx.coroutines.flow.Flow

class LoadCityListUseCase(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository
) {
    suspend fun execute(cityName: String): List<CityListItem>? {
        return retrofitRepository.getCityList(cityName = cityName)
    }

    fun executeSavedCityList(): Flow<List<SavedCity>> {
        return roomRepository.getSavedCityList()
    }

    suspend fun dropSavedCityList() {
        return roomRepository.dropTable()
    }

    suspend fun saveCity(city: SavedCity): String {
        return roomRepository.saveCity(city)
    }
}