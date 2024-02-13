package com.kasko.victor.weather_places.data.datasource.remote

import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDto
import com.kasko.victor.weather_places.data.datasource.remote.model.SearchedCityDto
import com.kasko.victor.weather_places.utils.NetworkConstants.DAYS_COUNT_DEFAULT
import com.kasko.victor.weather_places.utils.NetworkConstants.FORECAST_DAILY_ENDPOINT
import com.kasko.victor.weather_places.utils.NetworkConstants.API_KEY
import com.kasko.victor.weather_places.utils.NetworkConstants.CITY_WEATHER_DEFAULT
import com.kasko.victor.weather_places.utils.NetworkConstants.FORECAST_CITY_ENDPOINT
import com.kasko.victor.weather_places.utils.NetworkConstants.LANGUAGE_DEFAULT
import com.kasko.victor.weather_places.utils.NetworkConstants.UNITS_DEFAULT
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET(FORECAST_DAILY_ENDPOINT)
    suspend fun getForecastForLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String = API_KEY, //todo move
        @Query("cnt") daysCount: Int = DAYS_COUNT_DEFAULT,
        @Query("lang") language: String = LANGUAGE_DEFAULT,
        @Query("units") units: String = UNITS_DEFAULT
    ): ForecastDto

    @GET(FORECAST_CITY_ENDPOINT)
    suspend fun getCityByName(
        @Query("q") cityName: String,
        @Query("APPID") apiKey: String = API_KEY,
        @Query("units") units: String = UNITS_DEFAULT,
        @Query("cnt") count: Int = CITY_WEATHER_DEFAULT
    ): SearchedCityDto

}


