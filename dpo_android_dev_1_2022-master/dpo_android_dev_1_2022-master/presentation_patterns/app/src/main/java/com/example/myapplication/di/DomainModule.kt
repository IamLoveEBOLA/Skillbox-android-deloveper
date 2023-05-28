package com.example.myapplication.di

import com.example.myapplication.data.retrofit.RetrofitRepository
import com.example.myapplication.data.retrofit.RetrofitService
import com.example.myapplication.data.room.RoomRepository
import com.example.myapplication.domain.GetCityWeatherUseCase
import com.example.myapplication.domain.LoadCityListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetCityWeatherUseCase(
        retrofitRepository: RetrofitRepository
    ): GetCityWeatherUseCase {
        return GetCityWeatherUseCase(retrofitRepository = retrofitRepository)
    }

    @Provides
    fun provideLoadCityListUseCase(
        retrofitRepository: RetrofitRepository ,
        roomRepository: RoomRepository
    ): LoadCityListUseCase {
        return LoadCityListUseCase(
        retrofitRepository = retrofitRepository ,
        roomRepository = roomRepository
        )
    }
}