package com.example.re0.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.re0.Converters
import com.example.re0.model.Achievement
import com.example.re0.model.Profile

@Database(
    entities = [Achievement::class, Profile::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun achievementDao(): AchievementDao
    abstract fun profileDao(): ProfileDao
}