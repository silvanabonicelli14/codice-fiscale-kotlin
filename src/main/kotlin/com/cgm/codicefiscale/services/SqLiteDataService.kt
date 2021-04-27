package com.cgm.codicefiscale.services

import com.cgm.codicefiscale.entities.CountryCode
import com.cgm.codicefiscale.interfaces.IDataService
import org.sql2o.Sql2o

class SqLiteDataService: IDataService {

    override fun loadData(): List<CountryCode> {
        val sql2o =
            Sql2o("jdbc:sqlite:D:\\Corsi formazione\\Kotlin\\codice-fiscale-kotlin\\src\\main\\resources\\dbCodiciComuni.db", null, null)
        sql2o.open().use { con ->
            return con.createQuery("SELECT field6 as country, field20 as code FROM ElencoComuniItaliani")
                .executeAndFetch(CountryCode::class.java)
        }
    }
}