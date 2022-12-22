package com.belajarkotlin.librarymanagement.data

sealed class TabState1 {
    class Success(val data: MutableList<DataPeminjaman>) : TabState1()
    class Failure(val message: String) : TabState1()
    object Loading : TabState1()
    object Empty : TabState1()
}

sealed class TabState2 {
    class Success(val data: MutableList<DataPeminjaman>) : TabState1()
    class Failure(val message: String) : TabState1()
    object Loading : TabState1()
    object Empty : TabState1()
}
