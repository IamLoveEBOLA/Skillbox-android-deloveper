package com.example.myapplication.presentation.ui.search_history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.LoadCityListUseCase

class CitySearchHistoryViewModelFactory(
    private val loadCityListUseCase: LoadCityListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CitySearchHistoryViewModel(loadCityListUseCase = loadCityListUseCase) as T
    }
}