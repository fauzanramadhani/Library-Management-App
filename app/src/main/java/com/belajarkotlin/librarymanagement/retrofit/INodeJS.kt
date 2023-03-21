package com.belajarkotlin.librarymanagement.retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface INodeJS {
    @POST("read-data-pinjam")
    fun readData(
    ): Observable<String>

    @POST("create-data-pinjam")
    @FormUrlEncoded
    fun writeData(
        @Field("nama") nama: String,
        @Field("nim") nim: Int,
        @Field("buku") buku: String,
        @Field("tgl_peminjaman") tgl_peminjaman: Long,
        @Field("tgl_pengembalian") tgl_pengembalian: Long
    ): Observable<String>

}