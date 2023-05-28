package com.example.myapplication.data.room

import com.example.myapplication.data.models.saved_city.SavedCity
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class RoomRepository(
    private val appDatabaseDao: AppDatabaseDao
) {

    fun getSavedCityList(): Flow<List<SavedCity>>{
        return appDatabaseDao.getAll()
    }

    suspend fun dropTable(){
        return appDatabaseDao.deleteALL()

    }

    suspend fun saveCity(city : SavedCity):String{
        return try {
            appDatabaseDao.insert(city).toString()
        } catch (e:Exception){
            e.message.toString()
        }
    }
}