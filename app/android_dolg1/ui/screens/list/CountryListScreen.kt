package com.example.android_dolg1.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android_dolg1.ui.components.CountryCard
import com.example.android_dolg1.ui.state.UiState
import com.example.android_dolg1.viewmodel.CountryViewModel

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.unit.dp

@Composable
fun CountryListScreen(
    navController: NavController,
    viewModel: CountryViewModel = hiltViewModel()
) {

    val state = viewModel.countriesState

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    when (state) {

        is UiState.Loading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {

            val countries = (state as UiState.Success).data

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {

                items(countries) { country ->

                    CountryCard(country) {
                        navController.navigate("detail/${country.code}")
                    }

                }
            }
        }

        is UiState.Error -> {

            val message = (state as UiState.Error).message

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(message)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.loadCountries() }
                ) {
                    Text("Retry")
                }
            }
        }
    }
}