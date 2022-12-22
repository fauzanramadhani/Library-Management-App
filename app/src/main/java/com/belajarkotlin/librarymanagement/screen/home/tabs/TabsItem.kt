package com.belajarkotlin.librarymanagement.screen.home.tabs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

typealias ComposableFun = @Composable () -> Unit

sealed class TabsItem(var title: String, var screen: ComposableFun) {
    object Tabs1 : TabsItem("Peminjaman", { Tabs1(navController = rememberNavController()) })
    object Tabs2 : TabsItem("Pengembalian", { Tabs2(navController = rememberNavController()) })
}