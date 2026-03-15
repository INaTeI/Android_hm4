package com.example.android_dolg1.ui.screens.detail

import androidx.compose.foundation.layout.*


import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.android_dolg1.ui.state.UiState
import com.example.android_dolg1.viewmodel.CountryViewModel


@Composable
fun CountryDetailScreen(
    code: String,
    viewModel: CountryViewModel = hiltViewModel()
) {

    val state = viewModel.detailState

    LaunchedEffect(code) {
        viewModel.loadCountry(code)
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

            val country = (state as UiState.Success).data

            Column(modifier = Modifier.padding(16.dp)) {

                AsyncImage(
                    model = country.flag,
                    contentDescription = null,
                    modifier = Modifier.height(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Name: ${country.name}")
                Text("Capital: ${country.capital}")
                Text("Region: ${country.region}")
                Text("Population: ${country.population}")
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
                    onClick = { viewModel.loadCountry(code) }
                ) {
                    Text("Retry")
                }
            }
        }
    }
}