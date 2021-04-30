package com.cgm.codicefiscale.entities

import com.cgm.codicefiscale.helpers.checkStringValue
import com.cgm.codicefiscale.helpers.dateValidator
import com.cgm.codicefiscale.helpers.enumValueOfOrNull
import java.time.LocalDate

class Person private constructor(
    val firstName: String,
    val lastName: String,
    val genre: Genre,
    val dateOfBirth: LocalDate,
    val cityOfBirth: String
) {
    companion object {
        fun of(
            firstName: String,
            lastName: String,
            genre: String,
            dateOfBirth: String,
            cityOfBirth: String
        ): Person {
            val enumGenre = enumValueOfOrNull<Genre>(genre.toUpperCase())
            checkStringValue(firstName, "FirstName")
            checkStringValue(lastName, "Lastname")
            checkStringValue(genre, "Genre")
            checkStringValue(dateOfBirth, "Date fo Birth")
            checkStringValue(cityOfBirth, "City of Birth")

            dateValidator(dateOfBirth).let {
                when {
                    it <= LocalDate.now() -> {
                        when {
                            enumGenre != null
                            -> return Person(firstName, lastName, enumGenre, it, cityOfBirth)
                            else -> throw IllegalArgumentException("Genre not valid")
                        }
                    }
                }
                throw IllegalArgumentException("dateOfBirth not valid")
            }
        }
    }
}

enum class Genre {
    F,
    M
}