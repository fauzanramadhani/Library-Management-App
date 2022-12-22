package com.belajarkotlin.librarymanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.belajarkotlin.librarymanagement.router.SetupNavGraph
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