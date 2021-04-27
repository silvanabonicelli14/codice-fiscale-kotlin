package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.helpers.*
import com.cgm.codicefiscale.interfaces.IDataService
import java.time.LocalDate

class FiscalCodeCalculator(dataService: IDataService){
    private lateinit var dateOfPerson: LocalDate
    private lateinit var person: Person
    private var countryList = mutableListOf<CountryCode>()

    init {
        countryList = dataService.loadData() as MutableList<CountryCode>
    }

    fun getFiscalCode(inputPerson: Person): String {
        person = inputPerson
        return person.let {
            checkValues()
            calculateFiscalCode()
        }
    }
    private fun checkValues() {
        checkStringValue(person.firstName,"Name")
        checkStringValue(person.lastName,"Lastname")
        checkStringValue(person.dateOfBirth,"Date fo Birth")
        checkStringValue(person.genre,"Genre")
        checkStringValue(person.cityOfBirth,"City of Birth")
        dateOfPerson = dateValidator(person.dateOfBirth)
    }

    private fun calculateFiscalCode(): String {
        val fiscalCode =
            encodedLastName(person.lastName) +
            encodedFirstName(person.firstName) +
            encodedYearOfBirth(dateOfPerson) +
            encodedMonthOfBirth(dateOfPerson) +
            encodedDayOfBirth (dateOfPerson, person.genre) +
            encodedCityOfBirth(person.cityOfBirth)

        return fiscalCode + checkDigit(fiscalCode)
    }

    fun encodedFirstName(inputString: String):String {
        val (consonants, vowels) = getLetters(inputString)
        consonants.takeIf {
            if (it.length >= 4 )
                return (consonants[0].toString() + consonants[2].toString() + consonants[3].toString()).toUpperCase()
            return  (consonants + vowels).toUpperCase().padEnd(3,'X').take(3)
        }
    }

    fun encodedLastName(inputString:String):String {
        val (consonants, vowels) = getLetters(inputString)
        return (consonants + vowels).toUpperCase().padEnd(3,'X').take(3)
    }

    fun encodedYearOfBirth(date: LocalDate):String = date.year.toString().takeLast(2)

    fun encodedMonthOfBirth(date: LocalDate):String = lettersForMonths[date.monthValue -1].toString()


    fun encodedDayOfBirth(date: LocalDate, genre: String):String{
        return when(genre.toUpperCase()) {
            "F" -> date.dayOfMonth.plus(40).toString()
            else -> date.dayOfMonth.toString().padStart(2,'0')
        }
    }

    fun encodedCityOfBirth(inputString: String): String {
        countryList.filter {it.country.toLowerCase().trim() == inputString.toLowerCase().trim()}
            .takeIf {
                if (it.isNullOrEmpty()) throw IllegalStateException("city of birth not valid: $it")
                return it[0].code.toUpperCase()}
    }

    fun checkDigit(inputString: String): String {
        var sumForEvanChar = 0
        var sumForOddChar = 0

        for (i in inputString.indices) {
            when( (i+1) % 2){
                0 ->   sumForEvanChar += charForEven[inputString[i].toUpperCase().toString()]
                    ?: error("Can't calculate check digit for $inputString")
                else ->  sumForOddChar += charForOdd[inputString[i].toUpperCase().toString()]
                    ?: error("Can't calculate check digit for $inputString")
            }
        }
        return digitChar[((sumForEvanChar + sumForOddChar) % 26)].toString()
    }
}