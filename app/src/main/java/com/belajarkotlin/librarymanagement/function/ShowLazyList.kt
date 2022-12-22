package com.belajarkotlin.librarymanagement.function

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.belajarkotlin.librarymanagement.component.TabsCardView
import com.belajarkotlin.librarymanagement.data.DataPeminjaman

@Composable
fun ShowLazyList(tabs: Int, data: MutableList<DataPeminjaman>, navController: NavHostController) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(top = 1.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(data) {
            TabsCardView(
                tabs = tabs,
                id = it.id,
                nama = it.nama,
                nim = it.nim,
                buku = it.buku,
                tglPeminjaman = it.tglPeminjaman,
                tglPengembalian = it.tglPengembalian,
                dikembalikan = it.dikembalikan,
                status = it.status
            ) {
                Toast.makeText(context, "Data ${it.id} Clicked!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
