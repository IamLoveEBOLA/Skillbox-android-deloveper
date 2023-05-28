package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.CharactersRepository
import com.example.myapplication.data.data_classes.episode.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
):ViewModel() {
    private val _episodes = MutableStateFlow<MutableList<Episode>>(mutableListOf())
    val episodes = _episodes.asStateFlow()
    fun load(array: ArrayList<String>){
        viewModelScope.launch {
            var list = mutableListOf<String>()
            array.forEach{ list.add(it.replace("https://rickandmortyapi.com/api/episode/",""))}
            val str = list.joinToString(separator = ",")
            _episodes.value = charactersRepository.getEpisodes(str)?.toMutableList()?: mutableListOf<Episode>()
        }
    }
}