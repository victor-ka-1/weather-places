package com.kasko.victor.weather_places.data.repository

import com.kasko.victor.weather_places.data.datasource.remote.ForecastRemoteDataSource
import com.kasko.victor.weather_places.data.mappers.toForecast
import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import com.kasko.victor.weather_places.utils.Resource
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource
) : WeatherRepository {

    override suspend fun getForecastForPlace(latitude: Double, longitude: Double): Resource<Forecast> {
        return try {
            Resource.Success(
                remoteDataSource.getForecastForLocation(
                    latitude = latitude, longitude = longitude
                ).toForecast()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error happened") //todo remove hardcoded string
        }
    }
}