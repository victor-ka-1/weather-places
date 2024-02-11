package com.kasko.victor.weather_places.utils

object NetworkConstants {
    //it's not fine to store these like that. todo refactor  and move it out
    const val FORECAST_ENDPOINT =  "/data/2.5/forecast/daily"
    const val DAYS_COUNT_DEFAULT = 7
    const val BASE_URL: String = "https://api.openweathermap.org"
    const val API_KEY = "4aff0d93fc6fb6fd2fd195632dc9bbc1"
}