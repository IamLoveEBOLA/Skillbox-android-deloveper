package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ModelView : ViewModel() {
    private val searchJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + searchJob)

    val searchResult = MutableLiveData<String>()
    val showProcess = MutableLiveData<Boolean>()
    val showError = MutableLiveData<Boolean>()




    init {
        showProcess.value = false
        showError.value = false
    }


    fun search(query: String) {
        if (query.length < 3) { return
        }

        uiScope.launch{
            showProcess.value = true
            delay(5000)
            if (query == "error") {
                showError.value = true
            } else {
                searchResult.value = "По запросу \"$query ничего не найдено"
            }

            showProcess.value = false

        }
    }


    override fun onCleared() {
        super.onCleared()
        searchJob.cancel()
    }
}



