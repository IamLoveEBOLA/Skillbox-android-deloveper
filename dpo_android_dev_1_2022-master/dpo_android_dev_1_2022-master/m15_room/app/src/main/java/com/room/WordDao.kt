package com.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY count DESC LIMIT 5")
    fun getTopWords(): Flow<List<Word>>

    @Query("SELECT * FROM word_table WHERE word = :word")
    fun findWord(word: String): Word?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()
}