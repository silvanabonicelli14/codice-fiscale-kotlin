package com.cgm.codicefiscale.factories

import com.cgm.codicefiscale.interfaces.IDataService
import com.cgm.codicefiscale.services.CsvDataService
import com.cgm.codicefiscale.services.SqLiteDataService

const val ENV_MOD_LOAD_DATA: String  = "CFDATASERVICE"

class DataServiceFactory {
    fun getDataService(dataServiceMode: String?): IDataService {
        return when (dataServiceMode.isNullOrEmpty()) {
            true -> { DataServiceFactory().getDataServiceFromEnvironment() }
            false -> { DataServiceFactory().getDataServiceByModelValue(dataServiceMode)}
        }
    }

    fun getDataServiceFromEnvironment(variableName: String = ENV_MOD_LOAD_DATA): IDataService {
        (System.getenv(variableName)?.toUpperCase()?: DataService.CSV.toString()).apply {
            return getDataServiceByModelValue(this)
        }
    }

    private fun getDataServiceByModelValue(value: String): IDataService {
        return when (value.toUpperCase()) {
            DataService.CSV.toString() -> {CsvDataService()}
            DataService.SQLITE.toString() -> {SqLiteDataService()}
            else -> { error("Invalid dataService")}
        }
    }
}

enum class DataService{
    CSV,
    SQLITE
}