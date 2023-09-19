package com.example.myapplication.apiService

import com.example.myapplication.models.Auth
import com.example.myapplication.models.User

import retrofit2.Response
import retrofit2.http.*


interface SimpleApi {
    @POST("auth/login")
    suspend fun loginUser(
        @Body auth: Auth
    ): Response<String>


}

