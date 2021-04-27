package com.cgm.codicefiscale.factories

import com.cgm.codicefiscale.interfaces.IDataService
import com.cgm.codicefiscale.services.CsvDataService
import com.cgm.codicefiscale.services.SqLiteDataService

class DataServiceFactory {
    fun getDataServiceFromEnvironment(variableName: String = "CFDATASERVICE"): IDataService {
        return when (System.getenv(variableName)?.toUpperCase()?: DataService.CSV.toString()) {
            DataService.CSV.toString() -> {
                CsvDataService()
            }
            DataService.SQLITE.toString() -> {
                SqLiteDataService()
            }
            else -> { error("Invalid dataService")}
        }
    }

    fun getDataServiceByModeValue(value: String): IDataService {
        return when (value.toUpperCase()) {
            DataService.CSV.toString() -> {
                CsvDataService()
            }
            DataService.SQLITE.toString() -> {
                SqLiteDataService()
            }
            else -> { error("Invalid dataService")}
        }
    }

    fun getDataService(dataServiceMode: String?): IDataService {
        return when (dataServiceMode.isNullOrEmpty()) {
            true -> { DataServiceFactory().getDataServiceFromEnvironment() }
            false -> { DataServiceFactory().getDataServiceByModeValue(dataServiceMode) }
        }
    }
}

enum class DataService{
    CSV,
    SQLITE
}