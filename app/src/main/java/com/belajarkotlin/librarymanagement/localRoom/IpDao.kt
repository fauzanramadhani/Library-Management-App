package com.belajarkotlin.librarymanagement.localRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface IpDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIp(ipEntity: IpEntity)

    @Update
    suspend fun updateIp(ipEntity: IpEntity)

    @Query("SELECT * FROM ip_table ORDER BY id ASC")
    fun readAllData(): List<IpEntity>
}