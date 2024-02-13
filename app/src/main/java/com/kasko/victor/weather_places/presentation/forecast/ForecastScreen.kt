package com.kasko.victor.weather_places.presentation.forecast

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kasko.victor.weather_places.data.utils.getIconForWeather
import com.kasko.victor.weather_places.domain.model.ForecastDay
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel,
    latitude: Float,
    longitude: Float,
    onForecastDayClick: (index: Int) -> Unit
) {
    LaunchedEffect(true) {
        viewModel.getForecastForLocation(latitude, longitude)
    }
    val forecastState by viewModel.forecastState.collectAsState()
    ForecastScreenContent(forecastState, onForecastDayClick)
}

@Composable
fun ForecastScreenContent(
    forecastState: ForecastState,
    onForecastDayClick: (index: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (forecastState) {
            is ForecastState.Error -> {
                Text(text = forecastState.errorMessage, color = Color.Red)
            }

            ForecastState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }

            is ForecastState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = forecastState.forecast.city.cityName,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Lat: ${forecastState.forecast.city.coordinate.latitude}",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(
                            text = "Long: ${forecastState.forecast.city.coordinate.longitude}",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
                    ) {
                        itemsIndexed(forecastState.forecast.forecasts) { index, item ->
                            ForecastDayItem(item, modifier = Modifier.clickable {
                                onForecastDayClick(index)
                            })
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun ForecastDayItem(
    forecastDay: ForecastDay,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = forecastDay.date)
                Text(text = "Temperature: ${forecastDay.temperatures.day}")
                Text(text = forecastDay.weather[0].weatherName)
            }
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp),
                painter = painterResource(id = forecastDay.weather[0].getIconForWeather()),
                contentDescription = null
            )
        }
    }
}