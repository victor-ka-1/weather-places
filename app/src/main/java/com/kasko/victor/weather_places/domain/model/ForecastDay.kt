package com.kasko.victor.weather_places.domain.model

data class ForecastDay(
    val date: String,
    val sunrise: String,
    val sunset: String,
    val temperatures: Temperature,
    val feelsLike: Temperature,
    val pressure: Double,
    val humidity: Int,
    val weather: List<Weather>
)
