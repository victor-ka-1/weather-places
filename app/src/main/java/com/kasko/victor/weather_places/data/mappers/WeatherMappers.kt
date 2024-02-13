package com.kasko.victor.weather_places.data.mappers

import android.text.format.DateFormat
import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDayDto
import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDto
import com.kasko.victor.weather_places.data.datasource.remote.model.TemperatureDto
import com.kasko.victor.weather_places.data.datasource.remote.model.WeatherDto
import com.kasko.victor.weather_places.domain.model.Forecast
import com.kasko.victor.weather_places.domain.model.ForecastDay
import com.kasko.victor.weather_places.domain.model.Temperature
import com.kasko.victor.weather_places.domain.model.Weather
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun ForecastDto.toForecast(): Forecast =
    Forecast(
        city = city.toCity(),
        forecasts = forecastList.map { it.toForecastDay() }
    )

fun ForecastDayDto.toForecastDay() =
    ForecastDay(
        date = convertToDate(dateMillis.toLong()),
        sunrise = convertToTime(sunrise.toLong()),
        sunset = convertToTime(sunset.toLong()),
        temperatures = temperatures.toTemperature(),
        feelsLike = feelsLike.toTemperature(),
        pressure = pressure,
        humidity = humidity,
        weather = weather.map { it.toWeather() }
    )

fun TemperatureDto.toTemperature() = Temperature(day = day, night = night)

fun WeatherDto.toWeather() = Weather(weatherName = weatherName, weatherDescription =  weatherDescription)

fun convertToDate(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000
    return DateFormat.format("dd-MM-yyyy", calendar).toString()
}

fun convertToTime(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp * 1000
    val formatter = SimpleDateFormat( "hh:mm", Locale.getDefault())
    return formatter.format(calendar.time)
}

