package com.example.flightsearchapp.data

import com.example.flightsearchapp.model.Airport
import com.example.flightsearchapp.model.Favorite
import kotlinx.coroutines.flow.Flow

class FlightRepositoryImpl(private val flightDao: FlightDao) : FlightRepository {

    override fun getAllAirportsFlow(): Flow<List<Airport>> = flightDao.getAllAirportsFlow()

    override fun getAllAirportsFlow(query: String): Flow<List<Airport>> = flightDao.getAllAirportsFlow(query)

    override fun getAirportByCodeFlow(code: String): Flow<Airport> = flightDao.getAirportByCodeFlow(code)

    override suspend fun getAllAirports(): List<Airport> = flightDao.getAllAirports()

    override suspend fun getAllAirports(query: String): List<Airport> = flightDao.getAllAirports(query)

    override suspend fun getAirportByCode(code: String): Airport = flightDao.getAirportByCode(code)

    override suspend fun getAirportById(id: Int): Airport = flightDao.getAirportById(id)

    override fun getAllFavoritesFlightsFlow(): Flow<List<Favorite>> = flightDao.getAllFavoritesFlow()

    override suspend fun getAllFavoritesFlights(): List<Favorite> = flightDao.getAllFavorites()

    override suspend fun insertFavoriteFlight(flight: Favorite) = flightDao.insertFavoriteFlight(flight)

    override suspend fun deleteFavoriteFlight(flight: Favorite) = flightDao.deleteFavoriteFlight(flight)

    override suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite? {
        return flightDao.getSingleFavorite(departureCode, destinationCode)
    }
}
