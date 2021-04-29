package com.cgm.codicefiscale.services

import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.interfaces.IDataService
import org.sql2o.Sql2o

class PostgresDataService: IDataService {
    override fun loadCountryCode(): List<CountryCode> {
            val sql2o =
                Sql2o("postgresql://127.0.0.1:5432/oak", "silvana", "pass")
            sql2o.open().use { con ->
                return con.createQuery("SELECT field1 as country, field2 as code FROM elencocomuniitaliani")
                    .executeAndFetch(CountryCode::class.java)
            }
    }
}