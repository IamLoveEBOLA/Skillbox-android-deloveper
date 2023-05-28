package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.CharactersPagingSource
import com.example.myapplication.data.data_classes.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val charatersPagingSource: CharactersPagingSource
) : ViewModel() {
    val pagedCharacters: Flow<PagingData<Results>> = Pager(
        config = PagingConfig(pageSize = 10) ,
        pagingSourceFactory = { charatersPagingSource }
    ).flow.cachedIn(viewModelScope)
}