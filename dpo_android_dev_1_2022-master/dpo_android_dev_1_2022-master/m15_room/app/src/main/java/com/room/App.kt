
package com.room

import android.app.Application
import androidx.room.Room

class App : Application() {

    lateinit var db: WordDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(       //databaseBuilder передаем :
            applicationContext,                 // контекст
            WordDatabase::class.java,             // класс БД
            "word_database"
        )
            .build()
    }
}
