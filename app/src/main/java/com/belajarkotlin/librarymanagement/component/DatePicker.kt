package com.belajarkotlin.librarymanagement.component

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.belajarkotlin.librarymanagement.R

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DatePicker(
    state: TextFieldState = remember { TextFieldState() },
    label: String
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerAnalog = DatePickerDialog(
        context,
        { _: DatePicker, years: Int, months: Int, dayOfMonth: Int ->
            if (dayOfMonth < 10) {
                if (months < 10 - 1) {
                    state.text = "0$dayOfMonth-0${months + 1}-$years"
                } else {
                    state.text = "0$dayOfMonth-${months + 1}-$years"
                }
            } else {
                if (months < 10 - 1) {
                    state.text = "$dayOfMonth-0${months + 1}-$years"
                } else {
                    state.text = "$dayOfMonth-${months + 1}-$years"
                }
            }
        }, year, month, day
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = state.text,
            onValueChange = { datePickerAnalog.show() },
            label = { Text(text = label) },
            trailingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            datePickerAnalog.show()
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
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .fillMaxWidth()
        )

    }
}