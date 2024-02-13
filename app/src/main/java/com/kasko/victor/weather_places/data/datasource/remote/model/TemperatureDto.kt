package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class TemperatureDto(
    @SerializedName("day") val day: Double,
    @SerializedName("night") val night: Double
)