package com.belajarkotlin.librarymanagement.retrofit

import io.reactivex.Observable
import retrofit2.http.POST

interface INodeJS {
    @POST("read-data-pinjam")
    fun readData(
    ): Observable<String>
}