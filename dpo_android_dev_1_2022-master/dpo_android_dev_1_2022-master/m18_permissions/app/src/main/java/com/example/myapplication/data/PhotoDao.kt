package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo_db")
    fun getAll(): Flow<List<Photo>>

    @Insert
    suspend fun insert(photo: Photo)
}