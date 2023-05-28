package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 2)
abstract class PhotoDataBase:RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}