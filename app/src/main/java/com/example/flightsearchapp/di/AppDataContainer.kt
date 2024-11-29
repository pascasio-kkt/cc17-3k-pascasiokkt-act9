package com.example.flightsearchapp.di

import android.content.Context
import com.example.flightsearchapp.data.FlightDatabase
import com.example.flightsearchapp.data.FlightRepository
import com.example.flightsearchapp.data.FlightRepositoryImpl

interface AppContainer {
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val database: FlightDatabase by lazy {
        FlightDatabase.getDatabase(context)
    }

    private val flightDao = database.flightDao()

    override val flightRepository: FlightRepository by lazy {
        FlightRepositoryImpl(flightDao)
    }
}
