package com.kasko.victor.weather_places.presentation.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasko.victor.weather_places.domain.model.City
import com.kasko.victor.weather_places.domain.model.Coordinates
import com.kasko.victor.weather_places.domain.repository.WeatherRepository
import com.kasko.victor.weather_places.domain.usecase.AddCityUseCase
import com.kasko.victor.weather_places.domain.usecase.GetCitiesFromDbUseCase
import com.kasko.victor.weather_places.domain.usecase.GetCityByNameUseCase
import com.kasko.victor.weather_places.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LocationsViewModel
@Inject constructor(
    private val addCity: AddCityUseCase,
    private val getCitiesFromDb: GetCitiesFromDbUseCase,
    private val getCityByNameUseCase: GetCityByNameUseCase
) : ViewModel() {

    private val _searchFieldState = MutableStateFlow("")
    val searchFieldState = _searchFieldState.asStateFlow()

    private val _addedCitiesState = MutableStateFlow<CitiesState>(CitiesState.Loading)
    val addedCitiesState = _addedCitiesState.asStateFlow()

    private val _foundCityState = MutableStateFlow<SearchedCityState>(SearchedCityState.Idle)
    val foundCityState = _foundCityState.asStateFlow()

    fun updateSearchFieldState(newState: String) {
        _searchFieldState.value = newState
    }

    init {
        loadSavedCities()
    }

    private fun loadSavedCities() {
        viewModelScope.launch {
            getCitiesFromDb.getSavedCities()
                .collect { result ->
                    _addedCitiesState.value = CitiesState.Success(result)
                }
        }
    }

    fun searchCityByName(cityName: String) {
        viewModelScope.launch {
            _foundCityState.value = when (val result = getCityByNameUseCase.getCityByName(cityName)) {
                is Resource.Error -> {
                    SearchedCityState.Error(errorMessage = "Failed to search this city")
                }

                is Resource.Loading -> SearchedCityState.Loading
                is Resource.Success -> result.data?.let { SearchedCityState.Success(it) }
                                       ?: SearchedCityState.Error(errorMessage = "Failed to search this city")
            }
        }
    }

    fun addSearchedCityToSaved() {
        viewModelScope.launch {
            val selectedCity = _foundCityState.value as? SearchedCityState.Success
            selectedCity?.let {
                addCity.addCity(it.city)
                _foundCityState.value = SearchedCityState.Idle
            }
        }
    }
}
