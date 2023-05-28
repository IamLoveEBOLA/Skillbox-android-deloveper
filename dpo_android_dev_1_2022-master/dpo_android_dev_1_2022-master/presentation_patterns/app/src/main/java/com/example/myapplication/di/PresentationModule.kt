package com.example.myapplication.di

import com.example.myapplication.domain.GetCityWeatherUseCase
import com.example.myapplication.domain.LoadCityListUseCase
import com.example.myapplication.presentation.ui.city_search_screen.SearchFragmentViewModel
import com.example.myapplication.presentation.ui.city_search_screen.SearchViewModelFactory
import com.example.myapplication.presentation.ui.city_weather_screen.CityFragmentViewModel
import com.example.myapplication.presentation.ui.city_weather_screen.CityFragmentViewModelFactory
import com.example.myapplication.presentation.ui.search_history_screen.CitySearchHistoryViewModel
import com.example.myapplication.presentation.ui.search_history_screen.CitySearchHistoryViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class PresentationModule {
    @Provides
    fun provideMainViewModelFactory(
        loadCityListUseCase: LoadCityListUseCase
    ): SearchViewModelFactory {
        return SearchViewModelFactory(
            loadCityListUseCase
        )
    }

    @Provides
    fun provideMainViewModel(
        loadCityListUseCase: LoadCityListUseCase
    ): SearchFragmentViewModel {
        return SearchFragmentViewModel(
            loadCityListUseCase
        )
    }

    @Provides
    fun provideCityFragmentViewModelFactory(
        getCityWeatherUseCase: GetCityWeatherUseCase , loadCityListUseCase: LoadCityListUseCase
    ): CityFragmentViewModelFactory {
        return CityFragmentViewModelFactory(getCityWeatherUseCase , loadCityListUseCase)
    }

    @Provides
    fun provideCityFragmentViewModel(
        getCityWeatherUseCase: GetCityWeatherUseCase , loadCityListUseCase: LoadCityListUseCase
    ): CityFragmentViewModel {
        return CityFragmentViewModel(getCityWeatherUseCase , loadCityListUseCase)
    }

    @Provides
    fun provideCitySearchHistoryViewModelFactory(
        loadCityListUseCase: LoadCityListUseCase
    ): CitySearchHistoryViewModelFactory {
        return CitySearchHistoryViewModelFactory(loadCityListUseCase = loadCityListUseCase)
    }

    @Provides
    fun provideCitySearchHistoryViewModel(
        loadCityListUseCase: LoadCityListUseCase
    ): CitySearchHistoryViewModel {
        return CitySearchHistoryViewModel(loadCityListUseCase = loadCityListUseCase)
    }


}