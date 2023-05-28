package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepository @Inject constructor(@ApplicationContext appContext: Context) {

     var db: PhotoDataBase
    init {
        db = Room.databaseBuilder(
            appContext,
            PhotoDataBase::class.java,
            "db")
            .fallbackToDestructiveMigration()
            .build()
    }
    suspend fun savePhoto(uri: String, date: String) {
        try {
            val result = db.photoDao().insert(Photo(uri, date))
            if (result == -1L) {
                // Вставка проигнорирована из-за дубликата, обработайте эту ситуацию
            } else {
                // Запись успешно вставлена
            }
        } catch (throwable: Throwable) {
            // Обработка других исключений
        }
    }
    fun getAllPhotos(): Flow<List<Photo>> {
        return db.photoDao().getAll()
    }
}