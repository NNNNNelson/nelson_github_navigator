package com.example.nelson_github_navigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.nelson_github_navigator.ui.page.GithubUserInfoScreen
import com.example.nelson_github_navigator.ui.viewmodel.GithubUserInfoViewModel
import com.example.nelson_github_navigator.ui.theme.Nelson_github_navigatorTheme

class MainActivity : ComponentActivity() {
    private val viewModel: GithubUserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nelson_github_navigatorTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    GithubUserInfoScreen(viewModel)
                }
            }
        }
    }
}