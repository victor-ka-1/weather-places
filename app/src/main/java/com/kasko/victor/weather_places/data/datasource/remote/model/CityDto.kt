package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("country") val country: String,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("name") val cityName: String,
    @SerializedName("coord") val coordinate: CoordinatesDto
)
