package com.example.re0.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.re0.R
import com.example.re0.components.CardTemplate
import com.example.re0.ui.theme.Mint
import com.example.re0.ui.theme.SkyBlue
import java.time.format.TextStyle


@Composable
fun InformationScreen(navController: NavController) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        InformationContent(innerPadding)
    }
}

@Composable
fun ImageRow(
    icons: List<Int>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEach { icon ->
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(74.dp)
            )
        }
    }
}



@Composable
fun InformationContent(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .width(412.dp)
            .height(917.dp)
            .background(Color.White)
    ) {
        Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
    ) {

        // 검색 박스
        Row(
            modifier = Modifier
                .padding(top = 25.dp)
                .fillMaxWidth()
                .height(53.dp)
                .background(Color(0xFFE3F3FB), RoundedCornerShape(48.dp))
                .padding(horizontal = 15.dp, vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("분리수거 검색하기", fontSize = 16.sp, color = Color(0xFF656565))
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }


        CardTemplate(
            modifier = Modifier.fillMaxWidth(),
            topColor = Color.White,
            bottomColor = SkyBlue,
            topContent = {
                Text("주요 분리수거 쓰레기", fontSize = 23.sp, fontWeight = FontWeight.Bold)
            },
            bottomContent = {

                Column(
                    modifier = Modifier
                        .width(380.dp)
                        .height(300.dp)
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(23.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {

                        // Frame 68
                        ImageRow(
                            icons = listOf(
                                R.drawable.paper,
                                R.drawable.glass,
                                R.drawable.pet,
                                R.drawable.plasticbag
                            )
                        )

                        // Frame 69
                        ImageRow(
                            icons = listOf(
                                R.drawable.can,
                                R.drawable.st,
                                R.drawable.cloth,
                                R.drawable.ch
                            )
                        )

                        // Frame 70
                        ImageRow(
                            icons = listOf(
                                R.drawable.light,
                                R.drawable.thing,
                                R.drawable.food,
                                R.drawable.big
                            )
                        )
                    }


                }
            }
        )
            CardTemplate(
                topColor = SkyBlue,
                bottomColor = Color.White,
                topContent = {
                    Text(
                        text = "제로웨이스트 AI 문답",
                        color = Color.Black,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                bottomContent = {
                    Text(
                        text = "챗봇에게 물어보세요",
                        fontSize = 16.sp,
                        color = Color(0xFF656565)

                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // 봇 프로필 아이콘
                            Image(
                                painter = painterResource(id = R.drawable.bot),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(
                                        color = Color(0xFFD9D9D9),
                                        shape = RoundedCornerShape(46.dp)
                                    )
                            )

                            // 검색 박스
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp)
                                    .background(
                                        color = Color(0xFFE3F3FB),
                                        shape = RoundedCornerShape(13.dp)
                                    )
                                    .padding(horizontal = 18.dp, vertical = 10.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "대형 폐기물 버리는 방법",
                                    fontSize = 16.sp,
                                    color = Color(0xFF656565)
                                )
                            }

                        }

                        // 하단 질문 박스
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.dp)
                                    .background(color = Color(0xFFE1E7E9), shape = RoundedCornerShape(size = 13.125.dp))
                                    .padding(start = 18.dp, top = 10.dp,  bottom = 10.dp)
                            ) {
                                Text(
                                    text = "Q 음식물이 묻은 배달 용기 버리는 방법 ",
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontSize = 16.sp,
                                        lineHeight = 22.4.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF656565),
                                    )
                                )
                            }

                        }

                    }
                }
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun InformationPreview() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },     // 실제 TopBar
        bottomBar = { BottomBar() } // 실제 BottomBar
    ) { innerPadding ->
        InformationContent(innerPadding)
    }
}
