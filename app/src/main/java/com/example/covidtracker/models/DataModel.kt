package com.example.covidtracker.models

data class DataModel(
        private var stateName: String,
        private var activeCases: Int,
        private var recovered: Int,
        private var death: Int
){

    fun getStateName(): String {
        return stateName
    }
    fun getActivecases(): Int {
        return activeCases
    }
    fun getRecovered(): Int {
        return recovered
    }
    fun getDeaths(): Int {
        return death
    }
    fun setStateName(value: String) {
         this.stateName = value
    }
    fun setActivecases(value: Int) {
        this.activeCases = value
    }
    fun setRecovered(value: Int) {
        this.recovered = value
    }
    fun setDeaths(value: Int) {
        this.death = value
    }



}
