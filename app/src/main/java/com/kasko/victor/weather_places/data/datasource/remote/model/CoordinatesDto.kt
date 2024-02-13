package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class CoordinatesDto(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
