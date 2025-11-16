package com.example.re0.model

import androidx.room.Entity
@Entity(tableName = "achievements")
class Achievement(
    val title: String,
    val description: String?=null,
    val date: String?=null,
    val iconUrl: Int
)