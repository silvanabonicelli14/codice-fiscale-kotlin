package com.cgm.codicefiscale.services

import CONN_STRING_SQLITE
import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.interfaces.IDataService
import org.sql2o.Sql2o

class SqLiteDataService: IDataService {

    override fun loadCountryCode(): List<CountryCode> {
        val sql2o = Sql2o(CONN_STRING_SQLITE, null, null)
        sql2o.open().use { con ->
            return con.createQuery("SELECT field6 as country, field20 as code FROM ElencoComuniItaliani")
                .executeAndFetch(CountryCode::class.java)
        }
    }
}