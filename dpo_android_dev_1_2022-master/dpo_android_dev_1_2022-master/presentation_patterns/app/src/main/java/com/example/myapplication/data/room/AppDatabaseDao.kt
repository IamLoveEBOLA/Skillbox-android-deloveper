package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.models.saved_city.SavedCity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDatabaseDao {
    @Query("SELECT * FROM saved_city")
    fun getAll(): Flow<List<SavedCity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedCity: SavedCity): Long

    @Query("DELETE FROM saved_city")
    suspend fun deleteALL()
}