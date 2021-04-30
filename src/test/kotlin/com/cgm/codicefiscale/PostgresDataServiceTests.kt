package com.cgm.codicefiscale

import com.cgm.codicefiscale.services.PostgresDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PostgresDataServiceTests {
    private var sut = PostgresDataService()

    @Test
    fun `Fiscal Code for Person with empty name`() {
        val result = sut.loadCountryCode()
        Assertions.assertEquals(7904, result.size)
    }
}