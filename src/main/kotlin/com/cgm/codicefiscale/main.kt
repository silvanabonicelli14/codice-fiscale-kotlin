package com.cgm.codicefiscale
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.FileReader
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths


fun main() {
    val out = mutableListOf<Pair<String,String>>()
    try {

        val reader: Reader = Files.newBufferedReader(Paths.get("D:\\Corsi formazione\\Kotlin\\codice-fiscale-kotlin\\target\\classes\\Elenco-comuni-italiani.csv"))
        val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

        for (csvRecord in csvParser) {

//            out.add(Pair( csvRecord[5], csvRecord[19]))
//            val name = csvRecord[5]
//            val email = csvRecord[19]

            println("Phone : $csvRecord")
//            println("Country : $email")
            println("---------------\n\n")
        }

    }catch (e:Exception){
        e.printStackTrace()
    }finally {
        println(out.size)
    }

}

