package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos_db")
    fun getAll(): Flow<List<Photo>>

    @Query("SELECT * FROM photos_db")
    fun getAllNotFLow(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(photo: Photo): Long

    @Delete
    suspend fun delete(photo: Photo)

    @Query("DELETE FROM photos_db")
    suspend fun clear()

    @Query("SELECT * FROM photos_db WHERE uri = :uri")
    suspend fun findByUri(uri: String): Photo?
}