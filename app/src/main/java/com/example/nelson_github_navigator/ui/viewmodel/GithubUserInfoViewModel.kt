package com.example.nelson_github_navigator.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nelson_github_navigator.GithubApiService
import com.example.nelson_github_navigator.GithubRepo
import com.example.nelson_github_navigator.GithubUser
import com.example.nelson_github_navigator.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

class GithubUserInfoViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<GithubUser>()
    val userInfo: LiveData<GithubUser> = _userInfo

    private val _userRepos = MutableLiveData<List<GithubRepo>>()
    val userRepos: LiveData<List<GithubRepo>> = _userRepos

    private val _repoDetail = MutableLiveData<GithubRepo>()
    val repoDetail: LiveData<GithubRepo> = _repoDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchUserInfo(username: String) {
        viewModelScope.launch {
            try {
                val user = RetrofitInstance.api.getUserInfo(username)
                _userInfo.value = user
            } catch (e: IOException) {
                _error.value = "Network error: ${e.message}"
            } catch (e: HttpException) {
                _error.value = "HTTP error: ${e.message}"
            } catch (e: Exception) {
                _error.value = "Unexpected error: ${e.message}"
            }
        }
    }

    fun fetchUserRepos(username: String) {
        viewModelScope.launch {
            try {
                val repos = RetrofitInstance.api.getUserRepos(username)
                _userRepos.value = repos
            } catch (e: IOException) {
                _error.value = "Network error: ${e.message}"
            } catch (e: HttpException) {
                _error.value = "HTTP error: ${e.message}"
            } catch (e: Exception) {
                _error.value = "Unexpected error: ${e.message}"
            }
        }
    }

    fun fetchRepoDetail(username: String, repoName: String) {
        viewModelScope.launch {
            try {
                val repo = RetrofitInstance.api.getUserRepos(username).find { it.name == repoName }
                _repoDetail.value = repo
            } catch (e: IOException) {
                _error.value = "Network error: ${e.message}"
            } catch (e: HttpException) {
                _error.value = "HTTP error: ${e.message}"
            } catch (e: Exception) {
                _error.value = "Unexpected error: ${e.message}"
            }
        }
    }
}
