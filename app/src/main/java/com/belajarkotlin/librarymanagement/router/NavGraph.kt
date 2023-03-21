package com.belajarkotlin.librarymanagement.router

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.belajarkotlin.librarymanagement.screen.onBoarding.OnBoardingScreen
import com.belajarkotlin.librarymanagement.screen.home.main.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    owner: ViewModelStoreOwner
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
            MainScreen(navController = navController, owner)
        }
    }
}