package com.example.myapplication.presentation.ui.city_weather_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.saved_city.SavedCity
import com.example.myapplication.data.models.weather.DailyForecast
import com.example.myapplication.domain.GetCityWeatherUseCase
import com.example.myapplication.domain.LoadCityListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityFragmentViewModel(
    private val getCityWeatherUseCase: GetCityWeatherUseCase ,
    private val loadCityListUseCase: LoadCityListUseCase
) :
    ViewModel() {
    private var _city = MutableStateFlow<DailyForecast?>(null)
    val city get() = _city.asStateFlow()

    fun loadTemperature(cityKey: String) {
        viewModelScope.launch {
            _city.value = getCityWeatherUseCase.execute(cityKey)
        }
    }

    fun saveCity(savedCity: SavedCity) {
        viewModelScope.launch {
            val result = loadCityListUseCase.saveCity(savedCity)
            Log.d("TAG", "saveCity: $result")
        }
    }
}