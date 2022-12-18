package com.belajarkotlin.librarymanagement.screen.home.tabs

import androidx.compose.runtime.Composable
import com.belajarkotlin.librarymanagement.R

typealias ComposableFun = @Composable () -> Unit

sealed class TabsItem(var title: String, var screen: ComposableFun) {
    object Tabs1 : TabsItem("Belum Dikembalikan", { Tabs1() })
    object Tabs2 : TabsItem("Sudah Dikembalikan", { Tabs2() })
}