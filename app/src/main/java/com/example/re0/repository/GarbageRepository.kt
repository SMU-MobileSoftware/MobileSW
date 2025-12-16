package com.example.re0.repository


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// DataStore 인스턴스 생성 (이름: garbage_prefs)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "garbage_prefs")

@Singleton
class GarbageRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // 저장하기
    suspend fun saveGarbageInfo(day: String, info: String) {
        // "월" -> "mon_garbage" 키로 변환해서 저장
        val key = stringPreferencesKey(convertDayToKey(day))
        context.dataStore.edit { preferences ->
            preferences[key] = info
        }
    }

    // 불러오기
    fun getGarbageInfo(day: String, default: String): Flow<String> {
        val key = stringPreferencesKey(convertDayToKey(day))
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: default
        }
    }

    // 한글 요일을 영문 키값으로 변환하는 헬퍼 함수
    private fun convertDayToKey(day: String): String {
        return when(day) {
            "월" -> "mon_garbage"
            "화" -> "tue_garbage"
            "수" -> "wed_garbage"
            "목" -> "thu_garbage"
            "금" -> "fri_garbage"
            "토" -> "sat_garbage"
            "일" -> "sun_garbage"
            else -> "unknown"
        }
    }
}