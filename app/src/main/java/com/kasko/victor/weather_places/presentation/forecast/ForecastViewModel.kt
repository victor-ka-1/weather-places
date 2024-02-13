package com.kasko.victor.weather_places.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasko.victor.weather_places.domain.usecase.GetWeatherForecastForLocationUseCase
import com.kasko.victor.weather_places.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ForecastViewModel
@Inject constructor(
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase
): ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState = _forecastState.asStateFlow()

    var selectedDayIndex = 0
        private set

    fun getForecastForLocation(
        latitude: Float, longitude: Float
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = getWeatherForecastForLocationUseCase.getWeatherForecastForLocation(
                latitude = latitude.toDouble(),
                longitude = longitude.toDouble(),
            )) {
                is Resource.Error -> {
                    _forecastState.value = ForecastState.Error("error happened")
                }
                is Resource.Loading -> _forecastState.value = ForecastState.Loading
                is Resource.Success -> _forecastState.value =
                    if(result.data != null)
                    ForecastState.Success(result.data)
                else
                    ForecastState.Error("result == null")
            }

        }
    }

    fun setSelectedDayIndex(index: Int) {
        selectedDayIndex = index
    }

}