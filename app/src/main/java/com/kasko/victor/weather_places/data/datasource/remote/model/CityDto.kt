package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("name") val cityName: String,
    @SerializedName("coord") val coordinate: CoordinatesDto,
    @SerializedName("country") val country: String,
    @SerializedName("timezone") val timezone: Int,
)

data class SearchedCityDto(
    @SerializedName("city") val city: CityDto,
    @SerializedName("cod") val code: String,
)
