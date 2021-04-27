package com.cgm.codicefiscale.services

import com.cgm.codicefiscale.interfaces.IDataService

class DataServiceFactory {
        fun getDataServiceFromEnvironment(variableName: String = "CFDATASERVICE"): IDataService {
        return when (System.getenv(variableName) ?: "csv") {
            "csv" -> {CsvDataService()}
            "sqlite" -> {SqLiteDataService()}
            else -> { error("Invalid dataService")}
        }
    }
}