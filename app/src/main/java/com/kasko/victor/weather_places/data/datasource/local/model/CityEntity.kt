package com.kasko.victor.weather_places.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Coordinates

@Entity(tableName = "city_entity")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val uid: Int = 0,

    var cityName: String,
    var country: String,
    var timezone: Int,

    @Embedded
    val coordinates: Coordinates
)

fun CityEntity.toCity(): City =
    City(
        cityName = cityName,
        coordinate = coordinates,
        country = country,
        timezone = timezone
    )

fun City.toCityEntity() =
    CityEntity(
        cityName = cityName,
        country = country,
        timezone = timezone,
        coordinates = coordinate
    )