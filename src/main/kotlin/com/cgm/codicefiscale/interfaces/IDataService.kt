package com.cgm.codicefiscale.interfaces

import com.cgm.codicefiscale.entities.CountryCode

interface IDataService{
    fun loadCountryCode(): List<CountryCode>
}