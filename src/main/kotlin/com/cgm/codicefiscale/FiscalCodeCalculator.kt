package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.interfaces.IDataService
import com.cgm.codicefiscale.services.checkStringValue
import com.cgm.codicefiscale.services.dateValidator
import com.cgm.codicefiscale.services.getLetters
import com.cgm.codicefiscale.services.lettersForMonths
import java.lang.IllegalArgumentException
import java.time.LocalDate

class FiscalCodeCalculator(dataService: IDataService){
    private lateinit var dateOfPerson: LocalDate
    private var countryList = mutableListOf<Pair<String,String>>()

    init {
        countryList = dataService.loadData() as MutableList<Pair<String, String>>
    }

    fun getFiscalCode(person: Person): String {
        return person.let {
            checkValues(it)
            calculateFiscalCode(it)
        }
    }

    private fun calculateFiscalCode(person: Person): String {
        return encodedLastName(person.lastName) +
               encodedFirstName(person.firstName) +
               encodedYearOfBirth(dateOfPerson) +
               encodedMonthOfBirth(dateOfPerson) +
               encodedDayOfBirth (dateOfPerson, person.genre) +
               encodedCityOfBirth(person.cityOfBirth) +
               checkDigit()
    }

    fun encodedFirstName(firstName: String):String {
        val (consonants, vowels) = getLetters(firstName)
        consonants.takeIf {
            if (it.length >= 4 )
                return (consonants[0].toString() + consonants[2].toString() + consonants[3].toString()).toUpperCase()
            return  (consonants + vowels).toUpperCase().padEnd(3,'X').take(3)
        }
    }

    fun encodedLastName(lastName:String):String {
        val (consonants, vowels) = getLetters(lastName)
        return (consonants + vowels).toUpperCase().padEnd(3,'X').take(3)
    }

    fun encodedYearOfBirth(date: LocalDate):String = date.year.toString().takeLast(2)

    fun encodedMonthOfBirth(date: LocalDate):String = lettersForMonths[date.monthValue -1].toString()


    fun encodedDayOfBirth(date: LocalDate, sex: String):String{
        return when(sex) {
            "F" -> date.dayOfMonth.plus(40).toString()
            else -> date.dayOfMonth.toString().padStart(2,'0')
        }
    }

    fun encodedCityOfBirth(cityOfBirth: String): String {
        val result = countryList
            .filter {it.first.toLowerCase().trim() == cityOfBirth.toLowerCase().trim()}
            .takeIf {
                if (it.isNullOrEmpty()) throw IllegalArgumentException("city of birth not valid: $it")
                return it[0].second.toUpperCase()}
    }

    fun checkDigit(): String = ""

    fun getListOfCities(): MutableList<Pair<String, String>> {
        return countryList
    }

    private fun checkValues(person: Person) {
        checkStringValue(person.firstName,"Name")
        checkStringValue(person.lastName,"Lastname")
        checkStringValue(person.dateOfBirth,"Date fo Birth")
        checkStringValue(person.genre,"Genre")
        checkStringValue(person.cityOfBirth,"City of Birth")
        dateOfPerson = dateValidator(person.dateOfBirth)
    }
}