package com.cgm.codicefiscale.entities

import com.cgm.codicefiscale.helpers.Genre
import java.time.LocalDate

data class Person(
    val firstName: String,
    val lastName: String,
    val genre: Genre,
    val dateOfBirth: LocalDate,
    val cityOfBirth: String,
)