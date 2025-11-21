package com.example.re0.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.re0.R
import com.example.re0.model.Achievement
import com.example.re0.model.Profile
import com.example.re0.repository.MypageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
open class ProfileViewModel @Inject constructor(
    private val repository: MypageRepository
) : ViewModel() {

    var uiState by mutableStateOf(Profile(
        imageUrl = R.drawable.rectangle1_2,
        name = "",
        email = "",
    )
    )
        private set

    var profile by mutableStateOf<Profile?>(null)
        private set
    var achievementsState by mutableStateOf<List<Achievement>>(emptyList())
        private set

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            var loaded = repository.getProfile()
            if (loaded == null) {
                repository.insertProfile(Profile(
                    imageUrl =  R.drawable.rectangle1_2,
                    name = "홍길동",
                    email = "hong@test.com",
                    )
                )
                loaded = repository.getProfile()
            }
            val achievements = repository.getAllAchievement()
            Log.d("ProfileVM", "Loaded profile = $loaded")
            Log.d("ProfileVM", "Loaded achievements = $achievements")
            loaded?.let { profileData ->
                uiState = uiState.copy(
                    imageUrl = profileData.imageUrl,
                    name = profileData.name,
                    email = profileData.email,
                )
            }
            achievementsState = repository.getAllAchievement()

        }
    }


    fun updateUserProfile(name: String, email: String) {
        val updated = uiState.copy(
            name = name,
            email = email
        )

        viewModelScope.launch {
            repository.updateProfile(updated)
            uiState = updated
        }
    }

    fun addAchievement(achievement: Achievement) {
        viewModelScope.launch {
            repository.insertAchievement(achievement)
            achievementsState = repository.getAllAchievement()  // ⭐ UI 반영 핵심!
        }
    }
}