package com.belajarkotlin.librarymanagement.retrofit

import android.app.Application
import com.belajarkotlin.librarymanagement.viewModel.IpViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private val application = Application()
    private var ourInstance: Retrofit? = null
    val instance: Retrofit
        get() {
            if (ourInstance == null) {
                val mIpViewModel = IpViewModel(application)
                ourInstance = Retrofit.Builder()
                    .baseUrl("http://${mIpViewModel.readAllData(application)[0].ip}:3000" + "/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            }
            return ourInstance!!
        }
}