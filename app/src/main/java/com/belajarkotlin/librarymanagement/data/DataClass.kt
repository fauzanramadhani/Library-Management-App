package com.belajarkotlin.librarymanagement.data

data class DataPeminjaman(
    val id: Int = 0,
    val nama: String = "",
    val nim: Int = 0,
    val buku: String = "",
    val tglPeminjaman: Int = 0,
    val tglPengembalian: Int = 0,
    val dikembalikan: Int = 0,
    val status: Int = 0
)

