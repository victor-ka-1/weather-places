package com.kasko.victor.weather_places.data.repository

import com.kasko.victor.weather_places.data.datasource.local.WeatherPlacesLocalDataSource
import com.kasko.victor.weather_places.data.datasource.local.model.toCity
import com.kasko.victor.weather_places.data.datasource.local.model.toCityEntity
import com.kasko.victor.weather_places.data.datasource.remote.ForecastRemoteDataSource
import com.kasko.victor.weather_places.data.mappers.toCity
import com.kasko.victor.weather_places.data.mappers.toForecast
import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import com.kasko.victor.weather_places.utils.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl
@Inject constructor(
    private val remoteDataSource: ForecastRemoteDataSource,
    private val localDataSource: WeatherPlacesLocalDataSource
) : WeatherRepository {

    override suspend fun getForecastForLocation(latitude: Double, longitude: Double): Resource<Forecast> {
        return try {
            Resource.Success(
                remoteDataSource.getForecastForLocation(
                    latitude = latitude, longitude = longitude
                ).toForecast()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error happened") //todo remove hardcoded string
        }
    }

    override fun getSavedLocations(): Flow<List<City>> {
        return localDataSource.getSavedCities().map {
            it.map { it.toCity() }
        }
    }

    override suspend fun addCity(city: City) {
        localDataSource.addCity(
            city.toCityEntity()
        )
    }

    override suspend fun getCityByName(cityName: String): Resource<City> {
        return try {
            Resource.Success(
                remoteDataSource.getCityByName(cityName).city.toCity()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "unknown error")
        }
    }

    override suspend fun clearSavedCities() {
        localDataSource.clearSavedCities()
    }
}