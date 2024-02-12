package com.kasko.victor.weather_places.presentation.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kasko.victor.weather_places.R

@Composable
fun LocationsScreen(viewModel: LocationsViewModel = hiltViewModel(), onLocationClicked: () -> Unit ={}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .background(color = Color.Gray)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 24.sp,
                text = "My Locations"
                )
            LocationsScreenContent(
                viewModel = viewModel
            )
        }

}

@Composable
fun LocationsScreenContent(
    viewModel: LocationsViewModel
) {
    val searchFieldValue by viewModel.searchFieldState.collectAsState()
    val cities by viewModel.addedCitiesState.collectAsState()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        value = searchFieldValue,
        onValueChange = {
            viewModel.updateSearchFieldState(it)
                        },
        maxLines = 1,
        label = { Text(text = "Search the place") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        trailingIcon = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = Color.White,
                    contentDescription = "Search button"
                )
            }
        }
    )

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        item { Text(text = "test place", color = Color.White) }
        item { Text(text = "test place", color = Color.White) }
        item { Text(text = "test place", color = Color.White) }
        item { Text(text = "test place", color = Color.White) }
        item { Text(text = "test place", color = Color.White) }
        item { Text(text = "test place", color = Color.White) }
        items(cities){
            Text(text = it.cityName, color = Color.White, fontSize = 20.sp)
        }
    }
}