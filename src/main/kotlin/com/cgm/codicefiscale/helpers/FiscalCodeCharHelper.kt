package com.cgm.codicefiscale.helpers

const val  lettersForMonths = "ABCDEHLMPRST"

val charForOdd = mapOf(
    "0" to 1, "1" to 0, "2" to 5, "3" to 7, "4" to 9, "5" to 13,
    "6" to 15, "7" to 17, "8" to 19,"9" to 21, "A" to 1, "B" to 0,
    "C" to 5, "D" to 7, "E" to 9,"F" to 13, "G" to 15, "H" to 17,
    "I" to 19, "J" to 21, "K" to 2,"L" to 4, "M" to 18, "N" to 20,
    "O" to 11, "P" to 3, "Q" to 6,"R" to 8, "S" to 12, "T" to 14,
    "U" to 16, "V" to 10, "W" to 22,"X" to 25, "Y" to 24, "Z" to 23
)

val charForEven = mapOf(
    "0" to 0, "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5,
    "6" to 6, "7" to 7, "8" to 8, "9" to 9, "A" to 0, "B" to 1,
    "C" to 2, "D" to 3, "E" to 4, "F" to 5, "G" to 6, "H" to 7,
    "I" to 8, "J" to 9, "K" to 10, "L" to 11, "M" to 12, "N" to 13,
    "O" to 14, "P" to 15, "Q" to 16, "R" to 17, "S" to 18, "T" to 19,
    "U" to 20, "V" to 21, "W" to 22, "X" to 23, "Y" to 24, "Z" to 25
)

val digitChar = mapOf(
    0 to "A", 1 to "B", 2 to "C", 3 to "D", 4 to "E", 5 to "F",
    6 to "G", 7 to "H", 8 to "I", 9 to "J", 10 to "K", 11 to "L",
    12 to "M", 13 to "N", 14 to "O", 15 to "P", 16 to "Q", 17 to "R",
    18 to "S", 19 to "T", 20 to "U", 21 to "V", 22 to "W", 23 to "X",
    24 to "Y", 25 to "Z"
)


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
