package com.cgm.codicefiscale.services

import CONN_STRING_POSTGRE
import CONN_STRING_PWD
import CONN_STRING_USER
import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.interfaces.IDataService
import org.sql2o.Sql2o

class PostgresDataService: IDataService {
    override fun loadCountryCode(): List<CountryCode> {
            val sql2o =
                Sql2o(CONN_STRING_POSTGRE, CONN_STRING_USER, CONN_STRING_PWD)
            sql2o.open().use { con ->
                return con.createQuery("SELECT field1 as country, field2 as code FROM elencocomuniitaliani")
                    .executeAndFetch(CountryCode::class.java)
            }
    }
}