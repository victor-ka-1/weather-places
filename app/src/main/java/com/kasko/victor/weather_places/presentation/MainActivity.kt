package com.kasko.victor.weather_places.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kasko.victor.weather_places.presentation.forecast.ForecastViewModel
import com.kasko.victor.weather_places.presentation.navigation.NavGraph
import com.kasko.victor.weather_places.presentation.ui.theme.WeatherplacesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val forecastVieModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherplacesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {
                    NavGraph(
                        forecastViewModel = forecastVieModel
                    )
                }
            }
        }
    }
}
