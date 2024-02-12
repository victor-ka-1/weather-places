package com.kasko.victor.weather_places.data.datasource.local

import com.kasko.victor.weather_places.data.datasource.local.model.CityEntity
import javax.inject.Inject

class WeatherPlacesLocalDataSource @Inject constructor(private val cityDao: CityDao) {

    suspend fun addCity(cityEntity: CityEntity) =
        cityDao.addCity(cityEntity)

    suspend fun getSavedCities(): List<CityEntity> = cityDao.getCitiesFlow()
}