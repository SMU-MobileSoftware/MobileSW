package com.example.re0.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.re0.components.PlaceItem
import com.example.re0.model.PlaceType
import com.example.re0.ui.theme.Mint
import com.example.re0.viewModel.PlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(viewModel: PlacesViewModel = hiltViewModel()){
    val places by viewModel.places.collectAsState()
    val filteredPlaces by viewModel.filteredPlaces.collectAsState()

    val placeList = if (filteredPlaces.isNotEmpty()) {
        filteredPlaces
    } else {
        places
    }

    // 어떤 리스트를 보여줄지 선택
    val listToShow = if (filteredPlaces.isNotEmpty()) filteredPlaces else placeList
    // 서울 좌표
    val seoul = LatLng(37.5665, 126.9780)
// 카메라 초기 위치
    val selectedFilter by viewModel.selectedFilter.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoul, 12f)
    }
    Scaffold (
        topBar = { TopBar() },
        bottomBar ={ BottomBar()}
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
// 지도 컴포넌트
            Column(
                modifier = Modifier.height(400.dp)
                    .fillMaxWidth()
                    .background(Mint)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    placeList.forEach { item ->
                        Marker(
                            state = MarkerState(
                                position = LatLng(item.lat, item.lng)
                            ),
                            title = item.name
                        )
                    }
                }
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Row {
                    FilterButton(
                        text = "제로웨이스트",
                        selected = selectedFilter == PlaceType.ZERO_WASTE
                    ) { viewModel.applyFilter(PlaceType.ZERO_WASTE) }

                    FilterButton(
                        text = "텀블러 할인",
                        selected = selectedFilter == PlaceType.TUMBLER_DISCOUNT
                    ) { viewModel.applyFilter(PlaceType.TUMBLER_DISCOUNT) }

                    FilterButton(
                        text = "분리수거장",
                        selected = selectedFilter == PlaceType.RECYCLE_BIN
                    ) { viewModel.applyFilter(PlaceType.RECYCLE_BIN) }
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(listToShow) { place ->
                        PlaceItem(place)
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen()
}
@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) Color.White else Mint
    val textColor = if (selected) Mint else Color.White

    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}