package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.apiService.RetrofitInstance
import com.example.myapplication.models.Auth
import com.example.myapplication.models.User
import retrofit2.Response
import retrofit2.http.GET

class Repository {
    suspend fun loginUser(auth: Auth):  Response<String> {
        return RetrofitInstance.api.loginUser(auth)
    }
}