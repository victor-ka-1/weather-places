package com.kasko.victor.weather_places.domain.repository

import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getForecastForLocation(latitude: Double, longitude: Double): Resource<Forecast>
    suspend fun getSavedLocations(): Flow<Resource<List<City>>>
    suspend fun addCity(city: City)
}