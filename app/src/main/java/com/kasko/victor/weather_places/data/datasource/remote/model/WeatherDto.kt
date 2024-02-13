package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("main") val weatherName: String,
    @SerializedName("description") val weatherDescription: String
)