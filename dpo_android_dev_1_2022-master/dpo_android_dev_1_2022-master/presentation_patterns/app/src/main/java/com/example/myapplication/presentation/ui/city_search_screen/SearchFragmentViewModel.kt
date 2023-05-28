package com.example.myapplication.presentation.ui.city_search_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.city.CityListItem
import com.example.myapplication.domain.LoadCityListUseCase
import com.example.myapplication.domain.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchFragmentViewModel(
    private val loadCityListUseCase: LoadCityListUseCase
) : ViewModel() {
    init {
        Log.d("TAG", "INIT: viewModel")
    }

    private var _cityList = MutableStateFlow<List<CityListItem>?>(null)
    val cityList get() = _cityList.asStateFlow()

    private var _state = MutableStateFlow<State>(State.Success)
    val state get() = _state.asStateFlow()

    fun loadCityList(city: String) {
        Log.d("TAG", "loadCityList: START")
        _state.value = State.Loading
        viewModelScope.launch {
            Log.d("TAG", "loadCityList: LAUNCH")
            val result = loadCityListUseCase.execute(cityName = city)
            Log.d("TAG", "loadCityList: $result")
            if (result != null) {
                _cityList.value = result
                _state.value = State.Success
            }
            else {
                _state.value = State.Error("Error")
                Log.d("TAG", "loadCityList: ELSE")
            }
        }
    }
}