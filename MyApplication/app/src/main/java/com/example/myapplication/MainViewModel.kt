package com.example.myapplication


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Auth
import com.example.myapplication.models.User
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val userResponse: MutableLiveData<String> = MutableLiveData()


    fun loginUser(auth: Auth){
        viewModelScope.launch {
            val response: Response<String> = repository.loginUser(auth)
            userResponse.value = response.toString()
        }
    }


}