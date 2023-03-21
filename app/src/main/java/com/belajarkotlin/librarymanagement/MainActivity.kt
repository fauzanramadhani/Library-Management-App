package com.belajarkotlin.librarymanagement

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.belajarkotlin.librarymanagement.localRoom.IpEntity
import com.belajarkotlin.librarymanagement.router.SetupNavGraph
import com.belajarkotlin.librarymanagement.ui.theme.LibraryManagementTheme
import com.belajarkotlin.librarymanagement.viewModel.IpViewModel
import kotlinx.coroutines.coroutineScope

class MainActivity : ComponentActivity() {
    private lateinit var mIpViewModel: IpViewModel
    private lateinit var navController: NavHostController
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val owner = this
        mIpViewModel = IpViewModel(this)
        super.onCreate(savedInstanceState)
        setContent {
            LibraryManagementTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController, owner = this)
                if (mIpViewModel.readAllData(this).isEmpty()) {
                    val newIp = IpEntity(0, "192.168.0.1")
                    mIpViewModel.addIp(newIp)
                }

                Log.e("currentIp", mIpViewModel.readAllData(this).toString())
            }
        }
    }

}