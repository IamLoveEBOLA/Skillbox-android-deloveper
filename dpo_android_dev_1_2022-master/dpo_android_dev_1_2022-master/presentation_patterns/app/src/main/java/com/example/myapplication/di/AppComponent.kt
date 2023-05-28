package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.presentation.ui.city_search_screen.SearchViewModelFactory
import com.example.myapplication.presentation.ui.city_weather_screen.CityFragmentViewModelFactory
import com.example.myapplication.presentation.ui.search_history_screen.CitySearchHistoryViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun searchViewModelFactory(): SearchViewModelFactory
    fun cityViewModelFactory(): CityFragmentViewModelFactory
    fun citySearchHistoryViewModelFactory(): CitySearchHistoryViewModelFactory
}