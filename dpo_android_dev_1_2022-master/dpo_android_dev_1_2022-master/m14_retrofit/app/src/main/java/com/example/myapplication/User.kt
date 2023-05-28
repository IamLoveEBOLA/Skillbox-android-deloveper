package com.example.myapplication


data class User(
    val name: Name,
    val email: String,
    val phone: String,
    val picture: Picture
)

data class Name(
    val first: String,
    val last: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)