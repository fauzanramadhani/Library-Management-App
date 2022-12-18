package com.belajarkotlin.librarymanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.belajarkotlin.librarymanagement.router.SetupNavGraph
import com.belajarkotlin.librarymanagement.screen.OnBoardingScreen
import com.belajarkotlin.librarymanagement.ui.theme.LibraryManagementTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryManagementTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}