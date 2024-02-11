package com.kasko.victor.weather_places.domain.model


data class City(
    val country: String,
    val timezone: Int,
    val cityName: String,
    val coordinate: Coordinates
)