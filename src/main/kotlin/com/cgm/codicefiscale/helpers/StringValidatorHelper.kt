package com.cgm.codicefiscale.helpers

import java.lang.IllegalStateException
import java.time.LocalDate


fun checkStringValue(fieldValue: String, fieldName: String) {
    if (fieldValue.isEmpty()) throw IllegalArgumentException("Filed $fieldName is required")
}

fun dateValidator(dateInput: String) : LocalDate {
    try {
        return LocalDate.parse(dateInput)
    } catch (e: Exception) {
        throw IllegalStateException("Format Date $dateInput  is not valid")
    }
}

fun getValueFromCommandLine(context: String): String? {
    println("Insert value of $context")
    return readLine()
}