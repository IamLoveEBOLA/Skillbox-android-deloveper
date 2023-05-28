package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.models.saved_city.SavedCity

@Database(entities = [SavedCity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appDatabaseDao():AppDatabaseDao
}