package com.cgm.codicefiscale.helpers

import java.time.LocalDate

fun checkStringValue(fieldValue: String, fieldName: String) {
    if (fieldValue.isEmpty()) throw IllegalArgumentException("Filed $fieldName is required")
}

fun dateValidator(dateInput: String): LocalDate {
    try {
        return LocalDate.parse(dateInput)
    } catch (e: Exception) {
        throw IllegalArgumentException("Format Date $dateInput  is not valid")
    }
}

inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String): T? {
    return enumValues<T>().find { it.name == name }
}