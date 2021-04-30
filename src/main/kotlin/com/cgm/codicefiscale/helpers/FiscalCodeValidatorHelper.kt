package com.cgm.codicefiscale.helpers

import com.cgm.codicefiscale.entities.Person
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

fun getValueFromCommandLine(context: String): String {
    println("Insert value of $context")
    return readLine() ?: ""
}

fun validatePerson(
    firstName: String,
    lastName: String,
    dateOfBirth: String,
    genre: String,
    cityOfBirth: String
): Person {

    checkStringValue(firstName, "FirstName")
    checkStringValue(lastName, "Lastname")
    checkStringValue(dateOfBirth, "Date fo Birth")
    checkStringValue(genre, "Genre")
    val enumGenre = enumValueOfOrNull<Genre>(genre.toUpperCase())
    checkStringValue(cityOfBirth, "City of Birth")

    dateValidator(dateOfBirth).let {
        when {
            it <= LocalDate.now() -> {
                when {
                    enumGenre != null -> return Person(firstName, lastName, enumGenre, it, cityOfBirth)
                    else -> throw IllegalArgumentException("Genre not valid")
                }
            }
        }
        throw IllegalArgumentException("dateOfBirth not valid")
    }
}

inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String): T? {
    return enumValues<T>().find { it.name == name }
}

enum class Genre {
    F,
    M
}