package com.kasko.victor.weather_places.data.utils

import android.util.Log
import androidx.annotation.DrawableRes
import com.kasko.victor.weather_places.R
import com.kasko.victor.weather_places.domain.model.Weather

object WeatherVariants {

    const val CLEAR_SKY = "clear sky"
    const val FEW_CLOUDS = "few clouds"
    const val CLOUDS = "clouds"
    const val SCATTERED_CLOUDS = "scattered clouds"
    const val BROKEN_CLOUDS = "broken clouds"
    const val SHOWER_RAIN = "shower rain"
    const val RAIN = "rain"
    const val THUNDERSTORM = "thunderstorm"
    const val SNOW = "snow"
    const val MIST = "mist"
}

@DrawableRes
fun Weather.getIconForWeather(): Int {
   return when (weatherName.lowercase()) {
        WeatherVariants.CLEAR_SKY -> R.drawable.clear_sky
        WeatherVariants.FEW_CLOUDS -> R.drawable.few_clouds
        WeatherVariants.SCATTERED_CLOUDS -> R.drawable.cloud
        WeatherVariants.BROKEN_CLOUDS -> R.drawable.cloud
        WeatherVariants.CLOUDS -> R.drawable.cloud
        WeatherVariants.SHOWER_RAIN -> R.drawable.shower_rain
        WeatherVariants.RAIN -> R.drawable.rain
        WeatherVariants.THUNDERSTORM -> R.drawable.thunderstorm
        WeatherVariants.SNOW -> R.drawable.snow
        WeatherVariants.MIST -> R.drawable.mist

       else ->  R.drawable.clear_sky
   }
}