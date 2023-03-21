package com.belajarkotlin.librarymanagement.screen.home.main

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import com.belajarkotlin.librarymanagement.component.*
import com.belajarkotlin.librarymanagement.localRoom.IpEntity
import com.belajarkotlin.librarymanagement.screen.home.tabs.Tabs
import com.belajarkotlin.librarymanagement.screen.home.tabs.TabsContent
import com.belajarkotlin.librarymanagement.screen.home.tabs.TabsItem
import com.belajarkotlin.librarymanagement.viewModel.IpViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(navController: NavHostController, owner: ViewModelStoreOwner) {
    val context = LocalContext.current
    lateinit var mIpViewModel: IpViewModel
    val keyboardController = LocalSoftwareKeyboardController.current
    val linkDialogState = remember {
        mutableStateOf(false)
    }
    val dialogTextState = remember {
        TextFieldState()
    }
    val tabs = listOf(
        TabsItem.Tabs1,
        TabsItem.Tabs2,
    )
    val pagerState = rememberPagerState()
    BottomActionSheet { state, scope, focusManager ->

        if (state.currentValue == ModalBottomSheetValue.Expanded || state.currentValue == ModalBottomSheetValue.HalfExpanded) {
            BackHandler {
                scope.launch {
                    state.animateTo(ModalBottomSheetValue.Hidden, tween(100))
                }
            }
        } else {
            keyboardController?.hide()
            focusManager.clearFocus(true)
        }

        Scaffold { padding ->

            Column(modifier = Modifier.padding(padding)) {
                Tabs(tabs = tabs, pagerState = pagerState, isLinkDialogOpen = linkDialogState)
                TabsContent(tabs = tabs, pagerState = pagerState)
            }
        }
        if (linkDialogState.value) {
            mIpViewModel = IpViewModel(context)
            dialogTextState.text = mIpViewModel.readAllData(context)[0].ip
            LinkDialog(
                titleText = "Hubungkan Database",
                bodyText = "Silahkan masukan alamat IP database anda",
                onCancel = { linkDialogState.value = false },
                onYes = {
                    if (dialogTextState.text.isEmpty() || dialogTextState.text.contains(",") || dialogTextState.text.contains(" ")) {
                        Toast.makeText(context, "Alamat database tidak valid", Toast.LENGTH_SHORT).show()
                    } else {
                        val updatedIp = IpEntity(1, dialogTextState.text)
                        mIpViewModel.updateIp(updatedIp)
                        Log.e("Successfully Updated", updatedIp.toString())
                        linkDialogState.value = false
                        val packageManager: PackageManager = context.packageManager
                        val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
                        val componentName: ComponentName = intent.component!!
                        val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
                        context.startActivity(restartIntent)
                        Runtime.getRuntime().exit(0)
                    }
                },
                addressState = dialogTextState,
                label = "IP Address"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        if (!state.isVisible) {
                            state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                        }
                    }
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
}