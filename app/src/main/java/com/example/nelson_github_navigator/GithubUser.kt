package com.example.nelson_github_navigator

data class GithubUser(
    val login: String,
    val name: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val repos_url: String
)