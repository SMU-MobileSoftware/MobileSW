package com.example.re0.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        imageUrl = android.R.drawable.sym_def_app_icon,
        name = "",
        email = "",
        achievements = emptyList()
    )
    )
        private set

    var profile by mutableStateOf<Profile?>(null)
        private set

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            profile = repository.getProfile()
        }
    }
    fun updateUserProfile(
        name: String,
        email: String
    ) {
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

            uiState = uiState.copy(
                achievements = uiState.achievements + achievement
            )
        }
    }
}