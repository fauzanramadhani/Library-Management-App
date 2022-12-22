package com.belajarkotlin.librarymanagement.router

sealed class Screen(val route: String) {
    object OnBoarding : Screen(route = "onBoarding_screen")
    object Home : Screen(route = "main_screen")
}
