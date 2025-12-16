package com.example.re0.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.re0.model.DailyRecord
import com.example.re0.repository.GarbageRepository
import com.example.re0.repository.MypageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val repository: MypageRepository,
    private val garbageRepository: GarbageRepository // ★ 추가된 Repository
) : ViewModel() {

    // ==========================================
    // 1. DailyRecord 관련 상태 (목표/챌린지)
    // ==========================================

    // 화면용 리스트 (오늘 날짜의 기록만)
    private val _uiList = MutableStateFlow<List<DailyRecord>>(emptyList())
    val uiList: StateFlow<List<DailyRecord>> = _uiList

    // 달력용 전체 기록 (통계나 전체 조회용)
    private val _calendarRecords = MutableStateFlow<List<DailyRecord>>(emptyList())
    val calendarRecords: StateFlow<List<DailyRecord>> = _calendarRecords

    // ==========================================
    // 2. 쓰레기 요일 관련 상태 (DataStore)
    // ==========================================

    // 요일별 데이터를 한 번에 관리하는 데이터 클래스
    data class GarbageState(
        val mon: String = "재활용 수거일",
        val tue: String = "일반 쓰레기",
        val wed: String = "음식물 수거일",
        val thu: String = "대형 폐기물",
        val fri: String = "대형 폐기물",
        val sat: String = "대형 폐기물",
        val sun: String = "대형 폐기물"
    )

    private val _garbageState = MutableStateFlow(GarbageState())
    val garbageState: StateFlow<GarbageState> = _garbageState.asStateFlow()


    // ==========================================
    // 초기화 및 데이터 로드
    // ==========================================
    init {
        loadData()        // DailyRecord 로드
        loadGarbageData() // 쓰레기 요일 정보 로드
    }

    // [API 24 호환] 오늘 날짜 구하기 (yyyy-MM-dd)
    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

    // DailyRecord 데이터 로드
    fun loadData() {
        viewModelScope.launch {
            val today = getTodayDate()
            // 1. 오늘 날짜 기록만 가져옴 (내일 되면 리스트 비워짐)
            _uiList.value = repository.getRecordsByDate(today)
            // 2. 달력용 전체 기록
            _calendarRecords.value = repository.getAllRecords()
        }
    }

    // 쓰레기 요일 데이터 로드 (DataStore -> Flow combine)
    // 쓰레기 요일 데이터 로드 (DataStore -> Flow combine)
    private fun loadGarbageData() {
        viewModelScope.launch {
            combine(
                garbageRepository.getGarbageInfo("월", "재활용 수거일"),
                garbageRepository.getGarbageInfo("화", "일반 쓰레기"),
                garbageRepository.getGarbageInfo("수", "음식물 수거일"),
                garbageRepository.getGarbageInfo("목", "대형 폐기물"),
                garbageRepository.getGarbageInfo("금", "대형 폐기물"),
                garbageRepository.getGarbageInfo("토", "대형 폐기물"),
                garbageRepository.getGarbageInfo("일", "대형 폐기물")
            ) { args: Array<String> ->
                // ★ 수정 포인트: 5개가 넘어가면 '배열(Array)'로 들어옵니다.
                // 순서대로 꺼내서 사용해야 합니다.
                val mon = args[0]
                val tue = args[1]
                val wed = args[2]
                val thu = args[3]
                val fri = args[4]
                val sat = args[5]
                val sun = args[6]

                GarbageState(mon, tue, wed, thu, fri, sat, sun)
            }.collect { state ->
                _garbageState.value = state
            }
        }
    }

    // ==========================================
    // 기능 함수들 (Actions)
    // ==========================================

    // [DailyRecord] 추가 (오늘 날짜로 저장)
    fun addChallenge(title: String) {
        viewModelScope.launch {
            val newRecord = DailyRecord(
                title = title,
                isDone = false,
                date = getTodayDate(), // 오늘 날짜 박제
                iconUrl = 0
            )
            repository.addRecord(newRecord)
            loadData() // 목록 갱신
        }
    }

    // [DailyRecord] 체크 (성공 여부 변경)
    fun toggleCheck(record: DailyRecord, isChecked: Boolean) {
        viewModelScope.launch {
            val updated = record.copy(isDone = isChecked)
            repository.updateRecord(updated)
            loadData()
        }
    }

    // [DailyRecord] 수정 (제목 변경)
    fun updateChallenge(record: DailyRecord, newTitle: String) {
        viewModelScope.launch {
            val updated = record.copy(title = newTitle)
            repository.updateRecord(updated)
            loadData()
        }
    }

    // [DailyRecord] 삭제
    fun deleteChallenge(record: DailyRecord) {
        viewModelScope.launch {
            repository.deleteRecord(record)
            loadData()
        }
    }

    // [Garbage] 요일별 텍스트 업데이트 (DataStore 저장)
    fun updateGarbageDay(day: String, text: String) {
        viewModelScope.launch {
            garbageRepository.saveGarbageInfo(day, text)
            // collect가 자동으로 감지하므로 loadGarbageData()를 다시 호출할 필요 없음
        }
    }
}