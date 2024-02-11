package com.kasko.victor.weather_places.data.mappers

import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDayDto
import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDto
import com.kasko.victor.weather_places.data.datasource.remote.model.TemperatureDto
import com.kasko.victor.weather_places.data.datasource.remote.model.WeatherDto
import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.domain.model.ForecastDay
import com.kasko.victor.weather_places.domain.model.Temperature
import com.kasko.victor.weather_places.domain.model.Weather

fun ForecastDto.toForecast(): Forecast =
    Forecast(
        city = city.toCity(),
        forecasts = forecastList.map { it.toForecastDay() }
    )

fun ForecastDayDto.toForecastDay() =
    ForecastDay(
        date = date,
        sunrise = sunrise,
        sunset = sunset,
        temperatures = temperatures.toTemperature(),
        feelsLike = feelsLike.toTemperature(),
        pressure = pressure,
        humidity = humidity,
        weather = weather.toWeather()
    )

fun TemperatureDto.toTemperature() = Temperature(day = day, night = night)

fun WeatherDto.toWeather() = Weather(weatherName = weatherName, weatherDescription =  weatherDescription)