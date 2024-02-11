package com.kasko.victor.weather_places.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("city") val city: CityDto,
    @SerializedName("list") val forecastList: List<ForecastDayDto>
)
