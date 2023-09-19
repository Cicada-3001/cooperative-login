package com.example.myapplication.models


import java.io.Serializable

data class User(
    val fullname: String,
    val phone: String,
    val email: String,
    val password: String,
)
