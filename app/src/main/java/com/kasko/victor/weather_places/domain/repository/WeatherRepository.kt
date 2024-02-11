package com.kasko.victor.weather_places.domain.repository

import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.utils.Resource

interface WeatherRepository {
    suspend fun getForecastForPlace(latitude: Double, longitude: Double): Resource<Forecast>
}