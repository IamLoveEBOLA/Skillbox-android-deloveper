package com.skillbox.reddit.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.reddit.R
import com.skillbox.reddit.data.MainRepository
import com.skillbox.reddit.domain.ApiResult
import com.skillbox.reddit.presentation.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class SubscribableViewModel(
    private val mainRepository: MainRepository ,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    
    private val _subscribeChannel = Channel<ApiResult<Int>>()
    val subscribeChannel = _subscribeChannel.receiveAsFlow()
    
    fun subscribeUnsubscribe(displayName: String, isUserSubscriber: Boolean, position: Int) {
        viewModelScope.launch (ioDispatcher) {
            _subscribeChannel.send(ApiResult.Loading(position))
            kotlin.runCatching {
                if (isUserSubscriber) {
                    mainRepository.subscribeUnsubscribe("unsub", displayName)
                } else {
                    mainRepository.subscribeUnsubscribe("sub", displayName)
                }
            }.fold(
                onSuccess = { _subscribeChannel.send(ApiResult.Success(position)) },
                onFailure = {
                    _subscribeChannel.send(
                        ApiResult.Error(UiText.ResourceString(R.string.something_went_wrong))
                    )
                }
            )
        }
    }
}