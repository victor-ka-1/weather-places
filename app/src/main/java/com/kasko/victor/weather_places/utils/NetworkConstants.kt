package com.kasko.victor.weather_places.utils

object NetworkConstants {

    //do not store it like that. todo refactor
    const val API_KEY = "4aff0d93fc6fb6fd2fd195632dc9bbc1"
    const val FORECAST_DAILY_ENDPOINT = "/data/2.5/forecast/daily"
    const val FORECAST_CITY_ENDPOINT = "/data/2.5/forecast"
    const val DAYS_COUNT_DEFAULT = 7
    const val LANGUAGE_DEFAULT = "en"
    const val UNITS_DEFAULT = "metric"
    const val CITY_WEATHER_DEFAULT = 1
    const val BASE_URL: String = "https://api.openweathermap.org"
}