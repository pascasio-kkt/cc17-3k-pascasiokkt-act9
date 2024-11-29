package com.example.flightsearchapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flightsearchapp.ui.screen.flightscreen.FlightScreen
import com.example.flightsearchapp.ui.screen.flightscreen.FlightScreenDestination
import com.example.flightsearchapp.ui.screen.search.SearchDestination
import com.example.flightsearchapp.ui.screen.search.SearchScreen

@Composable
fun FlightSearchApp() {
    val navController = rememberNavController()
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = SearchDestination.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = SearchDestination.route) {
                SearchScreen(
                    modifier = Modifier,
                    onSelectCode = {
                        navController.navigate("${FlightScreenDestination.route}/$it")
                    }
                )
            }
            composable(
                route = FlightScreenDestination.routeWithArgs,
                arguments = listOf(navArgument(FlightScreenDestination.CODE_ARG) {
                    type = NavType.StringType
                })
            ) { navBackStackEntry ->
                FlightScreen(navBackStackEntry)
            }
        }
    }
}
