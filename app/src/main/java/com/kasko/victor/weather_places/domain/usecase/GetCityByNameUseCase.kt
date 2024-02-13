package com.kasko.victor.weather_places.domain.usecase

import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import com.kasko.victor.weather_places.utils.Resource
import javax.inject.Inject

class GetCityByNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getCityByName(cityName: String): Resource<City> =
        weatherRepository.getCityByName(cityName)

}