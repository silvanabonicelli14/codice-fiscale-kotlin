package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.factories.DataService
import com.cgm.codicefiscale.helpers.getValueFromCommandLine
import com.cgm.codicefiscale.factories.DataServiceFactory
import com.cgm.codicefiscale.interfaces.IDataService
import com.cgm.codicefiscale.services.CsvDataService
import com.cgm.codicefiscale.services.SqLiteDataService

fun main() {
    val firstName = getValueFromCommandLine("firstName")
    val lastName = getValueFromCommandLine("lastName")
    val genre = getValueFromCommandLine("genre")
    val dateOfBirth = getValueFromCommandLine("dateOfBirth [yyyy-MM-dd]")
    val cityOfBirth = getValueFromCommandLine("cityOfBirth")
    val dataServiceMode = getValueFromCommandLine("data Service mode")
    when {
        !firstName.isNullOrEmpty() &&
                !lastName.isNullOrEmpty() &&
                !genre.isNullOrEmpty() &&
                !dateOfBirth.isNullOrEmpty() &&
                !cityOfBirth.isNullOrEmpty() -> {

            println(
                FiscalCodeCalculator(DataServiceFactory().getDataService(dataServiceMode))
                    .getFiscalCode(Person(firstName, lastName, genre, dateOfBirth, cityOfBirth))
            )
        }
        else -> println("Values are not correct")
    }
}