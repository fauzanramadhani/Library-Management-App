package com.belajarkotlin.librarymanagement.screen

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.belajarkotlin.librarymanagement.R
import com.belajarkotlin.librarymanagement.router.Screen

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),

        ) {
        Image(
            painter = painterResource(id = R.drawable.books),
            contentDescription = null,
            modifier = Modifier
                .width(127.dp)
                .height(127.dp)
        )
    }
    Handler().postDelayed({
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.OnBoarding.route) { inclusive = true }
            launchSingleTop = true
        }
    }, 3000) // 3000 is the delayed time in milliseconds.
}