package com.example.re0.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.re0.model.Profile

interface  ProfileDao {
    @Query("SELECT * FROM profile")
    suspend fun getProfile(): Profile?

    @Update
    suspend fun updateProfile(profile: Profile)

    @Insert
    suspend fun insertProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)

}