package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class TemperatureDto(
    @SerializedName("day") val day: Int,
    @SerializedName("night") val night: Int
)