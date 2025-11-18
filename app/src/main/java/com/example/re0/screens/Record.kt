package com.example.re0.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.re0.R
import com.example.re0.components.CardTemplate
import com.example.re0.ui.theme.Mint
import com.example.re0.ui.theme.NeonBlue
import com.example.re0.ui.theme.SkyBlue
import java.time.format.TextStyle


@Composable
fun RecordScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        RecordContent(innerPadding)
    }
}

@Composable
fun GarbageDayItem(
    day: String,          // "월", "화", "수" ...
    description: String   // "재활용 수거일"
) {
    Column(
        modifier = Modifier
            .width(43.dp)
            .background(
                color = Color(0x2601E7C5),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 요일
        Text(
            text = day,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 17.44.sp,
                lineHeight = 24.42.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )

        // 설명 박스
        Column(
            modifier = Modifier
                .width(38.88.dp)
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = description,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 15.4.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                )
            )
        }
    }
}
@Composable
fun GoalItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(start = 2.dp, top = 6.dp, end = 7.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 체크박스
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.scale(1.4f)
        )

        // 회색 영역
        Row(
            modifier = Modifier
                .weight(1f)   // ← width(307.dp)를 없애고 자동으로 꽉 차게!
                .height(42.dp)
                .background(
                    color = Color(0xFFE1E7E9),
                    shape = RoundedCornerShape(13.125.dp)
                )
                .padding(start = 18.dp, top = 10.dp, end = 14.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF656565),
                )
            )
        }
    }
}

@Composable
fun RecordContent(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(29.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        Column (
            modifier = Modifier .border(width = 1.dp, color =Mint, shape = RoundedCornerShape(size = 10.dp))
            .fillMaxWidth()
            .padding(7.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = "Progress Bar",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 33.6.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(42.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .width(42.dp)
                        .height(42.dp)
                        .background(color = NeonBlue, shape = RoundedCornerShape(size = 10.dp))
                        .padding(start = 3.dp, top = 6.dp, end = 3.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "67%",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),
                        )
                    )

                }
                Column(
                    modifier = Modifier
                        .border(width = 2.dp, color = Color(0xFF79ECF4), shape = RoundedCornerShape(size = 13.125.dp))
                    .width(285.dp)
                    .height(42.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 13.125.dp))
                    .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        modifier = Modifier
                            .width(210.dp)
                            .height(34.dp)
                            .background(color = NeonBlue, shape = RoundedCornerShape(size = 10.dp))
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.Top,
                ) {
                }
                }
            }
        }

        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Mint, shape = RoundedCornerShape(size = 10.dp)),
            verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val strokeWidth = 2.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color = Color(0xFF79ECF4),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(top = 5.dp, bottom = 5.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "쓰레기 버리는 날",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 33.6.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
                Text(
                    text = "우리 동네 쓰레기 수거일을 등록해보세요",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF656565),
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 7.dp, end = 12.dp, bottom = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                GarbageDayItem("월", "재활용 수거일")
                GarbageDayItem("화", "일반 쓰레기")
                GarbageDayItem("수", "음식물 수거일")
                GarbageDayItem("목", "대형 폐기물")
                GarbageDayItem("금", "대형 폐기물")
                GarbageDayItem("토", "대형 폐기물")
                GarbageDayItem("일", "대형 폐기물")

            }
        }
        Column(
            modifier = Modifier
                .border(width = 1.dp, color = Mint, shape = RoundedCornerShape(size = 10.dp)),
            verticalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val strokeWidth = 2.dp.toPx()
                        val y = size.height - strokeWidth / 2
                        drawLine(
                            color = Color(0xFF79ECF4),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(top = 5.dp, bottom = 5.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "나만의 목표",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 33.6.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
                Text(
                    text = "나만의 목표 등록 및 달성 기록",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF656565),
                    )
                )
            }
            Column(
                modifier = Modifier

                    .background(color = Color(0xEBFFFFFF), shape = RoundedCornerShape(size = 15.dp))
                    .padding(start = 10.dp, top = 14.dp, end = 10.dp, bottom = 14.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {

                GoalItem(
                    text = "배달음식 안시켜먹기",
                    checked = false,
                    onCheckedChange = { /* TODO */ }
                )
                GoalItem(
                    text = "배달음식 안시켜먹기",
                    checked = false,
                    onCheckedChange = { /* TODO */ }
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecordPreview() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },     // 실제 TopBar
        bottomBar = { BottomBar() } // 실제 BottomBar
    ) { innerPadding ->
        RecordContent(innerPadding)
    }
}
