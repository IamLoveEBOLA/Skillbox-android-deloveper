package com.example.myapplication

import android.content.Context

class Repository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private var localText: String? = null

    fun getDataFromSharedPreference(): String? {
        return sharedPreferences.getString("text", null)
    }

    fun getDataFromLocalVariable(): String? {
        return localText
    }

    fun saveText(text: String) {
        with(sharedPreferences.edit()) {
            putString("text", text)
            apply()
        }
        localText = text
    }

    fun clearText() {
        with(sharedPreferences.edit()) {
            remove("text")
            apply()
        }
        localText = null
    }

    fun getText(): String {
        return localText ?: getDataFromSharedPreference() ?: ""
    }
}