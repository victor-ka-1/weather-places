package com.kasko.victor.weather_places.domain.usecase

import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import com.kasko.victor.weather_places.utils.Resource
import javax.inject.Inject

class GetWeatherForecastForLocationUseCase
@Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun getWeatherForecastForLocation(latitude: Double, longitude: Double): Resource<Forecast> =
        weatherRepository.getForecastForLocation(latitude, longitude)
}