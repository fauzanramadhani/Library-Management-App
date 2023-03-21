package com.belajarkotlin.librarymanagement.function

fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}