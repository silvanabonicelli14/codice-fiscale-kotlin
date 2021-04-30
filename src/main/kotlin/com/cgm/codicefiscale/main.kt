package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.factories.DataServiceFactory
import java.lang.Exception

fun main() {
    try {
        val dataServiceMode = getValueFromCommandLine("data Service mode")
        val person =
            Person.of(
                getValueFromCommandLine("firstName"),
                getValueFromCommandLine("lastName"),
                getValueFromCommandLine("genre"),
                getValueFromCommandLine("dateOfBirth [yyyy-MM-dd]"),
                getValueFromCommandLine("city of Birth")
            )
        print(
            "Your FiscalCode is ${
                FiscalCodeCalculator(DataServiceFactory().getDataService(dataServiceMode)).getFiscalCode(person)
            }"
        )
    } catch (x: Exception) {
        println(x.message)
    }
}

fun getValueFromCommandLine(context: String): String {
    println("Insert value of $context")
    return readLine() ?: ""
}
