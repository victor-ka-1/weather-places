package com.kasko.victor.weather_places.presentation.locations

import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Forecast

sealed interface CitiesState {
    data class Success(val cities: List<City>): CitiesState
    data class  Error(val errorMessage: String): CitiesState
    data object Loading: CitiesState
}

sealed interface SearchedCityState {
    data class Success(val city: City): SearchedCityState
    data class  Error(val errorMessage: String): SearchedCityState
    data object Idle: SearchedCityState
    data object Loading: SearchedCityState
}