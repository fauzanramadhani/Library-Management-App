package com.belajarkotlin.librarymanagement.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.belajarkotlin.librarymanagement.data.DropDownBookStateHolder

@Composable
fun DropdownBook(
    modifier: Modifier = Modifier,
    stateHolder: DropDownBookStateHolder,
    label: String,
    focusManager: FocusManager
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 15.dp)
    ) {

        TextField(
            readOnly = true,
            value = stateHolder.value,
            onValueChange = { },
            label = { Text(text = label) },
            trailingIcon = {
                Icon(
                    painterResource(id = stateHolder.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            stateHolder.onEnabled(!(stateHolder.enabled))
                        }
                )
            },
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0x2500B98C),
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            modifier = Modifier
                .onGloballyPositioned {
                    stateHolder.onSize(it.size.toSize())
                }
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = stateHolder.enabled,
            onDismissRequest = { stateHolder.onEnabled(false) },
            modifier = Modifier
                .background(
                    Color.White
                )
                .width(with(LocalDensity.current) { stateHolder.size.width.toDp() })
        ) {
            stateHolder.items.forEachIndexed { index, s ->
                DropdownMenuItem(
                    onClick = {
                        stateHolder.onSelectedIndex(index)
                        stateHolder.onEnabled(false)
                    }
                ) {
                    Text(
                        text = s,
                    )
                }
            }
        }
    }
}