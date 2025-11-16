package com.example.re0.repository

import com.example.re0.data.AchievementDao
import com.example.re0.data.ProfileDao
import com.example.re0.model.Achievement
import com.example.re0.model.Profile
import jakarta.inject.Inject

open class MypageRepository@Inject constructor(
    private val achievementDao: AchievementDao,
    private val profileDao: ProfileDao,
    ) {
    //val allAchievements: List<Achievement> = achievementDao.getAllAchievement()

    suspend fun insertAchievement(achievement: Achievement) = achievementDao.insertAchievement(achievement)
    fun getAllAchievement(): List<Achievement> =achievementDao.getAllAchievement()
    suspend fun getProfile(): Profile? = profileDao.getProfile()
    suspend fun updateProfile(profile: Profile) = profileDao.updateProfile(profile)
    suspend fun insertProfile(profile: Profile) = profileDao.insertProfile(profile)
    suspend fun deleteProfile(profile: Profile) = profileDao.deleteProfile(profile)}
