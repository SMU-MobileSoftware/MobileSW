package com.example.re0.viewModel

import androidx.lifecycle.ViewModel
import com.example.re0.model.Place
import com.example.re0.model.PlaceType
import com.example.re0.repository.PlacesRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val repository: PlacesRepository,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places

    init {
        loadDummyPlaces()
        //loadPlaces()
    }
    private val _filteredPlaces = MutableStateFlow<List<Place>>(emptyList())
    val filteredPlaces: StateFlow<List<Place>> = _filteredPlaces
    private val _selectedFilter = MutableStateFlow<PlaceType?>(null)
    val selectedFilter: StateFlow<PlaceType?> = _selectedFilter
    fun applyFilter(type: PlaceType) {
        _selectedFilter.value = type
        val list = _places.value.filter { it.type == type }
        _filteredPlaces.value = list
    }
    private fun loadPlaces() {
        db.collection("places")
            .get()
            .addOnSuccessListener { result ->
                val list = result.documents.mapNotNull { doc ->
                    doc.toObject(Place::class.java)
                }
                _places.value = list
            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
    private fun loadDummyPlaces() {
        _places.value = listOf(
            Place(
                name = "서울 제로웨이스트샵",
                address = "서울시 마포구",
                description = "친환경 용품 판매",
                lat = 37.5665,
                lng = 126.9780,
                type = PlaceType.ZERO_WASTE
            ),
            Place(
                name = "스타벅스 텀블러 할인",
                address = "서울시 종로구",
                description = "텀블러 지참 시 할인",
                lat = 37.56,
                lng = 126.99,
                type = PlaceType.TUMBLER_DISCOUNT
            ),
            Place(
                name = "홍대 분리수거장",
                address = "서울시 홍대입구",
                description = "일반/플라스틱/캔 구분 가능",
                lat = 37.55,
                lng = 126.92,
                type = PlaceType.RECYCLE_BIN
            )
            ,
            Place(
                name = "홍대 분리수거장",
                address = "서울시 홍대입구",
                description = "일반/플라스틱/캔 구분 가능",
                lat = 37.55,
                lng = 126.92,
                type = PlaceType.RECYCLE_BIN
            )
        )
    }
}