package com.cgm.codicefiscale

import com.cgm.codicefiscale.factories.DataServiceFactory
import com.cgm.codicefiscale.helpers.getValueFromCommandLine
import com.cgm.codicefiscale.helpers.validatePerson
import java.lang.Exception

fun main() {
    try {
        val dataServiceMode = getValueFromCommandLine("data Service mode")
        val person =
            validatePerson(
                getValueFromCommandLine("firstName"),
                getValueFromCommandLine("lastName"),
                getValueFromCommandLine("dateOfBirth [yyyy-MM-dd]"),
                getValueFromCommandLine("genre"),
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