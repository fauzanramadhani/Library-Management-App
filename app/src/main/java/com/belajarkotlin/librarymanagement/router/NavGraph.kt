package com.belajarkotlin.librarymanagement.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.belajarkotlin.librarymanagement.screen.OnBoardingScreen
import com.belajarkotlin.librarymanagement.screen.home.main.MainScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoarding.route
    ) {
        composable(
            route = Screen.OnBoarding.route
        ) {
            OnBoardingScreen(navController = navController)
        }

        composable(
            route = Screen.Home.route
        ) {
            MainScreen(navController = navController)
        }
    }
}