package com.kasko.victor.weather_places.domain.usecase

import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import javax.inject.Inject

class AddCityUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: City) = repository.addCity(city)
}