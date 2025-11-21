package com.example.re0.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.re0.R
import com.example.re0.data.AchievementDao
import com.example.re0.data.AppDatabase
import com.example.re0.data.ProfileDao
import com.example.re0.model.Achievement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        lateinit var dbInstance: AppDatabase
        val instance = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {

                        val dao = dbInstance.achievementDao()
                        Log.d("DB", "DAO received: $dao")

                            val achievements = listOf(
                                Achievement(
                                    title = "재활용마스터",
                                    iconUrl = R.drawable.rectangle1_2,
                                    date = "2025.10.10",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "분리수거천재",
                                    iconUrl = R.drawable.rectangle1_1,
                                    date = "2025.10.10",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "텀블러킹",
                                    iconUrl = R.drawable.rectangle1_2,
                                    date = "2025.10.08",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "분리수거천재",
                                    iconUrl = R.drawable.rectangle1_1,
                                    date = "2025.10.06",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "텀블러킹",
                                    iconUrl = R.drawable.rectangle1_2,
                                    date = "2025.10.10",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "재활용마스터",
                                    iconUrl = R.drawable.rectangle1_1,
                                    date = "2025.10.07",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "재활용마스터",
                                    iconUrl = R.drawable.rectangle1_2,
                                    date = "2025.10.07",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "분리수거천재",
                                    iconUrl = R.drawable.rectangle1_1,
                                    date = "2025.10.07",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "분리수거천재",
                                    iconUrl = R.drawable.rectangle1_1,
                                    date = "2025.10.07",
                                    isDone = false
                                ),
                                Achievement(
                                    title = "분리수거천재",
                                    iconUrl = R.drawable.rectangle1_1,
                                    date = "2025.10.04",
                                    isDone = false
                                )
                            )
                            Log.d("DB", "Default achievements count = ${achievements.size}")
                            achievements.forEach { dao.insertAchievement(it) }
                            Log.d("DB", "Default achievements inserted successfully")
                        }

                    }
                })
            .build()

        dbInstance = instance

        Log.d("DB", "Database instance created")

        return instance
    }
    @Provides
    fun provideAchievementDao(db: AppDatabase): AchievementDao = db.achievementDao()

    @Provides
    fun provideProfileDao(db: AppDatabase): ProfileDao = db.profileDao()
}

