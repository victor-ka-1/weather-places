package com.kasko.victor.weather_places.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Coordinates
import com.kasko.victor.weather_places.domain.usecase.AddCityUseCase
import com.kasko.victor.weather_places.domain.usecase.GetCitiesFromDbUseCase
import com.kasko.victor.weather_places.domain.usecase.GetWeatherForecastForLocationUseCase
import com.kasko.victor.weather_places.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LocationsViewModel
@Inject constructor(
    private val getWeatherForecastForLocationUseCase: GetWeatherForecastForLocationUseCase,
    private val addCity: AddCityUseCase,
    private val getCitiesFromDb: GetCitiesFromDbUseCase
): ViewModel() {
    private val _searchFieldState = MutableStateFlow("")
    val searchFieldState = _searchFieldState.asStateFlow()

    private val _addedCitiesState = MutableStateFlow<List<City>>(listOf())
    val addedCitiesState = _addedCitiesState.asStateFlow()

    fun updateSearchFieldState(newState: String) {
        _searchFieldState.value = newState
    }

    init {
        viewModelScope.launch {
            addCity.addCity(City(
                country = "UK",
                timezone = 24,
                cityName = "TestCity",
                Coordinates(latitude = 43.21,44.59)
            ))
            addCity.addCity(City(
                country = "UK",
                timezone = 24,
                cityName = "TestCity",
                Coordinates(latitude = 43.21,44.59)
            ))
            addCity.addCity(City(
                country = "UK",
                timezone = 24,
                cityName = "TestCity",
                Coordinates(latitude = 43.21,44.59)
            ))
            getSavedCities()
        }



    }

    suspend fun getSavedCities() {
       // viewModelScope.launch {
            getCitiesFromDb.getSavedCities()
                .collect {result ->
                    when(result) {
                        is Resource.Success -> result.data?.let {
                            _addedCitiesState.value = it
                        }
                        is Resource.Loading -> {}
                        is Resource.Error -> {}
                    }
                }
      //  }
    }

}