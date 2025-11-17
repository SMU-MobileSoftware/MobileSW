package com.example.re0.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.re0.model.Place
import com.example.re0.model.PlaceType

@Composable
fun PlaceItem(place: Place) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)

    ) {
        Row (modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column{
                Text(place.name, fontSize = 15.sp)
                Text(place.address, fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.width(40.dp))
            Text("거리: 5.5km", fontSize = 15.sp)
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PlaceListPreview() {
    PlaceItem(Place(
        name = "제로웨이스트 상점 1",
        address = "서울시 강남구 어딘가 123",
        description = "리필스테이션 및 친환경 제품 판매",
        lat = 37.4979,
        lng = 127.0276,
        type = PlaceType.ZERO_WASTE
    )
    )
}
