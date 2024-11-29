package com.example.flightsearchapp.ui.screen.flightscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.model.Airport
import com.example.flightsearchapp.model.Favorite
import com.example.flightsearchapp.ui.screen.flightscreen.FlightRow

@Composable
fun FlightResults(
    modifier: Modifier = Modifier,
    departureAirport: Airport?,
    destinationList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        departureAirport?.let { airport ->
            Text(text = "Departure Airport: ${airport.name}")
            Text(text = "Code: ${airport.code}")
            Text(text = "Passengers: ${airport.passengers}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Destinations:")

        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(destinationList, key = { it.id }) { item ->
                val isFavorite = favoriteList.find { f ->
                    f.departureCode == departureAirport?.code && f.destinationCode == item.code
                }

                FlightRow(
                    isFavorite = isFavorite != null,
                    departureAirportCode = departureAirport?.code.orEmpty(),
                    departureAirportName = departureAirport?.name.orEmpty(),
                    destinationAirportCode = item.code,
                    destinationAirportName = item.name,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Preview
@Composable
fun FlightResultsPreview() {
    val mockData = listOf(
        Airport(id = 1, code = "JFK", name = "John F. Kennedy", passengers = 1000),
        Airport(id = 2, code = "LAX", name = "Los Angeles", passengers = 2000)
    )

    FlightResults(
        departureAirport = mockData.firstOrNull(),
        destinationList = mockData,
        favoriteList = emptyList(),
        onFavoriteClick = { _, _ -> }
    )
}
