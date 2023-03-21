package com.belajarkotlin.librarymanagement.localRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ip_table")
data class IpEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val ip: String
)
