package com.example.re0.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.re0.model.Achievement
import com.example.re0.model.Profile
import com.example.re0.ui.theme.Mint

@Composable
fun MyProfile(
    uiState: Profile,
    achievements: List<Achievement>
) {
    CardTemplate(
        topColor = Mint,
        bottomColor = Mint,
        topContent = {
            Text(text = "내 정보", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold)
            Text("나만의 목표 등록 및 달성 기록", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        },
        bottomContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
                    .clip(RoundedCornerShape(12.dp))

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.background(Color.White)
                        .fillMaxWidth()
                        .padding(10.dp)


                ) {
                    AsyncImage(
                        model = uiState.imageUrl,
                        contentDescription = "프로필 이미지",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Mint),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text("닉네임: ${uiState.name}", fontSize = 15.sp)
                        Text("이메일: ${uiState.email}", fontSize = 15.sp)
                        Text("달성기록:  ${achievements.size}", fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column{
                        FloatingActionButton(onClick = {},
                            containerColor = Mint,
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Edit,
                                contentDescription = "edit",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        FloatingActionButton(onClick = {},
                            containerColor = Mint,
                            modifier = Modifier.width(60.dp)
                                .height(30.dp)
                        ) {
                            Text("로그아웃", fontSize = 10.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    )
}


/*
@Preview(showBackground = true)
@Composable
fun MyProfilePreview() {
    MyProfile(
        uiState = Profile(
            imageUrl = R.drawable.rectangle1_1,
            name = "홍길동",
            email = "hong@test.com",
            achievements = listOf(
                Achievement(
                    iconUrl = R.drawable.rectangle1_1,
                    date = "2023.10.25",
                    description = "재활용 10회 이상",
                    title = "배지1",

                    ),
                Achievement(
                    iconUrl = R.drawable.rectangle1_1,
                    date = "2023.10.25",
                    description = "재활용 10회 이상",
                    title = "배지1",

                    )
            )
        )
    )
}

 */