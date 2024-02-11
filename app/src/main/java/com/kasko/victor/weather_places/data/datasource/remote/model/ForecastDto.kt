package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("dt") val date: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
    @SerializedName("temp") val temperatures: TemperatureDto,
    @SerializedName("feels_like") val feelsLike: TemperatureDto,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weather: WeatherDto,
)