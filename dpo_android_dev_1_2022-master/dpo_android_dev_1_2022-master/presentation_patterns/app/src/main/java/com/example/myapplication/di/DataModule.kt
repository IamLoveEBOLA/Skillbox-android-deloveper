package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.retrofit.RetrofitRepository
import com.example.myapplication.data.retrofit.RetrofitService
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.data.room.RoomRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {
    @Provides
    fun provideRetrofitRepository(retrofitService: RetrofitService): RetrofitRepository {
        return RetrofitRepository(retrofitService)
    }

    @Provides
    @Singleton
    fun provideRetrofitService(): RetrofitService {
        return RetrofitService()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext ,
            AppDatabase::class.java ,
            "app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomRepository(database: AppDatabase): RoomRepository {
        return RoomRepository(database.appDatabaseDao())
    }
}