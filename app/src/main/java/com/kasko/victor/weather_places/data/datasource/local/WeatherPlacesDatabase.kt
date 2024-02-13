package com.kasko.victor.weather_places.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasko.victor.weather_places.data.datasource.local.model.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class WeatherPlacesDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}