package com.kasko.victor.weather_places.domain.usecase

import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCitiesFromDbUseCase
@Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getSavedLocations()
}