package com.belajarkotlin.librarymanagement.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingDialog(
    content: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(Color(0xFFE9ECF4))
            .fillMaxSize()
            .scrollable(scrollState, Orientation.Vertical),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}