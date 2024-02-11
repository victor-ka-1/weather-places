package com.kasko.victor.weather_places.domain.model

data class ForecastDay(
    val date: String,
    val sunrise: Long,
    val sunset: Long,
    val temperatures: Temperature,
    val feelsLike: Temperature,
    val pressure: Double,
    val humidity: Int,
    val weather: Weather
)
