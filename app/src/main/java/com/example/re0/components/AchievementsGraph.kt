package com.example.re0.components
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.re0.model.Achievement
import com.example.re0.ui.theme.Mint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun groupAchievementsByDay(achievements: List<Achievement>): Map<LocalDate, Int> {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    return achievements
        .map { LocalDate.parse(it.date, formatter) }
        .groupingBy { it }
        .eachCount()
        .toSortedMap()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AchievementsGraph(
    achievements: List<Achievement>
) {
    CardTemplate(
        topColor = Mint,
        bottomColor = Color.White,
        topContent = {
            Text(text = "통계 그래프", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold)
            Text("나만의 목표 등록 및 달성 기록", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        },
        bottomContent = {

            val daliyDate= groupAchievementsByDay(achievements)

            if(daliyDate.isEmpty()){
                Text("아직 기록이 없습니다")
                return@CardTemplate
            }

            val maxVValue=daliyDate.values.maxOrNull()?:1
            val points =daliyDate.values.toList()

            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(10.dp)
            ) {
                val width =size.width
                val height = size.height

                val stepX=width/(points.size-1).coerceAtLeast(1)

                val normalized= points.map { it.toFloat()/maxVValue }

                for(i in 1 until points.size) {
                    val x1 = (i - 1) * stepX
                    val y1 = height - normalized[i - 1] * height

                    val x2 = i * stepX
                    val y2 = height - normalized[i] * height

                    drawLine(
                        start = Offset(x1, y1),
                        end = Offset(x2, y2),
                        strokeWidth = 4f,
                        color = Color(0xFF4CAF50)
                    )

                    normalized.forEachIndexed { index, value ->
                        val x = index * stepX
                        val y = height - value * height

                        drawCircle(
                            color = Color(0xFF4CAF50),
                            radius = 5f,
                            center = Offset(x, y)
                        )

                    }

                }
            }
        }
    )
}
