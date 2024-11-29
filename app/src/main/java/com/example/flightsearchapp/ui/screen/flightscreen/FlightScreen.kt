package com.example.flightsearchapp.ui.screen.flightscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.flightsearchapp.NavigationDestination
import com.example.flightsearchapp.R

object FlightScreenDestination : NavigationDestination {
    override val route = "flight_screen"
    override val titleRes = R.string.app_name
    const val CODE_ARG = "code"
    val routeWithArgs = "$route/{$CODE_ARG}"
}

@Composable
fun FlightScreen(navBackStackEntry: NavBackStackEntry) {
    val code = navBackStackEntry.arguments?.getString(FlightScreenDestination.CODE_ARG)
    val viewModel: FlightViewModel = viewModel(factory = FlightViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(code) {
        code?.let {
            viewModel.processFlightList(it)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        uiState.value.departureAirport?.let { departureAirport ->
            FlightResults(
                departureAirport = departureAirport,
                destinationList = uiState.value.destinationList,
                favoriteList = uiState.value.favoriteList,
                onFavoriteClick = { s1: String, s2: String ->
                    viewModel.addFavoriteFlight(s1, s2)
                    if (viewModel.flightAdded) {
                        Toast.makeText(context, "ADDED", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "DELETED", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}
