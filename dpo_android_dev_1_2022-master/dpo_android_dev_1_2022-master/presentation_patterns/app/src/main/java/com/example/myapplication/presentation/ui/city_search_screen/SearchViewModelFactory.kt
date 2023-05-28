package com.example.myapplication.presentation.ui.city_search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.LoadCityListUseCase

class SearchViewModelFactory(private val loadCityListUseCase: LoadCityListUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchFragmentViewModel(loadCityListUseCase = loadCityListUseCase) as T
    }
}