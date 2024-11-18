package com.example.nelson_github_navigator.ui.page

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nelson_github_navigator.ui.viewmodel.GithubUserInfoViewModel

@Composable
fun GithubUserInfoScreen(viewModel: GithubUserInfoViewModel) {
    var username by remember { mutableStateOf("") }
    val userInfo by viewModel.userInfo.observeAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Github User Info", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Github Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.fetchUserInfo(username) }) {
            Text("Fetch User Info")
        }
        Spacer(modifier = Modifier.height(16.dp))
        userInfo?.let { user ->
            Text("Username: ${user.login}")
            Text("Name: ${user.name}")
            Text("Public Repos: ${user.public_repos}")
            Text("Followers: ${user.followers}")
            Text("Following: ${user.following}")
            TextButton(onClick = {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.repos_url))
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/orgs/huggingface/repositories"))
                context.startActivity(intent)
            }) {
                Text("Public Repos")
            }
        }
    }
}
