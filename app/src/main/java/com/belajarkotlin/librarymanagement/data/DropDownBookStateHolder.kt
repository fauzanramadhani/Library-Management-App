package com.belajarkotlin.librarymanagement.data

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import com.belajarkotlin.librarymanagement.R

class DropDownBookStateHolder(currentState: String) {

    var enabled by mutableStateOf(false)
    var value by mutableStateOf(currentState)
    var selectedndex by mutableStateOf(-1)
    var size by mutableStateOf(Size.Zero)
    val icon: Int
        @Composable get() = if (enabled) {
            R.drawable.ic_arrow_drop_up
        } else {
            R.drawable.ic_arrow_drop_down
        }
    val items = listOf("Invaluable", "The Department Chair", "The Coddling of the American Mind")

    fun onEnabled(newValue: Boolean) {
        enabled = newValue
    }

    fun onSelectedIndex(newValue: Int) {
        selectedndex = newValue
        value = items[selectedndex]
    }

    fun onSize(newValue: Size) {
        size = newValue
    }
}

@Composable
fun rememberDropDownBookStateHolder(currentState: String) = remember {
    DropDownBookStateHolder(currentState)
}