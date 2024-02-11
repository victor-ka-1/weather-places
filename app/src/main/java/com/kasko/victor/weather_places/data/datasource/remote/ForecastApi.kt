package com.kasko.victor.weather_places.data.datasource.remote

import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val FORECAST_ENDPOINT =  "/data/2.5/forecast/daily" //todo move
private const val DAYS_COUNT_DEFAULT = 7 //todo move

interface ForecastApi {

    @GET(FORECAST_ENDPOINT)
    suspend fun getForecastForLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String = "4aff0d93fc6fb6fd2fd195632dc9bbc1", //todo move
        @Query("cnt") daysCount: Int = DAYS_COUNT_DEFAULT,
    ): ForecastDto

}


