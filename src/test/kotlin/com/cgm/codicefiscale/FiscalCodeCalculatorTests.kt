package com.cgm.codicefiscale

import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.services.PostgresDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalStateException

class FiscalCodeCalculatorTests {
    private var sut = FiscalCodeCalculator(PostgresDataService())

    @ParameterizedTest(name = "Calculate CF function should return {1}")
    @MethodSource("encodeCfArguments")
    fun `Fiscal Code for Person Happy Path`(person: Person, expected: String) {
        Assertions.assertEquals(expected, sut.getFiscalCode(person))
    }

    @Test
    fun `Fiscal Code for Person with wrong Country of birth throws Exception`() {
        val person = Person.of("sasas","sasasas","F","1977-05-01","sasas")
        val exception = assertThrows<IllegalStateException>{sut.getFiscalCode(person)}
        exception.message?.let { Assertions.assertTrue(it.contains("city of birth not founded")) }
    }

    companion object {
        @JvmStatic
        fun encodeCfArguments(): List<Arguments> =
            listOf(
                Arguments.of(
                    Person.of("Silvana", "Bonicelli", "F", "1977-05-01", "Breno"),
                    "BNCSVN77E41B149S"
                ),
                Arguments.of(
                    Person.of("Alessandro", "Fiorini", "M", "1976-09-09", "Lovere"),
                    "FRNLSN76P09E704H"
                )
            )
    }
}