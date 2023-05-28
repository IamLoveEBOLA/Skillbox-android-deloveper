package com.room

import kotlinx.coroutines.flow.Flow

class WordRepository(private val _wordDao: WordDao) {

    val topWords: Flow<List<Word>> = _wordDao.getTopWords()

    suspend fun insertWord(word: Word) {
        _wordDao.insertWord(word)
    }

    suspend fun findWord(word: String): Word? {
        return _wordDao.findWord(word)
    }

    suspend fun updateWord(word: Word) {
        _wordDao.updateWord(word)
    }

    suspend fun deleteAllWords() {
        _wordDao.deleteAllWords()
    }
}