package com.kasko.victor.weather_places.presentation.forecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kasko.victor.weather_places.R
import com.kasko.victor.weather_places.data.utils.getIconForWeather
import com.kasko.victor.weather_places.domain.model.ForecastDay

@Composable
fun ForecastDetailsScreen(viewModel: ForecastViewModel, selectedDayIndex: Int) {
    val forecastState by viewModel.forecastState.collectAsState()
    LaunchedEffect(true) {
        viewModel.setSelectedDayIndex(selectedDayIndex)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        ForecastDetailsScreenContent(viewModel, forecastState)
    }
}

@Composable
fun ForecastDetailsScreenContent(
    viewModel: ForecastViewModel,
    forecastState: ForecastState
) {
    if (forecastState is ForecastState.Success) {
        ForecastDetails(forecastState.forecast.forecasts[viewModel.selectedDayIndex])
    } else {
        Text(text = stringResource(R.string.error_happened_while_fetching_forecast), color = Color.Red)
    }
}

@Composable
fun ForecastDetails(
    forecastDay: ForecastDay
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val icon = forecastDay.weather[0].getIconForWeather()
        Text(text = forecastDay.date, fontSize = 28.sp, modifier = Modifier.padding(10.dp))
        Text(text = forecastDay.weather[0].weatherName, fontSize = 28.sp, modifier = Modifier.padding(10.dp))
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(text = forecastDay.weather[0].weatherDescription, fontSize = 21.sp, modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = stringResource(R.string.sunrise, forecastDay.sunrise),
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp))
            Text(text = stringResource(R.string.sunset, forecastDay.sunset),
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column {
                Text(text = stringResource(R.string.day_temp_c, forecastDay.temperatures.day),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(4.dp))
                Text(text = stringResource(R.string.feels_like_c, forecastDay.feelsLike.day),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(4.dp))
            }
            Column {
                Text(text = stringResource(R.string.night_temp_c, forecastDay.temperatures.night),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(4.dp))
                Text(text = stringResource(R.string.feels_like_c, forecastDay.feelsLike.night),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(4.dp))
            }

        }

        Text(text = stringResource(R.string.pressure, forecastDay.pressure),
            fontSize = 20.sp,
            modifier = Modifier.padding(4.dp))
        Text(text = stringResource(R.string.humidity, forecastDay.humidity),
            fontSize = 20.sp,
            modifier = Modifier.padding(4.dp))

    }
}