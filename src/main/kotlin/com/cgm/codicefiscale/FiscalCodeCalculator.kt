package com.cgm.codicefiscale

import java.lang.IllegalArgumentException

class FiscalCodeCalculator {

    fun getFiscalCode(person: Person): String {
        return person.let {
            checkValues(person)
            calculateFiscalCode(person)
        }
    }

    private fun calculateFiscalCode(person: Person): String {
        var fiscalCode = ""

        fiscalCode += encodeFirstName(person.firstName)
        fiscalCode += encodeLastName(person.lastName)
        return fiscalCode
    }

    private fun checkValues(person: Person) {
        if (person.firstName.isNullOrEmpty()) throw IllegalArgumentException("Filed field is required")
    }

    fun encodeFirstName(firstName: String):String {
        var (consonants, vowels) = getLetters(firstName)

        consonants.takeIf {
            if (it.length >= 4 ) return consonants[0].toString() + consonants[2].toString() + consonants[3].toString()
            return  (consonants + vowels).toUpperCase().padStart(3,'X').take(3)
        }
    }

    fun encodeLastName(lastName:String):String {
        var (consonants, vowels) = getLetters(lastName)
        return (consonants + vowels).toUpperCase().padStart(3,'X').take(3)
    }

    fun getYearOfBirth():String = ""

    fun getMonthOfBirth():String = ""

    fun getDayOfBirth():String = ""

    fun getSex():String = ""

    fun cityOfBirth(): String = ""

    fun checkDigit(): String = ""

    private fun getLetters(lastName: String): Pair<String, String> {
        var consonants = ""
        var vowels = ""
        lastName.trim().toLowerCase().forEach {
            when (it) {
                'a', 'e', 'i', 'o', 'u' -> vowels += it.toString()
                in 'a'..'z' -> consonants += it.toString()
            }
        }
        return Pair(consonants, vowels)
    }
}