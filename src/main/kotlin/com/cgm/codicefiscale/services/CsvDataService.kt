package com.cgm.codicefiscale.services

import com.cgm.codicefiscale.interfaces.IDataService
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File

class CsvDataService: IDataService {
    override fun loadData(): List<Pair<String, String>> {
//        val out = mutableListOf<Pair<String,String>>()
//        try {
//
//            val csvReader = CsvDataService::class.java.getResource("Elenco-comuni-italiani.csv")
//                ?.let {
//                    val csvParser = CSVParser(File(it.toURI()).bufferedReader(), CSVFormat.DEFAULT)
//                    for (csvRecord in csvParser) {
//
////            out.add(Pair( csvRecord[5], csvRecord[19]))
////            val name = csvRecord[5]
////            val email = csvRecord[19]
//
//                        println("Phone : $csvRecord")
////            println("Country : $email")
//                        println("---------------\n\n")
//                    }
//
//                }
//                ?: error("Path not found")
//        }catch (e:Exception){
//            e.printStackTrace()
//        }finally {
//            return out
//        }
        val out = mutableListOf<Pair<String,String>>()
        out.add(Pair("Breno","B149s"))
        return out
    }
}