package com.belajarkotlin.librarymanagement.data

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

data class DataPeminjaman(
    val id: Int = 0,
    val nama: String = "",
    val nim: Int = 0,
    val buku: String = "",
    val tglPeminjaman: Long = 0,
    val tglPengembalian: Long = 0,
    val dikembalikan: Long = 0,
    val status: Int = 0
)

data class menuEachItem(
    val title: String,
    val icon: ImageVector
)
