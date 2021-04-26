package com.cgm.codicefiscale

const val  lettersForMonths = "ABCDEHLMPRST"

fun getLetters(lastName: String): Pair<String, String> {
    var consonants = ""
    var vowels = ""
    lastName.trim().toLowerCase().forEach {
        when (it) {
            'a', 'e', 'i', 'o', 'u' -> vowels += it
            in 'a'..'z' -> consonants += it
        }
    }
    return Pair(consonants, vowels)
}

