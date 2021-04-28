package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.helpers.*
import com.cgm.codicefiscale.interfaces.IDataService
import java.time.LocalDate

class FiscalCodeCalculator(dataService: IDataService) {

    private lateinit var person: Person

    fun getFiscalCode(inputPerson: Person): String {
        person = inputPerson
        val fiscalCode =
            encodedLastName() +
            encodedFirstName() +
            encodedYearOfBirth() +
            encodedMonthOfBirth() +
            encodedDayOfBirth() +
            encodedCityOfBirth()

        return fiscalCode + checkDigit(fiscalCode)
    }

    private val countryCodeList: List<CountryCode> by lazy {
        dataService.loadCountryCode()
    }

    private fun encodedFirstName(): String {
        val (consonants, vowels) = getLetters(person.firstName)
        consonants.takeIf {
            if (it.length >= 4)
                return (consonants[0].toString() + consonants[2].toString() + consonants[3].toString()).toUpperCase()
            return (consonants + vowels).toUpperCase().padEnd(3, 'X').take(3)
        }
    }

    private fun encodedLastName(): String {
        val (consonants, vowels) = getLetters(person.lastName)
        return (consonants + vowels).toUpperCase().padEnd(3, 'X').take(3)
    }

    private fun encodedYearOfBirth(): String {
        return person.dateOfBirth.year.toString().takeLast(2)
    }

    private fun encodedMonthOfBirth(): String {
        return lettersForMonths[person.dateOfBirth.monthValue - 1].toString()
    }

    private fun encodedDayOfBirth(): String {
        return when (person.genre) {
            Genre.F -> person.dateOfBirth.dayOfMonth.plus(40).toString()
            Genre.M -> person.dateOfBirth.dayOfMonth.toString().padStart(2, '0')
        }
    }

    private fun encodedCityOfBirth(): String {
        countryCodeList.filter { it.country.toLowerCase().trim() == person.cityOfBirth.toLowerCase().trim() }
            .takeIf {
                if (it.isNullOrEmpty()) throw IllegalStateException("city of birth not founded: $it")
                return it[0].code.toUpperCase()
            }
    }

    private fun checkDigit(inputString: String): String {
        var sumForEvanChar = 0
        var sumForOddChar = 0

        for (i in inputString.indices) {
            when ((i + 1) % 2) {
                0 -> sumForEvanChar += charForEven[inputString[i].toUpperCase().toString()]
                    ?: error("Can't calculate check digit for $inputString")
                else -> sumForOddChar += charForOdd[inputString[i].toUpperCase().toString()]
                    ?: error("Can't calculate check digit for $inputString")
            }
        }
        return digitChar[((sumForEvanChar + sumForOddChar) % 26)].toString()
    }
}