package com.skillbox.reddit.presentation.viewModels.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.reddit.data.MainRepository
import com.skillbox.reddit.di.IoDispatcher
import com.skillbox.reddit.domain.ApiResult
import com.skillbox.reddit.domain.model.AbstractRedditEntity
import com.skillbox.reddit.presentation.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val mainRepository: MainRepository ,
    private val communication: Communication.Base<ApiResult<AbstractRedditEntity>> ,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    
    fun getUserInfo(userName: String) {
        viewModelScope.launch(ioDispatcher) {
            communication.map(ApiResult.Loading())
            val response = mainRepository.getUserInfo(userName)
            communication.map(response)
        }
    }
    
    suspend fun observe(collector: FlowCollector<ApiResult<AbstractRedditEntity>?>) {
        communication.observe(collector)
    }
}