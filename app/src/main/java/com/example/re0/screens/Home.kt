package com.example.re0.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.re0.ui.theme.Mint

@Composable
fun HomeScreen( navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,

        ){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(150.dp)
            .border(
                width = 2.dp,
                color = Mint,
                shape = RoundedCornerShape(12.dp)  // ← 둥근 정도 조절
            )
        )
        {
            Column (modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Mint))
            {
            }
            Column (modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(1.dp)
                .background(color = Color.White))
            {
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController() // preview 전용
    HomeScreen(navController = navController)
}
