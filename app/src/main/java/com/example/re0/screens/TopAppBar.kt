package com.example.re0.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.re0.R
import com.example.re0.ui.theme.Mint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    Column {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.re_0),
                    contentDescription = "RE0"
                )
            },

            )
        HorizontalDivider(
            Modifier, DividerDefaults.Thickness, color = Mint
        )
    }
}

@Preview (showBackground = true)
@Composable
fun TopBarPreview(){
    TopBar()
}