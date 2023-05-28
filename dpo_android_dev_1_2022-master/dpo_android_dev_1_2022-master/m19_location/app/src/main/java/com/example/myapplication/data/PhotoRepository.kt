package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepository @Inject constructor(@ApplicationContext appContext: Context) {

    var db: PhotoDataBase
    init {
        db= Room.databaseBuilder(
            appContext,
            PhotoDataBase::class.java,
            "db").build()

    }
    suspend fun savePhoto(uri:String,date:String){
        try {
            db.photoDao().insert(
                Photo(uri, date)
            )
        }
        catch (throwable:Throwable){}
    }
    fun getAllPhotos():Flow<List<Photo>>{
        return db.photoDao().getAll()
    }
}