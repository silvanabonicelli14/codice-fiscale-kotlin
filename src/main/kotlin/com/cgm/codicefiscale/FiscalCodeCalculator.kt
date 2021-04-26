package com.cgm.codicefiscale

import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FiscalCodeCalculator{
    private lateinit var dateOfPerson: LocalDate

    fun getFiscalCode(person: Person): String {
        return person.let {
            checkValues(it)
            calculateFiscalCode(it)
        }
    }

    private fun calculateFiscalCode(person: Person): String {
        return getEncodedLastName(person.lastName) +
               getEncodedFirstName(person.firstName) +
               getEncodesYearOfBirth(dateOfPerson)
               getEncodedMonthOfBirth(dateOfPerson) +
               getEncodedDayOfBirth (dateOfPerson, person.sex)

        val string = "July 25, 2017"

        var convertedDate = LocalDate.parse(string, DateTimeFormatter.ISO_DATE)
    }

    fun getEncodedFirstName(firstName: String):String {
        val (consonants, vowels) = getLetters(firstName)

        consonants.takeIf {
            if (it.length >= 4 )
                return (consonants[0].toString() + consonants[2].toString() + consonants[3].toString()).toUpperCase()
            return  (consonants + vowels).toUpperCase().padEnd(3,'X').take(3)
        }
    }

    fun getEncodedLastName(lastName:String):String {
        val (consonants, vowels) = getLetters(lastName)
        return (consonants + vowels).toUpperCase().padEnd(3,'X').take(3)
    }

    fun getEncodesYearOfBirth(date: LocalDate):String = date.year.toString().takeLast(2)

    fun getEncodedMonthOfBirth(date: LocalDate):String = lettersForMonths[date.monthValue -1].toString()


    fun getEncodedDayOfBirth(date: LocalDate, sex: String):String{
        return when(sex) {
            "F" -> date.dayOfMonth.plus(40).toString()
            else -> date.dayOfMonth.toString().padStart(2,'0')
        }
    }

    fun getSex():String = ""

    fun cityOfBirth(): String = ""

    fun checkDigit(): String = ""

    private fun checkValues(person: Person) {
        try {
            dateOfPerson = LocalDate.parse(person.dateOfBirth)
        } catch (e: Exception) {
            throw IllegalArgumentException("Filed $person.dateOfBirth field is not valid")
        }

        if (person.firstName.isEmpty()) throw IllegalArgumentException("Filed $person.firstName field is required")
    }
}