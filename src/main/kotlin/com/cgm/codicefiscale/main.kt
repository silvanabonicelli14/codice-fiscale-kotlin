package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.services.SqLiteDataService
import com.cgm.codicefiscale.helpers.getValueFromCommandLine

fun main() {
    val firstName = getValueFromCommandLine("firstName")
    val lastName = getValueFromCommandLine("lastName")
    val genre = getValueFromCommandLine("genre")
    val dateOfBirth = getValueFromCommandLine("dateOfBirth [yyyy-MM-dd]")
    val cityOfBirth = getValueFromCommandLine("cityOfBirth")
    when {
        !firstName.isNullOrEmpty() &&
                !lastName.isNullOrEmpty() &&
                !genre.isNullOrEmpty() &&
                !dateOfBirth.isNullOrEmpty() &&
                !cityOfBirth.isNullOrEmpty() -> {

            println(FiscalCodeCalculator(SqLiteDataService())
                .getFiscalCode(Person(firstName,lastName,genre,dateOfBirth,cityOfBirth)))
        }
        else -> println("Values are not correct")
    }
}

