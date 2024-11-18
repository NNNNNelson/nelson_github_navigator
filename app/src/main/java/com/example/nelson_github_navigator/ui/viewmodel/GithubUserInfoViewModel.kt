

package com.example.nelson_github_navigator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nelson_github_navigator.GithubApiService
import com.example.nelson_github_navigator.GithubUser
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class GithubUserInfoViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<GithubUser>()
    val userInfo: LiveData<GithubUser> = _userInfo

    private val apiService: GithubApiService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApiService::class.java)

    fun fetchUserInfo(username: String) {
        viewModelScope.launch {
            try {
                val user = apiService.getUserInfo(username)
                _userInfo.value = user
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

interface GithubApiService {
    @GET("users/{username}")
    suspend fun getUserInfo(@Path("username") username: String): GithubUser
}