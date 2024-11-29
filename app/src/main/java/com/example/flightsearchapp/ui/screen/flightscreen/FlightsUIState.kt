package com.example.flightsearchapp.ui.screen.flightscreen

import com.example.flightsearchapp.model.Airport
import com.example.flightsearchapp.model.Favorite

data class FlightsUiState(
    val code: String = "",
    val favoriteList: List<Favorite> = emptyList(),
    val destinationList: List<Airport> = emptyList(),
    val departureAirport: Airport? = null
)
