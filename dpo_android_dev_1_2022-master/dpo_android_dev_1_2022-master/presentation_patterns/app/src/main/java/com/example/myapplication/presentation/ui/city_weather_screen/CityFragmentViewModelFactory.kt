package com.example.myapplication.presentation.ui.city_weather_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.GetCityWeatherUseCase
import com.example.myapplication.domain.LoadCityListUseCase

class CityFragmentViewModelFactory(
    private val getCityWeatherUseCase: GetCityWeatherUseCase ,
    private val loadCityListUseCase: LoadCityListUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CityFragmentViewModel(
            getCityWeatherUseCase = getCityWeatherUseCase,
            loadCityListUseCase = loadCityListUseCase
        ) as T
    }
}