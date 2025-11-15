package com.example.re0.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.re0.R
import com.example.re0.ui.theme.Mint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(){
    Column {
        HorizontalDivider(
            Modifier, DividerDefaults.Thickness, color = Mint
        )
        BottomAppBar(
            containerColor = Color.White,
            tonalElevation = 0.dp
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                Image(
                    painter = painterResource(id = R.drawable.info),
                    contentDescription = "bottomAppBar",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "bottomAppBar",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "bottomAppBar",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.history),
                    contentDescription = "bottomAppBar",
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.mypage),
                    contentDescription = "bottomAppBar",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun BottomBarPreview(){
    BottomBar()
}