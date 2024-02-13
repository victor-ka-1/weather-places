package com.kasko.victor.weather_places.data.mappers

import com.kasko.victor.weather_places.data.datasource.remote.model.CityDto
import com.kasko.victor.weather_places.data.datasource.remote.model.CoordinatesDto
import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Coordinates

fun CityDto.toCity(): City {
    return City(
        country = country,
        timezone = timezone,
        cityName = cityName,
        coordinate = coordinate.toModel()
    )
}

fun CoordinatesDto.toModel(): Coordinates =
    Coordinates(
        latitude = latitude,
        longitude = longitude
    )

