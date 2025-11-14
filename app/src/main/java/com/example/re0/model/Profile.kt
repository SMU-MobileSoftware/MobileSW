package com.example.re0.model

import androidx.room.Entity

@Entity(tableName = "Profile")
data class Profile(
    val imageUrl: Int,
    val name: String,
    val email: String,
    val achievements: List<Achievement>
)