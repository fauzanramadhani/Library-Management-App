package com.belajarkotlin.librarymanagement.function

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun dateToEpoch(date: String): Long {
    val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    val localDate = LocalDateTime.parse("$date 01:00", pattern)
    return localDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}