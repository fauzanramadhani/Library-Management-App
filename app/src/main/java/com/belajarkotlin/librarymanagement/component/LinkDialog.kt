package com.belajarkotlin.librarymanagement.component

import android.system.Os.link
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.belajarkotlin.librarymanagement.R

@Composable
fun LinkDialog(
    cornerRadius: Dp = 12.dp,
    cancelButtonColor: Color = Color(0xFF35898F),
    titleTextStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.87f),
        fontFamily = FontFamily(Font(R.font.lexend_bold, FontWeight.Bold)),
        fontSize = 20.sp
    ),
    messageTextStyle: TextStyle = TextStyle(
        color = Color.Black.copy(alpha = 0.95f),
        fontFamily = FontFamily(Font(R.font.lexend_regular, FontWeight.Normal)),
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    buttonTextStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.lexend_medium, FontWeight.Medium)),
        fontSize = 16.sp
    ),
    titleText: String,
    bodyText: String,
    addressState: TextFieldState = remember { TextFieldState() },
    label: String,
    onCancel: () -> Unit,
    onYes: () -> Unit
) {
    // This helps to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val buttonCorner = 6.dp

    Dialog(
        onDismissRequest = {
            onCancel()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = cornerRadius)
        ) {

            Column(modifier = Modifier.padding(all = 16.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 6.dp,
                        alignment = Alignment.Start
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.ic_link),
                        contentDescription = "Link Icon",
                    )

                    Text(
                        text = titleText,
                        style = titleTextStyle
                    )

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = bodyText,
                    style = messageTextStyle
                )

                CanvasTextField(
                    state = addressState,
                    label = label,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .padding(bottom = 30.dp),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.End
                    )
                ) {

                    // Cancel button
                    Box(
                        modifier = Modifier
                            .clickable(
                                // This is to disable the ripple effect
                                indication = null,
                                interactionSource = interactionSource
                            ) {
                                onCancel()
                            }
                            .border(
                                width = 1.dp,
                                color = cancelButtonColor,
                                shape = RoundedCornerShape(buttonCorner)
                            )
                            .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                    ) {
                        Text(
                            text = "Batal",
                            style = buttonTextStyle,
                            color = cancelButtonColor
                        )
                    }

                    // Yes button
                    Box(
                        modifier = Modifier
                            .clickable(
                                // This is to disable the ripple effect
                                indication = null,
                                interactionSource = interactionSource
                            ) {
                                onYes()
                            }
                            .background(
                                color = Color(0xFF00B98C),
                                shape = RoundedCornerShape(buttonCorner)
                            )
                            .padding(top = 6.dp, bottom = 8.dp, start = 24.dp, end = 24.dp),
                    ) {
                        Text(
                            text = "Hubungkan",
                            style = buttonTextStyle,
                            color = Color.White
                        )
                    }

                }
            }

        }

    }
}