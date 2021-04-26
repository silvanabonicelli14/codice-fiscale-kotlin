package com.cgm.codicefiscale.services

import java.time.LocalDate

const val  lettersForMonths = "ABCDEHLMPRST"

fun getLetters(lastName: String): Pair<String, String> {
    var consonants = ""
    var vowels = ""
    lastName.trim().toLowerCase().forEach {
        when (it) {
            'a', 'e', 'i', 'o', 'u' -> vowels += it
            in 'a'..'z' -> consonants += it
        }
    }
    return Pair(consonants, vowels)
}

fun checkStringValue(fieldValue: String, fieldName: String) {
    if (fieldValue.isEmpty()) throw IllegalArgumentException("Filed $fieldName is required")
}

fun dateValidator(dateInput: String) : LocalDate {
    try {
        return LocalDate.parse(dateInput)
    } catch (e: Exception) {
        throw IllegalArgumentException("Format Date $dateInput  is not valid")
    }
}