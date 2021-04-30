package com.cgm.codicefiscale

import com.cgm.codicefiscale.services.CsvDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CsvDataServiceTests {
    private var sut = CsvDataService()

    @Test
    fun `Fiscal Code for Person with empty name`() {
        val result = sut.loadCountryCode()
        Assertions.assertEquals(7904, result.size)
    }
}