package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    @SerializedName("dt") val dateMillis: String,
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("temp") val temperatures: TemperatureDto,
    @SerializedName("feels_like") val feelsLike: TemperatureDto,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weather: List<WeatherDto>,
)