package com.example.re0.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Achievement")
data class Achievement(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val date: String? = null,
    val iconUrl: Int,
    val isDone : Boolean
)