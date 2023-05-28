package com.example.myapplication.presentation.ui.search_history_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.saved_city.SavedCity
import com.example.myapplication.domain.LoadCityListUseCase
import com.example.myapplication.domain.utils.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CitySearchHistoryViewModel(
    val loadCityListUseCase: LoadCityListUseCase
) : ViewModel() {

    private var _cityList = MutableStateFlow<List<SavedCity>?>(null)
    val cityList get() = _cityList.asStateFlow()


    private var _state = MutableStateFlow<State>(State.Success)
    val state get() = _state.asStateFlow()

    init {
        loadCityList()
    }

    fun clearHistory() {
        viewModelScope.launch {
            loadCityListUseCase.dropSavedCityList()
            _cityList.value = null
        }
    }

    fun loadCityList() {
        _state.value = State.Loading
        viewModelScope.launch {
            delay(1000L)
            try {
                val db = loadCityListUseCase.executeSavedCityList()
                db.collect { listOfSavedCity ->
                    if (listOfSavedCity.isEmpty()) {
                        _state.value = State.Error("История поиска пуста")
                        _cityList.value = null
                    } else {
                        _cityList.value = listOfSavedCity
                        _state.value = State.Success
                    }
                }
            } catch (e: Exception) {
                Log.d("TAG", "loadCityList from DB: ${e.message}")
            }
        }
    }
}