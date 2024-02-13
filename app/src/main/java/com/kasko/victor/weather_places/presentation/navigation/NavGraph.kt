package com.kasko.victor.weather_places.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kasko.victor.weather_places.presentation.forecast.ForecastDetailsScreen
import com.kasko.victor.weather_places.presentation.forecast.ForecastScreen
import com.kasko.victor.weather_places.presentation.forecast.ForecastViewModel
import com.kasko.victor.weather_places.presentation.locations.LocationsScreen

@Composable
fun NavGraph(
    startDestination: String = Routes.locationsRoute,
    forecastViewModel: ForecastViewModel
) {
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavScreen.LocationScreen.route) {
                LocationsScreen() {
                    navController.navigate(NavScreen.ForecastScreen.route + "/${it.latitude}/${it.longitude}")
                }
            }
            composable(
                route = NavScreen.ForecastScreen.route + "/{$latitude_param}" + "/{$longitude_param}",
                arguments = listOf(
                    navArgument(name = latitude_param) {
                        type = NavType.FloatType
                    },
                    navArgument(name = longitude_param) {
                        type = NavType.FloatType
                    }
                )
            ) {
                val latitude = it.arguments?.getFloat(latitude_param) ?: 0f
                val longitude = it.arguments?.getFloat(longitude_param) ?: 0f
                ForecastScreen(viewModel = forecastViewModel, latitude = latitude, longitude = longitude) {
                    navController.navigate(NavScreen.ForecastDetailsScreen.route + "/$it")
                }
            }
            composable(
                route = NavScreen.ForecastDetailsScreen.route + "/{$selected_day_index_param}",
                arguments = listOf(
                    navArgument(name = selected_day_index_param) {
                        type = NavType.IntType
                    },
                )
            ) {
                val selectedDayIndex = it.arguments?.getInt(selected_day_index_param) ?: 0
                ForecastDetailsScreen(viewModel = forecastViewModel, selectedDayIndex = selectedDayIndex)
            }
        }
    }
}

sealed class NavScreen(val route: String) {
    data object LocationScreen : NavScreen(Routes.locationsRoute)
    data object ForecastScreen : NavScreen(Routes.LocationForecastRoute)
    data object ForecastDetailsScreen : NavScreen(Routes.ForecastDetailsRoute)
}