package com.example.re0.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: Int,
    val name: String,
    val email: String,
    //val achievements: List<Achievement>
)