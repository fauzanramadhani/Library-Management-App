package com.belajarkotlin.librarymanagement.screen.home.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.belajarkotlin.librarymanagement.component.Tabs
import com.belajarkotlin.librarymanagement.component.TabsContent
import com.belajarkotlin.librarymanagement.screen.home.tabs.TabsItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val tabs = listOf(
        TabsItem.Tabs1,
        TabsItem.Tabs2
    )
    val pagerState = rememberPagerState()
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        FloatingActionButton(
            onClick = {
                Toast.makeText(context, "Floating Action Button", Toast.LENGTH_SHORT).show()
            },
            backgroundColor = Color(0xFF00B98C),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }
}