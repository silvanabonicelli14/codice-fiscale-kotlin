package com.cgm.codicefiscale.services

import com.cgm.codicefiscale.interfaces.IDataService
import java.io.File

class CsvDataService: IDataService {
    override fun loadData(): List<Pair<String, String>> {
        val listOfCodeCountries = mutableListOf<Pair<String,String>>()
        try {
            val fileName = "D:\\Corsi formazione\\Kotlin\\codice-fiscale-kotlin\\target\\classes\\Elenco-comuni-italiani.csv"
            //val fileName = CsvDataService::class.java.getResource("Elenco-comuni-italiani.csv").file
            File(fileName).forEachLine {
                try {
                    listOfCodeCountries.add(Pair(it.split(";")[5], it.split(";")[19]))
                }
                catch (e:Exception){}
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            return listOfCodeCountries
        }
    }
}