package com.room

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    val topWords: LiveData<List<Word>>

    init {
        val wordDao : WordDao = (application as App).db.wordDao()
        repository = WordRepository(wordDao)
        topWords = repository.topWords.asLiveData()
    }



    fun insertWord(word: String) {
        val invalidChars = Regex("[\\s\\d.,]")
        if (word.isBlank() || invalidChars.containsMatchIn(word)) {
            Toast.makeText(getApplication(), R.string.error, Toast.LENGTH_SHORT).show()
            return
        }
        val newWord = Word(word.lowercase(Locale.ROOT) , 1)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.findWord(word)?.let {
                    it.count++
                    repository.updateWord(it)
                } ?: repository.insertWord(newWord)
            }
        }
    }

    fun deleteAllWords() {
        viewModelScope.launch {
            repository.deleteAllWords()
        }
    }

  /*  private fun isValidWord(word: String): Boolean {
        val regex = Regex("^[A-Za-z\\-]+$")
        return word.isNotBlank() && regex.matches(word)
                && !word.contains(" ") && !word.contains(",") && !word.contains(".")
                && !word.contains(Regex("\\d"))
    }*/
}






