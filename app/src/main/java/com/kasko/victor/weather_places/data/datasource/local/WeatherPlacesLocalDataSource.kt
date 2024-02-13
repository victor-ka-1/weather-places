package com.kasko.victor.weather_places.data.datasource.local

import com.kasko.victor.weather_places.data.datasource.local.model.CityEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class WeatherPlacesLocalDataSource @Inject constructor(private val cityDao: CityDao) {

    suspend fun addCity(cityEntity: CityEntity) =
        cityDao.addCity(cityEntity)

    fun getSavedCities(): Flow<List<CityEntity>> = cityDao.getCitiesFlow()

    suspend fun clearSavedCities() = cityDao.clear()
}