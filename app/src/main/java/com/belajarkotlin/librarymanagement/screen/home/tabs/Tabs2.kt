package com.belajarkotlin.librarymanagement.screen.home.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.belajarkotlin.librarymanagement.R
import com.belajarkotlin.librarymanagement.data.TabState1
import com.belajarkotlin.librarymanagement.doubleClickHandler.clickableSingle
import com.belajarkotlin.librarymanagement.function.ShowLazyList
import com.belajarkotlin.librarymanagement.viewModel.TabsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Tabs2(
    tabsViewModel: TabsViewModel = viewModel(),
    navController: NavHostController
) {
    val Lexend = FontFamily(
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold)
    )

    var refreshing by remember { mutableStateOf(false) }

    if (refreshing) {
        tabsViewModel.pullData()
    }
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE9ECF4)),
        ) {
            when (val result = tabsViewModel.response.value) {
                is TabState1.Loading -> {
                    refreshing = true
                }
                is TabState1.Success -> {
                    refreshing = false
                    ShowLazyList(2, result.data, navController)
                }
                is TabState1.Failure -> {
                    refreshing = false
                    Text(
                        text = result.message,
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Refresh",
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickableSingle {
                                refreshing = true
                            }
                    )
                }
                else -> {
                    Text(
                        text = "Unknown Error",
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Refresh",
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickableSingle {
                                refreshing = true
                            }
                    )
                }
            }
        }
    }
}