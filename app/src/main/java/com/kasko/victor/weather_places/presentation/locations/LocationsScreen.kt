package com.kasko.victor.weather_places.presentation.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kasko.victor.weather_places.domain.model.Coordinates

@Composable
fun LocationsScreen(viewModel: LocationsViewModel = hiltViewModel(),
                    onLocationClicked: (coordinates: Coordinates) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "My Locations"
        )
        LocationsScreenContent(
            viewModel = viewModel,
            onLocationClicked = onLocationClicked
        )
    }
}

@Composable
fun LocationsScreenContent(
    viewModel: LocationsViewModel,
    onLocationClicked: (coordinates: Coordinates) -> Unit
) {
    val searchFieldValue by viewModel.searchFieldState.collectAsState()
    val addedCitiesState by viewModel.addedCitiesState.collectAsState()
    val foundCity by viewModel.foundCityState.collectAsState()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        value = searchFieldValue,
        onValueChange = {
            viewModel.updateSearchFieldState(it)
        },
        maxLines = 1,
        label = { Text(text = "Enter city name and click search") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        trailingIcon = {
            IconButton(onClick = {
                viewModel.searchCityByName(searchFieldValue)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = Color.Black,
                    contentDescription = "Search button"
                )
            }
        }
    )

    SearchedCityComponent(viewModel, foundCity)

    SavedCitiesComponent(addedCitiesState, onSavedCityClick = onLocationClicked)
}

@Composable
fun SearchedCityComponent(viewModel: LocationsViewModel, foundCity: SearchedCityState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        when (foundCity) {
            is SearchedCityState.Error -> {
                Text(text = foundCity.errorMessage, color = Color.Red)
            }

            SearchedCityState.Idle -> {
                Text(text = "Search the city and add it to your locations", fontSize = 18.sp)
            }

            SearchedCityState.Loading -> {
                CircularProgressBar()
            }

            is SearchedCityState.Success -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = foundCity.city.cityName)
                    Spacer(modifier = Modifier.width(width = 16.dp))
                    Column {
                        Text(text = "latitude:${foundCity.city.coordinate.latitude}")
                        Text(text = "longitude:${foundCity.city.coordinate.longitude}")
                    }
                    IconButton(onClick = { viewModel.addSearchedCityToSaved() }) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add city to saved",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CircularProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colors.primaryVariant,
        strokeWidth = 4.dp
    )
}

@Composable
fun SavedCitiesComponent(
    savedCitiesState: CitiesState,
    onSavedCityClick: (coordinates: Coordinates) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        when (savedCitiesState) {
            is CitiesState.Error -> {
                Text(text = savedCitiesState.errorMessage, color = Color.Red, fontSize = 20.sp)
            }

            CitiesState.Loading -> {
                CircularProgressBar()
            }

            is CitiesState.Success -> {
                if (savedCitiesState.cities.isNotEmpty()) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        items(savedCitiesState.cities) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                                    .padding(4.dp)
                                    .clickable {
                                        onSavedCityClick(it.coordinate)
                                    },
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = it.cityName)
                                Spacer(modifier = Modifier.width(width = 16.dp))
                                Column {
                                    Text(text = "latitude:${it.coordinate.latitude}")
                                    Text(text = "longitude:${it.coordinate.longitude}")
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = "No cities added yet.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

//todo  add reviews and split screens into reusable components