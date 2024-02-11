package com.kasko.victor.weather_places.data.datasource.remote

import javax.inject.Inject

class ForecastRemoteDataSource
@Inject constructor(private val forecastApi: ForecastApi) {

    suspend fun getForecastForLocation(latitude: Double, longitude: Double) =
        forecastApi.getForecastForLocation(latitude, longitude)

}