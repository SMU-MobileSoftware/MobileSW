package com.example.re0.data

import androidx.room.Insert
import androidx.room.Query
import com.example.re0.model.Achievement

interface AchievementDao {
    @Query("SELECT * FROM achievements")
    fun getAllAchievement(): List<Achievement>

    @Insert
    suspend fun insertAchievement(achievement: Achievement)
}