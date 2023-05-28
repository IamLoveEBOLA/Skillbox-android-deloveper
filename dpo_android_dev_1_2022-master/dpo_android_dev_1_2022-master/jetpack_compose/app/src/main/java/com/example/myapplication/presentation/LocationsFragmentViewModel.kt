package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.LocationsPagingSource
import com.example.myapplication.data.data_classes.locations.ResultsLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LocationsFragmentViewModel @Inject constructor(
    private val locationsPagingSource: LocationsPagingSource
):ViewModel() {
    val pagedLocations: Flow<PagingData<ResultsLocation>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { locationsPagingSource}
    ).flow.cachedIn(viewModelScope)
}