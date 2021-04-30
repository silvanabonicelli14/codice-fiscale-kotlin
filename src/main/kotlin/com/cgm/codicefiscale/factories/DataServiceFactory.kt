package com.cgm.codicefiscale.factories

import ENV_MOD_LOAD_DATA
import com.cgm.codicefiscale.interfaces.IDataService
import com.cgm.codicefiscale.services.CsvDataService
import com.cgm.codicefiscale.services.PostgresDataService
import com.cgm.codicefiscale.services.SqLiteDataService

class DataServiceFactory {

    fun getDataServiceFromEnvironment(variableName: String = ENV_MOD_LOAD_DATA): IDataService {
        (System.getenv(variableName)?.toUpperCase()?: DataService.CSV.toString()).apply {
            return getDataServiceByModelValue(this)
        }
    }

    fun getDataService(dataServiceMode: String?): IDataService {
        return when (dataServiceMode.isNullOrEmpty()) {
            true -> { DataServiceFactory().getDataServiceFromEnvironment() }
            false -> { DataServiceFactory().getDataServiceByModelValue(dataServiceMode)}
        }
    }

    private fun getDataServiceByModelValue(value: String): IDataService {
        return when (value.toUpperCase()) {
            DataService.CSV.toString() -> {CsvDataService()}
            DataService.SQLITE.toString() -> {SqLiteDataService()}
            DataService.POSTGRE.toString() -> {PostgresDataService()}
            else -> { error("Invalid dataService")}
        }
    }
}
enum class DataService{
    CSV,
    SQLITE,
    POSTGRE
}