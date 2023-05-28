package com.example.myapplication.domain.utils

sealed class State{
    object Loading : State()
    object Success : State()
    data class Error (val error: String) : State()
}
