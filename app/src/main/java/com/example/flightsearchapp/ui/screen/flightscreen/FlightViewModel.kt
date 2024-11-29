package com.example.flightsearchapp.ui.screen.flightscreen

import androidx.compose.runtime.*
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchapp.FlightApplication
import com.example.flightsearchapp.data.FlightRepository
import com.example.flightsearchapp.model.Airport
import com.example.flightsearchapp.model.Favorite
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlightViewModel(
    savedStateHandle: SavedStateHandle,
    val flightRepository: FlightRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FlightsUiState())
    val uiState: StateFlow<FlightsUiState> = _uiState

    private val airportCode: String = savedStateHandle[FlightScreenDestination.CODE_ARG] ?: ""

    var flightAdded: Boolean by mutableStateOf(false)

    init {
        viewModelScope.launch {
            processFlightList(airportCode)
        }
    }

    fun processFlightList(airportCode: String) {
        viewModelScope.launch {
            val favoriteFlights = flightRepository.getAllFavoritesFlights()
            val allAirports = flightRepository.getAllAirports()
            val departureAirport = allAirports.firstOrNull { it.code == airportCode }
            departureAirport?.let {
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        code = airportCode,
                        favoriteList = favoriteFlights,
                        destinationList = allAirports,
                        departureAirport = it
                    )
                }
            }
        }
    }

    fun addFavoriteFlight(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            val favorite = flightRepository.getSingleFavorite(departureCode, destinationCode)

            if (favorite == null) {
                val newFavorite = Favorite(
                    id = 0, // Default value for auto-generated ID
                    departureCode = departureCode,
                    destinationCode = destinationCode
                )
                flightAdded = true
                flightRepository.insertFavoriteFlight(newFavorite)
            } else {
                flightAdded = false
                flightRepository.deleteFavoriteFlight(favorite)
            }

            val favoriteFlights = flightRepository.getAllFavoritesFlights()
            _uiState.update { currentUiState ->
                currentUiState.copy(favoriteList = favoriteFlights)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                FlightViewModel(
                    this.createSavedStateHandle(),
                    flightRepository = application.container.flightRepository
                )
            }
        }
    }
}
