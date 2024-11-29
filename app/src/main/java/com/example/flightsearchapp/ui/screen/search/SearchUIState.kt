package com.example.flightsearchapp.ui.screen.search

import com.example.flightsearchapp.model.Airport
import com.example.flightsearchapp.model.Favorite

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCode: String = "",
    val airportList: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList(),
)