package com.belajarkotlin.librarymanagement.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.belajarkotlin.librarymanagement.R

class TextFieldState {
    var text: String by mutableStateOf("")
}

@Composable
fun CanvasTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState = remember { TextFieldState() },
    label: String,
    keyboardOptions: KeyboardOptions,
    length: Int = 50,
    backgroundColor: Color = Color(0x2500B98C),
    singleLine: Boolean = true,
    readyOnly: Boolean = false,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        readOnly = readyOnly,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        value = state.text,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }

        ),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            cursorColor = Color.Black,
            disabledLabelColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            if (it.length <= length) state.text = it
        },
        shape = RoundedCornerShape(8.dp),
        singleLine = singleLine,
        trailingIcon = {
            if (state.text.isNotEmpty()) {
                if (!readyOnly) {
                    IconButton(onClick = { state.text = "" }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    )
}