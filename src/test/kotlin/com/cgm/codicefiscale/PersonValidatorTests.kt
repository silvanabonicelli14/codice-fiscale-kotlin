package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate

class PersonValidatorTests {

    @ParameterizedTest(name = "encodeLastName function should return Exception with {0}-{1}-{2}-{3}-{4}")
    @MethodSource("wrongPersonArguments")
    fun `validatePerson from input line with empty value throws exception`(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        genre: String,
        cityOfBirth: String
    ) {
        assertThrows<IllegalArgumentException> { Person.of(firstName, lastName, dateOfBirth, genre, cityOfBirth) }
    }

    @Test
    fun `validatePerson from input line with not valid Genre  throws exception`() {
        assertThrows<IllegalArgumentException> {
            Person.of(
                "Silvana",
                "bonicelli",
                "ssss",
                "1977-05-01",
                "Breno"
            )
        }
    }
    @Test
    fun `validatePerson from input line with not valid date throws exception`() {
        assertThrows<IllegalArgumentException> {
            Person.of(
                "Silvana",
                "bonicelli",
                "F",
                "19774584",
                "Breno"
            )
        }
    }

    @Test
    fun `validatePerson from input line with date major than today throws exception`() {
        assertThrows<IllegalArgumentException> {
            Person.of(
                "Silvana",
                "bonicelli",
                "F",
                LocalDate.now().plusDays(1).toString(),
                "Breno"
            )
        }
    }

    @Test
    fun `validatePerson from input line with dateof birth today return OK`() {
        val result = Person.of("Silvana", "bonicelli", "F", LocalDate.now().toString(),"Breno")
        Assertions.assertTrue(result is Person)
    }

    @Test
    fun `validatePerson from input line Happy Path`() {
        val result = Person.of("Silvana", "bonicelli", "F", "1977-05-01","Breno")
        Assertions.assertTrue(result is Person)
    }

    companion object {
        @JvmStatic
        fun wrongPersonArguments(): List<Arguments> =
            listOf(
                Arguments.of("", "", "", "", ""),
                Arguments.of("", "Bonicelli", "F", "1977-05-01", "Breno"),
                Arguments.of("Silvana", "","F", "1977-05-01",  "Breno"),
                Arguments.of("Silvana", "Bonicelli", "F", "", "Breno"),
                Arguments.of("Silvana", "Bonicelli", "", "1977-05-01", "Breno"),
                Arguments.of("Silvana", "Bonicelli", "F","1977-05-01",  "")
            )
    }
}