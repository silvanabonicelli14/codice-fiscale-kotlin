package com.cgm.codicefiscale.interfaces

interface IDataService {
    fun loadData():List<Pair<String,String>>
}