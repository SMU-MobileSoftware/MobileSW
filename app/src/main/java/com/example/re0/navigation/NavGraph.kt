package com.example.re0.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    //val viewModel: TodoViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("home") {
            //
        }
        composable("info") {
           //
        }
        composable("history") {
            //
        }
        composable("mypage") {
            //
        }
        composable("map") { 
            //
        }
        composable("challenge") {
            //
        }
    }
}
