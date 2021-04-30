package com.cgm.codicefiscale.services

import CONN_STRING_CSV
import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.interfaces.IDataService
import java.io.File

class CsvDataService : IDataService {

    override fun loadCountryCode(): List<CountryCode> {
        val listOfCodeCountries = mutableListOf<CountryCode>()
        try {
//          val fileName = CsvDataService::class.java.getResource(CONN_STRING_CSV) //Non Funziona questo :-(
            File(CONN_STRING_CSV).forEachLine { line ->
                line.takeIf { (it.split(";")).size >= 19 }
                    ?.let { listOfCodeCountries.add(CountryCode(it.split(";")[5], it.split(";")[19])) }
            }
            return listOfCodeCountries
        } catch (e: Exception) {
            throw e
        }
    }
}