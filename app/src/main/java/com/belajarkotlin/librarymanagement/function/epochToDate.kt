package com.belajarkotlin.librarymanagement.function

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun epochToDate(pattern: String, epochDate: Long?): String {
    val sdf = SimpleDateFormat(pattern)
    val calendar = Calendar.getInstance()
    if (epochDate != null) {
        val epochDateMills = "${epochDate}000"
        calendar.timeInMillis = epochDateMills.toLong()
    }
    return sdf.format(calendar.time)
}