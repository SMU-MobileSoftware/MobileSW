package com.example.re0.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.re0.R
import com.example.re0.model.Achievement
import com.example.re0.ui.theme.Mint

@Composable
fun BadgeItem(achievement: Achievement) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(50.dp)
    ) {
        Image(
            painter = painterResource(achievement.iconUrl),
            contentDescription = "배지 이미지",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Mint),
            contentScale = ContentScale.Crop
        )
        Text(text =achievement.title, fontSize = 10.sp)
    }
}

@Composable
fun MyBadge( achievements: List<Achievement>) {
    CardTemplate(
        topColor = Mint,
        bottomColor = Mint,
        topContent = {
            Text(
                text = "배지",
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text("획득 목록 : ${achievements.size}", fontSize = 18.sp)
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        },
        bottomContent = {
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clip(shape = RoundedCornerShape(15.dp)),
                ) {
                FlowRow(
                    maxItemsInEachRow = 6,
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.White)
                        .padding(10.dp)

                    ) {
                    achievements.forEach { achievement ->
                        Box(modifier = Modifier.padding(horizontal = 3.dp)) {
                            BadgeItem(achievement)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyPBadgePreview() {
    MyBadge(
        sampleAchievements
    )
}
val sampleAchievements = listOf(
    Achievement(
        title = "First Step",
        iconUrl = R.drawable.rectangle1_2,
    ),
    Achievement(
        title = "First Step2",
        iconUrl = R.drawable.rectangle1_1,
    ),
    Achievement(
        title = "First Step3",
        iconUrl = R.drawable.rectangle1_2,
    ),
    Achievement(
        title = "First Step4",
        iconUrl = R.drawable.rectangle1_1,
    ),
    Achievement(
        title = "First Step",
        iconUrl = R.drawable.rectangle1_2,
    ),
    Achievement(
        title = "First Step2",
        iconUrl = R.drawable.rectangle1_1,
    ),
    Achievement(
        title = "First Step3",
        iconUrl = R.drawable.rectangle1_2,
    ),
    Achievement(
        title = "First Step4",
        iconUrl = R.drawable.rectangle1_1,
    )
)