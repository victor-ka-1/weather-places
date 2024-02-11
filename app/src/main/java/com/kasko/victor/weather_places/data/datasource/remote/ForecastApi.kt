package com.kasko.victor.weather_places.data.datasource.remote

import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDayDto
import com.kasko.victor.weather_places.data.datasource.remote.model.ForecastDto
import com.kasko.victor.weather_places.utils.NetworkConstants.DAYS_COUNT_DEFAULT
import com.kasko.victor.weather_places.utils.NetworkConstants.FORECAST_ENDPOINT
import com.kasko.victor.weather_places.utils.NetworkConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query



interface ForecastApi {

    @GET(FORECAST_ENDPOINT)
    suspend fun getForecastForLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String = API_KEY, //todo move
        @Query("cnt") daysCount: Int = DAYS_COUNT_DEFAULT,
    ): ForecastDto

}


