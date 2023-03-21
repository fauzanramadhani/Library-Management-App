package com.belajarkotlin.librarymanagement.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.belajarkotlin.librarymanagement.data.DataPeminjaman
import com.belajarkotlin.librarymanagement.data.TabState1
import com.belajarkotlin.librarymanagement.retrofit.INodeJS
import com.belajarkotlin.librarymanagement.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import retrofit2.Retrofit


class TabsViewModel : ViewModel() {
    val response: MutableState<TabState1> = mutableStateOf(TabState1.Empty)
    lateinit var myAPI: INodeJS
    var compositeDisposable = CompositeDisposable()

    fun pullData() {
        val retrofit: Retrofit = RetrofitClient.instance
        response.value = TabState1.Loading
        myAPI = retrofit.create(INodeJS::class.java)
        compositeDisposable.add(myAPI.readData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { e ->
                e.localizedMessage?.let { message ->
                    response.value = TabState1.Failure(message)
                }
            }
            .onErrorReturn { e ->
                "Connection Error: ${e.localizedMessage} [ERROR CODE : *Nnsya78*87n@#!0dsa&^5]"
            }.subscribe { message ->
                if (message.contains("*Nnsya78*87n@#!0dsa&^5")) {
                    response.value = TabState1.Failure("Unable to connect to server")
                } else {
                    val jArray = JSONArray(message)
                    val dataPeminjaman = mutableListOf<DataPeminjaman>()
                    for (i in 0 until jArray.length()) {
                        val objectValue = jArray.getJSONObject(i)
                        val getId = objectValue.getInt("id")
                        val getNama = objectValue.getString("nama")
                        val getNim = objectValue.getInt("nim")
                        val getBuku = objectValue.getString("buku")
                        val getTglPeminjaman = objectValue.getLong("tgl_peminjaman")
                        val getTglPengembalian = objectValue.getLong("tgl_pengembalian")
                        val getDikembalikan = objectValue.getLong("dikembalikan")
                        val getStatus = objectValue.getInt("status")

                        dataPeminjaman.add(
                            DataPeminjaman(
                                id = getId,
                                nama = getNama,
                                nim = getNim,
                                buku = getBuku,
                                tglPeminjaman = getTglPeminjaman,
                                tglPengembalian = getTglPengembalian,
                                dikembalikan = getDikembalikan,
                                status = getStatus
                            )
                        )
                    }
                    dataPeminjaman.sortByDescending {
                        it.id
                    }
                    response.value = TabState1.Success(dataPeminjaman)
                }
            }
        )
    }
}