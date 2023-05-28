package com.skillbox.reddit.presentation.viewModels.favourite

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.skillbox.reddit.data.MainRepository
import com.skillbox.reddit.di.IoDispatcher
import com.skillbox.reddit.presentation.viewModels.subreddits.AbstractSubredditsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class FavoriteSubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository ,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AbstractSubredditsViewModel(mainRepository, ioDispatcher) {
    
    override fun getPagedSubreddits(query: String) =
        mainRepository.getPagedFavoriteSubreddits().cachedIn(viewModelScope)
    
}