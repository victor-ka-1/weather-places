package com.kasko.victor.weather_places.presentation.forecast

import com.kasko.victor.weather_places.domain.model.Forecast

sealed interface ForecastState {
    data class Success(val forecast: Forecast) : ForecastState
    data class Error(val errorMessage: String) : ForecastState
    data object Loading : ForecastState
}