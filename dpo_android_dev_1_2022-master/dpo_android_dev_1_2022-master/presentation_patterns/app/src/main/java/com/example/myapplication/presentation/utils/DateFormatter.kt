package com.example.myapplication.presentation.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat

import java.util.logging.SimpleFormatter

class DateFormatter(
    private val date:String,
    private val inputPattern:String,
    private val outputPattern:String
) {
    @SuppressLint("SimpleDateFormat")
    fun format():String{
        val readingFormatter = SimpleDateFormat(inputPattern)
        val date = readingFormatter.parse(date)

        val simpleDateFormatter = SimpleDateFormat(outputPattern)
        return simpleDateFormatter.format(date)
    }
}