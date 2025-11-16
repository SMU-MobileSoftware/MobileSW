package com.example.re0.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.re0.R
import com.example.re0.components.AchievementsGraph
import com.example.re0.components.MyBadge
import com.example.re0.components.MyProfile
import com.example.re0.model.Achievement
import com.example.re0.model.Profile
import com.example.re0.viewModel.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MypageScreen(navController: NavController,
           viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold (
        topBar = { TopBar() },
        bottomBar ={ BottomBar()}
    ) {innerPadding ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,) {
            val uiState = viewModel.uiState
            MyProfile(uiState = uiState)
            MyBadge(achievements = uiState.achievements)
            AchievementsGraph(achievements = uiState.achievements)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MyProfilePreview() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

        val fakeAchievements = listOf(
            Achievement(
                title = "First Step",
                iconUrl = R.drawable.rectangle1_2,
                date = "2025.10.10"
            ),
            Achievement(
                title = "First Step2",
                iconUrl = R.drawable.rectangle1_1,
                date = "2025.10.10"
            ),
            Achievement(
                title = "First Step3",
                iconUrl = R.drawable.rectangle1_2,
                date = "2025.10.08"
            ),
            Achievement(
                title = "First Step4",
                iconUrl = R.drawable.rectangle1_1,
                date = "2025.10.06"
            ),
            Achievement(
                title = "First Step",
                iconUrl = R.drawable.rectangle1_2,
                date = "2025.10.10"
            ),
            Achievement(
                title = "First Step2",
                iconUrl = R.drawable.rectangle1_1,
                date = "2025.10.07"
            ),
            Achievement(
                title = "First Step3",
                iconUrl = R.drawable.rectangle1_2,
                date = "2025.10.07"
            ),
            Achievement(
                title = "First Step4",
                iconUrl = R.drawable.rectangle1_1,
                date = "2025.10.07"
            ),
            Achievement(
                title = "First Step4",
                iconUrl = R.drawable.rectangle1_1,
                date = "2025.10.07"
            ),
            Achievement(
                title = "First Step4",
                iconUrl = R.drawable.rectangle1_1,
                date = "2025.10.04"
            )
        )
        val fakeProfile = Profile(
            imageUrl = R.drawable.rectangle1_1,
            name = "홍길동",
            email = "test@example.com",
            achievements = fakeAchievements
        )
        Column(modifier = Modifier.fillMaxSize()) {
            MyProfile(uiState = fakeProfile)
            MyBadge(achievements = fakeAchievements)
            AchievementsGraph(achievements = fakeAchievements)
        }
    }
}
}