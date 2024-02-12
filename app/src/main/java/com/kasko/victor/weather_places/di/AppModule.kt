package com.kasko.victor.weather_places.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.kasko.victor.weather_places.data.datasource.local.CityDao
import com.kasko.victor.weather_places.data.datasource.local.WeatherPlacesDatabase
import com.kasko.victor.weather_places.data.datasource.remote.ForecastApi
import com.kasko.victor.weather_places.utils.NetworkConstants.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideForecastApi(): ForecastApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun bindCityDao(weatherPlacesDatabase: WeatherPlacesDatabase): CityDao = weatherPlacesDatabase.cityDao()

    @Provides
    @Singleton
    fun provideStockDatabase(@ApplicationContext context: Context): WeatherPlacesDatabase {
        return Room.databaseBuilder(
            context,
            WeatherPlacesDatabase::class.java,
            "weather_places.db"
        ).build()
    }
}