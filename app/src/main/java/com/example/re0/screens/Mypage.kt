package com.example.re0.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.re0.components.MyProfile
import com.example.re0.viewModel.ProfileViewModel

@Composable
fun Mypage(navController: NavController,
           viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold (
        topBar = { TopBar() },
        bottomBar ={ BottomBar()}
    ) {innerPadding ->
        val uiState = viewModel.uiState
        MyProfile(uiState = uiState)

        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MypagePreview() {
    val navController = rememberNavController() // preview 전용
    Mypage(navController = navController)
}
