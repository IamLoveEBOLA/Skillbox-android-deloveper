package myapplication.Main.presentation

import androidx.lifecycle.ViewModel
import myapplication.Main.domain.GetUsefulActivityUseCase
import myapplication.Main.entity.UsefulActivity

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val TAG = "MainViewModel"

class MainViewModel @Inject constructor(val usefulActivitiesRepository: GetUsefulActivityUseCase) :
    ViewModel() {


    suspend fun reloadUsefulActivity(): StateFlow<UsefulActivity> {
        return MutableStateFlow(usefulActivitiesRepository.executeFun()) }
}


