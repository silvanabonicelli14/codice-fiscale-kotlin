package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.entities.Genre
import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.helpers.*
import com.cgm.codicefiscale.interfaces.IDataService
import java.time.LocalDate

class FiscalCodeCalculator(dataService: IDataService) {

    private val countryCodeList: List<CountryCode> by lazy {
        dataService.loadCountryCode()
    }

    fun getFiscalCode(person: Person): String {
        (encodedLastName(person.lastName) +
         encodedFirstName(person.firstName) +
         encodedYearOfBirth(person.dateOfBirth) +
         encodedMonthOfBirth(person.dateOfBirth) +
         encodedDayOfBirth(person.dateOfBirth, person.genre) +
         encodedCityOfBirth(person.cityOfBirth)).apply {
            return this + checkDigit(this)
         }
    }

    private fun encodedFirstName(inputString: String): String {
        val (consonants, vowels) = getLetters(inputString)
        consonants.takeIf {
            if (it.length >= 4)
                return (consonants[0].toString() + consonants[2].toString() + consonants[3].toString()).toUpperCase()
            return (consonants + vowels).toUpperCase().padEnd(3, 'X').take(3)
        }
    }

    private fun encodedLastName(inputString: String): String {
        val (consonants, vowels) = getLetters(inputString)
        return (consonants + vowels).toUpperCase().padEnd(3, 'X').take(3)
    }

    private fun encodedYearOfBirth(inputDate: LocalDate): String {
        return inputDate.year.toString().takeLast(2)
    }

    private fun encodedMonthOfBirth(inputDate: LocalDate): String {
        return lettersForMonths[inputDate.monthValue - 1].toString()
    }

    private fun encodedDayOfBirth(inputDate: LocalDate, genre:Genre): String {
        return when (genre) {
            Genre.F -> inputDate.dayOfMonth.plus(40).toString()
            Genre.M -> inputDate.dayOfMonth.toString().padStart(2, '0')
        }
    }

    private fun encodedCityOfBirth(inputString: String): String {
        countryCodeList.filter { it.country.toLowerCase().trim() == inputString.toLowerCase().trim() }
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