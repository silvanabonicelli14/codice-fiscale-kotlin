package com.cgm.codicefiscale.services

import com.cgm.codicefiscale.interfaces.IDataService

class SqLiteDataService: IDataService {
    override fun loadData(): List<Pair<String, String>> {
        val out = mutableListOf<Pair<String,String>>()
        out.add(Pair("Breno","B149"))
        out.add(Pair("Lovere","E704"))
        return out
    }
}