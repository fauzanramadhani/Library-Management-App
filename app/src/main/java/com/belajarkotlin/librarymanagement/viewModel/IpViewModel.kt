package com.belajarkotlin.librarymanagement.viewModel

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajarkotlin.librarymanagement.localRoom.IpDatabase
import com.belajarkotlin.librarymanagement.localRoom.IpEntity
import com.belajarkotlin.librarymanagement.localRoom.IpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IpViewModel(context: Context) : ViewModel() {

    private lateinit var repository: IpRepository


    fun readAllData(context: Context): List<IpEntity> {
        val ipDao = IpDatabase.getDatabase(context).ipDao()
        repository = IpRepository(ipDao)
        return repository.readAllData()
    }

    fun addIp(ipEntity: IpEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIp(ipEntity)
        }
    }

    fun updateIp(ipEntity: IpEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIp(ipEntity)
        }
    }
}