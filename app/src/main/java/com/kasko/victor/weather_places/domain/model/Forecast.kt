package com.kasko.victor.weather_places.domain.model

data class Forecast(
    val city: City,
    val forecasts: List<ForecastDay>
)