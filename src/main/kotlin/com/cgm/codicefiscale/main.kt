package com.cgm.codicefiscale


import java.io.File


fun main() {
    try {
        val fileName = getResourceAsText("Elenco-comuni-italiani.csv");
        var lines: List<String> = File(fileName).readLines()


    }catch (e:Exception){
        e.printStackTrace()
    }finally {
        println("Finish!")
    }

}

fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}

